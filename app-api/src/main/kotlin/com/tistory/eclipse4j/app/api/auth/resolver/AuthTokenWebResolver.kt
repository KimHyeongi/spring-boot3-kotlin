package com.tistory.eclipse4j.app.api.auth.resolver

import com.tistory.eclipse4j.app.api.auth.handler.AuthTokenHandler
import com.tistory.eclipse4j.app.api.auth.user.AuthUser
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.util.WebUtils

@Component
class AuthTokenWebResolver(
    private var authTokenHandler: AuthTokenHandler
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(methodParameter: MethodParameter): Boolean {
        return methodParameter.getParameterAnnotation(Auth::class.java) != null
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): AuthUser? {
        if (parameter.parameterType == AuthUser::class.java) {
            var authToken = webRequest.getHeader("authToken")
            if (authToken == null) {
                val servletRequest = webRequest.nativeRequest as HttpServletRequest
                val authTokenCookie = WebUtils.getCookie(servletRequest, "authToken")
                if (authTokenCookie != null)
                    authToken = authTokenCookie.value
            }
            return authTokenHandler.getUserInfoFromToken(authToken!!)
        }

        return null
    }

}