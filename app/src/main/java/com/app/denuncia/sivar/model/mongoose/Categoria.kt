package com.app.denuncia.sivar.model.mongoose

import com.google.gson.annotations.SerializedName

data class categoria(
    @SerializedName(value = "_id")
    var _id:String = "",

    @SerializedName(value = "name")
    var name:String = "",
)
