package com.kaspersky.uitest_framework.hostconnection

import com.kaspersky.command_handler.Command
import com.kaspersky.command_handler.ICommandHandler
import com.kaspersky.command_handler.local.LocalCommandExecutor
import com.kaspersky.command_handler.remote.RemoteCommandExecutor
import java.net.SocketException


fun main(args: Array<String>) {
    HostConnectionClient.executeCmdCommand("adb forward tcp:${HostConnectionClient.CLIENT_PORT} tcp:$PORT")
    while (true) {
        try {
            if (!RemoteCommandExecutor.socketConnected) {
                HostConnectionClient.connectSync()
            }
        } catch (e: SocketException) {
            //
        }
        Thread.sleep(500)
    }
}

private object HostConnectionClient : ICommandHandler {
    const val CLIENT_PORT = 9000
    private const val IP = "127.0.0.1"

    private val remoteCommandExecutor = RemoteCommandExecutor.client(
        IP,
        CLIENT_PORT, LocalCommandExecutor(this)
    )

    fun connectSync() {
        remoteCommandExecutor.connect()
    }

    fun disconnectSync() {
        remoteCommandExecutor.disconnect()
    }

    fun executeCmdCommand(command: String): String {
        val process = Runtime.getRuntime().exec(command)
        val resultMsg = process.inputStream.bufferedReader().readText()
        if (process.exitValue() != 0) {
            throw CmdException(resultMsg)
        } else {
            return resultMsg
        }
    }

    override fun <T> accept(command: Command<T>) = command is CmdCommand


    @Suppress("UNCHECKED_CAST")
    override fun <T> execute(command: Command<*>): T {
        when (command) {
            is CmdCommand -> return executeCmdCommand(command.body) as T
            else -> throw IllegalStateException("Wrong handler implementation?")
        }
    }

}
