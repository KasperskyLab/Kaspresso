package com.kaspersky.adbserver.common.api

abstract class ComplexCommand(override val body: String, open val arguments: List<String> = emptyList()) : Command(body)
