package br.com.lduran.mercadolivro.exception

class MethodArgumentNotValidException(override val message: String, val errorCode: String): Exception() {
}