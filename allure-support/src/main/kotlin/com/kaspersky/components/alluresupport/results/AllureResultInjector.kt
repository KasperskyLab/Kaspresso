package com.kaspersky.components.alluresupport.results

import androidx.test.uiautomator.UiDevice
import java.io.File

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
