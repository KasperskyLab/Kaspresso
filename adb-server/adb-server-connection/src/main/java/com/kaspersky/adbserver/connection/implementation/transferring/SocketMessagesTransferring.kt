package com.kaspersky.adbserver.connection.implementation.transferring

import com.kaspersky.adbserver.connection.implementation.lightsocket.LightSocketWrapper
import com.kaspersky.adbserver.common.log.logger.Logger
import java.io.EOFException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.concurrent.atomic.AtomicBoolean

internal class SocketMessagesTransferring<ReceiveModel, SendModel> private constructor(
    private val lightSocketWrapper: LightSocketWrapper,
    private val receiveModelClass: Class<ReceiveModel>,
    private val sendModelClass: Class<SendModel>,
    private val disruptAction: () -> Unit,
    private val logger: Logger
) {

    companion object {
        inline fun <reified Receive, reified Send> createTransferring(
            lightSocketWrapper: LightSocketWrapper,
            noinline disruptAction: () -> Unit,
            logger: Logger
        ): SocketMessagesTransferring<Receive, Send> {
            return SocketMessagesTransferring(
                lightSocketWrapper,
                Receive::class.java,
                Send::class.java,
                disruptAction,
                logger
            )
        }
    }

    private lateinit var inputStream: ObjectInputStream
    private lateinit var outputStream: ObjectOutputStream
    private lateinit var messagesListener: (ReceiveModel) -> Unit

    private val isRunning: AtomicBoolean = AtomicBoolean(false)

    fun prepareListening() {
        logger.d("Start")
        try {
            outputStream = ObjectOutputStream(lightSocketWrapper.getOutputStream())
            inputStream = ObjectInputStream(lightSocketWrapper.getInputStream())
            logger.d("IO Streams were created")
        } catch (exception: EOFException) {
            throw ExpectedEOFException()
        }
    }

    fun startListening(listener: (ReceiveModel) -> Unit) {
        logger.d("Started")
        messagesListener = listener
        isRunning.set(true)
        MessagesListeningThread().start()
    }

    fun sendMessage(sendModel: SendModel) {
        logger.d("Input sendModel=$sendModel")
        try {
            outputStream.writeObject(sendModel)
            outputStream.flush()
        } catch (exception: Throwable) {
            logger.e(exception.localizedMessage ?: exception.message ?: "")
            disruptAction.invoke()
        }
    }

    fun stopListening() {
        isRunning.set(false)
    }

    private inner class MessagesListeningThread : Thread() {
        override fun run() {
            logger.d("Start listening")
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
                logger.d("The message=$obj")
                messagesListener.invoke(obj as ReceiveModel)
            } else {
                logger.e("The message=$obj but this message type is not $receiveModelClass")
                disruptAction.invoke()
            }
        } catch (exception: Throwable) {
            if (!isRunning.get()) {
                // there is no mind to analyse exceptions after the socket connection was interrupted
                return
            }
            if (exception is EOFException) {
                logger.d(
                    "EOFException occurred in Socket inputStream. The most possible reason is the opposite socket just broke up the connection. " +
                            "Additional info: exception=$exception"
                )
            } else {
                logger.e(exception.localizedMessage)
            }
            disruptAction.invoke()
        }
    }
}
