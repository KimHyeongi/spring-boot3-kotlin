package com.tistory.eclipse4j.core.primary.memo.service

import com.tistory.eclipse4j.core.CoreDomainApplicationTest
import com.tistory.eclipse4j.core.primary.memo.condition.MemoQueryCondition
import com.tistory.eclipse4j.core.primary.memo.entity.MemoCategoryEntity
import com.tistory.eclipse4j.core.primary.memo.entity.MemoEntity
import com.tistory.eclipse4j.core.primary.memo.entity.MemoTagEntity
import com.tistory.eclipse4j.core.primary.memo.entity.MemoType
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import mu.KotlinLogging
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(classes = [CoreDomainApplicationTest::class])
@Transactional(readOnly = true)
@Rollback(false)
internal class MemoCommandServiceSpringTest(
    sut: MemoCommandService
) : StringSpec() {
    private val log = KotlinLogging.logger { }

    init {
        "메모만 저장" {
            val memo = MemoEntity(
                title = "타이틀입니다.",
                contents = "내용입니다.",
                memoType = MemoType.STANDARD,
                memoCategories = mutableListOf(),
                memoTags = mutableListOf()
            )
            val results = sut.save(memo)
            memo.title shouldBe "타이틀입니다"
            memo.memoCategories.size shouldBe 0
        }

        "메모 - 카테고리,태그 저장" {
            val memo = MemoEntity(
                title = "타이틀입니다.",
                contents = "내용입니다.",
                memoType = MemoType.STANDARD,
                memoCategories = mutableListOf(),
                memoTags = mutableListOf()
            )
            memo.updateCategories(mutableListOf(
                MemoCategoryEntity(
                    name = "카테고리1", sort = 0, memo = memo,
                ),
                MemoCategoryEntity(
                    name = "카테고리2", sort = 1, memo = memo,
                )
            ))
            memo.updateTags(mutableListOf(
                MemoTagEntity(
                    tag = "태그1", sort = 0, memo = memo
                ),
                MemoTagEntity(
                    tag = "태그2", sort = 1, memo = memo,
                )
            ))
            val results = sut.save(memo)
            memo.title shouldBe "타이틀입니다"
            memo.memoCategories.size shouldBe 2
        }

    }
}