package br.com.lduran.mercadolivro.controller.response

data class FieldErrorResponse(
    var message: String,
    var field: String
)