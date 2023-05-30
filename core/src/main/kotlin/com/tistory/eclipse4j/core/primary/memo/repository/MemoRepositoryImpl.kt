package com.tistory.eclipse4j.core.primary.memo.repository

import com.querydsl.core.types.Projections
import com.tistory.eclipse4j.core.config.db.JpaPrimaryQueryDslRepositorySupport
import com.tistory.eclipse4j.core.primary.memo.condition.MemoQueryCondition
import com.tistory.eclipse4j.core.primary.memo.entity.MemoEntity
import com.tistory.eclipse4j.core.primary.memo.entity.MemoTypeSummaryProjection
import com.tistory.eclipse4j.core.primary.memo.entity.QMemoEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

class MemoRepositoryImpl():
    JpaPrimaryQueryDslRepositorySupport(MemoEntity::class.java),
    MemoRepositoryCustom {

    val q = QMemoEntity.memoEntity!!

    override fun findAllByCondition(condition: MemoQueryCondition): Page<MemoEntity> {
        val count = from(q)
            .where(
                condition.eqMemoType()
            )
            .fetchCount()
        val results = from(q)
            .where(
                condition.eqMemoType()
            )
            .orderBy(q.id.desc())
            .limit(condition.pageable().pageSize.toLong())
            .offset(condition.pageable().offset)
            .fetch()
        return PageImpl(results, condition.pageable(), count)
    }

    override fun groupByMemoType(): MutableList<MemoTypeSummaryProjection> {
        return from(q) // table = message
            .groupBy(q.memoType)
            .select(Projections.constructor(MemoTypeSummaryProjection::class.java
                , q.memoType
                , q.count()
            ))
            .fetch()
    }

}