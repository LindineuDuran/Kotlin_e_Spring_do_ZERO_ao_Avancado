package br.com.lduran.mercadolivro.repository

import br.com.lduran.mercadolivro.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.web.PageableDefault

interface CustomerRepository: JpaRepository<CustomerModel, Int> {
    fun findByNameContaining(@PageableDefault(page= 0, size= 10) pageable: Pageable, name: String): Page<CustomerModel>
    fun existsByEmail(email: String):Boolean
    fun findByEmail(email: String): CustomerModel?
}