package com.kaspersky.adbserver.connection.implementation

import com.kaspersky.adbserver.common.log.logger.Logger

/**
 * The helper to establish a connection correctly according to all possible states and multithread's environment
 */
internal class ConnectionMaker(private val logger: Logger) {

    @Volatile
    private var connectionState: ConnectionState = ConnectionState.DISCONNECTED

    fun connect(
        connectAction: () -> Unit,
        successConnectAction: () -> Unit,
        failureConnectAction: (Throwable) -> Unit
    ) {
        logger.d("Start a connection establishment. The current state=$connectionState")
        if (connectionState == ConnectionState.CONNECTING) {
            logger.d("The connection establishment process is in progress. Skip the new attempt")
            return
        }
        if (connectionState == ConnectionState.DISCONNECTING) {
            logger.d("The connection interruption process is in progress. Skip the new attempt")
            return
        }
        if (connectionState == ConnectionState.CONNECTED) {
            logger.d("The connection has been established. Skip the new attempt")
            return
        }

        connectionState = ConnectionState.CONNECTING
        logger.d("The current state=$connectionState")
        try {
            connectAction.invoke()
            connectionState = ConnectionState.CONNECTED
            logger.d("The connection is established. The current state=$connectionState")
            successConnectAction.invoke()
        } catch (exception: Throwable) {
            connectionState = ConnectionState.DISCONNECTED
            logger.d("The connection establishment process failed. The current state=$connectionState")
            failureConnectAction.invoke(exception)
        }
    }

    fun disconnect(connectAction: () -> Unit) {
        logger.d("Start a connection interruption. The current state=$connectionState")
        if (connectionState == ConnectionState.DISCONNECTING) {
            logger.d("The connection interruption process is in progress. Skip the new attempt")
            return
        }
        if (connectionState == ConnectionState.DISCONNECTED) {
            logger.d("The connection has been disconnected. Skip the new attempt")
            return
        }

        connectionState = ConnectionState.DISCONNECTING
        logger.d("The current state=$connectionState")
        try {
            connectAction.invoke()
        } finally {
            connectionState = ConnectionState.DISCONNECTED
            logger.d("The connection is disconnected. The current state=$connectionState")
        }
    }

    fun isConnected(): Boolean =
        connectionState == ConnectionState.CONNECTED

    private enum class ConnectionState {
        CONNECTING,
        CONNECTED,
        DISCONNECTING,
        DISCONNECTED
    }
}
