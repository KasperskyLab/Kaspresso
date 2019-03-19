package com.kaspersky.uitest_framework.kakaoext

import com.kaspersky.uitest_framework.configurator.Configurator
import com.agoda.kakao.common.assertions.BaseAssertions

/**
 * A joined assertion.
 * If at least one of assertions passes, [compositeCheck] is passed.
 * If no one of assertions passes, [compositeCheck] is failed.
 */
fun <T : BaseAssertions> T.compositeCheck(
    vararg asserts: T.() -> Unit
) {
    var cachedThrowable: Throwable? = null

    asserts.forEach { assert ->
        try {
            assert.invoke(this)

            Configurator.logger.i(
                "Composite check successfully passed. Passed check: ${assert.methodName}"
            )

            return
        } catch (e: Throwable) {
            cachedThrowable = e

            Configurator.logger.i(
                "One part of composite check failed: ${assert.methodName}"
            )
        }
    }

    Configurator.logger.i("Composite check totally failed")

    throw cachedThrowable!!
}

/**
 * A safe joined assertion.
 * If at least one of assertions passes, [safeCompositeCheck] is passed and true is returned.
 * If no one of assertions passes, [safeCompositeCheck] is failed and false is returned.
 *
 * @return true if check is successful, otherwise - false.
 */
fun <T : BaseAssertions> T.safeCompositeCheck(
    vararg asserts: T.() -> Unit
): Boolean {

    asserts.forEach { assert ->
        try {
            assert.invoke(this)

            Configurator.logger.i(
                "Composite check successfully passed. Passed check: ${assert.methodName}"
            )

            return true
        } catch (e: Throwable) {
            Configurator.logger.i(
                "One part of composite check failed: ${assert.methodName}"
            )
        }
    }

    Configurator.logger.i("Composite check totally failed")

    return false
}

/**
 * @return declared name of the assertion.
 */
val <T : BaseAssertions> (T.() -> Unit).methodName: String
    get() = this::class.java.getDeclaredMethod("getName").invoke(this) as String