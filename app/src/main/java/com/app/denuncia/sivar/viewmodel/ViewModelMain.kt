package com.app.denuncia.sivar.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
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

    private val _loadingSession = MutableStateFlow(false)
    val loadingSession: StateFlow<Boolean> = _loadingSession


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
    val search = mutableStateOf("")

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

    //Update Complaint
    private val _updateComplaint = MutableStateFlow(false)
    val updateComplaint:StateFlow<Boolean> = _updateComplaint

    private val _detailsUpdateComplaint = MutableStateFlow("")
    val detailsUpdateComplaint:StateFlow<String> = _detailsUpdateComplaint

    //Delete COmplaint
    private val _deleteComplaint = MutableStateFlow(false)
    val deleteComplaint:StateFlow<Boolean> = _deleteComplaint

    private val _detailsDeleteComplaint = MutableStateFlow("")
    val detailsDeleteComplaint:StateFlow<String> = _detailsDeleteComplaint

    init {
        getCategoriesList()
    }

    fun getUserDenuncias(): List<publicacion> {
        return _denuncias.value.filter { it.usuario?._id == _profile.value._id }
    }


    fun verifyToken(context: Context) {
        val store = UserDataStorage(context)
        _loadingSession.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = apiRest.verifyToken(store.getToken().toString())) {
                    is Resources.Success -> {
                        Log.i("Token", store.getToken().toString())
                        _session.value = response.data.state
                        _profile.value = response.data.usuario!!
                        _loadingSession.value = false
                    }
                    is Resources.Error -> {
                        _session.value = false
                        _detailsErrorRequest.value = response.message
                        _loadingSession.value = false
                    }
                }
            } catch (e: Exception) {
                _errorRequest.value = true
                _detailsErrorRequest.value = e.message.toString()
                _session.value = false
                _loadingSession.value = false
            }
        }
    }

    fun loginUser(username: String, password: String, context: Context) {
        val store = UserDataStorage(context)
        _loading.value = true
        val body = login(username, password)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = apiRest.login(body)) {
                    is Resources.Success -> {
                        _loginState.value = response.data.state
                        _errorRequest.value = false
                        store.saveToken(response.data.token)
                        verifyToken(context)
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

    fun logout(context: Context){
        val store = UserDataStorage(context)
        store.removeToken()
        verifyToken(context)
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

    fun updatePhoto(id:String, body:photo, context: Context){
        val store = UserDataStorage(context)
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRest.updatePhoto(id, body)
                if(response is Resources.Success){
                    _stateUpdateProfile.value = true
                    _errorRequest.value = false
                    store.saveToken(response.data.token)
                    verifyToken(context)
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

    fun updateProfile(id:String, body:userBody, context: Context){
        val store = UserDataStorage(context)
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRest.updateProfile(id, body)
                if(response is Resources.Success){
                    _stateUpdateProfile.value = true
                    _errorRequest.value = false
                    store.saveToken(response.data.token)
                    verifyToken(context)
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
                        _code.value = ""
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

    fun updateComplaint(id:String, state:String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                when(val response = apiRest.updateComplaint(id, state)){
                    is Resources.Success -> {
                        _detailsUpdateComplaint.value = response.data.details
                        _updateComplaint.value = response.data.state
                    }
                    is Resources.Error -> {
                        _detailsUpdateComplaint.value = response.message
                        _updateComplaint.value = false
                    }
                }
            }catch (e:Exception){
                _detailsUpdateComplaint.value = e.message.toString()
                _updateComplaint.value = false
            }
        }
    }

    fun deleteComplaint(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when(val response = apiRest.deleteComplaint(id)){
                    is Resources.Success -> {
                        _detailsDeleteComplaint.value = response.data.details
                        _deleteComplaint.value = response.data.state
                    }
                    is Resources.Error -> {
                        _detailsDeleteComplaint.value = response.message
                        _deleteComplaint.value = false
                    }
                }
            }catch (e:Exception){
                _detailsDeleteComplaint.value = e.message.toString()
                _updateComplaint.value = false
            }
        }
    }
}
