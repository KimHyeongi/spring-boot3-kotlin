package com.tistory.eclipse4j.core.context

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope(value = "request")
class AuditorUserProvider(){
    private var auditorUser: AuditorUser? = null
    fun setAuditorUser(auditorUser:AuditorUser){
        this.auditorUser = auditorUser
    }

    fun setAuditorUserEmail(): String{
        return this.auditorUser!!.email
    }
}

data class AuditorUser (
    val email:String
)