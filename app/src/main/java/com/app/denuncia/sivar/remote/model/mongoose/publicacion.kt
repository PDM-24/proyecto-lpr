package com.app.denuncia.sivar.model.mongoose

import com.app.denuncia.sivar.remote.model.mongoose.Apoyo
import com.app.denuncia.sivar.remote.model.mongoose.Image
import com.google.gson.annotations.SerializedName

data class publicacion(
    @SerializedName(value = "_id")
    var _id : String = "",

    @SerializedName(value = "categoria")
    var categoria : categoria = categoria("",""),

    @SerializedName(value = "usuario")
    var usuario : Usuario = Usuario("", Image("",""),"","","","","","","", false),

    @SerializedName(value = "image")
    var image : Image = Image("",""),

    @SerializedName(value = "details")
    var details : String = "",

    @SerializedName(value = "date")
    var date : String = "",

    @SerializedName(value = "state")
    var state : String = "",

    @SerializedName(value = "apoyo")
    var apoyo : List<Apoyo> = emptyList()
)
