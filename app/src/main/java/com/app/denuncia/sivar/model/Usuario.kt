package com.app.denuncia.sivar.model

data class Usuario(
    val _id: String,
    val image: Image,
    val username: String,
    val name: String,
    val surname: String,
    val email: String,
    val birth: String,
    val password: String,
    val rol: String,
    val state: Boolean
)

data class Image(
    val public_id: String,
    val url: String
)