package com.kaspersky.uitest_framework.attempt

import com.kaspersky.uitest_framework.Configuration
import com.kaspersky.uitest_framework.Device
import com.kaspersky.uitest_framework.kakaoext.dispatchers.FlakySafeInterceptedViewDispatcher
import com.kaspersky.uitest_framework.kakao.KBaseView
import com.kaspersky.uitest_framework.kakao.dispatchers.ViewDispatcher
import java.util.concurrent.TimeUnit

/**
 * Created by egor.kurnikov on 04.03.2019
 */

private fun coreAttempt(
        timeoutMs: Long = Configuration.attemptsTimeoutMs,
        attemptsFrequencyMs: Long = Configuration.attemptsFrequencyMs,
        allowedExceptions: Set<Class<out Throwable>> = Configuration.allowedExceptionsForAttempt,
        action: () -> Unit
) {
    var timer = 0L
    var caughtAllowedException: Throwable

    val startTime = System.currentTimeMillis()

    do {
        try {
            action.invoke()
            return
        } catch (e: Throwable) {
            val isExceptionAllowed =
                    allowedExceptions.find { it.isAssignableFrom(e.javaClass) } != null

            when {
                isExceptionAllowed -> {
                    Thread.sleep(attemptsFrequencyMs)
                    timer += attemptsFrequencyMs
                    caughtAllowedException = e
                }
                else -> {
                    throw e
                }
            }
        }
    } while (timer <= timeoutMs && System.currentTimeMillis() - startTime <= timeoutMs)

    throw caughtAllowedException
}

fun <T : KBaseView<Any>> T.attempt(
        timeoutMs: Long = Configuration.attemptsTimeoutMs,
        attemptsFrequencyMs: Long = Configuration.attemptsFrequencyMs,
        allowedExceptions: Set<Class<out Throwable>> = Configuration.allowedExceptionsForAttempt,
        action: T.() -> Unit
) {
    coreAttempt(
            timeoutMs,
            attemptsFrequencyMs,
            allowedExceptions
    ) {
        action.invoke(this)
    }
}

fun <T : KBaseView<Any>> T.attempt(
        timeoutSec: Long,
        action: T.() -> Unit
) {
    coreAttempt(
            TimeUnit.SECONDS.toMillis(timeoutSec),
            Configuration.attemptsFrequencyMs,
            Configuration.allowedExceptionsForAttempt
    ) {
        action.invoke(this)
    }
}

fun FlakySafeInterceptedViewDispatcher.attempt(
        timeoutMs: Long = Configuration.attemptsTimeoutMs,
        attemptsFrequencyMs: Long = Configuration.attemptsFrequencyMs,
        allowedExceptions: Set<Class<out Throwable>> = Configuration.allowedExceptionsForAttempt,
        action: ViewDispatcher.() -> ViewDispatcher
) {
    coreAttempt(
            timeoutMs,
            attemptsFrequencyMs,
            allowedExceptions
    ) {
        action.invoke(this)
    }
}

fun Device.attempt(
        timeoutMs: Long = Configuration.attemptsTimeoutMs,
        attemptsFrequencyMs: Long = Configuration.attemptsFrequencyMs,
        allowedExceptions: Set<Class<out Throwable>> = Configuration.allowedExceptionsForAttempt,
        action: () -> Unit
) {
    coreAttempt(
            timeoutMs,
            attemptsFrequencyMs,
            allowedExceptions
    ) {
        action.invoke()
    }
}

//fun ViewInteraction.attempt(
//        timeoutMs: Long = Configuration.attemptsTimeoutMs,
//        attemptsFrequencyMs: Long = Configuration.attemptsFrequencyMs,
//        allowedExceptions: Set<Class<out Throwable>> = Configuration.allowedExceptionsForAttempt,
//        action: ViewInteraction.() -> ViewInteraction
//) {
//    coreAttempt(
//            timeoutMs,
//            attemptsFrequencyMs,
//            allowedExceptions
//    ) {
//        action.invoke(this)
//    }
//}
//
//fun ViewInteraction.attempt(
//        action: ViewInteraction.() -> ViewInteraction
//) {
//    coreAttempt(
//            Configuration.attemptsTimeoutMs,
//            Configuration.attemptsFrequencyMs,
//            Configuration.allowedExceptionsForAttempt
//    ) {
//        action.invoke(this)
//    }
//}
//
//fun ViewInteraction.attempt(
//        timeoutMs: Long = Configuration.attemptsTimeoutMs,
//        attemptsFrequencyMs: Long = Configuration.attemptsFrequencyMs,
//        allowedExceptions: Set<Class<out Throwable>> = Configuration.allowedExceptionsForAttempt,
//        action: ViewAction
//) {
//    coreAttempt(
//            timeoutMs,
//            attemptsFrequencyMs,
//            allowedExceptions
//    ) {
//        perform(action)
//    }
//}
//
//fun ViewInteraction.attempt(action: ViewAction) {
//    coreAttempt(
//            Configuration.attemptsTimeoutMs,
//            Configuration.attemptsFrequencyMs,
//            Configuration.allowedExceptionsForAttempt
//    ) {
//        perform(action)
//    }
//}
//
//fun ViewInteraction.attempt(
//        timeoutMs: Long = Configuration.attemptsTimeoutMs,
//        attemptsFrequencyMs: Long = Configuration.attemptsFrequencyMs,
//        allowedExceptions: Set<Class<out Throwable>> = Configuration.allowedExceptionsForAttempt,
//        assertion: ViewAssertion
//) {
//    coreAttempt(
//            timeoutMs,
//            attemptsFrequencyMs,
//            allowedExceptions
//    ) {
//        check(assertion)
//    }
//}
//
//fun ViewInteraction.attempt(assertion: ViewAssertion) {
//    coreAttempt(
//            Configuration.attemptsTimeoutMs,
//            Configuration.attemptsFrequencyMs,
//            Configuration.allowedExceptionsForAttempt
//    ) {
//        check(assertion)
//    }
//}