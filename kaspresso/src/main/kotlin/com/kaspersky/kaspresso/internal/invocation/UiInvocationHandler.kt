package com.kaspersky.kaspresso.internal.invocation

import android.annotation.SuppressLint
import android.util.Log
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.concurrent.CountDownLatch

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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
