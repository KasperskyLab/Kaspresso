package com.kaspersky.kaspresso.device.languages

import android.app.Activity
import android.content.Context
import androidx.core.os.ConfigurationCompat
import androidx.test.runner.lifecycle.ActivityLifecycleCallback
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.util.Locale

/**
 * The implementation of [Language]
 */
class LanguageImpl(
    private val logger: UiTestLogger,
    private val context: Context
) : Language {

    private var cachedActivity: Activity? = null
    private val lifecycleCallback: ActivityLifecycleCallback =
        ActivityLifecycleCallback { activity, stage ->
            if (stage == Stage.CREATED) {
                cachedActivity = activity
            }
        }

    init {
        ActivityLifecycleMonitorRegistry.getInstance().addLifecycleCallback(lifecycleCallback)
    }

    override fun switchInApp(locale: Locale) {
        logger.i("Switch the language in the Application to $locale: start")
        if (getCurrentLocale() == locale) {
            logger.i(
                "Switch the language in the Application to $locale is not needed " +
                    "because it's a current app's language"
            )
            return
        }

        try {
            applyCurrentLocaleToContext(
                context = cachedActivity ?: context,
                locale = locale
            )
            logger.i("Switch the language in the Application to $locale: success")
        } catch (error: Throwable) {
            logger.e("Switch the language in the Application to $locale: failed with the error: $error")
            throw error
        } finally {
            cachedActivity = null
        }
    }

    private fun getCurrentLocale(): Locale =
        ConfigurationCompat.getLocales(context.resources.configuration).get(0)

    @Suppress("DEPRECATION")
    private fun applyCurrentLocaleToContext(context: Context, locale: Locale) {
        // resources.updateConfiguration is deprecated method
        // which must be replaced by createConfigurationContext method
        // but in such a case, a developer must put created context in Application.attachBaseContext
        // we take time to think about it
        // details are available by this reference
        // https://proandroiddev.com/change-language-programmatically-at-runtime-on-android-5e6bc15c758
        val resources = context.resources
        Locale.setDefault(locale)
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}