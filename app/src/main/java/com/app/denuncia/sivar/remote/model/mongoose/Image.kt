package com.app.denuncia.sivar.remote.model.mongoose

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName(value = "public_id")
    var public_id:String = "",
    var url:String = ""
)
