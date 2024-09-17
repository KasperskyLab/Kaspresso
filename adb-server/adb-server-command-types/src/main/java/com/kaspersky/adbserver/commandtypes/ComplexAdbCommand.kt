package com.kaspersky.adbserver.commandtypes

import com.kaspersky.adbserver.common.api.ComplexCommand

data class ComplexAdbCommand(
    override val body: String,
    override val arguments: List<String> = emptyList()
) : ComplexCommand(body, arguments)
