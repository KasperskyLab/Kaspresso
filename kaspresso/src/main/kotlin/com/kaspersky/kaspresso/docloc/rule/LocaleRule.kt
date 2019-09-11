package com.kaspersky.kaspresso.docloc.rule

import android.Manifest
import android.app.Activity
import android.content.Context
import androidx.test.runner.lifecycle.ActivityLifecycleCallback
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.docloc.DoclocException
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
    private val changeSystemLocale: Boolean,
    private val logger: UiTestLogger
) : TestRule {

    private val localeSettings: LocaleSettings = LocaleSettings(device.context, logger)

    private var deviceLocale: Locale? = null
    private var currentLocale: Locale? = null

    val locale: Locale
        get() = currentLocale!!

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                if (changeSystemLocale) changeLanguageInOs(base)
                else changeLanguageInApp(base)
            }
        }
    }

    private fun changeLanguageInApp(base: Statement) {
        logger.i("DocLoc: changeLanguageInApp started")
        var cachedActivity: Activity? = null
        val callback = ActivityLifecycleCallback { activity, stage ->
            if (stage == Stage.CREATED) {
                cachedActivity = activity
            }
        }
        ActivityLifecycleMonitorRegistry.getInstance().addLifecycleCallback(callback)

        try {
            deviceLocale = Locale.getDefault()

            for (locale in locales) {
                currentLocale = locale
                logger.i("DocLoc: changeLanguageInApp is processing. New language = $locale")
                applyCurrentLocaleToContext(cachedActivity ?: device.targetContext)
                base.evaluate()
            }
        } finally {
            if (deviceLocale != null) {
                currentLocale = deviceLocale!!
                logger.i("DocLoc: changeLanguageInOs is finishing. Return to language = $currentLocale")
                applyCurrentLocaleToContext(device.targetContext)
            }
            ActivityLifecycleMonitorRegistry.getInstance().removeLifecycleCallback(callback)
        }
    }

    private fun applyCurrentLocaleToContext(context: Context) {
        val resources = context.resources
        Locale.setDefault(currentLocale)
        val configuration = resources.configuration
        configuration.setLocale(currentLocale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    private fun changeLanguageInOs(base: Statement) {
        logger.i("DocLoc: changeLanguageInOs started")
        val permissionGranted = device.hackPermissions.grant(device.targetContext.packageName, Manifest.permission.CHANGE_CONFIGURATION)
        if (!permissionGranted) {
            throw DoclocException("The attempt to grant Manifest.permission.CHANGE_CONFIGURATION for " +
                    "DocLoc screenshots of system windows failed"
            )
        }
        try {
            deviceLocale = Locale.getDefault()
            for (locale in locales) {
                currentLocale = locale
                logger.i("DocLoc: changeLanguageInOs is processing. New language = $locale")
                localeSettings.changeLanguage(locale)
                base.evaluate()
            }
        } finally {
            if (deviceLocale != null) {
                currentLocale = deviceLocale!!
                logger.i("DocLoc: changeLanguageInOs is finishing. Return to language = $currentLocale")
                currentLocale?.let { localeSettings.changeLanguage(it) }
            }
        }
    }
}
