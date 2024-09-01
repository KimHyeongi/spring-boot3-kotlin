package com.tistory.eclipse4j.core.primary.memo.entity

import jakarta.persistence.*

@Embeddable
data class MemoTagEntity(
    @Column(name="tag")
    val tag: String,
    @Column(name="sort")
    val sort: Int,
)