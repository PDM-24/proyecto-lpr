package com.app.denuncia.sivar.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.denuncia.sivar.model.Denuncia
import com.app.denuncia.sivar.model.body.login
import com.app.denuncia.sivar.model.body.signup
import com.app.denuncia.sivar.model.mongoose.Usuario
import com.app.denuncia.sivar.model.mongoose.publicacion
import com.app.denuncia.sivar.remote.ApiProvider
import com.app.denuncia.sivar.remote.model.mongoose.Categoria
import com.app.denuncia.sivar.resources.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelMain : ViewModel() {
    private val apiRest = ApiProvider.repository

    // Sesión
    private val _session = MutableStateFlow(false)
    val session: StateFlow<Boolean> = _session

    // Token
    private val _token = MutableStateFlow("")

    // Perfil
    private val _profile = MutableStateFlow(Usuario())
    val profile: StateFlow<Usuario> = _profile

    private val _details = MutableStateFlow("")
    private val _stateApp = MutableStateFlow(false)

    // Estado de registro
    private val _signUpState = MutableStateFlow(false)
    val signUpState: StateFlow<Boolean> = _signUpState

    // Mensaje de error
    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    // Denuncias
    private val _denuncias = MutableStateFlow<List<publicacion>>(emptyList())
    val denuncias: StateFlow<List<publicacion>> = _denuncias

    //Departamento
    private val _categorias = MutableStateFlow<List<Categoria>>(emptyList())
    val categorias: StateFlow<List<Categoria>> = _categorias

    init {
        getComplainst()
    }


    fun verifyToken() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = apiRest.verifyToken(_token.value)) {
                    is Resources.Success -> {
                        _session.value = response.data.state
                        _profile.value = response.data.usuario!!
                    }
                    is Resources.Error -> {
                        _stateApp.value = false
                        _details.value = response.message
                    }
                }
            } catch (e: Exception) {
                _details.value = e.message.toString()
                _stateApp.value = false
            }
        }
    }

    fun loginUser(username: String, password: String) {
        val body = login(username, password)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = apiRest.login(body)) {
                    is Resources.Success -> {
                        _token.value = response.data.token
                        _stateApp.value = response.data.state
                        _errorMessage.value = ""
                        verifyToken()
                    }
                    is Resources.Error -> {
                        _details.value = response.message
                        _stateApp.value = false
                        _errorMessage.value = "Nombre de usuario o contraseña incorrectos"
                        _session.value = false
                    }
                }
            } catch (e: Exception) {
                _details.value = e.message.toString()
                _stateApp.value = false
                _errorMessage.value = "Error de conexión. Por favor, inténtalo de nuevo."
                _session.value = false
            }
        }
    }

    fun setErrorMessage(message: String) {
        _errorMessage.value = message
    }

    fun signUp(username: String, email: String, password: String) {
        val body = signup(username, email, password)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = apiRest.signUp(body)) {
                    is Resources.Success -> {
                        _details.value = response.data.details
                        _stateApp.value = response.data.state
                        _signUpState.value = true
                    }
                    is Resources.Error -> {
                        _details.value = response.message
                        _stateApp.value = false
                        _signUpState.value = false
                    }
                }
            } catch (e: Exception) {
                _details.value = e.message.toString()
                _stateApp.value = false
                _signUpState.value = false
            }
        }
    }

    private fun getComplainst(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = apiRest.getComplaints()) {
                    is Resources.Success -> {
                        _denuncias.value = response.data
                        _denuncias.value.forEach {
                            Log.d("DENUNCIAS", it.toString())
                        }
                    }
                    is Resources.Error -> {
                        setErrorMessage(response.message)
                    }
                }
            }catch (e: Exception){
                setErrorMessage(e.message.toString())
                Log.d("ERROR", e.message.toString())
            }
        }
    }

}
