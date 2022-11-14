package com.kaspersky.adbserver.desktop

import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.common.api.ExecutorResultStatus
import java.io.File
import java.nio.file.Path
import java.util.concurrent.TimeUnit

/**
 * @param adbPath - path to adb binary if null when used "adb" command
 */
class AdbCommandPerformer(
    private val adbPath: Path?,
    private val cmdCommandPerformer: CmdCommandPerformer,
) {

    /**
     * Be aware it's a synchronous method
     * @param command - adb command without path to adb binaries
     */
    fun perform(command: String): CommandResult {
        val adbPath = adbPath?.toString() ?: "adb"
        return cmdCommandPerformer.perform("$adbPath $command")
    }
}
