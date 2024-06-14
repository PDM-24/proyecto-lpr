package com.app.denuncia.sivar.model

data class PostData(
    val id: Int,
    val userImage: Int,
    val username: String,
    val category: String,
    val status: String,
    val location: String,
    val timer: String,
    val description: String,
    val image: Int,
    val supportCount: Int = 0,
    )