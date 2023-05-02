package com.tistory.eclipse4j.core.primary.memo.entity

import jakarta.persistence.*
import org.hibernate.annotations.Where


@Entity
@Table(
    name = "memo_category",
    indexes = [
        // ,Index(name = "idx_dic_multiple_columns", columnList = "word, xxxx")
    ]
)
@org.hibernate.annotations.Table(appliesTo = "memo_category", comment = "메모 분류")
class MemoCategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long = 0,
    @Column(nullable = false, name = "name")
    val name: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @Where(clause = "type = 'brand'")
    var memo: MemoEntity
) {

}