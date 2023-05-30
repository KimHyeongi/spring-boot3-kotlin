package com.tistory.eclipse4j.core.primary.base.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(value = [AuditingEntityListener::class])
@MappedSuperclass
abstract class AuditingEntity {

    @Column(name = "deleted", nullable = false)
    @Comment("삭제여부")
    var deleted: Boolean? = false

    @CreatedDate
    @Column(nullable = true)
    @Comment("작성일")
    val createdAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(nullable = true)
    @Comment("수정일")
    var modifiedAt: LocalDateTime? = null
        protected set

    @CreatedBy
    @Column(nullable = true)
    @Comment("작성자")
    var createdBy: String? = null
        protected set

    @LastModifiedBy
    @Column(nullable = true)
    @Comment("수정자")
    var modifiedBy: String? = null
        protected set
}
