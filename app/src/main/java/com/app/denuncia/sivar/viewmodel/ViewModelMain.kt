package com.app.denuncia.sivar.viewmodel

import androidx.lifecycle.ViewModel
import com.app.denuncia.sivar.remote.ApiProvider

class ViewModelMain: ViewModel() {

    private val apiRest = ApiProvider.repository
}