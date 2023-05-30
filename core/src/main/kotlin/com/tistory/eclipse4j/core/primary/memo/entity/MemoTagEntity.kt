package com.tistory.eclipse4j.core.primary.memo.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.Where


@Embeddable
data class MemoTagEntity(
    @Column(name="tag")
    val tag: String,
    @Column(name="sort")
    val sort: Int,
)