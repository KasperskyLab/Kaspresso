package com.kaspersky.uitest_framework.kakaoext

import com.kaspersky.uitest_framework.Configuration
import com.kaspersky.uitest_framework.kakao.common.assertions.BaseAssertions

fun <T : BaseAssertions> T.compositeCheck(
        vararg asserts: T.() -> Unit
): Boolean {

    asserts.forEach { assert ->
        try {
            assert.invoke(this)

            Configuration.logger.i("Composite check successfully passed")

            return true
        } catch (e: Throwable) {
            Configuration.logger.i("One part of composite check failed: ${assert::class.java}")
        }
    }

    Configuration.logger.i("Composite check totally failed")

    return false
}