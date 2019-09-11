package com.kaspersky.kaspresso.docloc.rule

import android.Manifest
import android.app.Activity
import android.content.Context
import androidx.test.runner.lifecycle.ActivityLifecycleCallback
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.docloc.LocaleSettings
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.util.Locale
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * The test rule to switch locales.
 */
class LocaleRule internal constructor(
    private val locales: Set<Locale>,
    private val device: Device,
    changeSystemLocale: Boolean,
    logger: UiTestLogger
) : TestRule {

    private val localeSettings: LocaleSettings = LocaleSettings(device.context, logger)
    private val systemLocaleShouldBeChanged: Boolean = changeSystemLocale &&
            device.hackPermissions.grant(device.targetContext.packageName, Manifest.permission.CHANGE_CONFIGURATION)

    private var deviceLocale: Locale? = null
    private var currentLocale: Locale? = null

    val locale: Locale
        get() = currentLocale!!

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {

            override fun evaluate() {
                var cachedActivity: Activity? = null
                val callback = ActivityLifecycleCallback { activity, stage ->
                    if (stage == Stage.CREATED) {
                        applyCurrentLocaleToContext(activity)
                        cachedActivity = activity
                    }
                }

                ActivityLifecycleMonitorRegistry.getInstance().addLifecycleCallback(callback)

                try {
                    deviceLocale = Locale.getDefault()

                    for (locale in locales) {
                        currentLocale = locale
                        if (systemLocaleShouldBeChanged) {
                            localeSettings.changeLanguage(locale)
                        } else {
                            applyCurrentLocaleToContext(cachedActivity ?: device.context)
                        }
                        base.evaluate()
                    }
                } finally {
                    if (deviceLocale != null) {
                        currentLocale = deviceLocale!!
                        applyCurrentLocaleToContext(cachedActivity ?: device.context)
                    }
                    ActivityLifecycleMonitorRegistry.getInstance().removeLifecycleCallback(callback)
                }
            }
        }
    }

    private fun applyCurrentLocaleToContext(context: Context) {
        val resources = context.resources
        Locale.setDefault(currentLocale)
        val configuration = resources.configuration
        configuration.setLocale(currentLocale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}
