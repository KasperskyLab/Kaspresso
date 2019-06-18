package com.kaspersky.kaspresso.device.phone

interface Phone {

    /**
     *  Emulates incoming call.
     *
     *  Required Permissions: INTERNET
     */
    fun emulateCall(number: String)

    /**
     *  Cancels incoming call.
     *
     *  Required Permissions: INTERNET
     */
    fun cancelCall(number: String)

    /**
     *  Emulates receiving an SMS from [number].
     *
     *  Required Permissions: INTERNET
     */
    fun receiveSms(number: String, text: String)
}
