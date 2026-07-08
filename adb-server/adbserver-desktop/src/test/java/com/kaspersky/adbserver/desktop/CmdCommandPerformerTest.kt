package com.kaspersky.adbserver.desktop

import com.google.common.truth.Truth.assertThat
import com.kaspersky.adbserver.common.api.ExecutorResultStatus
import com.kaspersky.adbserver.common.log.logger.Logger
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.condition.DisabledOnOs
import org.junit.jupiter.api.condition.OS

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

@DisabledOnOs(OS.WINDOWS)
class CmdCommandPerformerTest {

    private val performer = CmdCommandPerformer(
        desktopName = "test-desktop",
        logger = NoOpLogger,
        executionTimeoutSeconds = 5,
    )

    @Test
    @Timeout(30)
    fun `command with output larger than the os pipe buffer completes successfully`() {
        // ~170 KB of stdout: overflows the OS pipe buffer (64 KB on Linux by default)
        val result = performer.perform("seq 1 30000")

        assertThat(result.status).isEqualTo(ExecutorResultStatus.SUCCESS)
        assertThat(result.description).contains("30000")
    }

    @Test
    @Timeout(30)
    fun `command with error output larger than the os pipe buffer completes successfully`() {
        val result = performer.perform("sh", listOf("-c", "seq 1 30000 1>&2"))

        assertThat(result.status).isEqualTo(ExecutorResultStatus.SUCCESS)
    }

    @Test
    @Timeout(30)
    fun `failed command reports failure with the error stream content`() {
        val result = performer.perform("ls /nonexistent-path-for-cmd-command-performer-test")

        assertThat(result.status).isEqualTo(ExecutorResultStatus.FAILURE)
        assertThat(result.description).contains("nonexistent-path-for-cmd-command-performer-test")
    }

    @Test
    @Timeout(30)
    fun `command running longer than the execution timeout reports timeout`() {
        val result = performer.perform("sleep 60")

        assertThat(result.status).isEqualTo(ExecutorResultStatus.TIMEOUT)
        assertThat(result.description).contains("Command execution timeout (5 sec) overhead")
    }

    @Test
    @Timeout(60)
    fun `output larger than the capture limit is truncated but the command still succeeds`() {
        // ~12 MB of stdout: above the capture cap, the tail must be dropped
        // so that a runaway command cannot exhaust the desktop JVM heap
        val result = performer.perform("sh", listOf("-c", "yes captured-line | head -c 12000000"))

        assertThat(result.status).isEqualTo(ExecutorResultStatus.SUCCESS)
        assertThat(result.description.length).isLessThan(6_000_000)
        assertThat(result.description).contains("truncated")
    }

    @Test
    @Timeout(30)
    fun `timeout result contains the output captured before the timeout`() {
        val result = performer.perform("sh", listOf("-c", "echo started-marker; sleep 60"))

        assertThat(result.status).isEqualTo(ExecutorResultStatus.TIMEOUT)
        assertThat(result.description).contains("started-marker")
    }

    private object NoOpLogger : Logger {
        override fun v(text: String) = Unit
        override fun d(text: String) = Unit
        override fun i(text: String) = Unit
        override fun w(text: String) = Unit
        override fun e(text: String) = Unit
    }
}
