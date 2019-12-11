package com.kaspersky.kaspresso.device.languages

import java.util.Locale

/**
 * The switcher of languages
 */
interface LanguageSwitcher {

    /**
     * Switches language only in the current Application (not in OS!).
     * Please, keep in mind the following fact:
     * If you have switched languages then you need to refresh current screen to get the screen with new language!
     * Also, don't forget to restore the previous language if you don't clean the state of the Application after each test.
     *
     * @throws Exception if something went wrong
     */
    fun switchLanguageInApp(locale: Locale)
}