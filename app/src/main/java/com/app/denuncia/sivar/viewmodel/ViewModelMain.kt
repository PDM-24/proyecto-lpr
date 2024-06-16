package com.app.denuncia.sivar.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.denuncia.sivar.model.body.login
import com.app.denuncia.sivar.model.body.signup
import com.app.denuncia.sivar.model.mongoose.Usuario
import com.app.denuncia.sivar.remote.ApiProvider
import com.app.denuncia.sivar.resources.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelMain: ViewModel() {

    private val apiRest = ApiProvider.repository

    //Sesion del usuario
    private val token = mutableStateOf("")
    val session = mutableStateOf(false)
    val profile = mutableStateOf(Usuario())

    //Mensaje de error o exito de la operacion
    val details = mutableStateOf("")
    val stateApp = mutableStateOf(false)

    fun verifyToken(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                when (val response = apiRest.verifyToken(token.value)) {
                    is Resources.Success -> {
                        session.value = response.data.state
                        profile.value = response.data.usuario!!
                        Log.i("Session", response.data.state.toString())
                        Log.i("profile", profile.value.toString())
                    }
                    is Resources.Error -> {
                        stateApp.value = false
                        details.value = response.message
                    }
                }
            }catch (e:Exception){
                details.value = e.message.toString()
                stateApp.value = false
                Log.i("error", e.message.toString())
            }
        }
    }

    fun loginUser(username:String, password:String){
        val body = login(username, password)
        viewModelScope.launch(Dispatchers.IO){
            try {
                when(val response = apiRest.login(body)){
                    is Resources.Success -> {
                        token.value = response.data.token
                        stateApp.value = response.data.state
                        verifyToken()
                    }

                    is Resources.Error -> {
                        details.value = response.message
                        stateApp.value = false
                        Log.i("error", response.message)
                    }
                }
            }catch (e:Exception){
                details.value = e.message.toString()
                stateApp.value = false
                Log.i("error", e.message.toString())
            }
        }
    }

    fun signUp(body:signup){
        viewModelScope.launch(Dispatchers.IO){
            try {
                when(val response = apiRest.signUp(body)){
                    is Resources.Success -> {
                        details.value = response.data.details
                        stateApp.value = response.data.state
                    }
                    is Resources.Error -> {
                        details.value = response.message
                        stateApp.value = false
                    }
                }
            }catch (e:Exception){
                details.value = e.message.toString()
                stateApp.value = false
            }
        }
    }
}