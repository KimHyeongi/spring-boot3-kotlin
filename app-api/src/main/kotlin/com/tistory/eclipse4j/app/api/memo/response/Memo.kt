package com.tistory.eclipse4j.app.api.memo.response

import com.tistory.eclipse4j.core.primary.memo.entity.*
import org.springframework.data.domain.Page
import java.io.Serializable

data class Memo(
    val title:String,
    val contents:String,
    val memoType: MemoType,
    val memoCategories: MutableList<MemoCategory>,
    val memoTags: MutableList<MemoTag>,
) : Serializable {

    companion object Factory {
        fun build(memoEntity: MemoEntity): Memo{
            return Memo(
                title = memoEntity.title,
                contents = memoEntity.contents,
                memoType = memoEntity.memoType,
                memoCategories = MemoCategory.build(memoEntity.memoCategoryMappings),
                memoTags = MemoTag.build(memoEntity.memoTags)
            )
        }
        fun build(page: Page<MemoEntity>): Page<Memo>{
            return page.map {
                Memo(
                    title = it.title,
                    contents = it.contents,
                    memoType = it.memoType,
                    memoCategories = MemoCategory.build(it.memoCategoryMappings),
                    memoTags = MemoTag.build(it.memoTags)
                )
            }
        }
    }
}

data class MemoCategory(
    val id: Long,
    val name: String,
){
    companion object {
        fun build(memoCategoryMappings: MutableList<MemoCategoryMappingEntity>): MutableList<MemoCategory> {
            return memoCategoryMappings.map {
                MemoCategory(
                    id = it.memoCategory.id!!,
                    name = it.memoCategory.name
                )
            }.toMutableList()
        }
    }

}

data class MemoTag(
    val tag: String,
    val sort: Int
){
    companion object {
        fun build(memoTags: MutableList<MemoTagEntity>): MutableList<MemoTag> {
            return memoTags.map {
                MemoTag(
                    tag = it.tag,
                    sort = it.sort
                )
            }.toMutableList()
        }
    }

}