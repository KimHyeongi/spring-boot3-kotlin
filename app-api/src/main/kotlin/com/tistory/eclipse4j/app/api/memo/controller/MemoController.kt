package com.tistory.eclipse4j.app.api.memo.controller

import com.tistory.eclipse4j.app.api.auth.resolver.Auth
import com.tistory.eclipse4j.app.api.auth.user.AuthUser
import com.tistory.eclipse4j.app.api.memo.response.Memo
import com.tistory.eclipse4j.app.api.memo.service.MemoFindService
import com.tistory.eclipse4j.core.primary.memo.condition.MemoQueryCondition
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/memos")
class MemoController(
    private val memoFindService: MemoFindService
) {

    val log = KotlinLogging.logger { }

    @GetMapping("")
    fun all(memoQueryCondition: MemoQueryCondition, @Auth user: AuthUser): ResponseEntity<Page<Memo>> {
        log.info { "로그인 정보 : ${user.email} / ${user.name}" }
        val pageMemos = memoFindService.findAll(memoQueryCondition)
        return ResponseEntity.ok(pageMemos)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long, @Auth user: AuthUser): ResponseEntity<Memo> {
        val memo = memoFindService.get(id)
        return ResponseEntity.ok(memo)
    }
}