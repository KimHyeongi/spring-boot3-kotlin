package com.tistory.eclipse4j.app.api.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId


@Service
class MemoDateTimeProvider {

    @Value("\${local.timezone}")
    lateinit var zone:String

    fun getZone(): ZoneId {
        return ZoneId.of("Asia/Seoul")
    }

    fun now(): Long {
        return LocalDateTime.now().atZone(getZone()).toInstant().toEpochMilli();
    }
}