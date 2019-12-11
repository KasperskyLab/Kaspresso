package com.kaspersky.kaspresso.docloc.rule

import android.Manifest
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

    private val deviceLocale: Locale = Locale.getDefault()
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
        try {
            locales.onEach {
                currentLocale = it
                logger.i("DocLoc: changeLanguageInApp is processing. New language=$it is installing")
                device.languageSwitcher.switchLanguageInApp(it)
                logger.i("DocLoc: changeLanguageInApp is processing. New language=$it is installed")
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
        val permissionGranted = device.hackPermissions.grant(device.targetContext.packageName, Manifest.permission.CHANGE_CONFIGURATION)
        if (!permissionGranted) {
            throw DoclocException("The attempt to grant Manifest.permission.CHANGE_CONFIGURATION for " +
                    "DocLoc screenshots of system windows failed"
            )
        }
        try {
            locales.onEach {
                currentLocale = it
                logger.i("DocLoc: changeLanguageInOs is processing. New language=$it is installing")
                localeSettings.changeLanguage(it)
                logger.i("DocLoc: changeLanguageInOs is processing. New language=$it is installed")
                base.evaluate()
            }
        } finally {
            logger.i("DocLoc: changeLanguageInOs is finishing. Device language=$deviceLocale is restoring")
            currentLocale = deviceLocale
            localeSettings.changeLanguage(deviceLocale)
            logger.i("DocLoc: changeLanguageInOs is finishing. Device language=$deviceLocale is restored")
        }
    }
}
