package com.app.denuncia.sivar.remote.repository

import android.service.autofill.UserData
import com.app.denuncia.sivar.model.body.login
import com.app.denuncia.sivar.model.body.signup
import com.app.denuncia.sivar.remote.model.JsonResponse
import com.app.denuncia.sivar.remote.model.TokenJson
import com.app.denuncia.sivar.resources.Resources

interface Repository {

    suspend fun login(body:login): Resources<TokenJson>
    suspend fun signup(data:signup): Resources<JsonResponse>
}