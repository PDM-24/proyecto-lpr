package com.app.denuncia.sivar.model

data class Denuncia(
    val _id: String,
    val categoria: Categoria,
    val usuario: Usuario,
    val departamento: String,
    val image: Image,
    val details: String,
    val date: String,
    val state: String
)