package com.kaspersky.kaspresso.device.logcat

import java.io.BufferedReader
import java.io.InputStreamReader

class LogcatImpl(
    override val defaultBufferSize: String = "64K",
    override val isNeededToPrintExecutedCommand: Boolean = false
) : Logcat {

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
     * @param excludePatternIsIgnoreCase boolean is exclude pattern must ignore string case
     * @param includePattern logcat will contains ONLY rows that match the pattern
     * @param includePatternIsIgnoreCase boolean is include pattern must ignore string case
     * @param buffer one of available logcat buffers
     * @param rowLimit limiter of logcat output, starts FROM BEGINNING of logcat dump
     * with extra row of buffer beginning, if null return all rows
     *
     * @return [List<String>] with logcat rows
     */
    override fun readLogcatRows(
        excludePattern: String?,
        excludePatternIsIgnoreCase: Boolean,
        includePattern: String?,
        includePatternIsIgnoreCase: Boolean,
        buffer: Logcat.Buffer,
        rowLimit: Int?
    ): List<String> {
        val logcatOutput = mutableListOf<String>()
        readLogcatRows(
            excludePattern,
            excludePatternIsIgnoreCase,
            includePattern,
            includePatternIsIgnoreCase,
            buffer,
            rowLimit
        ) { logcatRow ->
            logcatOutput.add(logcatRow)
            false
        }
        return logcatOutput
    }

    /**
     * Get logcat dump and analyze each row.
     * Logcat reading stops if analyzerBlock returns false on some row
     *
     * @param excludePattern logcat will EXCLUDE rows that match the pattern
     * @param excludePatternIgnoreCase boolean is exclude pattern must ignore string case
     * @param includePattern logcat will contains ONLY rows that match the pattern
     * @param includePatternIgnoreCase boolean is include pattern must ignore string case
     * @param buffer one of available logcat buffers
     * @param rowLimit limiter of logcat output, starts FROM BEGINNING of logcat dump
     * with extra row of buffer beginning, if null return all rows
     * @param analyzerBlock lambda with String input and Boolean output. Invokes on
     * every readed logcat row. Stop reading logcat if lambda returns false. If you needed
     * all rows of log always return false
     */
    override fun readLogcatRows(
        excludePattern: String?,
        excludePatternIgnoreCase: Boolean,
        includePattern: String?,
        includePatternIgnoreCase: Boolean,
        buffer: Logcat.Buffer,
        rowLimit: Int?,
        analyzerBlock: (logcatRow: String) -> Boolean
    ): Boolean {
        val command = prepareCommand(
            excludePattern,
            excludePatternIgnoreCase,
            includePattern,
            includePatternIgnoreCase,
            buffer,
            rowLimit
        )
        val process = executeCommand(command)
        val logcatStream = InputStreamReader(process.inputStream)
        val bufferedReader = BufferedReader(logcatStream)
        var logcatReadedLine = bufferedReader.readLine()
        try {
            while (logcatReadedLine != null) {
                if (analyzerBlock.invoke(logcatReadedLine)) {
                    return true
                }
                logcatReadedLine = bufferedReader.readLine()
            }
        } finally {
            bufferedReader.close()
            logcatStream.close()
            process.destroy()
        }
        return false
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
            command += """| grep -${if (excludeIgnoreCase) "i" else ""}Ev '${excludePattern.replace(
                "\"",
                "\\\""
            )}' """
        }
        if (includePattern != null) {
            command += """| grep -${if (includeIgnoreCase) "i" else ""}E '${includePattern.replace(
                "\"",
                "\\\""
            )}' """
        }
        return command
    }

    /**
     * Execute command via Runtime.exec()
     */
    private fun executeCommand(command: String): Process {
        val process = Runtime.getRuntime().exec(arrayOf("sh", "-c", command))
        if (isNeededToPrintExecutedCommand) println("Executed command: $command")
        return process
    }
}