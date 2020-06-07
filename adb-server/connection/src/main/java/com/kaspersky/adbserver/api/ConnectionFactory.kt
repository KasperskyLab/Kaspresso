package com.kaspersky.adbserver.api

import com.kaspersky.adbserver.implementation.ConnectionClientImplBySocket
import com.kaspersky.adbserver.implementation.ConnectionServerImplBySocket
import java.net.Socket

/**
 * The singleton to provide convenient methods to create Server and Client
 */
object ConnectionFactory {

    fun createServer(
        socketCreation: () -> Socket,
        commandExecutor: CommandExecutor,
        deviceName: String
    ): ConnectionServer =
        ConnectionServerImplBySocket(
            socketCreation,
            commandExecutor,
            deviceName
        )

    fun createClient(
        socketCreation: () -> Socket
    ): ConnectionClient =
        ConnectionClientImplBySocket(
            socketCreation
        )
}
