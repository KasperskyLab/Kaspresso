package com.kaspersky.adbserver.connection.api

/**
 * Interface for common Connection
 */
interface BaseConnection {

    /**
     * Try to connect.
     * The connection must be established by this method before any other operations
     */
    fun tryConnect()

    /**
     * If the connection is not needed then call this method to close all channels
     */
    fun tryDisconnect()

    /**
     * Return status of the connection
     */
    fun isConnected(): Boolean
}
