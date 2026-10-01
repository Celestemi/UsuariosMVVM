package com.celeste.usuarios.data.repository

import com.celeste.usuarios.data.model.Usuario
import com.celeste.usuarios.data.remote.ApiService
import com.celeste.usuarios.data.remote.RetrofitClient

// REPOSITORIO: única fuente de datos para el ViewModel
class UsuarioRepository(private val api: ApiService = RetrofitClient.api) {
    suspend fun obtenerUsuarios(): List<Usuario> = api.getUsers().results
}
