package com.kaspersky.kaspresso.device.logcat

import java.io.File

/**
 * The interface to work with logcat.
 */
interface Logcat {

    /**
     * NOT WORKING ON ANDROID 8+
     *
     * The problem: Android OS has a special introduced mechanism to filter and collapse of some bunches of logs produced by applications.
     * The name of the such mechanism is Chatty. Chatty turns on when an application writes a lot of logs.
     * The goal of Logcat interface is to analyze all logs. But Chatty prevents achievement of the mentioned goal.
     * That's why, there is this method to disable Chatty.
     * Please, call the method in "before" section of a test.
     */
    fun disableChatty()

    /**
     * Set new logcat buffer size
     *
     * @param size a LogcatBufferSize value
     */
    fun setBufferSize(size: LogcatBufferSize)

    /**
     * Set default logcat buffer size
     */
    fun setDefaultBufferSize()

    /**
     * Clear (flush) the selected buffers and exit.
     * The default buffer set is main, system and crash.
     *
     * @param buffer one of available logcat buffers
     */
    fun clear(buffer: Buffer = Buffer.DEFAULT)

    fun dumpLogcat(
        file: File,
        tags: List<String>? = null,
        timeFrom: String? = null,
        excludePattern: String? = null,
        excludePatternIsIgnoreCase: Boolean = false,
        includePattern: String? = null,
        includePatternIsIgnoreCase: Boolean = false,
        buffer: Buffer = Buffer.DEFAULT
    )

    /**
     * Get logcat dump as list of strings
     *
     * @param excludePattern logcat will EXCLUDE rows that match the pattern
     * @param excludePatternIsIgnoreCase boolean is exclude pattern must ignore string case
     * @param includePattern logcat will contains ONLY rows that match the pattern
     * @param includePatternIsIgnoreCase boolean is include pattern must ignore string case
     * @param buffer one of available logcat buffers
     * @param rowLimit limiter of logcat output, starts FROM BEGINNING of logcat dump
     * WITH EXTRA ROW of buffer beginning, if null return all rows
     *
     * @return [List<String>] with logcat rows
     */
    fun readLogcatRows(
        excludePattern: String? = null,
        excludePatternIsIgnoreCase: Boolean = false,
        includePattern: String? = null,
        includePatternIsIgnoreCase: Boolean = false,
        buffer: Buffer = Buffer.DEFAULT,
        rowLimit: Int? = null
    ): List<String>

    /**
     * Get logcat dump and analyze each row.
     * Logcat reading stops if analyzerBlock returns false on some row
     *
     * @param excludePattern logcat will EXCLUDE rows that match the pattern
     * @param excludePatternIsIgnoreCase boolean is exclude pattern must ignore string case
     * @param includePattern logcat will contains ONLY rows that match the pattern
     * @param includePatternIsIgnoreCase boolean is include pattern must ignore string case
     * @param buffer one of available logcat buffers
     * @param rowLimit limiter of logcat output, starts FROM BEGINNING of logcat dump
     * WITH EXTRA ROW of buffer beginning, if null return all rows
     * @param readingBlock lambda with String input and Boolean output. Invokes on
     * every readed logcat row. Stop reading logcat if lambda returns false. If you needed
     * all rows of log always return false
     */
    fun readLogcatRows(
        excludePattern: String? = null,
        excludePatternIsIgnoreCase: Boolean = false,
        includePattern: String? = null,
        includePatternIsIgnoreCase: Boolean = false,
        buffer: Buffer = Buffer.DEFAULT,
        rowLimit: Int? = null,
        readingBlock: (logcatRow: String) -> Boolean
    ): Boolean

    enum class Buffer(val bufferName: String) {
        /**
         * Represents logcat buffer names
         * https://developer.android.com/studio/command-line/logcat#alternativeBuffers
         */
        RADIO("radio"), // View the buffer that contains radio/telephony related messages.
        EVENTS("events"), // View the interpreted binary system event buffer messages.
        MAIN("main"), // View the main log buffer (default) does not contain system and crash log messages.
        SYSTEM("system"), // View the system log buffer (default).
        CRASH("crash"), // View the crash log buffer (default).
        ALL("all"), // View all buffers.
        DEFAULT("default") // Reports main, system, and crash buffers.
    }
}
