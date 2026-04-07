package com.kaspersky.components.alluresupport.interceptors.step

import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.StepInfo
import io.qameta.allure.kotlin.Allure
import io.qameta.allure.kotlin.model.Status
import io.qameta.allure.kotlin.model.StepResult
import java.util.Stack
import java.util.UUID

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

class AllureMapperStepInterceptor : StepWatcherInterceptor {

    private val lifecycle = Allure.lifecycle

    private val uuids: Stack<String> = Stack()

    override fun interceptBefore(stepInfo: StepInfo) {
        val uuid = UUID.randomUUID().toString()
        uuids.push(uuid)
        lifecycle.startStep(
            uuid,
            StepResult().also {
                it.name = stepInfo.description
            }
        )
    }

    override fun interceptAfterWithSuccess(stepInfo: StepInfo) {
        val current = uuids.peek()
        lifecycle.updateStep(current) {
            it.status = Status.PASSED
        }
    }

    override fun interceptAfterWithError(stepInfo: StepInfo, error: Throwable) {
        val current = uuids.peek()
        lifecycle.updateStep(current) {
            it.status = Status.FAILED
        }
    }

    override fun interceptAfterFinally(stepInfo: StepInfo) {
        val uuid = uuids.pop()
        lifecycle.stopStep(uuid)
    }
}
