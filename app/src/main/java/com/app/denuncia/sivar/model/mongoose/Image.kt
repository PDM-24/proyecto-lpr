package com.app.denuncia.sivar.model

import com.google.gson.annotations.SerializedName

data class image(
    @SerializedName(value = "public_id")
    var public_id:String = "",
    var url:String = ""
)
