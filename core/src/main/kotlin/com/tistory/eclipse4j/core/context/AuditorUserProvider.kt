package com.tistory.eclipse4j.core.context

interface AuditorUserProvider {
    fun getAuditorUserEmail(): String
    fun setAuditorUser(auditorUser: AuditorUser)
}