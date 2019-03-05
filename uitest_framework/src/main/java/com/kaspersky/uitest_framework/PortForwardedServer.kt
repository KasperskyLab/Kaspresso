package com.kaspersky.uitest_framework

import www.kaspersky.connector.ChatListener
import www.kaspersky.connector.ChatMessage
import www.kaspersky.connector.P2PChatConnection
import java.util.concurrent.Executors

object PortForwardedServer: ChatListener {

    private const val DISCONNECT_MESSAGE = "disconnect"
    private const val MESSAGE_DELIVERED = "message_done"

    private val chatConnection = P2PChatConnection.server(8500)

    private val bgExecutor = Executors.newSingleThreadExecutor()

    private var isWatingSync = false

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

        val message = ChatMessage(System.currentTimeMillis(), command, true)

        if (message.needSync) {
            isWatingSync = true
        }

        bgExecutor.submit { chatConnection.sendMessage(message) }
    }

    override fun onMessageIncome(incomeMessage: ChatMessage) {
        if (incomeMessage.body.toString() == MESSAGE_DELIVERED) {
            isWatingSync = false
        }
    }

    override fun onMessageSend(outcomeMessage: ChatMessage) {
//        bgExecutor.submit { chatConnection.disconnect() }
    }

    override fun onConnectionChanged(connected: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun waitSync() {
        while (isWatingSync) {
            Thread.sleep(500)
        }
    }

}
