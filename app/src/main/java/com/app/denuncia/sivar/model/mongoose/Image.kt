package com.app.denuncia.sivar.model.mongoose

import com.google.gson.annotations.SerializedName

data class image(
    @SerializedName(value = "public_id")
    var public_id:String = "",
    var url:String = ""
)
