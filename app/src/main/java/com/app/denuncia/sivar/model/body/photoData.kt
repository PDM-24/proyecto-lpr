package com.app.denuncia.sivar.model.body

import com.google.gson.annotations.SerializedName

data class photoData(
    @SerializedName(value = "photo")
    var photo:String = ""
)
