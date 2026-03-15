package com.kaspersky.components.alluresupport.interceptors.testrun

import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo
import com.kaspersky.components.alluresupport.files.attachLogcatToAllureReport
import com.kaspersky.kaspresso.device.logcat.dumper.LogcatDumper

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

class DumpLogcatTestInterceptor(
    private val logcatDumper: LogcatDumper
) : TestRunWatcherInterceptor {

    override fun onTestStarted(testInfo: TestInfo) {
        logcatDumper.charge()
    }

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        logcatDumper.dumpAndApply("TestLogcat") { attachLogcatToAllureReport() }
    }
}
