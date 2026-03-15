package com.kaspersky.adbserver.common.log.filterlog

import java.util.ArrayDeque
import java.util.Deque

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
