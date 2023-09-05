package br.com.lduran.mercadolivro.controller.response

import br.com.lduran.mercadolivro.enum.CustomerStatus

class CustomerResponse(var id: Int? = null,
                       var name: String,
                       var email: String,
                       var status: CustomerStatus
)