package com.kaspersky.kaspresso.internal.invocation

import android.annotation.SuppressLint
import android.util.Log
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.concurrent.CountDownLatch

/**
 * The [InvocationHandler] implementation for dynamic proxy which will suppress any exception thrown from the target.
 */
@PublishedApi
internal class UiInvocationHandler(
    private val target: Any,
    private val logger: UiTestLogger
) : InvocationHandler {

    @SuppressLint("RestrictedApi")
    override fun invoke(proxy: Any, method: Method, args: Array<Any>?): Any? {
        var result: Any? = null
        val latch = CountDownLatch(1)

        UiThreadStatement.runOnUiThread {
            try {
                @Suppress("SpreadOperator")
                result = method(target, *(args ?: emptyArray()))
            } catch (e: Exception) {
                val ex = if (e is InvocationTargetException) e.cause else e
                logger.e("Exception during proxy invocation: $ex, ${Log.getStackTraceString(ex)}")
            } finally {
                latch.countDown()
            }
        }

        latch.await()
        return result
    }
}
