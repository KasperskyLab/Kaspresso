package com.kaspersky.adbserver.commandtypes

import com.kaspersky.adbserver.common.api.Command

data class CmdCommand(
    override val command: String,
    override val arguments: List<String> = emptyList()
) : Command(command, arguments)
