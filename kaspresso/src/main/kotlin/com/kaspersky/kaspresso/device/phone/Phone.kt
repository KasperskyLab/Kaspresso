package com.kaspersky.kaspresso.device.phone

import com.kaspersky.kaspresso.annotations.RequiresAdbServer

/**
 * The interface to work with telephony.
 *
 * Required: Started AdbServer
 *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
 *     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"
 * Methods demanding to use AdbServer in the default implementation of this interface are marked.
 *     But nobody can't deprecate you to write implementation that doesn't require AdbServer.
 */
interface Phone {

    /**
     * Emulates incoming call.
     *
     * Required Permissions: INTERNET
     */
    @RequiresAdbServer
    fun emulateCall(number: String)

    /**
     * Cancels incoming call.
     *
     * Required Permissions: INTERNET
     */
    @RequiresAdbServer
    fun cancelCall(number: String)

    /**
     * Emulates receiving an SMS from [number].
     *
     * Required Permissions: INTERNET
     */
    @RequiresAdbServer
    fun receiveSms(number: String, text: String)
}
