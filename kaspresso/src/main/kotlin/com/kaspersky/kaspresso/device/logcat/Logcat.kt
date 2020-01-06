package com.kaspersky.kaspresso.device.logcat

/**
 * The interface to work with logcat.
 */
interface Logcat {
    /**
     * Available buffer names:
     * radio: View the buffer that contains radio/telephony related messages.
     * events: View the interpreted binary system event buffer messages.
     * main: View the main log buffer (default) does not contain system and crash log messages.
     * system: View the system log buffer (default).
     * crash: View the crash log buffer (default).
     * all: View all buffers.
     * default: Reports main, system, and crash buffers.
     **/

    /**
     * Set new logcat buffer size (by default = 64K)
     *
     * @param size a string with size of buffer and dimension.
     * Can add K or M at the end to indicate kilobytes or megabytes.
     */
    fun setBufferSize(size: String = "64K")

    /**
     * Clear (flush) the selected buffers and exit.
     * The default buffer set is main, system and crash.
     *
     * @param bufferName one of available buffer names
     */
    fun clear(bufferName: String = "default")

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
     * @param bufferName one of available buffer names
     * @param rowLimit limiter of logcat output, if -1 return all rows
     */
    fun getLogcatRows(
        excludePattern: String? = null,
        excludeIgnoreCase: Boolean = false,
        includePattern: String? = null,
        includeIgnoreCase: Boolean = false,
        bufferName: String = "default",
        rowLimit: Int = -1
    ): List<String>
}