package com.tistory.eclipse4j.app.api.auth.handler

import com.tistory.eclipse4j.app.api.auth.user.AuthUser
import org.springframework.stereotype.Service

@Service
class AuthTokenApiProvider {

    fun findAuthUserByTokenNotExpired(token: String, timestamp: Long): AuthUser {
        return AuthUser(email = "", name = "", accessToken = "")
    }
}