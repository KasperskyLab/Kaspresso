package com.kaspersky.adbserver.implementation.transferring

import com.kaspersky.adbserver.implementation.lightsocket.LightSocketWrapper
import com.kaspresky.adbserver.log.LoggerFactory
import java.io.EOFException
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

    fun prepareListening() {
        logger.d("prepareListening", "start")
        try {
            outputStream = ObjectOutputStream(lightSocketWrapper.getOutputStream())
            inputStream = ObjectInputStream(lightSocketWrapper.getInputStream())
            logger.d("prepareListening", "IO Streams were created")
        } catch (exception: EOFException) {
            throw ExpectedEOFException()
        }
    }

    fun startListening(listener: (ReceiveModel) -> Unit) {
        logger.d("startListening", "start")
        messagesListener = listener
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
            disruptAction.invoke()
        }
    }

    fun sendDesktopName(desktopName: String) {
        logger.d("sendDesktopName", "where desktopName=$desktopName")
        try {
            outputStream.writeObject(desktopName)
            outputStream.flush()
        } catch (exception: Exception) {
            logger.e("sendDesktopName", exception)
        }
    }

    fun stopListening() {
        isRunning.set(false)
    }

    private inner class MessagesListeningThread : Thread() {
        override fun run() {
            logger.d("MessagesListeningThread.run", "Start listening")
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
                logger.d("MessagesListeningThread.peekNextMessage", "The message=$obj")
                messagesListener.invoke(obj as ReceiveModel)
            } else if (obj.javaClass == String::class.java) {
                // todo remove it
                LoggerFactory.setDesktopName(obj as String)
            } else {
                logger.e(
                    "MessagesListeningThread.peekNextMessage",
                    "The message=$obj but this message type is not $receiveModelClass"
                )
                disruptAction.invoke()
            }
        } catch (exception: Exception) {
            if (exception is EOFException) {
                logger.d("MessagesListeningThread.peekNextMessage",
                    "EOFException occurred in Socket inputStream. The most possible reason is the opposite socket just broke up the connection. " +
                            "Additional info: exception=$exception")
            } else {
                logger.e("MessagesListeningThread.peekNextMessage", exception)
            }
            disruptAction.invoke()
        }
    }
}
