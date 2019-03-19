package com.kaspersky.uitest_framework.server

interface ServerInterface {

    /**
     *  Executes shell commands blocking current thread.
     */
    fun performCmd(vararg commands: String)

    /**
     *  Performs adb commands blocking current thread.
     */
    fun performAdb(vararg commands: String)
}
