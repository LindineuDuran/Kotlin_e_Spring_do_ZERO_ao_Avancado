package br.com.lduran.mercadolivro.security

import br.com.lduran.mercadolivro.enum.Errors
import br.com.lduran.mercadolivro.exception.AuthenticationException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {

    @Value("\${spring.jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${spring.jwt.secret}")
    private val secret: String? = null

    fun generateToken(id: Int): String {
        return Jwts.builder()
            .setSubject(id.toString())
            .setExpiration(Date(System.currentTimeMillis() + expiration!!))
            .signWith(SignatureAlgorithm.HS512, secret!!.toByteArray())
            .compact()
    }

    fun isValidToken(token: String): Boolean {
        val claims = getClaims(token)
        if(claims.subject == null || claims.expiration == null || Date().after(claims.expiration)) {
            return false
        }
        return true
    }

    private fun getClaims(token: String): Claims {
        try {
            return Jwts.parser().setSigningKey(secret!!.toByteArray()).parseClaimsJws(token).body
        } catch (ex : Exception) {
            throw AuthenticationException(Errors.ML000.message, Errors.ML000.code)
        }
    }

    fun getSubject(token: String): String {
        return getClaims(token).subject
    }

}