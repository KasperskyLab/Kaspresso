package com.kaspersky.kaspresso.device.video

import com.kaspersky.kaspresso.device.video.recorder.VideoRecorder
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File

class VideosImpl(
    private val logger: UiTestLogger,
    private val resourceFilesProvider: ResourceFilesProvider,
    private val videoRecorder: VideoRecorder
) : Videos {

    override fun record(tag: String) {
        val videoFile: File = resourceFilesProvider.provideVideoFile(tag)
        videoRecorder.start(videoFile)
    }

    override fun save() {
        videoRecorder.stop()?.also { videoFile: File ->
            logger.i("Video saved to $videoFile")
        }
    }

    override fun saveAndApply(block: File.() -> Unit) {
        videoRecorder.stop()?.also { videoFile: File ->
            block(videoFile)
            logger.i("Video saved to $videoFile")
        }
    }
}
