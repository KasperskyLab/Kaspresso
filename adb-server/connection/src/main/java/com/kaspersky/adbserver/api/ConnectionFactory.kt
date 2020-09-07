package com.kaspersky.adbserver.api

import com.kaspersky.adbserver.implementation.ConnectionClientImplBySocket
import com.kaspersky.adbserver.implementation.ConnectionServerImplBySocket
import com.kaspresky.adbserver.log.logger.Logger
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
