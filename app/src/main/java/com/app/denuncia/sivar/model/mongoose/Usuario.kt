package com.app.denuncia.sivar.model.mongoose

import com.app.denuncia.sivar.model.mongoose.image
import com.google.gson.annotations.SerializedName

data class usuario(
    @SerializedName(value = "_id")
    var _id:String = "",

    @SerializedName(value = "image")
    var image: image = image("", ""),

    @SerializedName(value = "username")
    var username:String = "",

    @SerializedName(value = "name")
    var name:String = "",

    @SerializedName(value = "surname")
    var surname:String = "",

    @SerializedName(value = "email")
    var email:String = "",

    @SerializedName(value = "birth")
    var birth:String = "",

    @SerializedName(value = "password")
    var password:String = "",

    @SerializedName(value = "rol")
    var rol:String = "",

    @SerializedName(value = "state")
    var state:Boolean = false,

    )
