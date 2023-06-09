package com.tistory.eclipse4j.app.api.auth.handler

import com.tistory.eclipse4j.app.api.auth.user.AuthUser
import org.springframework.stereotype.Service

@Service
class AuthTokenApiProvider {

    fun findAuthUserByTokenNotExpired(token: String, timestamp: Long): AuthUser {
        return AuthUser(
            email = "eclipse4j@tistory.com",  // TEST
            name = "그리썸",
            accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlY2xpcHNlNGpAZ21haWwuY29tIiwiaWF0IjoxNjg2MDQ1MjQ1LCJleHAiOjE2ODYxMzE2NDV9.5SbzV4_L2aGv4mFfAGj4fzlTMaZMlLF3c8eZcb0RQB8"
        )
    }
}