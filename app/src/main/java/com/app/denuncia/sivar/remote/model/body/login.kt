package com.app.denuncia.sivar.model.body

import com.google.gson.annotations.SerializedName

data class login(
    @SerializedName(value = "username")
    var username:String = "",

    @SerializedName(value = "password")
    var password:String = ""
)
