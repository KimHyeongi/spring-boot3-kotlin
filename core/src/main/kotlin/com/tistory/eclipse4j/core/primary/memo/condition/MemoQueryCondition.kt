package com.tistory.eclipse4j.core.primary.memo.condition

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.BooleanExpression
import com.tistory.eclipse4j.core.primary.memo.entity.MemoType
import com.tistory.eclipse4j.core.primary.memo.entity.QMemoEntity
import org.springframework.data.domain.PageRequest

data class MemoQueryCondition(
    var memoType: MemoType?,
    var page: Int = 0,
    var size: Int = 10,
) {
    private val q = QMemoEntity.memoEntity!!

    fun pageable(): PageRequest {
        return PageRequest.of(page, size)
    }

    fun eqMemoType(): BooleanExpression?{
        return memoType?.let {
            q.memoType.eq(it)
        }
    }
}