package com.kaspersky.adbserver.commandtypes

import com.kaspersky.adbserver.common.api.Command

data class ComplexCmdCommand(override val body: List<String>) : Command(body)
