package com.app.denuncia.sivar.model.body

import com.google.gson.annotations.SerializedName

data class newpass(
    @SerializedName(value = "password")
    var password:String = ""
)
