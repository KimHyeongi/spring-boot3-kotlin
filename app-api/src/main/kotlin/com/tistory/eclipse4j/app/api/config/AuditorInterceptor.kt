package com.tistory.eclipse4j.app.api.config

import com.tistory.eclipse4j.app.api.auth.handler.AuthTokenHandler
import com.tistory.eclipse4j.core.context.AuditorUser
import com.tistory.eclipse4j.core.context.AuditorUserProvider
import com.tistory.eclipse4j.core.context.MemoApplicationContextProvider
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.util.WebUtils


@Component
class AuditorInterceptor(
    val authTokenHandler: AuthTokenHandler
):HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val auditorUserProvider = MemoApplicationContextProvider.getContext().getBean("auditorUserProvider") as AuditorUserProvider
        var authorization = request.getHeader("Authorization")
        if (authorization == null) {
            val authTokenCookie = WebUtils.getCookie(request, "Authorization")
            if (authTokenCookie != null)
                authorization = authTokenCookie.value
        }
        val authUser = authTokenHandler.getUserInfoFromToken(authorization!!)
        auditorUserProvider.setAuditorUser(AuditorUser(authUser.email))
        return true
    }
}