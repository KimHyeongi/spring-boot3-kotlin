package com.tistory.eclipse4j.core.primary.memo.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(
    name = "memo_category",
    indexes = [
        // ,Index(name = "idx_dic_multiple_columns", columnList = "word, xxxx")
    ]
)
@Comment("메모 카테고리")
class MemoCategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long? = null,
    name: String,
    sort: Int
) {

    @Column(nullable = false, name = "name")
    var name: String = name
        protected set

    @Column(nullable = false, name = "sort")
    var sort: Int = sort
        protected set

    @OneToMany(mappedBy = "memoCategory", fetch = FetchType.LAZY)
    var memoCategoryMappings: MutableList<MemoCategoryMappingEntity> = mutableListOf()
        protected set

    fun updateCategoryMappings(categoryMappings: MutableList<MemoCategoryMappingEntity>){
        this.memoCategoryMappings = categoryMappings
    }
}