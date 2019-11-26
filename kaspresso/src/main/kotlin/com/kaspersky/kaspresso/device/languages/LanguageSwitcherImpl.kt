package com.kaspersky.kaspresso.device.languages

import android.app.Activity
import android.content.Context
import androidx.test.runner.lifecycle.ActivityLifecycleCallback
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.util.Locale

class LanguageSwitcherImpl(
    private val logger: UiTestLogger,
    private val context: Context
) : LanguageSwitcher {

    companion object {
        private const val TAG = "LanguageSwitcherImpl"
    }

    override fun switchLanguageInApp(locale: Locale) {
        logger.i(TAG, "Switch the language in the Application to $locale: start")
        var cachedActivity: Activity? = null
        val callback = ActivityLifecycleCallback { activity, stage ->
            if (stage == Stage.CREATED) {
                cachedActivity = activity
            }
        }
        ActivityLifecycleMonitorRegistry.getInstance().addLifecycleCallback(callback)

        try {
            applyCurrentLocaleToContext(
                context = cachedActivity ?: context,
                locale = locale
            )
            logger.i(TAG, "Switch the language in the Application to $locale: success")
        } catch (exception: Exception) {
            logger.e(TAG, "Switch the language in the Application to $locale: failed with the exception: $exception")
        } finally {
            ActivityLifecycleMonitorRegistry.getInstance().removeLifecycleCallback(callback)
        }
    }

    private fun applyCurrentLocaleToContext(context: Context, locale: Locale) {
        val resources = context.resources
        Locale.setDefault(locale)
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}