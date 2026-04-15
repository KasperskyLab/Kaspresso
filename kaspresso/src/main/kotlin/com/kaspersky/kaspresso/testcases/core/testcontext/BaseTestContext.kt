package com.kaspersky.kaspresso.testcases.core.testcontext

import com.kaspersky.kaspresso.compose.ComposeProvider
import com.kaspersky.kaspresso.compose.ComposeProviderImpl
import com.kaspersky.kaspresso.compose.WebComposeProvider
import com.kaspersky.kaspresso.compose.WebComposeProviderImpl
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.flakysafety.ContinuouslyProvider
import com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderGlobalImpl
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.testcases.core.testassistants.TestAssistantsProvider
import com.kaspersky.kaspresso.testcases.core.testassistants.TestAssistantsProviderImpl

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
 * Provides the Kaspresso functionality for "run" section: [Device], [AdbServer], the [UiTestLogger] implementation
 * for external developers. Also provides flaky safety, composing and web composing functionalities via
 * implementing [FlakySafetyProvider], [ComposeProvider] and [WebComposeProvider] interfaces.
 */
open class BaseTestContext internal constructor(
    kaspresso: Kaspresso
) : FlakySafetyProvider by FlakySafetyProviderGlobalImpl(kaspresso),
    ContinuouslyProvider by ContinuouslyProviderImpl(kaspresso),
    ComposeProvider by ComposeProviderImpl(kaspresso),
    WebComposeProvider by WebComposeProviderImpl(kaspresso),
    TestAssistantsProvider by TestAssistantsProviderImpl(kaspresso)
