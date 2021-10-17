package com.kaspersky.kaspresso.params

class VideoParams(
    val startRecordingTimeMs: Long = 3_000L,
    val stopRecordingTimeMs: Long = 2_000L,
    val bitRate: Int = 1_000_000
)
