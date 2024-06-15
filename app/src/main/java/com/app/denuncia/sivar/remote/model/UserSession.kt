package com.app.denuncia.sivar.remote.model

import com.app.denuncia.sivar.model.mongoose.Usuario

data class UserSession(
    var state:Boolean = false,
    var usuario:Usuario? = null
)
