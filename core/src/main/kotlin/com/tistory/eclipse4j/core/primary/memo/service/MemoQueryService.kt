package com.tistory.eclipse4j.core.primary.memo.service

import com.tistory.eclipse4j.core.primary.memo.condition.MemoQueryCondition
import com.tistory.eclipse4j.core.primary.memo.entity.MemoEntity
import com.tistory.eclipse4j.core.primary.memo.entity.MemoTypeSummaryProjection
import com.tistory.eclipse4j.core.primary.memo.repository.MemoRepository
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemoQueryService(
    val memoRepository: MemoRepository
) {
    fun findAllByCondition(memoQueryCondition: MemoQueryCondition): Page<MemoEntity> {
        return memoRepository.findAllByCondition(memoQueryCondition)
    }

    fun groupByMemoType(): MutableList<MemoTypeSummaryProjection> {
        return memoRepository.groupByMemoType()
    }

    fun findById(id: Long): MemoEntity = checkNotNull(memoRepository.findByIdOrNull(id)) { "검색된 정보가 없습니다." }
}