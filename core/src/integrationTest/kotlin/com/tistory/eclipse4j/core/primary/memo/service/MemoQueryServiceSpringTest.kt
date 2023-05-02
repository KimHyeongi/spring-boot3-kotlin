package com.tistory.eclipse4j.core.primary.memo.service

import com.tistory.eclipse4j.core.CoreDomainApplicationTest
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
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
        "페이징 검색" {
            val results = sut.findByCondition(PageRequest.of(0, 10))
            results.content.size shouldBe 0
        }

    }
}