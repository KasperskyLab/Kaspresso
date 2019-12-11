package com.kaspersky.kaspresso.docloc.rule

import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.docloc.SystemLanguageSwitcher
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

    private val systemLanguageSwitcher: SystemLanguageSwitcher =
        SystemLanguageSwitcher(device.context, logger, device.hackPermissions)

    private val deviceLocale: Locale = Locale.getDefault()
    private var currentLocale: Locale? = null

    val currentLocaleName: String
        get() = currentLocale.toString()

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
        try {
            locales.onEach { locale ->
                currentLocale = locale
                logger.i("DocLoc: changeLanguageInApp is processing. New language=$locale is installing")
                device.languageSwitcher.switchLanguageInApp(locale)
                logger.i("DocLoc: changeLanguageInApp is processing. New language=$locale is installed")
                base.evaluate()
            }
        } finally {
            logger.i("DocLoc: changeLanguageInApp is finishing. Device language=$deviceLocale is restoring")
            currentLocale = deviceLocale
            device.languageSwitcher.switchLanguageInApp(deviceLocale)
            logger.i("DocLoc: changeLanguageInApp is finishing. Device language=$deviceLocale is restored")
        }
    }

    private fun changeLanguageInOs(base: Statement) {
        logger.i("DocLoc: changeLanguageInOs started")
        try {
            locales.onEach { locale ->
                currentLocale = locale
                logger.i("DocLoc: changeLanguageInOs is processing. New language=$locale is installing")
                systemLanguageSwitcher.changeLanguage(locale)
                logger.i("DocLoc: changeLanguageInOs is processing. New language=$locale is installed")
                base.evaluate()
            }
        } finally {
            logger.i("DocLoc: changeLanguageInOs is finishing. Device language=$deviceLocale is restoring")
            currentLocale = deviceLocale
            systemLanguageSwitcher.changeLanguage(deviceLocale)
            logger.i("DocLoc: changeLanguageInOs is finishing. Device language=$deviceLocale is restored")
        }
    }
}
