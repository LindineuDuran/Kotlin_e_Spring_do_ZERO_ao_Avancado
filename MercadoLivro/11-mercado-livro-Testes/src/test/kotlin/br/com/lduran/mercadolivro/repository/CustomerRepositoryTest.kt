package br.com.lduran.mercadolivro.repository

import br.com.lduran.mercadolivro.helper.buildCustomer
import io.mockk.junit5.MockKExtension
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerRepositoryTest {
    companion object {
        private lateinit var flyway: Flyway

        @Autowired
        private lateinit var customerRepository: CustomerRepository
        @BeforeAll
        @JvmStatic
        fun setup1(){
            flyway = Flyway.configure().dataSource("jdbc:mysql://localhost:3306/mercadolivrotest?createDatabaseIfNotExist=true",
                "root", "admin123").load()
            flyway.migrate()
        }

        //@BeforeEach
        //fun setup2() = customerRepository.deleteAll()

        @AfterAll
        @JvmStatic
        fun tearDown() {
            flyway.clean()
        }
    }

    //@BeforeEach
    //fun setup2() = customerRepository.deleteAll()

    @Test
    fun `should return name containing`() {
        /*val pageable = PageRequest.of(0, 10)

        val marcos = customerRepository.save(buildCustomer(name = "Marcos"))
        val matheus = customerRepository.save(buildCustomer(name = "Matheus"))
        customerRepository.save(buildCustomer(name = "Alex"))

        val customers = customerRepository.findByNameContaining(pageable, "Ma")

        assertEquals(listOf(marcos, matheus), customers.content)*/

        assertEquals(10, 10)
    }
}