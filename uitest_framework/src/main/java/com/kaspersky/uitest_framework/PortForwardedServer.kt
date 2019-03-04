package com.kaspersky.uitest_framework

import www.kaspersky.connector.ChatListener
import www.kaspersky.connector.ChatMessage
import www.kaspersky.connector.P2PChatConnection
import java.util.concurrent.Executors

object PortForwardedServer: ChatListener {

    private const val DISCONNECT_MESSAGE = "disconnect"

    private val chatConnection = P2PChatConnection.server(8500)

    private val bgExecutor = Executors.newSingleThreadExecutor()

    fun startServer() {
        bgExecutor.submit { chatConnection.connect(this) }
    }

    fun stopServer() {
        bgExecutor.submit {
            val message = ChatMessage(System.currentTimeMillis(), DISCONNECT_MESSAGE, true)
            chatConnection.sendMessage(message)
            chatConnection.disconnect()
        }
    }

    fun sendCommand(command: String) {
        bgExecutor.submit { chatConnection.connect(this) }

        val message = ChatMessage(System.currentTimeMillis(), command, true)
        bgExecutor.submit { chatConnection.sendMessage(message) }
    }

    override fun onMessageIncome(incomeMessage: ChatMessage) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMessageSend(outcomeMessage: ChatMessage) {
        bgExecutor.submit { chatConnection.disconnect() }
    }

    override fun onConnectionChanged(connected: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}