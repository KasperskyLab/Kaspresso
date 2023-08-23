package com.kaspersky.adbserver.commandtypes

import com.kaspersky.adbserver.common.api.Command

data class AdbCommand(override val body: String) : Command(body)
