package com.tistory.eclipse4j.core.primary.memo.condition

import com.tistory.eclipse4j.core.primary.memo.entity.MemoType
import org.springframework.data.domain.Pageable

data class MemoQueryCondition(
    val memoType: MemoType,
    val pageable: Pageable
)