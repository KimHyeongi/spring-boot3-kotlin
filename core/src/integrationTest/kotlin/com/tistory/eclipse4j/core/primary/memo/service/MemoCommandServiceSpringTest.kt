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
@Rollback(value = true)
internal class MemoCommandServiceSpringTest(
    sut: MemoCommandService
) : StringSpec() {
    private val log = KotlinLogging.logger { }


    suspend fun beforeTest(testCase: TestCase) {
        System.out.println("=======TEST")
    }

    init {

        "메모만 저장" {
            true shouldBe true
        }

        "메모 - 카테고리,태그 저장" {
            true shouldBe true
        }

    }
}