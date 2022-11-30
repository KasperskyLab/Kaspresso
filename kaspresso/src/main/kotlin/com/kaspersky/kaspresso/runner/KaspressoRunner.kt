package com.kaspersky.kaspresso.runner

import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import com.kaspersky.kaspresso.runner.listener.KaspressoRunNotifier
import com.kaspersky.kaspresso.runner.listener.KaspressoRunNotifierImpl
import com.kaspersky.kaspresso.runner.listener.SpyRunListener

@Suppress("UNUSED")
class KaspressoRunner : AndroidJUnitRunner(), KaspressoRunNotifier by KaspressoRunNotifierImpl() {

    override fun onCreate(arguments: Bundle) {
        arguments.putArgs("listener", SpyRunListener::class.java.name)
        super.onCreate(arguments)
    }

    private fun Bundle.putArgs(key: String, vararg values: CharSequence?) {
        val valuesArg: String = listOfNotNull<CharSequence>(
            getCharSequence(key),
            *values
        ).joinToString(separator = ",")
        putCharSequence(key, valuesArg)
    }
}
