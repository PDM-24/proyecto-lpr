package com.app.denuncia.sivar.model.body

import com.google.gson.annotations.SerializedName

data class uploadPublicacion(
    @SerializedName(value = "user")
    var user: String = "",

    @SerializedName(value = "category")
    var category:String = "",

    @SerializedName(value = "departamento")
    var departamento:String = "",

    @SerializedName(value = "details")
    var details:String = "",

    @SerializedName(value = "date")
    var date:String = "",

    @SerializedName(value = "img")
    var img:String = "",
)