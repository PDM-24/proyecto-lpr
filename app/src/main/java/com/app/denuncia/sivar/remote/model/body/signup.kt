package com.app.denuncia.sivar.model.body

import com.google.gson.annotations.SerializedName

data class signup(
    @SerializedName(value = "username")
    var username:String = "",

    @SerializedName(value = "name")
    var name:String = "",

    @SerializedName(value = "surname")
    var surname:String = "",

    @SerializedName(value = "email")
    var email:String = "",

    @SerializedName(value = "birthdate")
    var birthdate:String = "",

    @SerializedName(value = "pass")
    var pass:String = "",

    @SerializedName(value = "rol")
    var rol:String = "",
)
