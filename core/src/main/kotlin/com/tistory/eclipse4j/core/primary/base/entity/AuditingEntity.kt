package com.tistory.eclipse4j.core.primary.base.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.ColumnDefault
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
    @ColumnDefault("false")
    @Comment("삭제여부")
    var deleted: Boolean? = false
        protected set

    @CreatedDate
    @Column(nullable = true, name="created_at")
    @Comment("작성일")
    var createdAt: LocalDateTime? = null
        protected set

    @LastModifiedDate
    @Column(nullable = true, name="modified_at")
    @Comment("수정일")
    var modifiedAt: LocalDateTime? = null
        protected set

    @CreatedBy
    @Column(nullable = true, name="created_by")
    @Comment("작성자")
    var createdBy: String? = null
        protected set

    @LastModifiedBy
    @Column(nullable = true, name="modified_by")
    @Comment("수정자")
    var modifiedBy: String? = null
        protected set
}
