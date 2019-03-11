package com.kaspersky.uitest_framework.kakaoext

import com.kaspersky.uitest_framework.Configuration
import com.kaspersky.uitest_framework.kakao.common.assertions.BaseAssertions

fun <T : BaseAssertions> T.compositeCheck(
        vararg asserts: T.() -> Unit
) {
    var cachedThrowable: Throwable? = null

    asserts.forEach { assert ->
        try {
            assert.invoke(this)

            Configuration.logger.i(
                    "Composite check successfully passed. Passed check: ${assert.methodName}"
            )

            return
        } catch (e: Throwable) {
            cachedThrowable = e

            Configuration.logger.i(
                    "One part of composite check failed: ${assert.methodName}"
            )
        }
    }

    Configuration.logger.i("Composite check totally failed")

    throw cachedThrowable!!
}

fun <T : BaseAssertions> T.safeCompositeCheck(
        vararg asserts: T.() -> Unit
): Boolean {

    asserts.forEach { assert ->
        try {
            assert.invoke(this)

            Configuration.logger.i(
                    "Composite check successfully passed. Passed check: ${assert.methodName}"
            )

            return true
        } catch (e: Throwable) {
            Configuration.logger.i(
                    "One part of composite check failed: ${assert.methodName}"
            )
        }
    }

    Configuration.logger.i("Composite check totally failed")

    return false
}

val <T : BaseAssertions> (T.() -> Unit).methodName: String
    get() = this::class.java.getDeclaredMethod("getName").invoke(this) as String