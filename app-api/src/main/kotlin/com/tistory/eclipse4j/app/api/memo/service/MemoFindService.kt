package com.tistory.eclipse4j.app.api.memo.service

import com.tistory.eclipse4j.app.api.memo.response.Memo
import com.tistory.eclipse4j.core.primary.memo.condition.MemoQueryCondition
import com.tistory.eclipse4j.core.primary.memo.service.MemoQueryService
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service


@Service
class MemoFindService(
    private val memoQueryService: MemoQueryService
) {

    @Cacheable(value = ["cached_memo_by_id"], key = "#id")
    fun get(id:Long):Memo {
        val entity = memoQueryService.findById(id)
        return Memo.build(entity)
    }

    fun findAll(memoQueryCondition: MemoQueryCondition): Page<Memo> {
        val page = memoQueryService.findAllByCondition(memoQueryCondition)
        return Memo.build(page)
    }
}