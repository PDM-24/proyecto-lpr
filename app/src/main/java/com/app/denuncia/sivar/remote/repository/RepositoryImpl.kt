package com.app.denuncia.sivar.remote.repository

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

    override suspend fun updatePhoto(id: String, body: photo): Resources<JsonResponse> {
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

    override suspend fun updateProfile(id: String, body: userBody): Resources<JsonResponse> {
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

    override suspend fun getEmailCode(id: String): Resources<JsonCodeResponse> {
        try {
            val response = service.getEmailCode(id)
            if(response.isSuccessful){
                return Resources.Success(response.body()!!)
            }else{
                val errorBody = response.errorBody()?.string()!!
                try {
                    val exception = gson.fromJson(errorBody, ErrorResponse::class.java)
                    return Resources.Error(exception.details)
                }catch (e:Exception){
                    return Resources.Error(errorBody)
                }
            }
        }catch (e:Exception){
            return Resources.Error(e.message.toString())
        }
    }

    override suspend fun supportComplaint(id: String, body: Apoyo): Resources<JsonResponse> {
        try {
            val response = service.supportComplaint(id, body)
            if(response.isSuccessful){
                return Resources.Success(response.body()!!)
            }else{
                val errorBody = response.errorBody()?.string()!!
                try {
                    val exception = gson.fromJson(errorBody, ErrorResponse::class.java)
                    return Resources.Error(exception.details)
                }catch (e:Exception){
                    return Resources.Error(errorBody)
                }
            }
        }catch (e:Exception){
            return Resources.Error(e.message.toString())
        }
    }

    override suspend fun updateComplaint(id: String, state: String): Resources<JsonResponse> {
        try {
            val response = service.updateComplaint(id, state)
            if(response.isSuccessful){
                return Resources.Success(response.body()!!)
            }else{
                val errorBody = response.errorBody()?.string()!!
                try {
                    val exception = gson.fromJson(errorBody, ErrorResponse::class.java)
                    return Resources.Error(exception.details)
                }catch (e:Exception){
                    return Resources.Error(errorBody)
                }
            }
        }catch (e:Exception){
            return  Resources.Error(e.message.toString())
        }
    }

    override suspend fun deleteComplaint(id: String): Resources<JsonResponse> {
        try {
            val response = service.deleteComplaint(id)
            if(response.isSuccessful){
                return Resources.Success(response.body()!!)
            }else{
                val errorBody = response.errorBody()?.string()!!
                try {
                    val exception = gson.fromJson(errorBody, ErrorResponse::class.java)
                    return Resources.Error(exception.details)
                }catch (e:Exception){
                    return Resources.Error(errorBody)
                }
            }
        }catch (e:Exception){
            return  Resources.Error(e.message.toString())
        }
    }

    override suspend fun getComplaints(search: String,departamento: String,categorie: String): Resources<List<publicacion>> {
        try {
            val response = service.getComplaints(search, departamento, categorie)
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
