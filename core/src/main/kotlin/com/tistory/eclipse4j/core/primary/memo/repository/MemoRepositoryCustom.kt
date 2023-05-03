package com.tistory.eclipse4j.core.primary.memo.repository

import com.tistory.eclipse4j.core.primary.memo.condition.MemoQueryCondition
import com.tistory.eclipse4j.core.primary.memo.entity.MemoEntity
import org.springframework.data.domain.Page
import org.springframework.stereotype.Repository

@Repository
interface MemoRepositoryCustom {
    fun findAllByCondition(condition: MemoQueryCondition): Page<MemoEntity>
}
