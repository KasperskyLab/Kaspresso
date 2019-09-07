package com.kaspersky.kaspresso.device.phone

import com.kaspersky.kaspresso.device.server.AdbServer

class PhoneImpl(
    private val adbServer: AdbServer
) : Phone {

    /**
     *  Emulates incoming call.
     *
     *  Required Permissions: INTERNET
     */
    override fun emulateCall(number: String) {
        adbServer.performAdb("emu gsm call $number")
    }

    /**
     *  Cancels incoming call.
     *
     *  Required Permissions: INTERNET
     */
    override fun cancelCall(number: String) {
        adbServer.performAdb("emu gsm cancel $number")
    }

    /**
     *  Emulates receiving an SMS from [number].
     *
     *  Required Permissions: INTERNET
     */
    override fun receiveSms(number: String, text: String) {
        adbServer.performAdb("emu sms send $number $text")
    }
}
