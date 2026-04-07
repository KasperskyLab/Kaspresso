package com.kaspersky.kaspresso.device.logcat.dumper

import android.util.Log
import com.kaspersky.kaspresso.device.logcat.Logcat
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

class LogcatDumperImpl(
    private val logger: UiTestLogger,
    private val resourceFilesProvider: ResourceFilesProvider,
    private val logcat: Logcat,
    private val loggerTags: List<String>
) : LogcatDumper {

    private val dateTimeFormat = SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.getDefault())
    private var timeDumpFrom: String? = null

    override fun charge() {
        timeDumpFrom = dateTimeFormat.format(Date())
        logger.i("Logcat buffer may be dumped from $timeDumpFrom")
    }

    override fun dump(tag: String): Unit = doDump(tag, null)

    override fun dumpAndApply(tag: String, block: File.() -> Unit): Unit = doDump(tag, block)

    private fun doDump(tag: String, block: (File.() -> Unit)?) {
        try {
            val logcatFile: File = resourceFilesProvider.provideLogcatFile(tag)
            logcat.dumpLogcat(
                file = logcatFile,
                tags = loggerTags,
                timeFrom = timeDumpFrom
            )
            block?.invoke(logcatFile)
        } catch (e: Throwable) {
            logger.e("Logcat dumping error occurred: ${Log.getStackTraceString(e)}")
        }
    }
}
