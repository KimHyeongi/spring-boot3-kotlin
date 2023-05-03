package com.tistory.eclipse4j.core.primary.memo.repository

import com.querydsl.core.types.Projections
import com.tistory.eclipse4j.core.config.db.JpaPrimaryQueryDslRepositorySupport
import com.tistory.eclipse4j.core.primary.memo.condition.MemoQueryCondition
import com.tistory.eclipse4j.core.primary.memo.entity.MemoEntity
import com.tistory.eclipse4j.core.primary.memo.entity.QMemoEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

class MemoRepositoryImpl():
    JpaPrimaryQueryDslRepositorySupport(MemoEntity::class.java),
    MemoRepositoryCustom {

    val q = QMemoEntity.memoEntity
    override fun findAllByCondition(condition: MemoQueryCondition): Page<MemoEntity> {
        val count = from(q)
            .where(
                q.memoType.eq(condition.memoType)
            )
            .fetchCount()
        val results = from(q)
            .where(
                q.memoType.eq(condition.memoType)
            )
            .orderBy(q.id.desc())
            .limit(condition.pageable.pageSize.toLong())
            .offset(condition.pageable.offset)
            .fetch()
        return PageImpl(results, condition.pageable, count)
    }

}