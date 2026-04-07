package com.kaspersky.kaspresso.compose

import com.kaspersky.kaspresso.compose.pack.branch.ComplexComposeBranch
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderGlobalImpl
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.logger.UiTestLogger
import io.reactivex.exceptions.ExtCompositeException

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

internal class ComposeExecutor(
    private val kaspresso: Kaspresso
) {

    private val failureLoggingProvider: FailureLoggingProvider =
        FailureLoggingProviderImpl(kaspresso.libLogger)

    private val flakySafetyProvider: FlakySafetyProvider =
        FlakySafetyProviderGlobalImpl(kaspresso)

    fun <Type> executeComposeBranches(
        timeoutMs: Long?,
        intervalMs: Long?,
        allowedExceptions: Set<Class<out Throwable>>?,
        composeBranches: List<ComplexComposeBranch<out Type>>
    ) {
        val composeChecks = composeBranches.map { it.check }
        val compositeAction = getCompositeAction(composeChecks, kaspresso.libLogger)

        val succeedBranchOrderNumber = failureLoggingProvider.withLoggingOnFailure {
            flakySafetyProvider.flakySafely(
                timeoutMs = timeoutMs,
                intervalMs = intervalMs,
                allowedExceptions = allowedExceptions,
                action = compositeAction
            )
        }

        // execution `then` section of a success branch
        composeBranches[succeedBranchOrderNumber].postAction?.invoke()
    }

    fun <Type> executeComposeBranchesUnsafely(
        composeBranches: List<ComplexComposeBranch<out Type>>
    ) {
        val composeChecks = composeBranches.map { it.check }
        val compositeAction = getCompositeAction(composeChecks, kaspresso.libLogger)

        val succeedBranchOrderNumber = compositeAction.invoke()

        // execution `then` section of a success branch
        composeBranches[succeedBranchOrderNumber].postAction?.invoke()
    }

    /**
     * Invokes the given list of [actions] and succeeds if at least one of them succeeds.
     *
     * @param actions the list of actions to invoke.
     * @param logger the logger
     *
     * @return the order number of a succeed branch
     */
    @Throws(ExtCompositeException::class)
    private fun getCompositeAction(
        actions: List<() -> Unit>,
        logger: UiTestLogger
    ): () -> Int = {
        logger.i("Composite action started.")
        var branchIndex = 0
        var success = false
        val cachedErrors: MutableList<Throwable> = mutableListOf()

        for (action in actions) {
            try {
                action.invoke()
                success = true
                logger.i("Composed action #$branchIndex succeeded.")
                break
            } catch (error: Throwable) {
                cachedErrors += error
                logger.e("Composed action #$branchIndex failed. The reason is ${error.javaClass.simpleName}")
                branchIndex++
            }
        }

        if (success) {
            branchIndex
        } else {
            logger.i("All composed actions totally failed.")
            throw ExtCompositeException(cachedErrors)
        }
    }
}
