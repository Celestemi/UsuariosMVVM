package com.celeste.usuarios.data.model

// MODEL: datos que devuelve la API (https://randomuser.me/api/?results=20)
data class ApiResponse(val results: List<Usuario>)
data class Usuario(val name: Nombre, val picture: Imagen)
data class Nombre(val first: String, val last: String)
data class Imagen(val medium: String)
