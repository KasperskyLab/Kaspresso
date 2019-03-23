package com.kaspersky.uitest_framework.internal

import android.content.Context
import java.io.File

/**
 * Encapsulates all work with shared preferences.
 */
internal class SharedPreferences(
    private val contextGetter: () -> Context
) {
    /**
     * Clears shared preferences.
     */
    fun clear() {
        sharedPreferencesFileNames().forEach { fileName ->

            @Suppress("Missing commit() on SharedPreference editor")
            contextGetter.invoke()
                .getSharedPreferences(fileName, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .commit()
        }
    }

    /**
     * @return [File] with shared preferences.
     */
    private fun sharedPreferencesLocation(): File {
        return File(
            contextGetter.invoke().filesDir.parentFile,
            "shared_prefs"
        )
    }

    /**
     * @return [List] of shared preference file names.
     */
    private fun sharedPreferencesFileNames(): List<String> {
        return sharedPreferencesLocation()
            .list()
            ?.map { it.replace(".xml", "") }
            ?: emptyList()
    }
}