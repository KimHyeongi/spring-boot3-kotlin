package com.tistory.eclipse4j.core.context

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component("auditorUserProvider")
@Profile("!prod")
class AuditorUserProviderDummyImpl(): AuditorUserProvider{
    private var auditorUser: AuditorUser? = null
    override fun setAuditorUser(auditorUser:AuditorUser){
        this.auditorUser = auditorUser
    }

    override fun getAuditorUserEmail(): String{
        return "eclipse4j@gmail.com"
    }
}
