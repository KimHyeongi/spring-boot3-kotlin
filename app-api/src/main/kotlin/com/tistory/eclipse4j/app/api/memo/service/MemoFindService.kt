package com.tistory.eclipse4j.app.api.memo.service

import com.tistory.eclipse4j.app.api.memo.response.Memo
import com.tistory.eclipse4j.core.primary.memo.service.MemoQueryService
import org.springframework.stereotype.Service


@Service
class MemoFindService(
    val memoQueryService: MemoQueryService
) {

    fun get(id:Long):Memo {
        val entity = memoQueryService.findById(id)
        return Memo.build(entity)
    }
}