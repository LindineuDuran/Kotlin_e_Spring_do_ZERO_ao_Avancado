package br.com.lduran.mercadolivro.exception

class NotFoundException(override val message: String, val errorCode: String) : Exception() {
}