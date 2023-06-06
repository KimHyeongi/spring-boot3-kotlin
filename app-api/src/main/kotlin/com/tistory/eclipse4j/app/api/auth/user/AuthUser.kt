package com.tistory.eclipse4j.app.api.auth.user

data class AuthUser(
    val email:String,
    val name:String,
    val accessToken:String
)