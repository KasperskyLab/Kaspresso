package com.kaspersky.kaspresso.extensions.kakaoext

import com.kaspersky.kaspresso.configurator.ConfiguratorExt
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.klkakao.common.assertions.BaseAssertions

/**
 * A multiple assertion.
 * If at least one of assertions passes, [compositeCheck] is passed.
 * If no one of assertions passes, [compositeCheck] is failed.
 */
fun <T : BaseAssertions> T.compositeCheck(
    vararg asserts: T.() -> Unit
) {
    val logger: UiTestLogger = ConfiguratorExt.logger
    var cachedThrowable: Throwable? = null

    asserts.forEach { assert ->
        try {
            assert.invoke(this)
            logger.i("Composite check successfully passed. Passed check: ${assert.methodName}")
            return
        } catch (e: Throwable) {
            cachedThrowable = e
            logger.i("One part of composite check failed: ${assert.methodName}")
        }
    }

    logger.i("Composite check totally failed")

    throw cachedThrowable!!
}

/**
 * A safe multiple assertion.
 * If at least one of assertions passes, [safeCompositeCheck] is passed and true is returned.
 * If no one of assertions passes, [safeCompositeCheck] is failed and false is returned.
 *
 * @return true if check is successful, otherwise - false.
 */
fun <T : BaseAssertions> T.safeCompositeCheck(
    vararg asserts: T.() -> Unit
): Boolean {
    val logger: UiTestLogger = ConfiguratorExt.logger

    asserts.forEach { assert ->
        try {
            assert.invoke(this)
            logger.i("Composite check successfully passed. Passed check: ${assert.methodName}")
            return true
        } catch (e: Throwable) {
            logger.i("One part of composite check failed: ${assert.methodName}")
        }
    }

    logger.i("Composite check totally failed")

    return false
}

/**
 * @return an actual method name.
 */
val <T : BaseAssertions> (T.() -> Unit).methodName: String
    get() = this::class.java.getDeclaredMethod("getName").invoke(this) as String