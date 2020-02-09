package com.kaspersky.kaspresso.device.logcat

import java.io.BufferedReader
import java.io.InputStreamReader

class LogcatImpl(
    // If needed to print executed command to System.out set isNeededToPrintExecutedCommand = true
    private val isNeededToPrintExecutedCommand: Boolean = false,
    private val defaultBufferSize: LogcatBufferSize = LogcatBufferSize(DEFAULT_BUFFER_SIZE, LogcatBufferSize.Dimension.KILOBYTES),
    isNeededToDisableChatty: Boolean = true
) : Logcat {

    companion object {
        const val DEFAULT_BUFFER_SIZE = 64
        const val DEFAULT_LOGCAT_CLEAR_DELAY: Long = 1_000
    }

    /**
     * Whitelisting all PIDs for disabling chatty
     * who can skip some rows when logging is very heavy
     *
     * I think if you analyze logcat you need all logcat rows.
     * If chatty is needed to be enabled - pass LogcatImpl to Kaspresso.Builder
     * with parameter isNeededToDisableChatty = false
     */
    init {
        if (isNeededToDisableChatty) {
            executeCommand("logcat -P \"\"")
        }
    }

    /**
     * Set new logcat buffer size
     *
     * @param size a LogcatBufferSize value
     */
    override fun setBufferSize(size: LogcatBufferSize) {
        executeCommand("logcat -G $size")
    }

    /**
     * Set default buffer size
     */
    override fun setDefaultBufferSize() {
        setBufferSize(defaultBufferSize)
    }

    /**
     * Clear (flush) the selected buffers and exit.
     * The default buffer set is main, system and crash.
     *
     * @param buffer one of available logcat buffers
     */
    override fun clear(buffer: Logcat.Buffer) {
        executeCommand("logcat -b ${buffer.bufferName} -c")
        Thread.sleep(DEFAULT_LOGCAT_CLEAR_DELAY)
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
     * WITH EXTRA ROW of buffer beginning, if null return all rows
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
    override fun readLogcatRows(
        excludePattern: String?,
        excludePatternIsIgnoreCase: Boolean,
        includePattern: String?,
        includePatternIsIgnoreCase: Boolean,
        buffer: Logcat.Buffer,
        rowLimit: Int?,
        readingBlock: (logcatRow: String) -> Boolean
    ): Boolean {
        val command = prepareCommand(
            excludePattern,
            excludePatternIsIgnoreCase,
            includePattern,
            includePatternIsIgnoreCase,
            buffer,
            rowLimit
        )
        val process = executeCommand(command)
        val logcatStream = InputStreamReader(process.inputStream)
        val bufferedReader = BufferedReader(logcatStream)
        var logcatReadedLine = bufferedReader.readLine()
        try {
            while (logcatReadedLine != null) {
                if (readingBlock.invoke(logcatReadedLine)) {
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
        excludePatternIsIgnoreCase: Boolean,
        includePattern: String?,
        includePatternIsIgnoreCase: Boolean,
        buffer: Logcat.Buffer,
        rowLimit: Int?
    ): String {
        var command = "logcat -b ${buffer.bufferName} -d "
        if (rowLimit != null && rowLimit > 0) {
            command += "-m $rowLimit "
        }
        if (excludePattern != null) {
            command += """| grep -${if (excludePatternIsIgnoreCase) "i" else ""}Ev '${excludePattern.replace(
                "\"",
                "\\\""
            )}' """
        }
        if (includePattern != null) {
            command += """| grep -${if (includePatternIsIgnoreCase) "i" else ""}E '${includePattern.replace(
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