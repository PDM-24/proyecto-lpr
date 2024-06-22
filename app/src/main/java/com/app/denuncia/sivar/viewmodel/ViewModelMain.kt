package com.app.denuncia.sivar.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.denuncia.sivar.model.body.complaint
import com.app.denuncia.sivar.model.body.login
import com.app.denuncia.sivar.model.body.photo
import com.app.denuncia.sivar.model.body.userBody
import com.app.denuncia.sivar.model.mongoose.Usuario
import com.app.denuncia.sivar.model.mongoose.publicacion
import com.app.denuncia.sivar.remote.ApiProvider
import com.app.denuncia.sivar.remote.model.mongoose.Apoyo
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

    //Restulado del registro de la denuncia
    private val _stateUploadComplaint = MutableStateFlow(false)
    val stateUploadComplaint: StateFlow<Boolean> = _stateUploadComplaint

    //Categorias
    private val _categorias = MutableStateFlow<List<Categoria>>(emptyList())
    val categorias: StateFlow<List<Categoria>> = _categorias

    //Lista de usuarios
    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarios: StateFlow<List<Usuario>> = _usuarios

    //stateUpdateUser
    private val _stateUpdateUser = MutableStateFlow(false)
    val stateUpdateUser: StateFlow<Boolean> = _stateUpdateUser

    //state delete user
    private val _stateDeleteUser = MutableStateFlow(false)
    val stateDeleteUser: StateFlow<Boolean> = _stateDeleteUser

    //state update profile
    private val _stateUpdateProfile = MutableStateFlow(false)
    val stateUpdateProfile: StateFlow<Boolean> = _stateUpdateProfile

    //Email
    private val _loadingEmail = MutableStateFlow(false)
    val loadingEmail: StateFlow<Boolean> = _loadingEmail

    private val _email = MutableStateFlow(false)
    val email: StateFlow<Boolean> = _email
    
    private val _detailsErrorEmail = MutableStateFlow("")
    val detailsErrorEmail: StateFlow<String> = _detailsErrorEmail

    //Support Complaint
    private val _loadingSupport = MutableStateFlow(false)
    val loadingSupport: StateFlow<Boolean> = _loadingSupport

    private val _code = MutableStateFlow("")
    val code: StateFlow<String> = _code

    private val _supportState = MutableStateFlow(false)
    val supportState: StateFlow<Boolean> = _supportState

    private val _supportDetails = MutableStateFlow("")
    val supportDetails: StateFlow<String> = _supportDetails

    init {
        getCategoriesList()
    }

    fun getUserDenuncias(): List<publicacion> {
        return _denuncias.value.filter { it.usuario?._id == _profile.value._id }
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
                        verifyToken()
                        _token.value = response.data.token
                        _loginState.value = response.data.state
                        _errorRequest.value = false
                        _loading.value = false
                    }
                    is Resources.Error -> {
                        _detailsErrorRequest.value = "Error al iniciar sesión ${response.message}"
                        _errorRequest.value = true
                        _loginState.value = false
                        _session.value = false
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
        val body = userBody(username, name, surname, email, birthdate, pass, rol)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRest.singUp(body)
                if (response is Resources.Success) {
                    _singUpState.value = true
                    _errorRequest.value = false
                }else if (response is Resources.Error){
                    _detailsErrorRequest.value = response.message
                    _singUpState.value = false
                    _errorRequest.value = true
                }
                _loading.value = false
                Log.i("Registro", "singUp: ${_singUpState.value}")
            } catch (e: Exception) {
                _detailsErrorRequest.value = e.message.toString()
                _singUpState.value = false
                _errorRequest.value = true
                _loading.value = false
            }

        }
    }

    fun getComplainst(search: String, departamento: String, categorie: String) {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = apiRest.getComplaints(search, departamento, categorie)) {
                    is Resources.Success -> {
                        _denuncias.value = response.data
                        _errorRequest.value = false
                    }
                    is Resources.Error -> {
                        _errorRequest.value = true
                        _detailsErrorRequest.value = response.message
                    }
                }
            } catch (e: Exception) {
                _errorRequest.value = true
                _detailsErrorRequest.value = e.message.toString()
            } finally {
                _loading.value = false
            }
        }
    }


    private fun getCategoriesList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = apiRest.getCategoriesList()) {
                    is Resources.Success -> {
                        _categorias.value = response.data
                    }
                    is Resources.Error -> {
                        _categorias.value = emptyList()
                        _errorRequest.value = true
                        _detailsErrorRequest.value = response.message
                    }
                }
            }catch (e:Exception){
                _errorRequest.value = true
                _detailsErrorRequest.value = e.message.toString()
            }
        }
    }
    fun uploadComplaint(user:String, category:String, departamento:String, details:String, date:String, img:String){
        _loading.value = true
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val body = complaint(user, category, departamento, details, date, img)
                when (val response = apiRest.uploadComplaint(body)) {
                    is Resources.Success -> {
                        getComplainst("","","")
                        _errorRequest.value = false
                        _stateUploadComplaint.value = true
                        _loading.value = false
                    }
                    is Resources.Error -> {
                        _errorRequest.value = true
                        _stateUploadComplaint.value = false
                        _detailsErrorRequest.value = response.message
                        _loading.value = false
                        Log.i("Complaint", response.message)
                    }
                }
            }
        }catch (e:Exception){
            _errorRequest.value = true
            _detailsErrorRequest.value = e.message.toString()
            _loading.value = false
        }
    }

    fun getUsers(search: String) {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = apiRest.getUsers(search)) {
                    is Resources.Success -> {
                        _usuarios.value = response.data
                        _errorRequest.value = false
                    }
                    is Resources.Error -> {
                        _usuarios.value = emptyList()
                        _errorRequest.value = true
                        _detailsErrorRequest.value = response.message
                    }
                }
            } catch (e: Exception) {
                _errorRequest.value = true
                _detailsErrorRequest.value = e.message.toString()
            } finally {
                _loading.value = false
            }
        }
    }

    fun changeRol(id:String, rol:String){
        _loading.value = false
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when(val response = apiRest.changeRol(id, rol)){
                    is Resources.Success -> {
                        _stateUpdateUser.value = true
                        _errorRequest.value = false
                        _loading.value = false
                    }
                    is Resources.Error -> {
                        _errorRequest.value = true
                        _detailsErrorRequest.value = response.message
                        _loading.value = false
                        _stateUpdateUser.value = false
                    }
                }
            }catch (e:Exception){
                _errorRequest.value = true
                _detailsErrorRequest.value = e.message.toString()
                _loading.value = false
            }
        }
    }

    fun deleteUser(id:String){
        _loading.value = false
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when(val response = apiRest.deleteUser(id)){
                    is Resources.Success -> {
                        _stateDeleteUser.value = true
                        _errorRequest.value = false
                        _loading.value = false
                    }
                    is Resources.Error -> {
                        _errorRequest.value = true
                        _detailsErrorRequest.value = response.message
                        _loading.value = false
                        _stateDeleteUser.value = false
                    }
                }
            }catch (e:Exception){
                _errorRequest.value = true
                _detailsErrorRequest.value = e.message.toString()
                _loading.value = false
            }
        }
    }

    fun updatePhoto(id:String, body:photo){
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRest.updatePhoto(id, body)
                if(response is Resources.Success){
                    _stateUpdateProfile.value = true
                    _errorRequest.value = false
                    _token.value = response.data.token
                    verifyToken()
                    _loading.value = false
                }
                else if(response is Resources.Error){
                    _errorRequest.value = true
                    _detailsErrorRequest.value = response.message
                    _stateUpdateProfile.value = false
                    _loading.value = false
                }
            }catch (e:Exception){
                _errorRequest.value = true
                _detailsErrorRequest.value = e.message.toString()
                _stateUpdateProfile.value = false
                _loading.value = false
            }
        }
    }

    fun updateProfile(id:String, body:userBody){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRest.updateProfile(id, body)
                if(response is Resources.Success){
                    _stateUpdateProfile.value = true
                    _errorRequest.value = false
                    _token.value = response.data.token
                    verifyToken()
                    _loading.value = false
                }
                else if(response is Resources.Error){
                    _errorRequest.value = true
                    _detailsErrorRequest.value = response.message
                    _stateUpdateProfile.value = false
                    _loading.value = false
                }
            }catch (e:Exception){
                _errorRequest.value = true
                _detailsErrorRequest.value = e.message.toString()
                _stateUpdateProfile.value = false
                _loading.value = false
            }
        }
    }

    fun getEmailCode(){
        _loadingEmail.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when(val response = apiRest.getEmailCode(profile.value._id)){
                    is Resources.Success -> {
                        _code.value = response.data.code
                        _email.value = true
                        _loadingEmail.value = false
                    }
                    is Resources.Error -> {
                        _detailsErrorEmail.value = response.message
                        _email.value = false
                        _loadingEmail.value = false
                    }
                }
            }catch (e:Exception){
                _detailsErrorRequest.value = e.message.toString()
                _email.value = false
                _loadingEmail.value = false
            }
        }
    }

    fun supportComplaint(id:String){
        _loadingSupport.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when(val response = apiRest.supportComplaint(id, Apoyo(profile.value._id, code.value))){
                    is Resources.Success -> {
                        _supportState.value = response.data.state
                        _supportDetails.value = response.data.details
                        _loadingSupport.value = false
                    }
                    is Resources.Error -> {
                        _supportState.value = false
                        _supportDetails.value = response.message
                        _loadingSupport.value = false
                    }
                }
            }catch (e:Exception){
                _detailsErrorRequest.value = e.message.toString()
                _email.value = false
                _loadingEmail.value = false
            }
        }
    }
}
