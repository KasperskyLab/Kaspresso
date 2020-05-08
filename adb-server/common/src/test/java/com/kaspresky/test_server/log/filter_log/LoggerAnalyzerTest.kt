package com.kaspresky.test_server.log.filter_log

import com.google.common.truth.Truth.assertThat
import com.kaspresky.test_server.log.full_logger.FullLogger
import org.junit.jupiter.api.Test

class LoggerAnalyzerTest {

    private val fullLoggerTest = FullLoggerForTest()
    private val loggerAnalyzer = FullLoggerOptimiser(
        originalFullLogger = fullLoggerTest,
        recordingStackMaxSize = 5
    )

    @Test
    fun check_common_case_and_brake_of_sequence() {
        fullLoggerTest.list.clear()
        // start
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text2")
        loggerAnalyzer.log(text = "text3")
        loggerAnalyzer.log(tag = "tag1", text = "text4")
        loggerAnalyzer.log(tag = "tag2", text = "text5")
        loggerAnalyzer.log(tag = "tag1", text = "text5")
        // repeated part
        loggerAnalyzer.log(text = "text6")
        loggerAnalyzer.log(text = "text7")
        loggerAnalyzer.log(text = "text8")
        loggerAnalyzer.log(text = "text6")
        loggerAnalyzer.log(text = "text7")
        loggerAnalyzer.log(text = "text8")
        loggerAnalyzer.log(text = "text6")
        loggerAnalyzer.log(text = "text7")
        // end
        loggerAnalyzer.log(tag = "tag2", text = "text8")
        loggerAnalyzer.log(tag = "tag2", text = "text7")
        // verify
        val outputLogs = fullLoggerTest.list
        assertThat(outputLogs[0]).isEqualTo("text1")
        assertThat(outputLogs[1]).isEqualTo("text2")
        assertThat(outputLogs[2]).isEqualTo("text3")
        assertThat(outputLogs[3]).isEqualTo("tag1text4")
        assertThat(outputLogs[4]).isEqualTo("tag2text5")
        assertThat(outputLogs[5]).isEqualTo("tag1text5")
        assertThat(outputLogs[6]).isEqualTo("text6")
        assertThat(outputLogs[7]).isEqualTo("text7")
        assertThat(outputLogs[8]).isEqualTo("text8")
        assertThat(outputLogs[9]).contains("REPEATED 1 TIMES")
        assertThat(outputLogs[10]).isEqualTo("text6")
        assertThat(outputLogs[11]).isEqualTo("text7")
        assertThat(outputLogs[12]).isEqualTo("text8")
        assertThat(outputLogs[13]).contains("/")
        assertThat(outputLogs[14]).isEqualTo("text6")
        assertThat(outputLogs[15]).isEqualTo("text7")
        assertThat(outputLogs[16]).isEqualTo("tag2text8")
        assertThat(outputLogs[17]).isEqualTo("tag2text7")
        assertThat(outputLogs).hasSize(18)
    }

    @Test
    fun check_recording_of_one_line() {
        fullLoggerTest.list.clear()
        // logs
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text4")
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text6")
        // verify
        val outputLogs = fullLoggerTest.list
        assertThat(outputLogs[0]).isEqualTo("text1")
        assertThat(outputLogs[1]).contains("REPEATED 2 TIMES")
        assertThat(outputLogs[2]).isEqualTo("text1")
        assertThat(outputLogs[3]).contains("/")
        assertThat(outputLogs[4]).isEqualTo("text4")
        assertThat(outputLogs[5]).isEqualTo("text1")
        assertThat(outputLogs[6]).contains("REPEATED 1 TIMES")
        assertThat(outputLogs[7]).isEqualTo("text1")
        assertThat(outputLogs[8]).contains("/")
        assertThat(outputLogs[9]).isEqualTo("text6")
        assertThat(outputLogs).hasSize(10)
    }

    @Test
    fun check_no_recordings_and_all_params() {
        fullLoggerTest.list.clear()
        // logs
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(logType = FullLogger.LogType.INFO, text = "text1")
        loggerAnalyzer.log(logType = FullLogger.LogType.DEBUG, text = "text1")
        loggerAnalyzer.log(logType = FullLogger.LogType.INFO, deviceName = "device1", text = "text1")
        loggerAnalyzer.log(logType = FullLogger.LogType.INFO, deviceName = "device1", tag = "tag1", text = "text1")
        loggerAnalyzer.log(logType = FullLogger.LogType.INFO, deviceName = "device1", tag = "tag1", method = "method1", text = "text1")
        loggerAnalyzer.log(logType = FullLogger.LogType.INFO, deviceName = "device1", tag = "tag2", method = "method1", text = "text1")
        loggerAnalyzer.log(logType = FullLogger.LogType.INFO, deviceName = "device1", tag = "tag1", method = "method2", text = "text1")
        // verify
        val outputLogs = fullLoggerTest.list
        assertThat(outputLogs[0]).isEqualTo("text1")
        assertThat(outputLogs[1]).isEqualTo("INFOtext1")
        assertThat(outputLogs[2]).isEqualTo("DEBUGtext1")
        assertThat(outputLogs[3]).isEqualTo("INFOdevice1text1")
        assertThat(outputLogs[4]).isEqualTo("INFOdevice1tag1text1")
        assertThat(outputLogs[5]).isEqualTo("INFOdevice1tag1method1text1")
        assertThat(outputLogs[6]).isEqualTo("INFOdevice1tag2method1text1")
        assertThat(outputLogs[7]).isEqualTo("INFOdevice1tag1method2text1")
        assertThat(outputLogs).hasSize(8)
    }

    @Test
    fun check_continuous_recording() {
        fullLoggerTest.list.clear()
        // logs
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text2")
        loggerAnalyzer.log(text = "text2")
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text2")
        loggerAnalyzer.log(text = "text2")
        loggerAnalyzer.log(text = "text2")
        // verify
        val outputLogs = fullLoggerTest.list
        assertThat(outputLogs[0]).isEqualTo("text1")
        assertThat(outputLogs[1]).isEqualTo("text2")
        assertThat(outputLogs[2]).contains("REPEATED 1 TIMES")
        assertThat(outputLogs[3]).isEqualTo("text2")
        assertThat(outputLogs[4]).contains("/")
        assertThat(outputLogs[5]).isEqualTo("text1")
        assertThat(outputLogs[6]).isEqualTo("text2")
        assertThat(outputLogs).hasSize(7)
        // we are waiting for the end of "text2" sequence that's why last logs has not been outputted yet
    }

    @Test
    fun check_recording_with_max_size() {
        fullLoggerTest.list.clear()
        // logs
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text2")
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text1")
        loggerAnalyzer.log(text = "text1")
        // verify
        val outputLogs = fullLoggerTest.list
        assertThat(outputLogs[0]).isEqualTo("text1")
        assertThat(outputLogs[1]).contains("REPEATED 5 TIMES")
        assertThat(outputLogs[2]).isEqualTo("text1")
        assertThat(outputLogs[3]).contains("/")
        assertThat(outputLogs[4]).isEqualTo("text2")
        assertThat(outputLogs[5]).isEqualTo("text1")
        assertThat(outputLogs[6]).contains("REPEATED 5 TIMES")
        assertThat(outputLogs[7]).isEqualTo("text1")
        assertThat(outputLogs[8]).contains("/")
        assertThat(outputLogs).hasSize(9)
    }
}