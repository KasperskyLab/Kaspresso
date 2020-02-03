package com.kaspersky.kaspresso.device.logcat

/**
 * The interface to work with logcat.
 */
interface Logcat {

    val defaultBufferSize: String

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
     * @param rowLimit limiter of logcat output, if null return all rows
     */
    fun readLogcatRows(
        excludePattern: String? = null,
        excludeIgnoreCase: Boolean = false,
        includePattern: String? = null,
        includeIgnoreCase: Boolean = false,
        buffer: Buffer = Buffer.DEFAULT,
        rowLimit: Int? = null
    ): List<String>

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