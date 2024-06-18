package com.app.denuncia.sivar.remote.services

import com.app.denuncia.sivar.model.body.complaint
import com.app.denuncia.sivar.model.body.login
import com.app.denuncia.sivar.model.body.singup
import com.app.denuncia.sivar.model.mongoose.publicacion
import com.app.denuncia.sivar.remote.model.JsonResponse
import com.app.denuncia.sivar.remote.model.TokenJson
import com.app.denuncia.sivar.remote.model.UserSession
import com.app.denuncia.sivar.remote.model.mongoose.Categoria
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Services {

    //Post
    @POST("login")
    suspend fun login(@Body body: login): Response<TokenJson>

    @POST("singup")
    suspend fun singup(@Body body: singup): Response<JsonResponse>

    @POST("uploadcomplaint")
    suspend fun uploadComplaint(@Body body: complaint): Response<JsonResponse>

    //Get
    @GET("verifytoken/{TokenKey}")
    suspend fun verifyToken(@Path("TokenKey") token: String): Response<UserSession>

    @GET("getcategories")
    suspend fun getCategoriesList(): Response<List<Categoria>>

    @GET("getcomplaints")
    suspend fun getComplaints(): Response<List<publicacion>>

}