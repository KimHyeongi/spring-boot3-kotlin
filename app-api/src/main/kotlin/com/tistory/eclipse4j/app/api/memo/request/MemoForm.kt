package com.tistory.eclipse4j.app.api.memo.request

import com.tistory.eclipse4j.core.primary.memo.entity.MemoCategoryEntity
import com.tistory.eclipse4j.core.primary.memo.entity.MemoEntity
import com.tistory.eclipse4j.core.primary.memo.entity.MemoTagEntity
import com.tistory.eclipse4j.core.primary.memo.entity.MemoType
import java.io.Serializable

data class MemoForm(
    val title: String,
    val contents: String,
    val memoType: MemoType,
    val memoCategoryIds: List<Long>,
    val memoTags: List<MemoTagForm>,
) : Serializable {

    companion object Factory {
        fun toEntity(form: MemoForm): MemoEntity {
            return MemoEntity(
                title = form.title,
                contents = form.contents,
                memoType = form.memoType,
            )
        }
    }

}

data class MemoCategoryForm(
    val id: Long?,
    val name: String,
    val sort: Int,
) : Serializable {

    companion object Factory {

        fun toEntity(form: MemoCategoryForm, memoEntity: MemoEntity): MemoCategoryEntity {
            return MemoCategoryEntity(
                id = form.id,
                name = form.name,
                sort = form.sort
            )
        }
    }

}

data class MemoTagForm(
    val tag: String,
    val sort: Int,
) : Serializable {

    companion object Factory {
        fun toEntity(form: MemoTagForm, memoTagEntity: MemoTagEntity): MemoTagEntity {
            return MemoTagEntity(
                tag = form.tag,
                sort = form.sort,
            )
        }
    }

}