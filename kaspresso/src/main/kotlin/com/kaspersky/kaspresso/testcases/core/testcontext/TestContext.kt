package com.kaspersky.kaspresso.testcases.core.testcontext

import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.internal.extensions.other.forEachSafely
import com.kaspersky.kaspresso.internal.extensions.other.invokeSafely
import com.kaspersky.kaspresso.internal.extensions.other.throwAll
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.step.StepInfoProducer
import com.kaspersky.kaspresso.testcases.models.info.StepInfo

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
 * The special class to operate with in user scenario.
 * Provides [step] and [scenario] methods in "run" section to build a test.
 *
 * @param Data data created in before section.
 */
class TestContext<Data> internal constructor(
    kaspresso: Kaspresso,
    private val stepInfoProducer: StepInfoProducer,
    val data: Data
) : BaseTestContext(kaspresso) {

    private val stepInterceptors: List<StepWatcherInterceptor> = kaspresso.stepWatcherInterceptors

    /**
     * The representation of a [TestContext]'s step.
     *
     * @param description a description of a step.
     * @param actions a set of actions of a step.
     */
    fun step(description: String, actions: (StepInfo) -> Unit) {
        val exceptions: MutableList<Throwable> = mutableListOf()
        val stepInfo = stepInfoProducer.produceStepInfo(description)

        stepInterceptors.forEachSafely(exceptions) { it.interceptBefore(stepInfo) }

        try {
            actions.invoke(stepInfo)
            stepInfoProducer.onStepFinished(stepInfo)

            stepInterceptors.forEach {
                invokeSafely(exceptions) { it.interceptAfterWithSuccess(stepInfo) }
                invokeSafely(exceptions) { it.interceptAfterFinally(stepInfo) }
            }
        } catch (throwable: Throwable) {
            stepInfoProducer.onStepFinished(stepInfo, throwable)
            stepInterceptors.forEach {
                invokeSafely(exceptions) { it.interceptAfterWithError(stepInfo, throwable) }
                invokeSafely(exceptions) { it.interceptAfterFinally(stepInfo) }
            }

            exceptions.add(throwable)
        }

        exceptions.throwAll()
    }

    /**
     * The representation of a composed [TestContext]'s steps.
     *
     * @param scenario the implementation of [BaseScenario].
     */
    fun scenario(scenario: BaseScenario<Data>) = scenario.invoke(this)
}
