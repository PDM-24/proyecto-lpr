package com.app.denuncia.sivar.remote.model

data class Departamentos(
    val id: Int,
    val nombre: String
)

val DepartamentList = listOf(
    Departamentos(1, "Ahuachapán"),
    Departamentos(2, "Cabañas"),
    Departamentos(3, "Chalatenango"),
    Departamentos(4, "Cuscatlán"),
    Departamentos(5, "La Libertad"),
    Departamentos(6, "La Paz"),
    Departamentos(7, "La Unión"),
    Departamentos(8, "Morazán"),
    Departamentos(9, "San Miguel"),
    Departamentos(10, "San Salvador"),
    Departamentos(11, "San Vicente"),
    Departamentos(12, "Santa Ana"),
    Departamentos(13, "Sonsonate"),
    Departamentos(14, "Usulután")
)