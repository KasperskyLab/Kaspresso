package com.kaspersky.kaspresso.device.logcat

import java.io.BufferedReader
import java.io.InputStreamReader

class LogcatImpl(override val defaultBufferSize: String = "64K") : Logcat {

    /**
     * Set new logcat buffer size
     *
     * @param size a string with size of buffer and dimension.
     * Can add K or M at the end to indicate kilobytes or megabytes.
     */
    override fun setBufferSize(size: String) {
        executeCommand("logcat -G $size")
    }

    /**
     * Clear (flush) the selected buffers and exit.
     * The default buffer set is main, system and crash.
     *
     * @param buffer one of available logcat buffers
     */
    override fun clear(buffer: Logcat.Buffer) {
        executeCommand("logcat -b ${buffer.bufferName} -c")
    }

    /**
     * Whitelisting all PIDs for disabling chatty
     * who can skip some rows when logging is very heavy
     */
    override fun disableChatty() {
        executeCommand("logcat -P \"\"")
    }

    /**
     * Get logcat dump as list of strings
     *
     * @param excludePattern logcat will EXCLUDE rows that match the pattern
     * @param excludeIgnoreCase boolean is exclude pattern must ignore string case
     * @param includePattern logcat will contains ONLY rows that match the pattern
     * @param includeIgnoreCase boolean is include pattern must ignore string case
     * @param buffer one of available logcat buffers
     * @param rowLimit limiter of logcat output, if -1 return all rows
     */
    override fun readLogcatRows(
        excludePattern: String?,
        excludeIgnoreCase: Boolean,
        includePattern: String?,
        includeIgnoreCase: Boolean,
        buffer: Logcat.Buffer,
        rowLimit: Int?
    ): List<String> {
        val logcatOutput = mutableListOf<String>()
        val command = prepareCommand(excludePattern, excludeIgnoreCase, includePattern, includeIgnoreCase, buffer, rowLimit)
        val process = executeCommand(command)
        val logcatStream = InputStreamReader(process.inputStream)
        val bufferedReader = BufferedReader(logcatStream)
        var logcatReadedLine = bufferedReader.readLine()
        while (logcatReadedLine != null) {
            logcatOutput += logcatReadedLine
            logcatReadedLine = bufferedReader.readLine()
        }
        bufferedReader.close()
        logcatStream.close()
        process.destroy()

        return logcatOutput
    }

    /**
     * Prepare logcat command for execution over "sh -c COMMAND"
     */
    @Suppress("LongParameterList")
    private fun prepareCommand(
        excludePattern: String?,
        excludeIgnoreCase: Boolean,
        includePattern: String?,
        includeIgnoreCase: Boolean,
        buffer: Logcat.Buffer,
        rowLimit: Int?
    ): String {
        var command = "logcat -b ${buffer.bufferName} -d "
        if (rowLimit != null && rowLimit > 0) {
            command += "-m $rowLimit "
        }
        if (excludePattern != null) {
            command += """| grep -${ if (excludeIgnoreCase) "i" else "" }Ev '${excludePattern.replace("\"", "\\")}' """
        }
        if (includePattern != null) {
            command += """| grep -${ if (includeIgnoreCase) "i" else "" }E '${includePattern.replace("\"", "\\")}' """
        }
        return command
    }

    /**
     * Execute command via Runtime.exec()
     */
    private fun executeCommand(command: String): Process {
        val process = Runtime.getRuntime().exec(arrayOf("sh", "-c", command))
        println("Executed command: $command")
        return process
    }
}