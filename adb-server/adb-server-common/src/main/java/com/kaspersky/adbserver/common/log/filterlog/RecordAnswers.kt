package com.kaspersky.adbserver.common.log.filterlog

import java.util.Deque

internal interface RecordAnswer

internal class RecordInProgress : RecordAnswer

internal data class ReadyRecord(
    /**
     * last log is at the first position
     */
    val recordingStack: Deque<LogData>,
    /**
     * The count of recordingStack
     */
    val countOfRecordingStack: Int = 0,
    /**
     * last log is at the first position
     */
    val remainedStack: Deque<LogData>
) : RecordAnswer
