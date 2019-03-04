package www.kaspersky.desktop_app

import www.kaspersky.connector.ChatListener
import www.kaspersky.connector.ChatMessage
import www.kaspersky.connector.P2PChatConnection
import java.lang.Exception
import java.util.*

fun main(args: Array<String>) {
    Main.start()
}

object Main : ChatListener {

    override fun onConnectionChanged(connected: Boolean) {
        if (!connected) {
            tryConnect()
        }
    }

    override fun onMessageIncome(incomeMessage: ChatMessage) {
        println(incomeMessage)
        Runtime.getRuntime().exec(incomeMessage.body.toString()).inputStream.bufferedReader().readText()
    }

    override fun onMessageSend(outcomeMessage: ChatMessage) {
        //
    }

    private val chatConnection = P2PChatConnection.client("127.0.0.1", 9000)
    fun start() {
        val tcpForwardResult =
            Runtime.getRuntime().exec("adb forward tcp:9000 tcp:8500").inputStream.bufferedReader().readText()
        println(tcpForwardResult)
        tryConnect()
    }

    private fun tryConnect() {
        println("Trying to connect")
        while (true) {
            try {
                chatConnection.connect(this)
                break
            } catch (e: Exception) {
                Thread.sleep(1000)
            }
        }
    }

}