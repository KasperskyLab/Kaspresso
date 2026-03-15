package com.kaspersky.kaspresso.testcases.core.step

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
 * The interface to provide step info producing functionality.
 */
internal interface StepInfoProducer {

    /**
     * Produces correct step info. Only this function may produce a StepInfo!
     *
     * @param description what happens on this step.
     * @return [StepInfo] of this step. Info may be changed internally but framework users can not mutate it.
     */
    fun produceStepInfo(description: String): StepInfo

    /**
     * A callback function. It should be called after every step finished.
     *
     * @param stepInfo that this [StepInfoProducer] produces early.
     * @param error throwable witch happens on step or null.
     */
    fun onStepFinished(stepInfo: StepInfo, error: Throwable? = null)
}
