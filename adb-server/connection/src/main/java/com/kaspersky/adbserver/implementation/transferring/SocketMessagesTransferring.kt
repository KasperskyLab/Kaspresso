package com.kaspersky.adbserver.implementation.transferring

import com.kaspersky.adbserver.implementation.lightsocket.LightSocketWrapper
import com.kaspresky.adbserver.log.LoggerFactory
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.concurrent.atomic.AtomicBoolean

internal class SocketMessagesTransferring<ReceiveModel, SendModel> private constructor(
    private val lightSocketWrapper: LightSocketWrapper,
    private val receiveModelClass: Class<ReceiveModel>,
    private val sendModelClass: Class<SendModel>,
    private val disruptAction: () -> Unit,
    deviceName: String?
) {

    companion object {
        inline fun <reified Receive, reified Send> createTransferring(
            lightSocketWrapper: LightSocketWrapper,
            noinline disruptAction: () -> Unit,
            deviceName: String? = null
        ): SocketMessagesTransferring<Receive, Send> {
            return SocketMessagesTransferring(
                lightSocketWrapper,
                Receive::class.java,
                Send::class.java,
                disruptAction,
                deviceName
            )
        }
    }

    private val logger = LoggerFactory.getLogger(tag = javaClass.simpleName, deviceName = deviceName)

    private lateinit var inputStream: ObjectInputStream
    private lateinit var outputStream: ObjectOutputStream
    private lateinit var messagesListener: (ReceiveModel) -> Unit

    private val isRunning: AtomicBoolean = AtomicBoolean(false)

    fun startListening(listener: (ReceiveModel) -> Unit) {
        logger.d("startListening", "start")
        messagesListener = listener
        try {
            outputStream = ObjectOutputStream(lightSocketWrapper.getOutputStream())
            inputStream = ObjectInputStream(lightSocketWrapper.getInputStream())
            logger.d("startListening", "IO Streams were created")
        } catch (exception: Exception) {
            disruptAction.invoke()
            return
        }
        startHandleMessages()
    }

    private fun startHandleMessages() {
        isRunning.set(true)
        MessagesListeningThread().start()
    }

    fun sendMessage(sendModel: SendModel) {
        logger.d("sendMessage", "where sendModel=$sendModel")
        try {
            outputStream.writeObject(sendModel)
            outputStream.flush()
        } catch (exception: Exception) {
            logger.e("sendMessage", exception)
        }
    }

    fun stopListening() {
        isRunning.set(false)
    }

    private inner class MessagesListeningThread : Thread() {
        override fun run() {
            logger.d("MessagesListeningThread.run", "start to work")
            while (isRunning.get()) {
                peekNextMessage()
            }
        }
    }

    private fun peekNextMessage() {
        val obj: Any
        try {
            obj = inputStream.readObject()
            if (obj.javaClass == receiveModelClass) {
                logger.d("MessagesListeningThread.peekNextMessage", "with message=$obj")
                messagesListener.invoke(obj as ReceiveModel)
            } else {
                logger.d("MessagesListeningThread.peekNextMessage", "with message=$obj" +
                        " but this message type is not $receiveModelClass"
                )
                disruptAction.invoke()
            }
        } catch (exception: Exception) {
            logger.e("MessagesListeningThread.peekNextMessage", exception)
            disruptAction.invoke()
        }
    }
}
