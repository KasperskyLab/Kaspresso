package com.kaspersky.adbserver.implementation.lightsocket

import java.io.InputStream
import java.io.OutputStream
import java.net.Socket

internal class LightSocketWrapperImpl(private val socket: Socket) :
    LightSocketWrapper {

    override fun getOutputStream(): OutputStream = socket.getOutputStream()

    override fun getInputStream(): InputStream = socket.getInputStream()
}
