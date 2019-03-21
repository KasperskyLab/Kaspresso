package com.kaspersky.kaspresso.device.server

/**
 * An interface to work with adb server.
 */
interface AdbServer {

    /**
     *  Executes shell commands blocking current thread.
     */
    fun performCmd(vararg commands: String)

    /**
     *  Performs adb commands blocking current thread.
     */
    fun performAdb(vararg commands: String)
}