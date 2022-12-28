package com.kaspersky.kaspresso.runner

import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import com.kaspersky.kaspresso.runner.listener.KaspressoRunNotifier
import com.kaspersky.kaspresso.runner.listener.KaspressoRunNotifierImpl
import com.kaspersky.kaspresso.runner.listener.SpyRunListener

@Suppress("UNUSED")
open class KaspressoRunner : AndroidJUnitRunner() {

    val runNotifier: KaspressoRunNotifier = KaspressoRunNotifierImpl()

    override fun onCreate(arguments: Bundle) {
        // Listeners have to be set through extras https://developer.android.com/reference/androidx/test/runner/AndroidJUnitRunner
        arguments.putArgs("listener", SpyRunListener::class.java.name)
        super.onCreate(arguments)
    }

    private fun Bundle.putArgs(key: String, vararg values: CharSequence?) {
        val valuesArg: String = listOfNotNull(
            getCharSequence(key),
            *values
        ).joinToString(separator = ",")
        putCharSequence(key, valuesArg)
    }
}
