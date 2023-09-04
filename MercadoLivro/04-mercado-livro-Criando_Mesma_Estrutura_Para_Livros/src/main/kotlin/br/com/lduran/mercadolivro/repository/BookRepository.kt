package br.com.lduran.mercadolivro.repository

import br.com.lduran.mercadolivro.enum.BookStatus
import br.com.lduran.mercadolivro.model.BookModel
import br.com.lduran.mercadolivro.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<BookModel, Int> {
    fun findByStatus(pageable: Pageable, status: BookStatus): Page<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>
    fun findByNameContaining(pageable: Pageable, it: String): Page<BookModel>
}