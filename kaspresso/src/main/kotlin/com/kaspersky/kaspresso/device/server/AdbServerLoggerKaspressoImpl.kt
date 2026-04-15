package com.kaspersky.kaspresso.device.server

import com.kaspersky.adbserver.common.log.logger.LogLevel
import com.kaspersky.adbserver.common.log.logger.Logger
import com.kaspersky.adbserver.common.log.utils.AdbLoggerReflection
import com.kaspersky.kaspresso.logger.UiTestLogger

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

internal class AdbServerLoggerKaspressoImpl(
    private val showLogLevel: LogLevel,
    private val logger: UiTestLogger
) : Logger {

    companion object {
        private const val TAG = "KASPRESSO_ADBSERVER"
        private const val CLASS = "class="
        private const val METHOD = "method="
        private const val MESSAGE = "message: "
    }

    override fun v(text: String) {
        callLog(logLevel = LogLevel.VERBOSE, originalText = text) { formattedText -> logger.d(TAG, formattedText) }
    }

    override fun d(text: String) {
        callLog(logLevel = LogLevel.DEBUG, originalText = text) { formattedText -> logger.d(TAG, formattedText) }
    }

    override fun i(text: String) {
        callLog(logLevel = LogLevel.INFO, originalText = text) { formattedText -> logger.i(TAG, formattedText) }
    }

    override fun w(text: String) {
        callLog(logLevel = LogLevel.WARN, originalText = text) { formattedText -> logger.w(TAG, formattedText) }
    }

    override fun e(text: String) {
        callLog(logLevel = LogLevel.ERROR, originalText = text) { formattedText -> logger.e(TAG, formattedText) }
    }

    private fun callLog(logLevel: LogLevel, originalText: String, logAction: (String) -> Unit) {
        if (logLevel < showLogLevel) {
            return
        }
        if (logLevel <= LogLevel.DEBUG) {
            val formattedText = "$CLASS${AdbLoggerReflection.getGeneratedClass()} " +
                    "$METHOD${AdbLoggerReflection.getGeneratedMethod()} $MESSAGE$originalText"
            logAction(formattedText)
            return
        }
        logAction(originalText)
    }
}
