package com.tistory.eclipse4j.app.api.config

import com.tistory.eclipse4j.app.api.auth.resolver.AuthTokenWebResolver
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Configuration
class WebConfig(
    val authTokenWebResolver: AuthTokenWebResolver
) : WebMvcConfigurationSupport() {

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        argumentResolvers.add(authTokenWebResolver)
    }

    // TODO: define CORS mappings
    public override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "DELETE", "PATCH", "POST", "PUT")
            .allowCredentials(true)
    }

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder.build()
    }
}