package br.com.lduran.mercadolivro.exception

class AccessDeniedException(override val message: String, val errorCode: String): Exception() {
}