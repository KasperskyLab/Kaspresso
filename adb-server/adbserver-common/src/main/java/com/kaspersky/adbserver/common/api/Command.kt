package com.kaspersky.adbserver.common.api

import java.io.Serializable

/**
 * Command to execute by AdbServer
 */
abstract class Command(open val body: String) : Serializable
