package www.kaspersky.connector

import www.kaspersky.command_handler.Command
import www.kaspersky.command_handler.ICommandHandler
import www.kaspersky.command_handler.SendMsgCommand
import www.kaspersky.command_handler.local.LocalCommandExecutor
import www.kaspersky.command_handler.remote.IRemoteCommandExecutor
import www.kaspersky.command_handler.remote.RemoteCommandExecutor
import java.io.IOException

class P2PChatConnection {
    companion object {
        val TAG = P2PChatConnection::class.java.simpleName
        fun server(port: Int) = P2PChatConnection(port)
        fun client(ip: String, port: Int) = P2PChatConnection(ip, port)
    }

    private val chatCommandHandler = ChatCommandHandler()
    private val remoteCommandExecutor: IRemoteCommandExecutor

    private constructor(port: Int) {
        remoteCommandExecutor =
            RemoteCommandExecutor.server(port, LocalCommandExecutor().addHandler(chatCommandHandler))
    }

    private constructor(ip: String, port: Int) {
        remoteCommandExecutor =
            RemoteCommandExecutor.client(ip, port, LocalCommandExecutor().addHandler(chatCommandHandler))
    }


    @Throws(IOException::class)
    fun connect(chatListener: ChatListener) {
        chatCommandHandler.chatListener = chatListener
        remoteCommandExecutor.connect()
        chatListener.onConnectionChanged(true)
    }

    @Throws(IOException::class)
    fun disconnect() {
        remoteCommandExecutor.disconnect()
        chatCommandHandler.chatListener!!.onConnectionChanged(false)
        chatCommandHandler.chatListener = null
    }

    fun isConnected() = remoteCommandExecutor.isConnected()

    @Throws(IOException::class)
    fun sendMessage(chatMessage: ChatMessage) {
        val sendMsgCommand = SendMsgCommand(chatMessage.id, chatMessage.body.toString())
        remoteCommandExecutor.execute(sendMsgCommand)
        chatCommandHandler.chatListener!!.onMessageSend(chatMessage)
    }
}

interface ChatListener {
    fun onMessageIncome(incomeMessage: ChatMessage)
    fun onMessageSend(outcomeMessage: ChatMessage)
    fun onConnectionChanged(connected: Boolean)
}

private class ChatCommandHandler : ICommandHandler {
    var chatListener: ChatListener? = null
    override fun <T> accept(command: Command<T>): Boolean = command is SendMsgCommand

    @Suppress("UNCHECKED_CAST")
    override fun <T> execute(command: Command<*>): T {
        val chatMessageCommand = command as SendMsgCommand
        chatListener!!.onMessageIncome(
            ChatMessage(
                chatMessageCommand.id,
                chatMessageCommand.body,
                false
            )
        )
        return "" as T
    }

}