package com.tistory.eclipse4j.core.primary.memo.service

import com.tistory.eclipse4j.core.primary.memo.condition.MemoQueryCondition
import com.tistory.eclipse4j.core.primary.memo.entity.MemoEntity
import com.tistory.eclipse4j.core.primary.memo.entity.MemoType
import com.tistory.eclipse4j.core.primary.memo.entity.QMemoEntity
import com.tistory.eclipse4j.core.primary.memo.repository.MemoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemoQueryService(
    val memoRepository: MemoRepository

) {
    fun findAllByCondition(memoQueryCondition: MemoQueryCondition): Page<MemoEntity>{
        return memoRepository.findAllByCondition(memoQueryCondition)
    }
}