package com.kaspersky.kaspresso.device.languages

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.ConfigurationCompat
import androidx.core.os.LocaleListCompat
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.util.Locale

/**
 * The implementation of [Language]
 */
class LanguageImpl(
    private val logger: UiTestLogger,
    private val context: Context
) : Language {

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
            applyCurrentLocaleToContext(locale = locale)
            logger.i("Switch the language in the Application to $locale: success")
        } catch (reflectiveException: NoSuchFieldException) {
            val message = """
                For in-app switching language you should use at least 1.6.0 version of appcompat library.
                Please find this dependency and increase version to androidx.appcompat:appcompat:1.6.0 or higher
                """.trimIndent()
            throw RuntimeException(message)
        } catch (error: Throwable) {
            logger.e("Switch the language in the Application to $locale: failed with the error: $error")
            throw error
        }
    }

    private fun getCurrentLocale(): Locale? =
        ConfigurationCompat.getLocales(context.resources.configuration).get(0)

    private fun applyCurrentLocaleToContext(locale: Locale) {
        /*
        We need to change locale in tested app so AppCompatDelegate must use it's context.
        But method setAppContext is private so we need to use reflection
        */
        AppCompatDelegate::class.java.getDeclaredField("sAppContext").apply {
            isAccessible = true
            set(null, context.applicationContext)
        }

        AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(locale))
    }
}
