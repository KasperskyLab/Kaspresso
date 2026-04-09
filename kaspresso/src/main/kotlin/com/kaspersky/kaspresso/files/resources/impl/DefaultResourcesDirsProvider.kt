package com.kaspersky.kaspresso.files.resources.impl

import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.files.extensions.findTestMethod
import com.kaspersky.kaspresso.files.models.TestMethod
import com.kaspersky.kaspresso.files.resources.ResourcesDirNameProvider
import com.kaspersky.kaspresso.files.resources.ResourcesDirsProvider
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

class DefaultResourcesDirsProvider(
    private val dirsProvider: DirsProvider,
    private val resourcesDirNameProvider: ResourcesDirNameProvider,
    private val testThread: Thread = Thread.currentThread()
) : ResourcesDirsProvider {

    override fun provide(dest: File, subDir: String?, provideCleared: Boolean): File {
        val rootDir: File = dirsProvider.provideNew(dest)
        val resourcesDest: File = resolveResourcesDirDest(rootDir, subDir)
        return if (provideCleared) {
            dirsProvider.provideCleared(resourcesDest)
        } else {
            resourcesDest.createDirIfNeeded()
        }
    }

    private fun resolveResourcesDirDest(rootDir: File, subDir: String? = null): File {

        return rootDir.run {
            var resourceDir = this
            val testMethod: TestMethod? = testThread.stackTrace.findTestMethod()

            subDir?.let { resourceDir = resourceDir.resolve(it) }
            testMethod?.let {
                val resourcesDirName: String = resourcesDirNameProvider.provideResourcesDirName(testMethod)
                resourceDir = resourceDir.resolve(resourcesDirName)
            }

            resourceDir
        }
    }
}
