package com.kaspersky.kaspresso.device.viewhierarchy

import android.util.Log
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File

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

class ViewHierarchyDumperImpl(
    private val instrumentalDependencyProvider: InstrumentalDependencyProvider,
    private val logger: UiTestLogger,
    private val resourceFilesProvider: ResourceFilesProvider
) : ViewHierarchyDumper {

    private val device: UiDevice
        get() = instrumentalDependencyProvider.uiDevice

    override fun dump(tag: String): Unit = doDumpAndApply(tag, null)

    override fun dumpAndApply(tag: String, block: File.() -> Unit): Unit = doDumpAndApply(tag, block)

    private fun doDumpAndApply(tag: String, block: (File.() -> Unit)?) {
        try {
            val viewHierarchyFile: File = resourceFilesProvider.provideViewHierarchyFile(tag)
            device.dumpWindowHierarchy(viewHierarchyFile)
            block?.invoke(viewHierarchyFile)
            logger.i("View hierarchy dumped to $viewHierarchyFile")
        } catch (e: Throwable) {
            logger.e("View hierarchy dumping error occurred: ${Log.getStackTraceString(e)}")
        }
    }
}
