package br.com.lduran.mercadolivro.controller.response

import br.com.lduran.mercadolivro.enum.BookStatus
import br.com.lduran.mercadolivro.model.CustomerModel
import java.math.BigDecimal

data class BookResponse(
    var id: Int? = null,
    var name: String,
    var price: BigDecimal,
    var customer: CustomerModel? = null,
    var status: BookStatus? = null
)