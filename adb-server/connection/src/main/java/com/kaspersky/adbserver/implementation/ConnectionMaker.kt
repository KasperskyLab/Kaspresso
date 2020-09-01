package com.kaspersky.adbserver.implementation

import com.kaspresky.adbserver.log.LoggerFactory

/**
 * The helper to establish a connection correctly according to all possible states and multithread's environment
 */
internal class ConnectionMaker(deviceName: String? = null) {

    private val logger = LoggerFactory.getLogger(tag = javaClass.simpleName, deviceName = deviceName)
    @Volatile
    private var connectionState: ConnectionState = ConnectionState.DISCONNECTED

    fun connect(connectAction: () -> Unit, successConnectAction: () -> Unit) {
        logger.i("connect", "start")
        logger.i("connect", "current state=$connectionState")
        if (connectionState == ConnectionState.CONNECTING || connectionState == ConnectionState.DISCONNECTING) {
            logger.i("connect", "Unexpected connection state appeared during connect")
            return
        }
        if (connectionState == ConnectionState.CONNECTED) {
            return
        }
        connectionState = ConnectionState.CONNECTING
        try {
            connectAction.invoke()
            connectionState = ConnectionState.CONNECTED
            logger.i("connect", "updated state=$connectionState")
            successConnectAction.invoke()
        } catch (exception: Exception) {
            logger.e("connect", exception)
            connectionState =
                ConnectionState.DISCONNECTED
        }
    }

    fun disconnect(connectAction: () -> Unit) {
        logger.i("disconnect", "start")
        logger.i("disconnect", "current state=$connectionState")
        if (connectionState == ConnectionState.DISCONNECTING) {
            logger.i("disconnect", "Unexpected connection state appeared during disconnect")
            return
        }
        if (connectionState == ConnectionState.DISCONNECTED) {
            return
        }
        try {
            connectionState = ConnectionState.DISCONNECTING
            logger.i("disconnect", "updated state=$connectionState")
            connectAction.invoke()
        } finally {
            connectionState = ConnectionState.DISCONNECTED
            logger.i("disconnect", "updated state=$connectionState")
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
