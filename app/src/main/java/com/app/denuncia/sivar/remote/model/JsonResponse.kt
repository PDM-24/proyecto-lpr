package com.app.denuncia.sivar.remote.model

data class JsonResponse(
    var state:Boolean = false,
    var details:String = "",
)

data class  JsonCodeResponse(
    var state:Boolean = false,
    var details:String = "",
    var code:String = "",

)