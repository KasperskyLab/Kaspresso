package com.kaspersky.kaspresso.device.logcat

/**
 * The interface to work with logcat.
 */
interface Logcat {

    val defaultBufferSize: String
    val printExecutedCommand: Boolean

    /**
     * Set new logcat buffer size
     *
     * @param size a string with size of buffer and dimension.
     * Can add K or M at the end to indicate kilobytes or megabytes.
     */
    fun setBufferSize(size: String)

    /**
     * Set default logcat buffer size
     */
    fun setDefaultBufferSize() = setBufferSize(defaultBufferSize)

    /**
     * Clear (flush) the selected buffers and exit.
     * The default buffer set is main, system and crash.
     *
     * @param buffer one of available logcat buffers
     */
    fun clear(buffer: Buffer = Buffer.DEFAULT)

    /**
     * Whitelisting all PIDs for disabling chatty
     * who can skip some rows when logging is very heavy
     */
    fun disableChatty()

    /**
     * Get logcat dump as list of strings
     *
     * @param excludePattern logcat will EXCLUDE rows that match the pattern
     * @param excludeIgnoreCase boolean is exclude pattern must ignore string case
     * @param includePattern logcat will contains ONLY rows that match the pattern
     * @param includeIgnoreCase boolean is include pattern must ignore string case
     * @param buffer one of available logcat buffers
     * @param rowLimit limiter of logcat output, starts FROM BEGINNING of logcat dump
     * with extra row of buffer beginning, if null return all rows
     *
     * @return [List<String>] with logcat rows
     */
    fun readLogcatRows(
        excludePattern: String? = null,
        excludeIgnoreCase: Boolean = false,
        includePattern: String? = null,
        includeIgnoreCase: Boolean = false,
        buffer: Buffer = Buffer.DEFAULT,
        rowLimit: Int? = null
    ): List<String>

    /**
     * Get logcat dump and analyze each row.
     * Logcat reading stops if analyzerBlock returns false on some row
     *
     * @param excludePattern logcat will EXCLUDE rows that match the pattern
     * @param excludeIgnoreCase boolean is exclude pattern must ignore string case
     * @param includePattern logcat will contains ONLY rows that match the pattern
     * @param includeIgnoreCase boolean is include pattern must ignore string case
     * @param buffer one of available logcat buffers
     * @param rowLimit limiter of logcat output, starts FROM BEGINNING of logcat dump
     * with extra row of buffer beginning, if null return all rows
     * @param analyzerBlock lambda with String input and Boolean output. Invokes on
     * every readed logcat row. Stop reading logcat if lambda returns false. If you needed
     * all rows of log always return false
     */
    fun readLogcatRows(
        excludePattern: String? = null,
        excludeIgnoreCase: Boolean = false,
        includePattern: String? = null,
        includeIgnoreCase: Boolean = false,
        buffer: Buffer = Buffer.DEFAULT,
        rowLimit: Int? = null,
        analyzerBlock: (logcatRow: String) -> Boolean
    ): Boolean

    enum class Buffer(val bufferName: String) {
        RADIO("radio"), // View the buffer that contains radio/telephony related messages.
        EVENTS("events"), // View the interpreted binary system event buffer messages.
        MAIN("main"), // View the main log buffer (default) does not contain system and crash log messages.
        SYSTEM("system"), // View the system log buffer (default).
        CRASH("crash"), // View the crash log buffer (default).
        ALL("all"), // View all buffers.
        DEFAULT("default") // Reports main, system, and crash buffers.
    }
}