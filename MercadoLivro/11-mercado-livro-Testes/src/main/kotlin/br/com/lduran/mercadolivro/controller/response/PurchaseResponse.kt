package br.com.lduran.mercadolivro.controller.response

import java.math.BigDecimal
import java.time.LocalDateTime

data class PurchaseResponse(
    var id: Int? = null,
    var customer: CustomerResponse,
    val books: MutableList<BookResponse>,
    val nfe: String? = null,
    val price: BigDecimal,
    val createdAt: LocalDateTime
)