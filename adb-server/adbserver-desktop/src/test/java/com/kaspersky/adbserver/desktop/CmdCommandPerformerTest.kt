package com.kaspersky.adbserver.desktop

import com.kaspersky.adbserver.common.api.ExecutorResultStatus
import com.kaspersky.adbserver.common.log.logger.Logger
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

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

class CmdCommandPerformerTest {

    private val logger = object : Logger {
        override fun v(text: String) = Unit
        override fun d(text: String) = Unit
        override fun i(text: String) = Unit
        override fun w(text: String) = Unit
        override fun e(text: String) = Unit
    }

    @Test
    fun perform_largeStdout_completesSuccessfully() {
        val performer = CmdCommandPerformer("test-desktop", logger, executionTimeoutSeconds = 5)

        val result = performer.perform("seq 1 30000")

        assertEquals(ExecutorResultStatus.SUCCESS, result.status)
        assertTrue(result.description.contains("30000"))
    }
}
