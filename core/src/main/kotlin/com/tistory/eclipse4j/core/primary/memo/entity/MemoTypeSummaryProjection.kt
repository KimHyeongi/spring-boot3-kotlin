package com.tistory.eclipse4j.core.primary.memo.entity

import com.querydsl.core.annotations.QueryProjection

data class MemoTypeSummaryProjection @QueryProjection constructor(
    val memoType: MemoType,
    val count: Long = 0,
)
