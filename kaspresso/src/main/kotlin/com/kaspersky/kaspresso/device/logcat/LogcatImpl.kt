package com.kaspersky.kaspresso.device.logcat

import android.os.Build
import android.util.Log
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class LogcatImpl(
    private val logger: UiTestLogger,
    private val adbServer: AdbServer,
    private val isNeededToPrintExecutedCommand: Boolean = false,
    private val defaultBufferSize: LogcatBufferSize = LogcatBufferSize(DEFAULT_BUFFER_SIZE, LogcatBufferSize.Dimension.KILOBYTES)
) : Logcat {

    companion object {
        const val DEFAULT_BUFFER_SIZE = 256
        const val DEFAULT_LOGCAT_CLEAR_DELAY: Long = 1_000
    }

    /**
     * NOT WORKING ON ANDROID 8+
     *
     * The problem: Android OS has a special introduced mechanism to filter and collapse of some bunches of logs produced by applications.
     * The name of the such mechanism is Chatty. Chatty turns on when an application writes a lot of logs.
     * The goal of Logcat interface is to analyze all logs. But Chatty prevents achievement of the mentioned goal.
     * That's why, there is this method to disable Chatty.
     * Please, call the method in "before" section of a test.
     */
    override fun disableChatty() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            adbServer.performShell("setprop ro.logd.filter disable")
            adbServer.performShell("setprop persist.logd.filter disable")
        }
        logger.i("Chatty disabled")
    }

    /**
     * Set new logcat buffer size
     *
     * @param size a LogcatBufferSize value
     */
    override fun setBufferSize(size: LogcatBufferSize) {
        adbServer.performShell("logcat -G $size")
        logger.i("Logcat buffer size set to $size")
    }

    /**
     * Set default buffer size
     */
    override fun setDefaultBufferSize() {
        setBufferSize(defaultBufferSize)
        logger.i("Logcat buffer size set to default $defaultBufferSize")
    }

    /**
     * Clear (flush) the selected buffers and exit.
     * The default buffer set is main, system and crash.
     *
     * @param buffer one of available logcat buffers
     */
    override fun clear(buffer: Logcat.Buffer) {
        adbServer.performShell("logcat -b ${buffer.bufferName} -c")
        Thread.sleep(DEFAULT_LOGCAT_CLEAR_DELAY)
        logger.i("Logcat buffer cleared")
    }

    override fun dumpLogcat(
        file: File,
        tags: List<String>?,
        timeFrom: String?,
        excludePattern: String?,
        excludePatternIsIgnoreCase: Boolean,
        includePattern: String?,
        includePatternIsIgnoreCase: Boolean,
        buffer: Logcat.Buffer
    ) {
        val command = prepareCommand(
            tags = tags,
            timeFrom = timeFrom,
            excludePattern = excludePattern,
            excludePatternIsIgnoreCase = excludePatternIsIgnoreCase,
            includePattern = includePattern,
            includePatternIsIgnoreCase = includePatternIsIgnoreCase,
            buffer = buffer,
            rowLimit = null
        )
        logger.i("Dump logcat buffer to $file: $command")

        val process = executeCommand(command)
        try {
            file.outputStream().use { process.inputStream.copyTo(it) }
        } catch (e: Throwable) {
            logger.e("Dump logcat buffer error: ${Log.getStackTraceString(e)}")
        } finally {
            process.destroy()
        }
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
            tags = null,
            timeFrom = null,
            excludePattern = excludePattern,
            excludePatternIsIgnoreCase = excludePatternIsIgnoreCase,
            includePattern = includePattern,
            includePatternIsIgnoreCase = includePatternIsIgnoreCase,
            buffer = buffer,
            rowLimit = rowLimit
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
     * Required Permissions: READ_EXTERNAL_STORAGE.
     * Required: Started AdbServer
     *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
     *     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"
     *
     * Get logcat dump via ADB and analyze each row.
     * Logcat reading stops if analyzerBlock returns false on some row
     *
     * Needed in cases when you want to check not only your application logs (with another PID).
     * For example: if you need to check Firebase Analytics logs
     *
     * @param excludePattern logcat will EXCLUDE rows that match the Regex
     * @param includePattern logcat will contains ONLY rows that match the Regex
     * @param buffer one of available logcat buffers
     * @param logcatFilePath path on device where logcat_dump will located. Must be accessible from app.
     * For example: Environment.getExternalStorageDirectory().absolutePath
     * @param readingBlock lambda with String input and Boolean output. Invokes on
     * every readed logcat row. Stop reading logcat if lambda returns false. If you needed
     * all rows of log always return false
     */
    fun readLogcatRowsViaAdb(
        excludePattern: Regex? = null,
        includePattern: Regex? = null,
        buffer: Logcat.Buffer = Logcat.Buffer.DEFAULT,
        logcatFilePath: String,
        readingBlock: (logRow: String) -> Boolean
    ): Boolean {
        val id = System.currentTimeMillis()
        val logcatFile = File("$logcatFilePath/logcat_dump_$id.txt")
        adbServer.performShell("logcat -b ${buffer.bufferName} -d > ${logcatFile.absolutePath} ")
        try {
            @Suppress("LoopWithTooManyJumpStatements")
            for (logRow in logcatFile.readLines()) {
                if (excludePattern != null && logRow.contains(excludePattern)) continue
                if (includePattern != null && !logRow.contains(includePattern)) continue
                if (readingBlock(logRow)) {
                    return true
                }
            }
        } finally {
            logcatFile.delete()
        }
        return false
    }

    /**
     * Prepare logcat command for execution over "sh -c COMMAND"
     */
    private fun prepareCommand(
        tags: List<String>?,
        timeFrom: String?,
        excludePattern: String?,
        excludePatternIsIgnoreCase: Boolean,
        includePattern: String?,
        includePatternIsIgnoreCase: Boolean,
        buffer: Logcat.Buffer,
        rowLimit: Int?
    ): String {
        var command = "logcat -b ${buffer.bufferName} -d "
        if (!timeFrom.isNullOrEmpty()) {
            command += "-t \"$timeFrom\" "
        }
        if (rowLimit != null && rowLimit > 0) {
            command += "-m $rowLimit "
        }
        if (!tags.isNullOrEmpty()) {
            command += "-s ${tags.joinToString(separator = "\",\"", prefix = "\"", postfix = "\"")}"
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
