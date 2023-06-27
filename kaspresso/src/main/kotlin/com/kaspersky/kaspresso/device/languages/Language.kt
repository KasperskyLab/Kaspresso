package com.kaspersky.kaspresso.device.languages

import androidx.annotation.MainThread
import java.util.Locale

/**
 * The interface to work with languages
 */
interface Language {

    /**
     * Switches language only in the current Application (not in OS!).
     * Please, keep in mind the following fact:
     *   If you have switched languages then activity.recreate() invoked so you have to do it on MainThread only
     *   Also, don't forget to restore the previous language if you don't clean the state of the Application after each test.
     *
     * @throws Throwable if something went wrong
     */
    @MainThread
    fun switchInApp(locale: Locale)
}
