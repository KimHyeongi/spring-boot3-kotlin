package com.tistory.eclipse4j.app.api.memo.controller

import com.tistory.eclipse4j.app.api.memo.response.Memo
import com.tistory.eclipse4j.app.api.memo.service.MemoFindService
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

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long): ResponseEntity<Memo> {
        val memo = memoFindService.get(id)
        return ResponseEntity.ok(memo)
    }
}