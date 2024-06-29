package com.app.denuncia.sivar.remote

import com.app.denuncia.sivar.remote.repository.Repository
import com.app.denuncia.sivar.remote.repository.RepositoryImpl
import com.app.denuncia.sivar.remote.services.Services
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://serverdenuncias.vercel.app/backend/api/rest/sivardenuncias/server/node/route/fetch/axios/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}



//Se obtiene la variable retrofit del objeto Retrofit y
// crea el servico
object ApiConsumer {
    val apiService: Services by lazy {
        RetrofitClient.retrofit.create(Services::class.java)
    }
}

//Se manda a llamar la variable repository al viewMoel
object ApiProvider {
    private val gson = Gson()
    val repository : Repository by lazy {
        RepositoryImpl(ApiConsumer.apiService, gson)
    }
}
