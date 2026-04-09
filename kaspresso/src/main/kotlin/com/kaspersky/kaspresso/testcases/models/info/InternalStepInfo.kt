package com.kaspersky.kaspresso.testcases.models.info

import com.kaspersky.kaspresso.testcases.models.StepStatus

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

internal class InternalStepInfo(
    override val description: String,
    override val testClassName: String,
    override val number: String?,
    override val ordinal: Int,
    override val startTime: Long,
    // position on each level of step hierarchy
    val stepNumber: MutableList<Int>?,
    // internal mutable properties to hide mutability from users
    val parentStepInfo: InternalStepInfo? = null,
    val internalSubStepInfos: MutableList<InternalStepInfo> = mutableListOf(),
    var internalStatus: StepStatus = StepStatus.STARTED,
    var internalThrowable: Throwable? = null,
    var internalStopTime: Long = -1L
) : StepInfo {

    override val subSteps: List<StepInfo>
        get() = internalSubStepInfos

    override val status: StepStatus
        get() = internalStatus

    override val throwable: Throwable?
        get() = internalThrowable

    override val stopTime: Long
        get() = internalStopTime

    override fun toString(): String {
        return "StepInfo(" +
                "description=$description, " +
                "testClassName=$testClassName, " +
                "number=$number, " +
                "ordinal=$ordinal, " +
                "stepNumber=$stepNumber, " +
                "subSteps=$internalSubStepInfos" +
                "startTime=$startTime" +
                "stopTime=$internalStopTime" +
                ")"
    }
}
