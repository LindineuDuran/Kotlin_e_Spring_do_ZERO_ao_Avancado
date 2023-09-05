package br.com.lduran.mercadolivro.controller.request

import br.com.lduran.mercadolivro.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PostCustomerRequest(
    @field:NotEmpty(message= "Nome deve ser informado!")
    var name: String,

    @field:Email(message= "E-mail deve ser válido!")
    @EmailAvailable(message = "Email em uso")
    var email: String)