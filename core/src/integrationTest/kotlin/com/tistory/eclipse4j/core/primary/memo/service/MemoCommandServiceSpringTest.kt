package com.tistory.eclipse4j.core.primary.memo.service

import com.tistory.eclipse4j.core.CoreDomainApplicationTest
import com.tistory.eclipse4j.core.primary.memo.condition.MemoQueryCondition
import com.tistory.eclipse4j.core.primary.memo.entity.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import junit.framework.TestCase
import mu.KotlinLogging
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.data.domain.PageRequest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@ActiveProfiles("test")
@Profile("test")
@SpringBootTest(classes = [CoreDomainApplicationTest::class])
@Transactional(readOnly = false)
@Rollback(value = false)
internal class MemoCommandServiceSpringTest(
    sut: MemoCommandService
) : StringSpec(
    {
        val log = KotlinLogging.logger { }

        "메모 - 저장" {
            val memo = MemoEntity(
                title = "타이틀입니다.",
                contents = "내용입니다.",
                memoType = MemoType.STANDARD,
                memoTags = mutableListOf()
            )
            val results = sut.save(memo)
            results.title shouldBe "타이틀입니다."
        }

        "메모 - 태그 저장" {
            val memo = MemoEntity(
                title = "타이틀입니다.",
                contents = "내용입니다.",
                memoType = MemoType.STANDARD,
                memoTags = mutableListOf(MemoTagEntity(tag = "태그", sort = 0), MemoTagEntity(tag = "태그", sort = 1))
            )
            val results = sut.save(memo)
            results.title shouldBe "타이틀입니다."
        }

    }
)