package com.kaspersky.kaspresso.device.phone

import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of the [Phone] interface.
 */
class PhoneImpl(
    private val logger: UiTestLogger,
    private val adbServer: AdbServer
) : Phone {

    /**
     * Emulates incoming call.
     *
     * Required Permissions: INTERNET
     */
    override fun emulateCall(number: String) {
        logger.i("Emulate incoming call from $number")
        adbServer.performAdb("emu gsm call $number")
    }

    /**
     * Cancels incoming call.
     *
     * Required Permissions: INTERNET
     */
    override fun cancelCall(number: String) {
        logger.i("Cancel incoming call from $number")
        adbServer.performAdb("emu gsm cancel $number")
    }

    /**
     * Emulates receiving an SMS from [number].
     *
     * Required Permissions: INTERNET
     */
    override fun receiveSms(number: String, text: String) {
        logger.i("Emulate receiving an sms from $number")
        adbServer.performAdb("emu sms send $number $text")
    }
}
