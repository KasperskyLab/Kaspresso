package com.kaspersky.adbserver.commandtypes

import com.kaspersky.adbserver.common.api.Command

data class ComplexAdbCommand(override val body: List<String>) : Command(body)
