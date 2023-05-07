package com.tistory.eclipse4j.app.api.memo.response

import com.tistory.eclipse4j.core.primary.memo.entity.MemoEntity
import com.tistory.eclipse4j.core.primary.memo.entity.MemoType
import java.io.Serializable

data class Memo(
    val title:String,
    val contents:String,
    val memoType: MemoType
) : Serializable {
    companion object Factory {
        fun build(memoEntity: MemoEntity): Memo{
            return Memo(
                title = memoEntity.title,
                contents = memoEntity.contents,
                memoType = memoEntity.memoType
            )
        }
    }
}
