package com.tistory.eclipse4j.core

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode
import io.kotest.core.test.AssertionMode
import io.kotest.extensions.spring.SpringExtension
import io.mockk.clearAllMocks

object DomainCoreTestConfig : AbstractProjectConfig() {
    override val parallelism = 3
    override val assertionMode = AssertionMode.Error
    override val globalAssertSoftly = true
    override val failOnIgnoredTests = false
//    override val isolationMode = IsolationMode.SingleInstance
    override val isolationMode = IsolationMode.InstancePerLeaf

    override suspend fun afterProject() {
        clearAllMocks()
    }

    override fun extensions() = listOf(SpringExtension)
}