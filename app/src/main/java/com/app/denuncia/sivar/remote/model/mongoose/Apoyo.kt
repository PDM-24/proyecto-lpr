package com.app.denuncia.sivar.remote.model.mongoose

import com.google.gson.annotations.SerializedName

data class Apoyo(
    @SerializedName(value = "usuario")
    var usuario:String = "",

    @SerializedName(value = "codigo")
    var codigo:String = "",
)
