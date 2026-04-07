package com.kaspersky.kaspresso.internal.runlisteners.artifactspull

import androidx.test.platform.app.InstrumentationRegistry
import com.kaspersky.kaspresso.device.files.Files
import com.kaspersky.kaspresso.files.dirs.DefaultDirsProvider
import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProviderFactory
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.logger.Logger
import com.kaspersky.kaspresso.params.ArtifactsPullParams
import com.kaspersky.kaspresso.runner.listener.KaspressoRunListener
import org.junit.runner.Result
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

class ArtifactsPullRunListener(
    private val params: ArtifactsPullParams,
    private val dirsProvider: DirsProvider = DefaultDirsProvider(InstrumentalDependencyProviderFactory().getComponentProvider<Kaspresso>(InstrumentationRegistry.getInstrumentation())),
    private val files: Files,
    private val logger: Logger
) : KaspressoRunListener {
    override fun testRunFinished(result: Result) {
        if (!params.enabled) return

        val rootDir = dirsProvider.provideNew(File(""))
        val filesInRootDir = rootDir.listFiles()
        if (filesInRootDir.isNullOrEmpty()) {
            logger.d("After test artifacts pulling abort: found no files to move")
            return
        }

        logger.d("After test artifacts pulling started. Root dir=${rootDir.absolutePath}; artifacts regex=${params.artifactsRegex}; destination path=${params.destinationPath}")
        filesInRootDir.forEach { file ->
            try {
                if (file.name.matches(params.artifactsRegex)) {
                    val fullFilePath = File(rootDir, file.name)
                    files.pull(
                        devicePath = fullFilePath.absolutePath,
                        serverPath = params.destinationPath
                    )
                }
            } catch (ex: Throwable) {
                logger.e("Failed to move file $file due to exception")
                logger.e(ex.stackTraceToString())
            }
        }
        logger.d("After test artifacts pulling finished")
    }
}
