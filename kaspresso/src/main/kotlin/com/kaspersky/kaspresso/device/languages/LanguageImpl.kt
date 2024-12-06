package com.kaspersky.kaspresso.device.languages

import android.app.Instrumentation
import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.ConfigurationCompat
import androidx.core.os.LocaleListCompat
import com.kaspersky.kaspresso.docloc.SystemLanguage
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.util.Locale

/**
 * The implementation of [Language]
 */
open class LanguageImpl(
    private val logger: UiTestLogger,
    private val instrumentation: Instrumentation,
    private val systemLanguage: SystemLanguage,
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
        } catch (e: NoSuchMethodError) {
            val message = """
                For in-app switching language you should use at least 1.6.0 version of appcompat library.
                Please find this dependency and increase version to androidx.appcompat:appcompat:1.6.0 or higher.
                """.trimIndent()
            throw RuntimeException(message)
        } catch (error: Throwable) {
            logger.e("Switch the language in the Application to $locale: failed with the error: $error")
            throw error
        }
    }

    override fun switchInSystem(locale: Locale) = systemLanguage.switch(locale)

    private fun getCurrentLocale(): Locale? =
        ConfigurationCompat.getLocales(instrumentation.targetContext.resources.configuration).get(0)

    private fun applyCurrentLocaleToContext(locale: Locale) {
        // For issue DocLocScreenshotTestCase does not change locale to sr-Latn-RS: https://github.com/KasperskyLab/Kaspresso/issues/389
        // added hotfix. For next releases we will find more correct solution.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (locale.country.equals("Latn", true) &&
                locale.language.equals("sr", true)
            ) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    val localeManager = instrumentation.targetContext.getSystemService(Context.LOCALE_SERVICE) as LocaleManager
                    localeManager.applicationLocales = LocaleList.forLanguageTags(Locale.Builder().setLanguage("sr").setScript("Latn").build().toLanguageTag())
                } else {
                    Handler(instrumentation.targetContext.mainLooper).post {
                        AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(Locale.Builder().setLanguage("sr").setScript("Latn").build()))
                    }
                }
                return
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val localeManager = instrumentation.targetContext.getSystemService(Context.LOCALE_SERVICE) as LocaleManager
            localeManager.applicationLocales = LocaleList.forLanguageTags(locale.toLanguageTag())
        } else {
            Handler(instrumentation.targetContext.mainLooper).post {
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(locale))
            }
        }
    }
}
