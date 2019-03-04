package www.kaspersky.command_handler.remote

import www.kaspersky.command_handler.ICommandExecutor
import java.io.IOException

interface IRemoteCommandExecutor : ICommandExecutor {
    @Throws(IOException::class)
    fun connect()

    fun disconnect()

    fun isConnected(): Boolean
}