package com.kaspersky.command_handler.remote

import com.kaspersky.command_handler.Command
import com.kaspersky.command_handler.ICommandExecutor
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicLong

class RemoteCommandExecutor private constructor(
    private val ip: String?,
    private val port: Int,
    private val commandExecutor: ICommandExecutor,
    private val connectAsServer: Boolean
) : IRemoteCommandExecutor {

    companion object {

        @Volatile
        var socketConnected: Boolean = false

        @Suppress("UNUSED")
        fun server(port: Int, remoteCommandExecutor: ICommandExecutor) =
            RemoteCommandExecutor(null, port, remoteCommandExecutor, true)

        @Suppress("UNUSED")
        fun client(ip: String, port: Int, remoteCommandExecutor: ICommandExecutor) =
            RemoteCommandExecutor(ip, port, remoteCommandExecutor, false)

    }

    private val commandTimeoutMin = TimeUnit.MINUTES.toSeconds(3)
    private val receiversMap = ConcurrentHashMap<Long, CommandResultReceiver>()
    private val bgExecutor = Executors.newCachedThreadPool()
    private val currentId = AtomicLong()

    private lateinit var socket: Socket
    private lateinit var inputStream: ObjectInputStream
    private lateinit var outputStream: ObjectOutputStream

    override fun isConnected(): Boolean = socketConnected

    @Throws(IOException::class)
    override fun connect() {
        if (connectAsServer) {
            connectAsServer()
        } else {
            connectAsClient()
        }
        socketConnected = true
    }

    @Suppress("UNUSED")
    @Throws(IOException::class)
    override fun disconnect() {
        if (!socketConnected) return
        println("disconnect stated")
        socketConnected = false
        if (connectAsServer) {
            sendCommand(ForceDisconnectCommand(), currentId.incrementAndGet())
        }
        receiversMap.forEach {
            val result = ResultMessage.Error(it.key, IOException("'disconnect' has been called"))
            it.value.setResult(result)
        }
        receiversMap.clear()
        socket.close()
        println("disconnect finished")
    }

    @Suppress("UNCHECKED_CAST")
    @Throws(Exception::class)
    override fun <T> execute(command: Command<T>): T {
        val id = currentId.incrementAndGet()
        val receiver = CommandResultReceiver()
        receiversMap[id] = receiver
        bgExecutor.execute { sendCommand(command, id) }
        val msg = receiver.waitResult(commandTimeoutMin, TimeUnit.SECONDS)
        receiversMap.remove(id)
        if (msg == null) {
            throw TimeoutException("Command execution timeout")
        } else {
            when (msg) {
                is ResultMessage.Success -> return msg.data as T
                is ResultMessage.Error -> throw msg.exception
            }
        }
    }

    private fun startCommandListenThread() {
        val thread = Thread {
            println("Command listen thread started!")
            while (socketConnected) {
                peekNextMessage()
            }
        }
        thread.start()
    }

    private fun peekNextMessage() {
        lateinit var obj: Any
        try {
            obj = inputStream.readObject()
        } catch (e: Exception) {
            if (!socketConnected) {
                return
            } else {
                disconnect()
                return
                //throw e
            }
        }
        when (obj) {
            is ExecuteMsg<*> -> processCommand(obj.command, obj.id)
            is ResultMessage -> deliverResult(obj)
            else -> throw IllegalStateException("Unknown received object: $obj")
        }
    }

    @Throws(IOException::class)
    private fun createStreams(socket: Socket) {
        outputStream = ObjectOutputStream(socket.getOutputStream())
        inputStream = ObjectInputStream(socket.getInputStream())
        println("IO streams created")
    }

    private fun processCommand(command: Command<*>, id: Long) {
        if (command is ForceDisconnectCommand) {
            disconnect()
        }
        bgExecutor.execute {
            try {
                val result = commandExecutor.execute(command)
                sendResultMessage(ResultMessage.Success(id, result!!))
            } catch (e: Exception) {
                if (socketConnected) {
                    sendResultMessage(ResultMessage.Error(id, e))
                }
            }
        }
    }

    private fun deliverResult(resultMessage: ResultMessage) {
        receiversMap[resultMessage.id]?.setResult(resultMessage)
    }

    private fun sendResultMessage(resultMessage: ResultMessage) {
        sendObject(resultMessage)
    }

    private fun sendCommand(command: Command<*>, id: Long) {
        val msg = ExecuteMsg(command, id)
        sendObject(msg)
    }

    private fun sendObject(`object`: Any) {
        try {
            outputStream.writeObject(`object`)
            outputStream.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    @Throws(IOException::class)
    private fun connectAsServer() {
        println("connect as server...started")
        val serverSocket = ServerSocket(port)
        socket = serverSocket.accept()
        createStreams(socket)
        startCommandListenThread()
        println("connect as client...finished")
    }

    @Throws(IOException::class)
    private fun connectAsClient() {
        println("connect as client...started")
        socket = Socket(ip, port)
        createStreams(socket)
        startCommandListenThread()
        println("connect as client...finished")
    }
}

private class CommandResultReceiver {
    @Volatile
    private var resultMessage: ResultMessage? = null
    private val waitLatch = CountDownLatch(1)

    fun setResult(resultMessage: ResultMessage) {
        this.resultMessage = resultMessage
        waitLatch.countDown()
    }

    fun waitResult(timeout: Long, unit: TimeUnit): ResultMessage? {
        try {
            waitLatch.await(timeout, unit)
        } catch (ignored: InterruptedException) {
        }

        return resultMessage
    }
}

private sealed class ResultMessage : Serializable {
    abstract val id: Long

    data class Success(override val id: Long, val data: Any) : ResultMessage()
    data class Error(override val id: Long, val exception: Exception) : ResultMessage()
}

private data class ExecuteMsg<T>(val command: Command<T>, val id: Long) : Serializable

private class ForceDisconnectCommand : Command<Unit>()