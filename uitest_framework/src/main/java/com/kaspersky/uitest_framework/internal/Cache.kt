package com.kaspersky.uitest_framework.internal

import android.content.Context
import com.kaspersky.uitest_framework.attempting.attempt
import org.hamcrest.Matchers
import org.junit.Assert
import java.io.File
import java.util.*

/**
 * Encapsulates all work with cache.
 */
internal class Cache(
    private val contextGetter: () -> Context
) {
    /**
     * Attempts to recursively clear cache directory.
     */
    fun clear() {
        val cacheDir = contextGetter.invoke().cacheDir
        if (cacheDir.list() != null) {
            attempt(
                timeoutMs = DELETE_TIMEOUT_MS,
                attemptsFrequencyMs = DELETE_FREQUENCY_MS
            ) {
                Assert.assertThat(
                    "Can't delete ${cacheDir.path}",
                    deleteRecursive(cacheDir),
                    Matchers.`is`(true)
                )
            }
        }
    }

    /**
     * Attempts to recursively clear the directory.
     *
     * @param directory a directory to clear.
     * @param excludes subdirectories that must not be cleared.
     */
    private fun deleteRecursive(
        directory: File,
        vararg excludes: String
    ): Boolean {

        if (excludes.isNotEmpty() &&
            Arrays.asList(*excludes).contains(directory.name)
        ) {
            return true
        }

        if (directory.isDirectory) {
            directory.list()?.forEach { content ->
                attempt(
                    timeoutMs = DELETE_TIMEOUT_MS,
                    attemptsFrequencyMs = DELETE_FREQUENCY_MS
                ) {
                    Assert.assertThat(
                        "Can't delete file $content in ${directory.path}",
                        deleteRecursive(File(directory, content), *excludes),
                        Matchers.`is`(true)
                    )
                }
            }
        }

        return directory.delete()
    }
}

private const val DELETE_TIMEOUT_MS = 5_000L

private const val DELETE_FREQUENCY_MS = 500L