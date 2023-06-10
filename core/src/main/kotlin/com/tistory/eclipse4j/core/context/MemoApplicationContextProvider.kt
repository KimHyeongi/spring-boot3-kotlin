package com.tistory.eclipse4j.core.context

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class MemoApplicationContextProvider {

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    companion object {
        private lateinit var context: ApplicationContext
        fun getContext(): ApplicationContext {
            return context
        }
    }

    @PostConstruct
    fun initApplicationContext() {
        context = applicationContext
    }
}
