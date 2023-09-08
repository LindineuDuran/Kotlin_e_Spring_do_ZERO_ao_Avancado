package br.com.lduran.mercadolivro.config

import br.com.lduran.mercadolivro.enum.Role
import br.com.lduran.mercadolivro.repository.CustomerRepository
import br.com.lduran.mercadolivro.security.AuthenticationFilter
import br.com.lduran.mercadolivro.security.AuthorizationFilter
import br.com.lduran.mercadolivro.security.CustomAuthenticationEntryPoint
import br.com.lduran.mercadolivro.security.JwtUtil
import br.com.lduran.mercadolivro.service.UserDetailsCustomService
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.security.web.SecurityFilterChain as SecurityFilterChain1


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
class SecurityConfig(private val configuration: AuthenticationConfiguration,
                     private val customerRepository: CustomerRepository,
                     private val userDetails: UserDetailsCustomService,
                     private val jwtUtil: JwtUtil,
                     private val customEntryPoint: CustomAuthenticationEntryPoint
) {
    private val ADMIN_MATCHERS = arrayOf("/admin/**")
    private val PUBLIC_MATCHERS = arrayOf<String>()
    private val PUBLIC_POST_MATCHERS = arrayOf("/customers", "/customers/list")
    private val PERMIT_ALL = arrayOf("/swagger-ui/**", "/v3/api-docs/**")

    fun config(auth: AuthenticationManagerBuilder){
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain1 {
        http.cors().and().csrf().disable()

        http.authorizeRequests()
            .antMatchers(*ADMIN_MATCHERS).hasAuthority(Role.ADMIN.description)
            .antMatchers(*PERMIT_ALL).permitAll()
            .antMatchers(*PUBLIC_MATCHERS).permitAll()
            .antMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll()
            //.antMatchers(HttpMethod.GET, "/books/**").permitAll()
            .anyRequest().authenticated()

        http.addFilter(AuthenticationFilter(authenticationManager = configuration.authenticationManager, customerRepository, jwtUtil))
        http.addFilter(AuthorizationFilter(authenticationManager = configuration.authenticationManager, userDetails, jwtUtil))

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.exceptionHandling().authenticationEntryPoint(customEntryPoint)

        return http.build()
    }

    @Bean
    fun corsConfig(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOriginPattern("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring()
                .antMatchers(*PERMIT_ALL)
                .antMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS)
        }
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }
}