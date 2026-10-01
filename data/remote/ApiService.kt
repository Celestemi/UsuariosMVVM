package com.celeste.usuarios.data.remote

import com.celeste.usuarios.data.model.ApiResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("api/?results=20")
    suspend fun getUsers(): ApiResponse
}

// Retrofit se crea UNA sola vez (ya no dentro del Composable)
object RetrofitClient {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
