package com.app.denuncia.sivar.remote.repository

import com.app.denuncia.sivar.model.body.login
import com.app.denuncia.sivar.model.body.signup
import com.app.denuncia.sivar.model.mongoose.publicacion
import com.app.denuncia.sivar.remote.model.JsonResponse
import com.app.denuncia.sivar.remote.model.TokenJson
import com.app.denuncia.sivar.remote.model.UserSession
import com.app.denuncia.sivar.resources.Resources

interface Repository {

    suspend fun login(body:login): Resources<TokenJson>
    suspend fun signUp(data:signup): Resources<JsonResponse>
    suspend fun verifyToken(token:String): Resources<UserSession>
    suspend fun getComplaints() : Resources<List<publicacion>>
}