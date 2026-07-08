package com.kaspersky.kaspresso.device.server

import com.google.common.truth.Truth.assertThat
import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.common.api.ExecutorResultStatus
import com.kaspersky.adbserver.common.log.logger.LogLevel
import com.kaspersky.adbserver.device.AdbTerminal
import com.kaspersky.kaspresso.internal.exceptions.AdbServerException
import com.kaspersky.kaspresso.logger.UiTestLogger
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.runs
import io.mockk.unmockkObject
import org.junit.After
import org.junit.Assert.assertThrows
import org.junit.Before
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

class AdbServerImplTest {

    private val logger = mockk<UiTestLogger>(relaxed = true)
    private val adbServer = AdbServerImpl(LogLevel.WARN, logger)

    @Before
    fun setUp() {
        mockkObject(AdbTerminal)
        every { AdbTerminal.connect(any(), any()) } just runs
    }

    @After
    fun tearDown() {
        unmockkObject(AdbTerminal)
    }

    @Test
    fun `timeout exception message contains the description of the command result`() {
        val timeoutResult = CommandResult(
            status = ExecutorResultStatus.TIMEOUT,
            description = "Command execution timeout (120 sec) overhead"
        )
        every { AdbTerminal.executeCmd(any(), any()) } returns timeoutResult

        val exception = assertThrows(AdbServerException::class.java) {
            adbServer.performCmd("ls -A1 /some/dir", emptyList())
        }

        assertThat(exception.message).contains("Command execution timeout (120 sec) overhead")
    }
}
