package com.tistory.eclipse4j.app.api.auth.handler

import com.tistory.eclipse4j.app.api.auth.user.AuthUser
import com.tistory.eclipse4j.app.api.util.MemoDateTimeProvider
import org.springframework.stereotype.Component

@Component
class AuthTokenHandler(
    var authTokenApiProvider: AuthTokenApiProvider,
    val memoDateTimeProvider: MemoDateTimeProvider
) {
    fun getUserInfoFromToken(token : String) : AuthUser {
        val timestampNow = memoDateTimeProvider.now()
        return authTokenApiProvider.findAuthUserByTokenNotExpired(
            token = token, timestamp = timestampNow
        )
    }
}