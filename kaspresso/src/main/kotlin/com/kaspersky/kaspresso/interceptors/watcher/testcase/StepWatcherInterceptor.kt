package com.kaspersky.kaspresso.interceptors.watcher.testcase

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
 * The interface for all interceptors intercepting step events.
 */
interface StepWatcherInterceptor {

    fun interceptBefore(stepInfo: StepInfo) = Unit

    fun interceptAfterWithSuccess(stepInfo: StepInfo) = Unit

    fun interceptAfterWithError(stepInfo: StepInfo, error: Throwable) = Unit

    fun interceptAfterFinally(stepInfo: StepInfo) = Unit
}
