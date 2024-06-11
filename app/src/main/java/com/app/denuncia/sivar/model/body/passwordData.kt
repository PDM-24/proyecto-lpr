package com.app.denuncia.sivar.model.body

import com.google.gson.annotations.SerializedName

data class passwordData(
    @SerializedName(value = "password")
    var password:String = ""
)
