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
                    val exception = gson.fromJson(errorBody, ErrorResponse::class.java)
                    return Resources.Error(exception.details)
                }catch(e:Exception){
                    return Resources.Error(errorBody)
                }
            }
        }catch (e:Exception){
            return Resources.Error(e.message.toString())
        }
    }

    override suspend fun singUp(data: userBody): Resources<JsonResponse> {
        try {
            val response = service.singup(data)
            if (response.isSuccessful) {
                return Resources.Success(response.body()!!)
            } else {
                try {
                    val errorBody = response.errorBody()?.string()!!
                    try {
                        val exception = gson.fromJson(errorBody, ErrorResponse::class.java)
                        return Resources.Error(exception.details)
                    }catch(e:Exception){
                        return Resources.Error(errorBody)
                    }
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
                val errorBody = response.errorBody()?.string()!!
                try {
                    val exception = gson.fromJson(errorBody, ErrorResponse::class.java)
                    return Resources.Error(exception.details)
                }catch(e:Exception){
                    return Resources.Error(errorBody)
                }
            }
        }catch (e:Exception){
            return Resources.Error(e.message.toString())
        }
    }

    override suspend fun getCategoriesList(): Resources<List<Categoria>> {
        try {
            val response = service.getCategoriesList()
            if(response.isSuccessful){
                return Resources.Success(response.body()!!)
            }else{
                val errorBody = response.errorBody()?.string()!!
                try {
                    val exception = gson.fromJson(errorBody, ErrorResponse::class.java)
                    return Resources.Error(exception.details)
                }catch(e:Exception){
                    return Resources.Error(errorBody)
                }
            }
        }catch (e:Exception){
            return Resources.Error(e.message.toString())
        }
    }

    override suspend fun getUsers(search: String): Resources<List<Usuario>> {
        try {
            val response = service.getUsers(search)
            if(response.isSuccessful){
                return Resources.Success(response.body()!!)
            }else{
                val errorBody = response.errorBody()?.string()!!
                try {
                    val exception = gson.fromJson(errorBody, ErrorResponse::class.java)
                    return Resources.Error(exception.details)
                }catch(e:Exception){
                    return Resources.Error(errorBody)
                }
            }
        }catch (e:Exception){
            return Resources.Error(e.message.toString())
        }
    }

    override suspend fun changeRol(id: String, rol: String): Resources<JsonResponse> {
        try {
            val response = service.changeRol(id, rol)
            if(response.isSuccessful){
                return Resources.Success(response.body()!!)
            }else{
                val errorBody = response.errorBody()?.string()!!
                try {
                    val exception = gson.fromJson(errorBody, ErrorResponse::class.java)
                    return Resources.Error(exception.details)
                }catch(e:Exception){
                    return Resources.Error(errorBody)
                }
            }
        }catch (e:Exception){
            return Resources.Error(e.message.toString())
        }
    }

    override suspend fun deleteUser(id: String): Resources<JsonResponse> {
        try {
            val response = service.deleteUser(id)
            if(response.isSuccessful){
                return Resources.Success(response.body()!!)
            }else{
                val errorBody = response.errorBody()?.string()!!
                try {
                    val exception = gson.fromJson(errorBody, ErrorResponse::class.java)
                    return Resources.Error(exception.details)
                }catch(e:Exception){
                    return Resources.Error(errorBody)
                }
            }
        }catch (e:Exception){
            return Resources.Error(e.message.toString())
        }
    }

    override suspend fun updatePhoto(id: String, body: photo): Resources<TokenJson> {
        try {
            val response = service.updatePhoto(id, body)
            if(response.isSuccessful){
                return Resources.Success(response.body()!!)
            }else{
                val errorBody = response.errorBody()?.string()!!
                try {
                    val exception = gson.fromJson(errorBody, ErrorResponse::class.java)
                    return Resources.Error(exception.details)
                }catch(e:Exception){
                    return Resources.Error(errorBody)
                }
            }
        }catch (e:Exception){
            return Resources.Error(e.message.toString())
        }
    }

    override suspend fun updateProfile(id: String, body: userBody): Resources<TokenJson> {
        try {
            val response = service.updateProfile(id, body)
            if(response.isSuccessful){
                return Resources.Success(response.body()!!)
            }else{
                val errorBody = response.errorBody()?.string()!!
                try {
                    val exception = gson.fromJson(errorBody, ErrorResponse::class.java)
                    return Resources.Error(exception.details)
                }catch(e:Exception){
                    return Resources.Error(errorBody)
                }
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
                val errorBody = response.errorBody()?.string()!!
                try {
                    val exception = gson.fromJson(errorBody, ErrorResponse::class.java)
                    return Resources.Error(exception.details)
                }catch(e:Exception){
                    return Resources.Error(errorBody)
                }
            }
        }catch (e:Exception){
            return Resources.Error(e.message.toString())
        }
    }

    override suspend fun uploadComplaint(body: complaint): Resources<JsonResponse> {
        try {
            val response = service.uploadComplaint(body)
            if(response.isSuccessful){
                return Resources.Success(response.body()!!)
            }else{
                val errorBody = response.errorBody()?.string()!!
                try {
                    val exception = gson.fromJson(errorBody, ErrorResponse::class.java)
                    return Resources.Error(exception.details)
                }catch(e:Exception){
                    return Resources.Error(errorBody)
                }
            }
        }catch (e:Exception){
            return Resources.Error(e.message.toString())
        }
    }

}
