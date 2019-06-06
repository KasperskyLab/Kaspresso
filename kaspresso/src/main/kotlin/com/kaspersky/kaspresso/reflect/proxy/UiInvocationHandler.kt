package com.kaspersky.kaspresso.reflect.proxy

import android.support.test.internal.runner.junit4.statement.UiThreadStatement
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.extensions.other.getStackTraceAsString
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.concurrent.CountDownLatch

/**
 *  [InvocationHandler] for dynamic proxy which will suppress any exception thrown from the target
 */
@PublishedApi
internal class UiInvocationHandler(
    private val target: Any,
    private val logger: UiTestLogger
) : InvocationHandler {

    override fun invoke(proxy: Any, method: Method, args: Array<Any>?): Any? {
        var result: Any? = null
        val latch = CountDownLatch(1)

        UiThreadStatement.runOnUiThread {
            try {
                result = method(target, *(args ?: emptyArray()))
            } catch (e: Exception) {
                val ex = if (e is InvocationTargetException) e.cause else e
                logger.e("Exception during proxy invocation: $ex, ${ex?.getStackTraceAsString()}")
            } finally {
                latch.countDown()
            }
        }
        latch.await()
        return result
    }
}
