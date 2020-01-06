package com.kaspersky.kaspresso.device.logcat

import java.io.BufferedReader
import java.io.InputStreamReader

class LogcatImpl : Logcat {
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
    override fun setBufferSize(size: String) {
        executeCommand("logcat -G $size")
    }

    /**
     * Clear (flush) the selected buffers and exit.
     * The default buffer set is main, system and crash.
     *
     * @param bufferName one of available buffer names
     */
    override fun clear(bufferName: String) {
        executeCommand("logcat -b $bufferName -c")
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
     * @param bufferName one of available buffer names
     * @param rowLimit limiter of logcat output, if -1 return all rows
     */
    override fun getLogcatRows(
        excludePattern: String?,
        excludeIgnoreCase: Boolean,
        includePattern: String?,
        includeIgnoreCase: Boolean,
        bufferName: String,
        rowLimit: Int
    ): List<String> {
        val logcatOutput = mutableListOf<String>()
        var command = "logcat -b $bufferName -d "
        if (rowLimit > 0) {
            command += "-m $rowLimit "
        }
        if (excludePattern != null) {
            command += """| grep -${if(excludeIgnoreCase) "i" else ""}Ev '${excludePattern.replace("\"", "\\")}' """
        }
        if (includePattern != null) {
            command += """| grep -${if(includeIgnoreCase) "i" else ""}E '${includePattern.replace("\"", "\\")}' """
        }
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
     * Execute command via Runtime.exec()
     */
    private fun executeCommand(command: String): Process {
        val process = Runtime.getRuntime().exec(arrayOf("sh", "-c", command))
        println("Executed command: $command")
        return process
    }
}