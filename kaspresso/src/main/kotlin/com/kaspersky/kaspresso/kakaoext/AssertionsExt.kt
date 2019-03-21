package com.kaspersky.kaspresso.kakaoext

import com.kaspersky.kaspresso.configurator.Configurator
import com.agoda.kakao.common.assertions.BaseAssertions
import com.kaspersky.kaspresso.logger.UiTestLogger

fun <T : BaseAssertions> T.compositeCheck(
    vararg asserts: T.() -> Unit
) {
    val logger: UiTestLogger = Configurator.logger
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

fun <T : BaseAssertions> T.safeCompositeCheck(
    vararg asserts: T.() -> Unit
): Boolean {

    val logger: UiTestLogger = Configurator.logger

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

val <T : BaseAssertions> (T.() -> Unit).methodName: String
    get() = this::class.java.getDeclaredMethod("getName").invoke(this) as String