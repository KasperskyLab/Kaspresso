package com.kaspersky.test_server.api

import java.io.Serializable

/**
 * Result of command's executing
 */
data class CommandResult(
    val status: ExecutorResultStatus,
    val description: String
) : Serializable

enum class ExecutorResultStatus {
    // if the command was successful executed
    SUCCESS,
    // if something went wrong.
    // Possible reasons are problems of server-client connection, wrong command to execute
    FAILED
}