package com.kaspersky.components.alluresupport.runlisteners

import com.kaspersky.kaspresso.runner.listener.KaspressoLateRunListener
import io.qameta.allure.android.AllureAndroidLifecycle
import io.qameta.allure.android.listeners.ExternalStoragePermissionsListener
import io.qameta.allure.android.writer.TestStorageResultsWriter
import io.qameta.allure.kotlin.Allure
import io.qameta.allure.kotlin.junit4.AllureJunit4
import io.qameta.allure.kotlin.util.PropertiesUtils
import org.junit.runner.Description
import org.junit.runner.Result
import org.junit.runner.notification.Failure

/**
 * This type is a proxy between kaspresso runner and allure listener.
 * Since kaspresso uses it's own runner it need to create allure lifecycle
 * and notify allure about test progress from our side.
 *
 * @property allureJunit4 allure listener
 * @property externalStoragePermissionsListener used to notify allure that
 * external storage should be used depending on allure.results.useTestStorage property
 */
class AllureRunListener : KaspressoLateRunListener {
    private val allureJunit4: AllureJunit4
    private val externalStoragePermissionsListener: ExternalStoragePermissionsListener?

    private val useTestStorage: Boolean
        get() = PropertiesUtils.loadAllureProperties()
            .getProperty("allure.results.useTestStorage", "false")
            .toBoolean()

    init {
        Allure.lifecycle = createAllureAndroidLifecycle()
        allureJunit4 = AllureJunit4()
        externalStoragePermissionsListener = if (useTestStorage) ExternalStoragePermissionsListener() else null
    }

    override fun testRunStarted(description: Description) {
        allureJunit4.testRunStarted(description)
        externalStoragePermissionsListener?.testRunStarted(description)
    }

    override fun testStarted(description: Description) {
        allureJunit4.testStarted(description)
    }

    override fun testFinished(description: Description) {
        allureJunit4.testFinished(description)
    }

    override fun testFailure(failure: Failure) {
        allureJunit4.testFailure(failure)
    }

    override fun testAssumptionFailure(failure: Failure) {
        allureJunit4.testAssumptionFailure(failure)
    }

    override fun testIgnored(description: Description) {
        allureJunit4.testIgnored(description)
    }

    override fun testRunFinished(result: Result) {
        allureJunit4.testRunFinished(result)
    }

    private fun createAllureAndroidLifecycle(): AllureAndroidLifecycle = when (useTestStorage) {
        true -> AllureAndroidLifecycle(TestStorageResultsWriter())
        else -> AllureAndroidLifecycle()
    }
}
