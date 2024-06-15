package com.app.denuncia.sivar.remote.model.mongoose

import com.google.gson.annotations.SerializedName

data class Categoria(
    @SerializedName(value = "_id")
    var _id:String = "",

    @SerializedName(value = "name")
    var name:String = "",
)
