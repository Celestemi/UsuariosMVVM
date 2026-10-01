package com.celeste.usuarios.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.celeste.usuarios.data.model.Usuario

// VIEW: solo dibuja el estado, no hace llamadas de red
@Composable
fun UsuarioScreen(viewModel: UsuarioViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()

    when (val s = state) {
        is UsuariosUiState.Cargando ->
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Cargando...") }

        is UsuariosUiState.Error ->
            Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                Text("Error: ${s.mensaje}")
                Button(onClick = { viewModel.cargarUsuarios() }) { Text("Reintentar") }
            }

        is UsuariosUiState.Exito ->
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) { items(s.usuarios) { ItemUsuario(it) } }
    }
}

@Composable
fun ItemUsuario(user: Usuario) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        AsyncImage(model = user.picture.medium, contentDescription = null, modifier = Modifier.size(64.dp))
        Column {
            Text(user.name.first)
            Text(user.name.last)
        }
    }
}
