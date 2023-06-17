package com.tistory.eclipse4j.core.primary.base.entity

import com.tistory.eclipse4j.core.context.AuditorUserProvider
import com.tistory.eclipse4j.core.context.MemoApplicationContextProvider
import org.springframework.data.domain.AuditorAware
import java.util.*

class AuditorAwareImpl: AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        val auditorUserProvider = MemoApplicationContextProvider
                .getContext()
                .getBean("auditorUserProvider") as AuditorUserProvider
        return Optional.of(auditorUserProvider.getAuditorUserEmail())
    }
}