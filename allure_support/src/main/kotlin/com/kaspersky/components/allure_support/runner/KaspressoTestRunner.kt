package com.kaspersky.components.allure_support.runner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.test.internal.runner.listener.InstrumentationRunListener
import androidx.test.runner.AndroidJUnitRunner
import androidx.test.uiautomator.UiDevice
import io.qameta.allure.espresso.AllureAndroidListener
import java.io.IOException
import kotlin.reflect.KClass

private const val WRITE_EXT_STORAGE_PERMISSION: String = "android.permission.WRITE_EXTERNAL_STORAGE"
private const val READ_EXT_STORAGE_PERMISSION: String = "android.permission.READ_EXTERNAL_STORAGE"

class KaspressoTestRunner : AndroidJUnitRunner() {

    override fun onCreate(arguments: Bundle?) {
        Log.d("KASPRESSO", "Creating KaspressoTestRunner")
        arguments?.apply {
            Log.d("KASPRESSO", "Registering AllureAndroidListener")
            putCharSequence("listener", provideListenersWith(AllureAndroidListener::class))
        }
        super.onCreate(arguments)
    }

    override fun onStart() {
        Log.d("KASPRESSO", "KaspressoTestRunner starts running tests")
        grantStoragePermissions()
        closeSystemDialogs()
        super.onStart()
    }

    private fun closeSystemDialogs() {
        Log.d("KASPRESSO", "Closing system dialogs")
        targetContext.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
    }

    /**
     * Ensure storage permissions are granted.
     * <p>
     * Trying to fight
     * "FileNotFoundException: /storage/emulated/0/allure-results/".
     * Probably AllureAndroidListener ignores silently when fails to create "allure-results" dir.
     * <p>
     * NOTE: to create directories, granting permission needed, so method must be called
     * after Application was fully established.
     */
    private fun grantStoragePermissions() {
        try {
            Log.d("KASPRESSO", "Granting storage read/write permissions")
            UiDevice.getInstance(this).apply {
                grantPermission(context.packageName, WRITE_EXT_STORAGE_PERMISSION)
                grantPermission(context.packageName, READ_EXT_STORAGE_PERMISSION)
                grantPermission(targetContext.packageName, WRITE_EXT_STORAGE_PERMISSION)
                grantPermission(targetContext.packageName, READ_EXT_STORAGE_PERMISSION)
            }
        } catch (e: IOException) {
            Log.d("KASPRESSO", "Grant read/write storage permissions failed")
            throw RuntimeException(e)
        }
    }

    @Throws(IOException::class)
    private fun UiDevice.grantPermission(packageName: String, permission: String): String =
        executeShellCommand("pm grant $packageName $permission")

    private fun Bundle.provideListenersWith(
        vararg addListeners: KClass<out InstrumentationRunListener>
    ): CharSequence = getCharSequence("listener").withListeners(*addListeners)

    private fun CharSequence?.withListeners(
        vararg addListeners: KClass<out InstrumentationRunListener>
    ): CharSequence = (this?.let { "$it," } ?: "") + addListeners.joinToString(",") { it.java.name }
}
