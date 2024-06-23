package com.app.denuncia.sivar.viewmodel

import android.content.Context

class UserDataStorage(context: Context){

    private val sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)

    fun saveToken(token: String){
        sharedPreferences.edit().putString("token", token).apply()
    }

    fun getToken(): String?{
        return sharedPreferences.getString("token", null)
    }

    fun removeToken(){
        sharedPreferences.edit().putString("token", "").apply()
    }
}