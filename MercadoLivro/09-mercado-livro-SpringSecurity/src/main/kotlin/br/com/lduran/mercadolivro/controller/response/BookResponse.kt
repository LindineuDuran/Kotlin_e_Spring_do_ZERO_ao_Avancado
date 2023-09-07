package br.com.lduran.mercadolivro.controller.response

import br.com.lduran.mercadolivro.enum.BookStatus
import java.math.BigDecimal

data class BookResponse(
    var id: Int? = null,
    var name: String,
    var price: BigDecimal,
    var customer: CustomerResponse,//CustomerModel? = null,
    var status: BookStatus? = null
)