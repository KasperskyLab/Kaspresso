package com.kaspersky.kaspresso.testcases.core.testassistants

import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.Params

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
 * Provider of test assistants allowed in [com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext],
 * [com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase] and their inheritors
 */
interface TestAssistantsProvider {

    val device: Device
    val adbServer: AdbServer
    val testLogger: UiTestLogger
    val params: Params
    val resourceFilesProvider: ResourceFilesProvider
}
