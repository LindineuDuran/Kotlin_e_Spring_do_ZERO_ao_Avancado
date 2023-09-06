package br.com.lduran.mercadolivro.exception

class BadRequestException(override val message: String, val errorCode: String) : Exception() {}
