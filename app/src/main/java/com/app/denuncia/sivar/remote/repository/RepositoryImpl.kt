package com.app.denuncia.sivar.remote.repository

import android.util.Log
import com.app.denuncia.sivar.model.body.login
import com.app.denuncia.sivar.model.body.singup
import com.app.denuncia.sivar.remote.model.JsonResponse
import com.app.denuncia.sivar.remote.model.TokenJson
import com.app.denuncia.sivar.remote.model.UserSession
import com.app.denuncia.sivar.remote.services.Services
import com.app.denuncia.sivar.resources.ErrorResponse
import com.app.denuncia.sivar.resources.Resources
import com.google.gson.Gson

class RepositoryImpl(private val service:Services, private val gson: Gson): Repository {

    override suspend fun login(body: login): Resources<TokenJson> {
        try {
            val response = service.login(body)
            if(response.isSuccessful){
                return Resources.Success(response.body()!!)
            }else{
                val errorBody = response.errorBody()?.string()!!
                try {
                    val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                    return Resources.Error(errorResponse.details)
                }catch (e:Exception){
                     return Resources.Error(e.message.toString())
                }
            }
        }catch (e:Exception){
            return Resources.Error(e.message.toString())
        }
    }

    override suspend fun singUp(data: singup): Resources<JsonResponse> {
        try {
            val response = service.singup(data)
            if (response.isSuccessful) {
                return Resources.Success(response.body()!!)
            } else {
                val errorBody = response.errorBody()?.string()!!
                try {
                    val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                    return Resources.Error(errorResponse.message)
                } catch (e: Exception) {
                    return Resources.Error(e.message.toString())
                }
            }
        } catch (e: Exception) {
            return Resources.Error(e.message.toString())
        }
    }

    override suspend fun verifyToken(token: String): Resources<UserSession> {
        try {
            val response = service.verifyToken(token)
            if(response.isSuccessful){
                return Resources.Success(response.body()!!)
            }else{
                val error = response.errorBody()?.string()!!
                val exception = gson.fromJson(error, ErrorResponse::class.java)
                return Resources.Error(exception.details)
            }
        }catch (e:Exception){
            return Resources.Error(e.message.toString())
        }
    }

    override suspend fun getComplaints(): Resources<List<publicacion>> {
        try {
            val response = service.getComplaints()
            if(response.isSuccessful){
                return Resources.Success(response.body()!!)
            }else{
                val error = response.errorBody()?.string()!!
                val exception = gson.fromJson(error, ErrorResponse::class.java)
                return Resources.Error(exception.details)
            }
        }catch (e:Exception){
            return Resources.Error(e.message.toString())
        }
    }

}
