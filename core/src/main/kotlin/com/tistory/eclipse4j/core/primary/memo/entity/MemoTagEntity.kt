package com.tistory.eclipse4j.core.primary.memo.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.Where


@Entity
@Table(
    name = "memo_tag",
    indexes = [
        // ,Index(name = "idx_dic_multiple_columns", columnList = "word, xxxx")
    ]
)
@org.hibernate.annotations.Table(appliesTo = "memo_tag", comment = "메모 Tag")
class MemoTagEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long = 0,
    tag: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @Where(clause = "type = 'brand'")
    var memo: MemoEntity
){
    @Column(nullable = false, name = "tag")
    @Comment("TAG")
    var tag: String = tag
        protected set
}