package com.app.denuncia.sivar.remote.services

import com.app.denuncia.sivar.model.body.login
import com.app.denuncia.sivar.model.body.signup
import com.app.denuncia.sivar.remote.model.JsonResponse
import com.app.denuncia.sivar.remote.model.TokenJson
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Services {

    @POST("login")
    suspend fun login(@Body body: login): Response<TokenJson>

    @POST("signup")
    suspend fun signup(@Body body: signup): Response<JsonResponse>
}