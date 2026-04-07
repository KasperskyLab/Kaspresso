package com.kaspersky.adbserver.common.log

import com.kaspersky.adbserver.common.log.filterlog.FullLoggerOptimiser
import com.kaspersky.adbserver.common.log.fulllogger.FullLoggerSystemImpl
import com.kaspersky.adbserver.common.log.logger.DesktopLogger
import com.kaspersky.adbserver.common.log.logger.LogLevel
import com.kaspersky.adbserver.common.log.logger.Logger
import com.kaspersky.adbserver.common.log.logger.LoggerImpl

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
 * The singleton to provide Logger interface and to hide an implementation
 */
object LoggerFactory {

    fun getDesktopLogger(logLevel: LogLevel, desktopName: String): DesktopLogger {
        val logger = getCommonLogger(logLevel, desktopName)
        return DesktopLogger(logger, logLevel, desktopName)
    }

    fun getDesktopLoggerReflectingDevice(desktopLogger: DesktopLogger, deviceName: String): Logger {
        val logLevel = desktopLogger.logLevel
        val desktopName = desktopLogger.desktopName
        return getCommonLogger(logLevel, desktopName, deviceName)
    }

    fun getDeviceLogger(logLevel: LogLevel): Logger =
        getCommonLogger(logLevel)

    private fun getCommonLogger(logLevel: LogLevel, desktopName: String? = null, deviceName: String? = null): Logger {
        val fullLogger = FullLoggerSystemImpl(logLevel, desktopName, deviceName)
        val fullLoggerWrapper =
            if (logLevel == LogLevel.DEBUG) FullLoggerOptimiser(originalFullLogger = fullLogger, generateLogs = true) else fullLogger
        return LoggerImpl(fullLoggerWrapper)
    }
}
