package com.app.denuncia.sivar.remote.model

import com.google.gson.annotations.SerializedName

data class TokenJson(
    @SerializedName(value = "state")
    var state:Boolean = false,

    @SerializedName(value = "token")
    var token:String = ""
)