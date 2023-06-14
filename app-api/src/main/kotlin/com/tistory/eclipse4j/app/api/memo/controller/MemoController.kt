package com.tistory.eclipse4j.app.api.memo.controller

import com.tistory.eclipse4j.app.api.auth.resolver.Auth
import com.tistory.eclipse4j.app.api.auth.user.AuthUser
import com.tistory.eclipse4j.app.api.memo.request.MemoForm
import com.tistory.eclipse4j.app.api.memo.response.Memo
import com.tistory.eclipse4j.app.api.memo.service.MemoCreateService
import com.tistory.eclipse4j.app.api.memo.service.MemoFindService
import com.tistory.eclipse4j.core.primary.memo.condition.MemoQueryCondition
import jakarta.annotation.PostConstruct
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/memos")
class MemoController(
    private val memoFindService: MemoFindService,
    private val memoCreateService: MemoCreateService
) {

    val log = KotlinLogging.logger { }

    @GetMapping("")
    fun all(memoQueryCondition: MemoQueryCondition, @Auth user: AuthUser): ResponseEntity<Page<Memo>> {
        val pageMemos = memoFindService.findAll(memoQueryCondition)
        return ResponseEntity.ok(pageMemos)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long, @Auth user: AuthUser): ResponseEntity<Memo> {
        val memo = memoFindService.get(id)
        return ResponseEntity.ok(memo)
    }

    @PostMapping("")
    fun add(@RequestBody memoForm: MemoForm, @Auth user: AuthUser): ResponseEntity<Long> {
        val memoId = memoCreateService.save(memoForm)
        return ResponseEntity.ok(memoId)
    }
}