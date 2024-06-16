package com.app.denuncia.sivar.remote.services

import com.app.denuncia.sivar.model.body.login
import com.app.denuncia.sivar.model.body.signup
import com.app.denuncia.sivar.model.mongoose.publicacion
import com.app.denuncia.sivar.remote.model.JsonResponse
import com.app.denuncia.sivar.remote.model.TokenJson
import com.app.denuncia.sivar.remote.model.UserSession
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Services {

    @POST("login")
    suspend fun login(@Body body: login): Response<TokenJson>

    @POST("signup")
    suspend fun signup(@Body body: signup): Response<JsonResponse>

    @GET("verifytoken/{TokenKey}")
    suspend fun verifyToken(@Path("TokenKey") token: String): Response<UserSession>

    @GET("getcomplaints")
    suspend fun getComplaints(): Response<List<publicacion>>

}