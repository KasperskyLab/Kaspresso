package com.kaspersky.components.alluresupport.runner

import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import io.qameta.allure.android.AllureAndroidLifecycle
import io.qameta.allure.android.listeners.ExternalStoragePermissionsListener
import io.qameta.allure.android.writer.TestStorageResultsWriter
import io.qameta.allure.kotlin.Allure
import io.qameta.allure.kotlin.junit4.AllureJunit4
import io.qameta.allure.kotlin.util.PropertiesUtils

open class KaspressoRunner : AndroidJUnitRunner() {

    private val useTestStorage: Boolean
        get() = PropertiesUtils.loadAllureProperties()
            .getProperty("allure.results.useTestStorage", "false")
            .toBoolean()

    override fun onCreate(arguments: Bundle) {
        Allure.lifecycle = createAllureAndroidLifecycle()
        arguments.putArgs(
            "listener",
            AllureJunit4::class.java.name,
            ExternalStoragePermissionsListener::class.java.name.takeIf { useTestStorage },
            KaspressoRunListener::class.java.name
        )
        super.onCreate(arguments)
    }

    protected open fun createAllureAndroidLifecycle(): AllureAndroidLifecycle = when (useTestStorage) {
        true -> AllureAndroidLifecycle(TestStorageResultsWriter())
        else -> AllureAndroidLifecycle()
    }

    private fun Bundle.putArgs(key: String, vararg values: CharSequence?) {
        val valuesArg: String = listOfNotNull<CharSequence>(
            getCharSequence(key),
            *values
        ).joinToString(separator = ",")
        putCharSequence(key, valuesArg)
    }
}
