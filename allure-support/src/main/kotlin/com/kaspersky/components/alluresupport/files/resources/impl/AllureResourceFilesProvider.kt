package com.kaspersky.components.alluresupport.files.resources.impl

import com.kaspersky.components.alluresupport.files.resources.AllureResourcesRootDirsProvider
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import com.kaspersky.kaspresso.files.resources.ResourcesDirsProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourceFilesProvider
import com.kaspersky.kaspresso.internal.extensions.other.createFileIfNeeded
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

class AllureResourceFilesProvider(
    defaultResourceFilesProvider: DefaultResourceFilesProvider,
    private val resourcesRootDirsProvider: AllureResourcesRootDirsProvider,
    private val resourcesDirsProvider: ResourcesDirsProvider
) : ResourceFilesProvider by defaultResourceFilesProvider {
    /**
     * Used for allure report video attachment workaround. Creates stub video file in package private directory so allure could attach it to report
     * @param actualVideoFile video file saved by screen recorder
     * @return stub video file under /data/data
     */
    fun provideStubVideoFile(actualVideoFile: File): File {
        val resFileName: String = actualVideoFile.name
        return resourcesDirsProvider.provide(resourcesRootDirsProvider.stubVideoDir)
            .resolve(resFileName)
            .createFileIfNeeded()
    }
}
