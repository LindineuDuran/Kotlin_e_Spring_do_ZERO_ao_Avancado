package br.com.lduran.mercadolivro.repository

import br.com.lduran.mercadolivro.model.CustomerModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface CustomerRepository: JpaRepository<CustomerModel, Int> {
    fun findByNameContaining(name: String): List<CustomerModel>
}