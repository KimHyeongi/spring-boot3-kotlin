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
import org.springframework.web.reactive.function.client.WebClientResponseException.NotFound
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
    ): AuthUser {
        if (parameter.parameterType == AuthUser::class.java) {
            var authorization = webRequest.getHeader("Authorization")
            if (authorization == null) {
                val servletRequest = webRequest.nativeRequest as HttpServletRequest
                val authTokenCookie = WebUtils.getCookie(servletRequest, "Authorization")
                if (authTokenCookie != null)
                    authorization = authTokenCookie.value
            }
            return authTokenHandler.getUserInfoFromToken(authorization!!)
        }
        throw RuntimeException("사용자 정보를 찾을 수 없습니다. Authorization 을 확인하세요.")
    }

}