package com.tistory.eclipse4j.core.primary.memo.entity

enum class MemoType(
    val description: String
) {
    STANDARD(
        "일반 메모",
    ),
    TEMPORARY(
        "임시 메모 - 7일",
    ),
}
