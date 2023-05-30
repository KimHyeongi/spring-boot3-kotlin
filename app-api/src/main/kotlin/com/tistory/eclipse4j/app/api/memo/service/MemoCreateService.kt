package com.tistory.eclipse4j.app.api.memo.service

import com.tistory.eclipse4j.app.api.memo.request.MemoForm
import com.tistory.eclipse4j.app.api.memo.response.Memo
import com.tistory.eclipse4j.core.primary.memo.condition.MemoQueryCondition
import com.tistory.eclipse4j.core.primary.memo.service.MemoCommandService
import com.tistory.eclipse4j.core.primary.memo.service.MemoQueryService
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = false)
class MemoCreateService(
    private val memoCommandService: MemoCommandService
) {
    fun save(form: MemoForm) : Long {
        return memoCommandService.save(MemoForm.toEntity(form)).let { it.id!! }
    }
}