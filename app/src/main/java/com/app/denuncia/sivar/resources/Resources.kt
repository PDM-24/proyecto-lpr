package com.app.denuncia.sivar.resources

sealed class Resources<T> {
    data class Success<T>(val data: T) : Resources<T>()
    data class Error<T>(val message: String) : Resources<T>()
}

data class ErrorResponse (
    val state:Boolean = false,
    val message: String = ""
)