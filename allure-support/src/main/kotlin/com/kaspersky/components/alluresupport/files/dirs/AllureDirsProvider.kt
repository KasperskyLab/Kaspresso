package com.kaspersky.components.alluresupport.files.dirs

import android.annotation.SuppressLint
import android.app.Instrumentation
import com.kaspersky.kaspresso.files.dirs.DefaultDirsProvider
import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.internal.extensions.other.createDirIfNeeded
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

class AllureDirsProvider(
    private val defaultDirsProvider: DefaultDirsProvider,
    private val instrumentation: Instrumentation,
    private val resourcesRootDirsProvider: ResourcesRootDirsProvider,
) : DirsProvider by defaultDirsProvider {

    @SuppressLint("WorldReadableFiles", "ObsoleteSdkInt")
    override fun provideNew(dest: File): File {
        if (isRealVideoDir(dest)) { // screen recorder can't record to /data/data
            return provideNewOnSdCard(dest)
        }

        return instrumentation.targetContext.applicationContext.filesDir
            .resolve(dest)
            .createDirIfNeeded()
    }

    fun provideNewOnSdCard(dest: File): File = defaultDirsProvider.provideNew(dest)

    private fun isRealVideoDir(dest: File): Boolean {
        return dest == resourcesRootDirsProvider.videoRootDir
    }
}
