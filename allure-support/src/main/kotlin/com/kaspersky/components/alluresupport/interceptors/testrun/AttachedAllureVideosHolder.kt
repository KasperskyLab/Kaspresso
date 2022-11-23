package com.kaspersky.components.alluresupport.interceptors.testrun

import java.io.File

/**
 * Used to store shared state used by multiple interceptors
 */
object AttachedAllureVideosHolder {
    private val _attachedVideos = mutableListOf<AttachedVideo>()
    val attachedVideos: List<AttachedVideo> = _attachedVideos

    fun rememberAttachedVideo(stubFile: File, actualFile: File, uuid: String) {
        val attachedVideo = AttachedVideo(attachedStubFile = stubFile, actualFile = actualFile, uuid = uuid)
        _attachedVideos.add(attachedVideo)
    }
}

/**
 * This model represents allure video attachment. It's used for screen recording workaround
 */
data class AttachedVideo(
    /**
     * Stub while which has been used to attach a video to report. Should be replaced with a real video file after moving
     * report to /sdcard
     */
    val attachedStubFile: File,
    /**
     * Actual screen record file which has been saved into /sdcard. Should be used to replace a stub in allure report
     */
    val actualFile: File,

    val uuid: String
)
