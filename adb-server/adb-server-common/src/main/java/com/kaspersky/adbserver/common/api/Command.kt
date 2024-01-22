package com.kaspersky.adbserver.common.api

import java.io.Serializable

/**
 * Command to execute by AdbServer
 */
abstract class Command(open val command: String, open val arguments: List<String> = emptyList()) : Serializable
