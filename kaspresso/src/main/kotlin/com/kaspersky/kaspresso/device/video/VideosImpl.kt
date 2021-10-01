package com.kaspersky.kaspresso.device.video

import com.kaspersky.kaspresso.device.video.recorder.VideoRecorder
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import java.io.File

class VideosImpl(
    private val resourceFilesProvider: ResourceFilesProvider,
    private val videoRecorder: VideoRecorder
) : Videos {

    override fun record(tag: String) {
        val videoFile: File = resourceFilesProvider.provideVideoFile(tag)
        videoRecorder.start(videoFile)
    }

    override fun save() {
        videoRecorder.stop()
    }

    override fun saveAndApply(block: File.() -> Unit) {
        videoRecorder.stop()?.apply { block(this) }
    }
}
