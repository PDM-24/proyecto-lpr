package com.app.denuncia.sivar.remote.repository

import com.app.denuncia.sivar.model.body.complaint
import com.app.denuncia.sivar.model.body.login
import com.app.denuncia.sivar.model.body.photo
import com.app.denuncia.sivar.model.body.userBody
import com.app.denuncia.sivar.model.mongoose.Usuario
import com.app.denuncia.sivar.model.mongoose.publicacion
import com.app.denuncia.sivar.remote.model.JsonResponse
import com.app.denuncia.sivar.remote.model.TokenJson
import com.app.denuncia.sivar.remote.model.UserSession
import com.app.denuncia.sivar.remote.model.mongoose.Categoria
import com.app.denuncia.sivar.resources.Resources

interface Repository {
    suspend fun login(body:login): Resources<TokenJson>
    suspend fun singUp(body: userBody): Resources<JsonResponse>
    suspend fun verifyToken(token:String): Resources<UserSession>
    suspend fun getComplaints(search: String, departamento: String, categorie: String) : Resources<List<publicacion>>
    suspend fun uploadComplaint(body: complaint): Resources<JsonResponse>
    suspend fun getCategoriesList(): Resources<List<Categoria>>
    suspend fun getUsers(search:String): Resources<List<Usuario>>
    suspend fun changeRol(id:String, rol:String): Resources<JsonResponse>
    suspend fun deleteUser(id:String): Resources<JsonResponse>
    suspend fun updatePhoto(id:String, body:photo): Resources<TokenJson>
    suspend fun updateProfile(id:String, body:userBody): Resources<TokenJson>

}