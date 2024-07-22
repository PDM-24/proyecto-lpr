package com.app.denuncia.sivar.remote.services

import com.app.denuncia.sivar.model.body.complaint
import com.app.denuncia.sivar.model.body.login
import com.app.denuncia.sivar.model.body.photo
import com.app.denuncia.sivar.model.body.userBody
import com.app.denuncia.sivar.model.mongoose.Usuario
import com.app.denuncia.sivar.model.mongoose.publicacion
import com.app.denuncia.sivar.remote.model.JsonCodeResponse
import com.app.denuncia.sivar.remote.model.JsonResponse
import com.app.denuncia.sivar.remote.model.TokenJson
import com.app.denuncia.sivar.remote.model.UserSession
import com.app.denuncia.sivar.remote.model.mongoose.Apoyo
import com.app.denuncia.sivar.remote.model.mongoose.Categoria
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {

    //Post
    @POST("login")
    suspend fun login(@Body body: login): Response<TokenJson>

    @POST("singup")
    suspend fun singup(@Body body: userBody): Response<JsonResponse>

    @POST("uploadcomplaint")
    suspend fun uploadComplaint(@Body body: complaint): Response<JsonResponse>

    //Get
    @GET("verifytoken/{TokenKey}")
    suspend fun verifyToken(@Path("TokenKey") token: String): Response<UserSession>

    @GET("getcategories")
    suspend fun getCategoriesList(): Response<List<Categoria>>

    @GET("getcomplaints")
    suspend fun getComplaints(@Query("Search") search: String, @Query("Departamento") departamento: String, @Query("Categorie") categorie: String): Response<List<publicacion>>

    @GET("getusers")
    suspend fun getUsers(@Query("Search") search: String): Response<List<Usuario>>

    @GET("getemailcode/{Id}")
    suspend fun getEmailCode(@Path("Id") id: String): Response<JsonCodeResponse>

    //Delete
    @DELETE("deleteuser/{Id}")
    suspend fun deleteUser(@Path("Id") id: String): Response<JsonResponse>

    @DELETE("deletecomplaint/{Id}")
    suspend fun deleteComplaint(@Path("Id") id: String): Response<JsonResponse>

    //Patch
    @PATCH("changerol/{Id}/{Rol}")
    suspend fun changeRol(@Path("Id") id: String, @Path("Rol") rol: String): Response<JsonResponse>

    @PATCH("updatephoto/{Id}")
    suspend fun updatePhoto(@Path("Id") id: String, @Body body: photo): Response<JsonResponse>

    @PATCH("supportcomplaint/{Id}")
    suspend fun supportComplaint(@Path("Id") id: String, @Body body: Apoyo): Response<JsonResponse>

    @PATCH("updatecomplaint/{Id}")
    suspend fun updateComplaint(@Path("Id") id: String, @Query("state") state: String): Response<JsonResponse>

    @PUT("updateprofile/{Id}")
    suspend fun updateProfile(@Path("Id") id: String, @Body body: userBody): Response<JsonResponse>
}