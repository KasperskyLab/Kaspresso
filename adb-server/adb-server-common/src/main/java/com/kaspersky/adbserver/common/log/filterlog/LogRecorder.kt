package com.kaspersky.adbserver.common.log.filterlog

import java.util.ArrayDeque
import java.util.Deque

internal class LogRecorder(
    private val recordingStackMaxSize: Int
) {

    companion object {
        private const val UNBOUND_INDEX: Int = -1
    }

    private var forecastedPositionAtInitialStack: Int = UNBOUND_INDEX
    private var recordingStackSize: Int = 0
    // last log is at the first position
    private val recordingStack: Deque<LogData> = ArrayDeque()
    private var countOfRecordingStack: Int = 0
    private var state: State = State.NO_RECORDING

    /**
     * @param foundPosition index of last found logData
     * @param logData found LogData
     */
    @SuppressWarnings("detekt.ReturnCount")
    fun put(foundPosition: Int, logData: LogData): RecordAnswer {
        if (state == State.NO_RECORDING && foundPosition == UNBOUND_INDEX) {
            return getSingleUniqueRecord(logData)
        }
        if (state == State.NO_RECORDING) {
            startRecording(foundPosition)
        }
        if (foundPosition != forecastedPositionAtInitialStack) {
            val record = getRecord(logData)
            resetState()
            return record
        }
        addNewLogToStack(logData)
        if (countOfRecordingStack >= recordingStackMaxSize) {
            val record = getRecord(null)
            resetState()
            return record
        }
        return RecordInProgress()
    }

    private fun getSingleUniqueRecord(logData: LogData): ReadyRecord {
        val remainedStack: Deque<LogData> = ArrayDeque()
        remainedStack.addFirst(logData)
        return ReadyRecord(
            recordingStack = ArrayDeque(),
            countOfRecordingStack = 0,
            remainedStack = remainedStack
        )
    }

    private fun startRecording(foundPosition: Int) {
        state = State.RECORDING
        recordingStackSize = foundPosition + 1
        forecastedPositionAtInitialStack = recordingStackSize - 1
    }

    private fun getRecord(newLog: LogData? = null): RecordAnswer {
        val remainedStack: Deque<LogData> = ArrayDeque()
        newLog?.let { remainedStack.addFirst(newLog) }
        if (forecastedPositionAtInitialStack > UNBOUND_INDEX) {
            recordingStack
                .filterIndexed { index, _ -> index > forecastedPositionAtInitialStack }
                .forEachIndexed { _, logData -> remainedStack.addLast(logData) }
        }
        return ReadyRecord(
            recordingStack = ArrayDeque(recordingStack),
            countOfRecordingStack = countOfRecordingStack,
            remainedStack = remainedStack
        )
    }

    private fun resetState() {
        forecastedPositionAtInitialStack = UNBOUND_INDEX
        recordingStackSize = 0
        recordingStack.clear()
        countOfRecordingStack = 0
        state = State.NO_RECORDING
    }

    private fun addNewLogToStack(logData: LogData) {
        if (countOfRecordingStack == 0) {
            recordingStack.addFirst(logData)
        }
        forecastedPositionAtInitialStack--
        if (forecastedPositionAtInitialStack == UNBOUND_INDEX) {
            countOfRecordingStack++
            forecastedPositionAtInitialStack = recordingStackSize - 1
        }
    }

    private enum class State {
        RECORDING,
        NO_RECORDING
    }
}
