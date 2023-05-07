package com.tistory.eclipse4j.core.primary.memo.service

import com.tistory.eclipse4j.core.CoreDomainApplicationTest
import com.tistory.eclipse4j.core.primary.memo.condition.MemoQueryCondition
import com.tistory.eclipse4j.core.primary.memo.entity.MemoType
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe
import mu.KotlinLogging
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(classes = [CoreDomainApplicationTest::class])
@Transactional(readOnly = true)
internal class MemoQueryServiceSpringTest(
    sut: MemoQueryService
) : StringSpec() {
    private val log = KotlinLogging.logger { }

    init {
        "메모 타입 STANDARD / 페이징 검색" {
            val condition = MemoQueryCondition(
                memoType=MemoType.STANDARD,
                pageable=PageRequest.of(0, 10)
            )
            val results = sut.findAllByCondition(condition)
            results.content.size shouldBeGreaterThanOrEqual  0
        }

    }
}