package com.tistory.eclipse4j.core.primary.memo.entity

import com.tistory.eclipse4j.core.primary.base.entity.AuditingEntity
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLRestriction

@Entity
@Table(
    name = "memo_category_mapping",
    indexes = [
        // ,Index(name = "idx_dic_multiple_columns", columnList = "word, xxxx")
    ]
)
@Comment("메모 카테고리")
class MemoCategoryMappingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long? = null,
    sort: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    @SQLRestriction("deleted = false")
    var memo: MemoEntity,
    @ManyToOne(fetch = FetchType.LAZY)
    @SQLRestriction("deleted = false")
    var memoCategory: MemoCategoryEntity
): AuditingEntity() {
    @Column(nullable = false, name = "sort")
    @Comment("순서")
    var sort: Int = sort
        protected set

}
