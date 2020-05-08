package com.kaspersky.test_server.api

import com.kaspersky.test_server.implementation.ConnectionClientImplBySocket
import com.kaspersky.test_server.implementation.ConnectionServerImplBySocket
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
            ConnectionServerImplBySocket(socketCreation, commandExecutor, deviceName)

    fun createClient(
        socketCreation: () -> Socket
    ): ConnectionClient =
        ConnectionClientImplBySocket(socketCreation)
}