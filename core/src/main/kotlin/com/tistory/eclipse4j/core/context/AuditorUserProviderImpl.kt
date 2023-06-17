package com.tistory.eclipse4j.core.context

import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component("auditorUserProvider")
@Scope(value = "request")
@Profile("prod")
class AuditorUserProviderProd(): AuditorUserProvider{
    private var auditorUser: AuditorUser? = null
    fun setAuditorUser(auditorUser:AuditorUser){
        this.auditorUser = auditorUser
    }

    override fun getAuditorUserEmail(): String{
        return this.auditorUser!!.email
    }
}

data class AuditorUser (
    val email:String
)