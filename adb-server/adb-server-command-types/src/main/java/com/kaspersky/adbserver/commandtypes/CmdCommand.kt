package com.kaspersky.adbserver.commandtypes

import com.kaspersky.adbserver.common.api.Command

data class CmdCommand(override val body: String) : Command(body)
