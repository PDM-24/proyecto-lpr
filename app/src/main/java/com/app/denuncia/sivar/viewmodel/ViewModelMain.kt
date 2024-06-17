package com.app.denuncia.sivar.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.denuncia.sivar.model.body.login
import com.app.denuncia.sivar.model.body.singup
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

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    // Sesión
    private val _session = MutableStateFlow(false)
    val session: StateFlow<Boolean> = _session

    // Token
    private val _token = MutableStateFlow("")

    // Perfil
    private val _profile = MutableStateFlow(Usuario())
    val profile: StateFlow<Usuario> = _profile

    //Errores en las solicitudes de las api
    private val _errorRequest = MutableStateFlow(false)
    val errorRequest: StateFlow<Boolean> = _errorRequest

    private val _detailsErrorRequest = MutableStateFlow("")
    val detailsErrorRequest: StateFlow<String> = _detailsErrorRequest


    // Estado de registro
    private val _singUpState = MutableStateFlow(false)
    val singUpState: StateFlow<Boolean> = _singUpState

    //Estado del login
    private val _loginState = MutableStateFlow(false)
    val loginState: StateFlow<Boolean> = _loginState

    // Denuncias
    private val _denuncias = MutableStateFlow<List<publicacion>>(emptyList())
    val denuncias: StateFlow<List<publicacion>> = _denuncias

    //Departamento
    private val _categorias = MutableStateFlow<List<Categoria>>(emptyList())
    val categorias: StateFlow<List<Categoria>> = _categorias


    init {
        getComplainst()
    }

    fun getUserDenuncias(): List<publicacion> {
        return _denuncias.value.filter { it.usuario._id == _profile.value._id }
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
                        _session.value = false
                        _detailsErrorRequest.value = response.message
                    }
                }
            } catch (e: Exception) {
                _errorRequest.value = true
                _detailsErrorRequest.value = e.message.toString()
                _session.value = false
            }
        }
    }

    fun loginUser(username: String, password: String) {
        _loading.value = true
        val body = login(username, password)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = apiRest.login(body)) {
                    is Resources.Success -> {
                        _token.value = response.data.token
                        _loginState.value = response.data.state
                        verifyToken()
                        _errorRequest.value = false
                    }
                    is Resources.Error -> {
                        _detailsErrorRequest.value = "Error al iniciar sesión ${response.message}"
                        _loginState.value = false
                        _session.value = false
                        _errorRequest.value = true
                    }
                }
                _loading.value = false
            } catch (e: Exception) {
                _detailsErrorRequest.value = e.message.toString()
                _loginState.value = false
                _session.value = false
                _loading.value = false
                _errorRequest.value = true
            }
        }
    }

    fun singUp(username: String, name: String, surname: String, email: String, birthdate: String, pass: String, rol: String) {
        _loading.value = true
        val body = singup(username, name, surname, email, birthdate, pass, rol)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = apiRest.singUp(body)) {
                    is Resources.Success -> {
                        _singUpState.value = response.data.state
                        _errorRequest.value = false
                    }
                    is Resources.Error -> {
                        _errorRequest.value = true
                        _detailsErrorRequest.value = response.message
                        _singUpState.value = false
                    }
                }
                _loading.value = false
            } catch (e: Exception) {
                _detailsErrorRequest.value = e.message.toString()
                _singUpState.value = false
                _errorRequest.value = true
                _loading.value = false
            }
        }
    }

    private fun getComplainst(){
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = apiRest.getComplaints()) {
                    is Resources.Success -> {
                        _denuncias.value = response.data
                        _errorRequest.value = false
                    }
                    is Resources.Error -> {
                        _errorRequest.value = true
                        _detailsErrorRequest.value = response.message
                    }
                }
                _loading.value = false
            }catch (e: Exception){
                _errorRequest.value = true
                _detailsErrorRequest.value = e.message.toString()
                _loading.value = false
            }
        }
    }

}
