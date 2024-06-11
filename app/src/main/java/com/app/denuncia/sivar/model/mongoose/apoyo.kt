package com.app.denuncia.sivar.model.mongoose

import com.google.gson.annotations.SerializedName

data class apoyo(
    @SerializedName(value = "usuario")
    var usuario:String = "",

    @SerializedName(value = "codigo")
    var codigo:String = "",
)
