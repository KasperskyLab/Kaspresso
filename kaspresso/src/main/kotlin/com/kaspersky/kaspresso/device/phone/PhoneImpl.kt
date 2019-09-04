package com.kaspersky.kaspresso.device.phone

import com.kaspersky.kaspresso.device.server.AdbServer

class PhoneImpl : Phone {

    /**
     *  Emulates incoming call.
     *
     *  Required Permissions: INTERNET
     */
    override fun emulateCall(number: String) {
        AdbServer.performAdb("emu gsm call $number")
    }

    /**
     *  Cancels incoming call.
     *
     *  Required Permissions: INTERNET
     */
    override fun cancelCall(number: String) {
        AdbServer.performAdb("emu gsm cancel $number")
    }

    /**
     *  Emulates receiving an SMS from [number].
     *
     *  Required Permissions: INTERNET
     */
    override fun receiveSms(number: String, text: String) {
        AdbServer.performAdb("emu sms send $number $text")
    }
}
