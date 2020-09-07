package com.kaspersky.adbserver.connection.implementation.lightsocket

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

/**
 * Wrap of a Socket to restrict possibilities to interact with the Socket directly
 */
internal interface LightSocketWrapper {

    @Throws(IOException::class)
    fun getOutputStream(): OutputStream

    @Throws(IOException::class)
    fun getInputStream(): InputStream
}
