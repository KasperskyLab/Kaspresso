package com.kaspersky.kaspresso.device.locales

import androidx.test.platform.app.InstrumentationRegistry
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.util.Locale

/**
 * The utility class for handling locale parsing.
 */
internal class Locales(
    private val logger: UiTestLogger
) {

    companion object {
        private const val LOCALES_ARG = "localizations"
        private const val LANGUAGE_COUNTRY_SEPARATOR = "-"
        private const val DEFAULT_LOCALES = "en,ru"
    }

    /**
     * Returns set of supported locales for test.
     * If csv string with locales is passed to test by localizations key its value will be used.
     * Otherwise, default localizations will be applied (en,ru).
     *
     * @return set of supported locales.
     */
    fun getSupportedLocales(): Set<Locale> {
        return parseLocales(getLocaleString())
    }

    /**
     * Parses comma-separated string to set of locales.
     *
     * @param locales comma-separated string containing locales.
     * @return set of pares locales.
     */
    fun parseLocales(locales: String): Set<Locale> {
        val localizationsArray = locales.replace(" ", "").split(",")
        return localizationsArray
            .map { createLocale(it) }
            .toSet()
    }

    private fun createLocale(locale: String): Locale {
        if (locale.isBlank()) throw IllegalArgumentException("Blank locale string")

        return if (locale.contains(LANGUAGE_COUNTRY_SEPARATOR)) {
            val langCountry = locale.split(LANGUAGE_COUNTRY_SEPARATOR)
            Locale(langCountry[0], langCountry[1])
        } else {
            Locale(locale, "")
        }
    }

    private fun getLocaleString(): String {
        val localizations: String? = InstrumentationRegistry.getArguments().getString(LOCALES_ARG)
        return if (localizations.isNullOrEmpty()) {
            logger.d("Parameter localizations is not set, default value is used: $DEFAULT_LOCALES")
            DEFAULT_LOCALES
        } else {
            localizations
        }
    }
}
