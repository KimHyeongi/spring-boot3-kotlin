package com.tistory.eclipse4j.core.primary.base.entity

import org.springframework.data.domain.AuditorAware
import java.util.*

class AuditorAwareImpl: AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {

        return Optional.of("")
    }
}