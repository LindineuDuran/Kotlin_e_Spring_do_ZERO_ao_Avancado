package br.com.lduran.mercadolivro.repository

import br.com.lduran.mercadolivro.model.CustomerModel
import br.com.lduran.mercadolivro.model.PurchaseModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PurchaseRepository: JpaRepository<PurchaseModel, Int> {
    fun findByCustomer(pageable: Pageable, customer: CustomerModel): Page<PurchaseModel>
}
