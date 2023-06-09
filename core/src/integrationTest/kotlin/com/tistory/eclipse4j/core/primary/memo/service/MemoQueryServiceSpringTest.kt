package com.tistory.eclipse4j.core.primary.memo.service

import com.tistory.eclipse4j.core.CoreDomainApplicationTest
import com.tistory.eclipse4j.core.primary.memo.entity.MemoEntity
import com.tistory.eclipse4j.core.primary.memo.entity.MemoType
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(classes = [CoreDomainApplicationTest::class])
@Transactional
@Rollback(true)
internal class MemoQueryServiceSpringTest(
    sut: MemoQueryService,
    memoCommandService: MemoCommandService
) : StringSpec(
    {
        var testMemoId:Long? = null

        beforeTest {
            testMemoId = memoCommandService.save(
                entity = MemoEntity(
                    title = "TITLE",
                    contents = "CONTENTS",
                    memoType = MemoType.STANDARD,
                    memoCategoryMappings = mutableListOf(),
                    memoTags = mutableListOf()
                )
            ).id
        }

        "메모 타입 STANDARD / 페이징 검색" {
            val results = sut.groupByAllMemoType()
            results.size shouldBe(1)
        }

        "메모 조회" {
            var title = String()
            testMemoId?.let {
                title = sut.findById(it).title
            }
            title shouldBeEqual "TITLE"
        }

    }
)