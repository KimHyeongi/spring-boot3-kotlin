package com.tistory.eclipse4j.core.primary.memo.entity

import com.tistory.eclipse4j.core.primary.base.entity.AuditingEntity
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(
    name = "memo",
    indexes = [
        // ,Index(name = "idx_dic_multiple_columns", columnList = "word, xxxx")
    ]
)
@Comment("메모")
class MemoEntity(
    title: String,
    contents: String,
    memoType: MemoType,
    @OneToMany(mappedBy = "memo")
    @OrderBy("sort asc")
    var memoCategoryMappings: MutableList<MemoCategoryMappingEntity> = mutableListOf(),
    @ElementCollection(fetch = FetchType.LAZY)
    @OrderBy("sort asc")
    var memoTags: MutableList<MemoTagEntity> = mutableListOf()
): AuditingEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long? = null
        protected set

    @Column(nullable = false, name = "title")
    @Comment("단어")
    var title: String = title
        protected set

    @Column(nullable = false, name = "contents")
    @Comment("단어 타입")
    var contents: String = contents
        protected set

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "memoType")
    @Comment("메모 타입")
    var memoType: MemoType = memoType
        protected set

    fun updateCategoryMappings(memoCategoryMappings: MutableList<MemoCategoryMappingEntity>) {
        this.memoCategoryMappings = memoCategoryMappings
    }

    fun updateTags(memoTags: MutableList<MemoTagEntity>) {
        this.memoTags = memoTags
    }
}