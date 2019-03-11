package com.kaspersky.uitest_framework.internal

import android.content.Context
import java.io.File

internal class SharedPreferences(
        private val contextGetter: () -> Context
) {
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

    private fun sharedPreferencesLocation(): File {
        return File(
                contextGetter.invoke().filesDir.parentFile,
                "shared_prefs"
        )
    }

    private fun sharedPreferencesFileNames(): List<String> {
        return sharedPreferencesLocation()
                .list()
                ?.map { it.replace(".xml", "") }
                ?: emptyList()
    }
}