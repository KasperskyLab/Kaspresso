package com.kaspersky.kaspresso.device.phone

import com.kaspersky.kaspresso.device.server.AdbServerWrapper

class PhoneImpl : Phone {

    /**
     *  Emulates incoming call.
     *
     *  Required Permissions: INTERNET
     */
    override fun emulateCall(number: String) {
        AdbServerWrapper.performAdb("emu gsm call $number")
    }

    /**
     *  Cancels incoming call.
     *
     *  Required Permissions: INTERNET
     */
    override fun cancelCall(number: String) {
        AdbServerWrapper.performAdb("emu gsm cancel $number")
    }

    /**
     *  Emulates receiving an SMS from [number].
     *
     *  Required Permissions: INTERNET
     */
    override fun receiveSms(number: String, text: String) {
        AdbServerWrapper.performAdb("emu sms send $number $text")
    }
}
