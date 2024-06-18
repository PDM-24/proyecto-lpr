package com.app.denuncia.sivar.remote.repository

import com.app.denuncia.sivar.model.body.complaint
import com.app.denuncia.sivar.model.body.login
import com.app.denuncia.sivar.model.body.singup
import com.app.denuncia.sivar.model.mongoose.publicacion
import com.app.denuncia.sivar.remote.model.JsonResponse
import com.app.denuncia.sivar.remote.model.TokenJson
import com.app.denuncia.sivar.remote.model.UserSession
import com.app.denuncia.sivar.remote.model.mongoose.Categoria
import com.app.denuncia.sivar.resources.Resources

interface Repository {
    suspend fun login(body:login): Resources<TokenJson>
    suspend fun singUp(body: singup): Resources<JsonResponse>
    suspend fun verifyToken(token:String): Resources<UserSession>
    suspend fun getComplaints() : Resources<List<publicacion>>
    suspend fun uploadComplaint(body: complaint): Resources<JsonResponse>
    suspend fun getCategoriesList(): Resources<List<Categoria>>
}