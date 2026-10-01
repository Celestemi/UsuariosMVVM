package com.celeste.usuarios.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.celeste.usuarios.data.model.Usuario
import com.celeste.usuarios.data.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface UsuariosUiState {
    data object Cargando : UsuariosUiState
    data class Exito(val usuarios: List<Usuario>) : UsuariosUiState
    data class Error(val mensaje: String) : UsuariosUiState
}

// VIEWMODEL: guarda el estado y llama al repositorio
class UsuarioViewModel(
    private val repository: UsuarioRepository = UsuarioRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<UsuariosUiState>(UsuariosUiState.Cargando)
    val uiState: StateFlow<UsuariosUiState> = _uiState.asStateFlow()

    init { cargarUsuarios() }

    fun cargarUsuarios() {
        viewModelScope.launch {
            _uiState.value = UsuariosUiState.Cargando
            _uiState.value = try {
                UsuariosUiState.Exito(repository.obtenerUsuarios())
            } catch (e: Exception) {
                UsuariosUiState.Error(e.message ?: "Error de red")
            }
        }
    }
}
