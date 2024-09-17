package com.kaspersky.adbserver.commandtypes

import com.kaspersky.adbserver.common.api.Command

/**
 * Command for backward compatibility with old version of adb-server
 */
data class AdbCommand(
    override val body: String,
) : Command(body)
