package com.tistory.eclipse4j.core.context

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
class MemoApplicationContextAware : ApplicationContextAware {

    private lateinit var applicationContext: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

    fun getMemoApplicationContextAware() {
        val myBean = applicationContext.getBean(MemoApplicationContextAware::class.java)
    }
}
