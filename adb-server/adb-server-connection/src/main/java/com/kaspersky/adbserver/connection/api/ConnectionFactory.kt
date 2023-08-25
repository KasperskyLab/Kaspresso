package com.kaspersky.adbserver.connection.api

import com.kaspersky.adbserver.common.api.CommandExecutor
import com.kaspersky.adbserver.connection.implementation.ConnectionClientImplBySocket
import com.kaspersky.adbserver.connection.implementation.ConnectionServerImplBySocket
import com.kaspersky.adbserver.common.log.logger.Logger
import java.net.Socket

/**
 * The singleton to provide convenient methods to create Server and Client
 */
object ConnectionFactory {

    fun createServer(
        socketCreation: () -> Socket,
        commandExecutor: CommandExecutor,
        logger: Logger,
        connectionServerLifecycle: ConnectionServerLifecycle
    ): ConnectionServer =
        ConnectionServerImplBySocket(
            socketCreation,
            commandExecutor,
            logger,
            connectionServerLifecycle
        )

    fun createClient(
        socketCreation: () -> Socket,
        logger: Logger,
        connectionClientLifecycle: ConnectionClientLifecycle
    ): ConnectionClient =
        ConnectionClientImplBySocket(socketCreation, logger, connectionClientLifecycle)
}
