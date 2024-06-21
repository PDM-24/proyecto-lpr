package com.app.denuncia.sivar.model

data class Estado(
    val _id: Int,
    val nombre: String
)

val EstadoList = listOf(
    Estado(1, "En revisión"),
    Estado(2, "En proceso"),
    Estado(3, "Resuelta"),
    Estado(4, "Descartada"),

)