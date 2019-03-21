package com.kaspersky.kaspresso.device.server

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