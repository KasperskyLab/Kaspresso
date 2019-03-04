package www.kaspersky.command_handler.remote

import www.kaspersky.command_handler.Command
import www.kaspersky.command_handler.ICommandExecutor
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

        @Suppress("UNUSED")
        fun server(port: Int, remoteCommandExecutor: ICommandExecutor) =
            RemoteCommandExecutor(null, port, remoteCommandExecutor, true)

        @Suppress("UNUSED")
        fun client(ip: String, port: Int, remoteCommandExecutor: ICommandExecutor) =
            RemoteCommandExecutor(ip, port, remoteCommandExecutor, false)

    }

    private val commandTimeoutMin = TimeUnit.MINUTES.toSeconds(3)
    private val receiversMap = ConcurrentHashMap<Long, CommandResultReceiver>()
    private val executor = Executors.newCachedThreadPool()
    private val currentId = AtomicLong()

    private lateinit var socket: Socket
    private lateinit var inputStream: ObjectInputStream
    private lateinit var outputStream: ObjectOutputStream
    @Volatile
    private var connected: Boolean = false

    private val uuid: Long
        get() = currentId.incrementAndGet()

    override fun isConnected(): Boolean = connected

    @Throws(IOException::class)
    override fun connect() {
        if (connectAsServer) {
            connectAsServer()
        } else {
            connectAsClient()
        }
        connected = true
    }


    @Suppress("UNCHECKED_CAST")
    @Throws(Exception::class)
    override fun <T> execute(command: Command<T>): T {
        val id = uuid
        val receiver = CommandResultReceiver()
        receiversMap[id] = receiver
        executor.execute { sendCommand(command, id) }
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

    @Suppress("UNUSED")
    @Throws(IOException::class)
    override fun disconnect() {
        if (!connected) return
        connected = false
        socket.close()
    }

    private fun startListenerThread() {
        val thread = object : Thread() {
            override fun run() {
                try {
                    println("Listen thread started!")
                    while (connected) {
                        val recvd = inputStream.readObject()
                        if (recvd is ExecuteMsg<*>) {
                            processCommand(recvd.command, recvd.id)
                        }
                        if (recvd is ResultMessage) {
                            deliverResult(recvd)
                        }
                    }
                } catch (e: Exception) {
                    onListenThreadException(e)
                }

            }
        }
        thread.start()
    }

    private fun onListenThreadException(e: Exception) {
        if (connected) {
            throw RuntimeException("Unexpected exception on listen thread", e)
        }
    }

    @Throws(IOException::class)
    private fun createStreams(socket: Socket) {
        outputStream = ObjectOutputStream(socket.getOutputStream())
        inputStream = ObjectInputStream(socket.getInputStream())
        println("Streams are created!")
    }

    private fun processCommand(command: Command<*>, id: Long) {
        executor.execute {
            try {
                val result = commandExecutor.execute(command)
                sendResultMsg(ResultMessage.Success(id, result!!))
            } catch (e: Exception) {
                sendResultMsg(ResultMessage.Error(id, e))
            }
        }
    }

    private fun deliverResult(resultMessage: ResultMessage) {
        val id = resultMessage.id
        val resultReceiver = receiversMap[id]
        resultReceiver?.setResult(resultMessage)
    }

    private fun sendResultMsg(resultMessage: ResultMessage) {
        sendObject(resultMessage)
    }

    private fun sendCommand(command: Command<*>, id: Long) {
        val msg = ExecuteMsg(command, id)
        sendObject(msg)
    }

    private fun sendObject(`object`: Any) {
        if (!connected) {
            throw IllegalStateException("Not connected")
        }
        try {
            outputStream.writeObject(`object`)
            outputStream.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    @Throws(IOException::class)
    private fun connectAsServer() {
        val serverSocket = ServerSocket(port)
        socket = serverSocket.accept()
        println("Connected as server. Creating streams...")
        createStreams(socket)
        startListenerThread()
    }

    @Throws(IOException::class)
    private fun connectAsClient() {
        socket = Socket(ip, port)
        println("Connected as client. Creating streams...")
        createStreams(socket)
        startListenerThread()
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