package com.kaspersky.test_server.implementation.transferring

import com.kaspersky.test_server.api.Command
import java.io.Serializable

/**
 * Data models for Server-Client communication
 */

internal abstract class Message(open val command: Command) : Serializable

internal data class TaskMessage(override val command: Command) : Message(command)

internal data class ResultMessage<T>(override val command: Command, val data: T) : Message(command)