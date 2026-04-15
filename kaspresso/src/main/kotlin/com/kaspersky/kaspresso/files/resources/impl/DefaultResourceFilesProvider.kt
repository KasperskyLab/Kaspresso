package com.kaspersky.kaspresso.files.resources.impl

import com.kaspersky.kaspresso.files.extensions.FileExtension
import com.kaspersky.kaspresso.files.resources.ResourceFileNamesProvider
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import com.kaspersky.kaspresso.files.resources.ResourcesDirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
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

class DefaultResourceFilesProvider(
    private val resourcesRootDirsProvider: ResourcesRootDirsProvider,
    private val resourcesDirsProvider: ResourcesDirsProvider,
    private val resourceFileNamesProvider: ResourceFileNamesProvider
) : ResourceFilesProvider {

    override fun provideLogcatFile(tag: String, subDir: String?): File {
        val resFileName: String = resourceFileNamesProvider.getFileName(
            tag,
            FileExtension.TXT.toString()
        )
        return resourcesDirsProvider.provide(
            resourcesRootDirsProvider.logcatRootDir,
            subDir
        ).resolve(resFileName)
    }

    override fun provideScreenshotFile(tag: String, subDir: String?): File {
        val resFileName: String = resourceFileNamesProvider.getFileName(
            tag,
            FileExtension.PNG.toString()
        )
        return resourcesDirsProvider.provide(
            resourcesRootDirsProvider.screenshotsRootDir,
            subDir
        ).resolve(resFileName)
    }

    override fun provideVideoFile(tag: String, subDir: String?): File {
        val resFileName: String = resourceFileNamesProvider.getFileName(
            tag,
            FileExtension.MP4.toString()
        )

        return resourcesDirsProvider.provide(resourcesRootDirsProvider.videoRootDir, subDir)
            .resolve(resFileName)
            .createFileIfNeeded()
    }

    override fun provideViewHierarchyFile(tag: String, subDir: String?): File {
        val resFileName: String = resourceFileNamesProvider.getFileName(
            tag,
            FileExtension.XML.toString()
        )
        return resourcesDirsProvider.provide(
            resourcesRootDirsProvider.viewHierarchy,
            subDir
        ).resolve(resFileName)
    }
}
