package com.kaspersky.components.alluresupport.results

import androidx.test.uiautomator.UiDevice
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

class AllureResultInjector(
    private val parser: AllureResultJsonParser,
    private val uiDevice: UiDevice,
    private val resultsDir: File
) {
    fun injectVideos(resultsDir: File, videoBindings: List<AllureResultsHack.VideoBinding>) {
        videoBindings.forEach { binding: AllureResultsHack.VideoBinding ->
            val aggregatedResult: File = resultsDir.getResultFileFor(binding.testUuid)
            val videoResult: File = aggregatedResult.getVideoResultFileFor(binding.stub)
            videoResult.replaceWith(binding.actual)
        }
    }

    private fun File.getResultFileFor(testUuid: String): File {
        val resultFileName = "$testUuid-result.json"
        val resultFile: File = resolve(resultFileName)
        if (!resultFile.exists()) {
            throw IllegalStateException(
                "The corresponding \"$resultFileName\" file not found in ${resultFile.absolutePath}"
            )
        }
        return resultFile
    }

    private fun File.getVideoResultFileFor(stubVideo: File): File {
        val videoResultFileName: String? = parser.getAttachmentSourceFileName(this, stubVideo.name)
        val videoResultFile: File? = videoResultFileName?.let(resultsDir::resolve)
        if (videoResultFile?.exists() != true) {
            throw IllegalStateException(
                "The corresponding video result file for ${stubVideo.name} not found in ${resultsDir.absolutePath}"
            )
        }
        return videoResultFile
    }

    private fun File.replaceWith(actualVideo: File) {
        uiDevice.executeShellCommand("cp ${actualVideo.absolutePath} $this")
    }
}
