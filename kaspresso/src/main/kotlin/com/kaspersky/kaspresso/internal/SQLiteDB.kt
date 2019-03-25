package com.kaspersky.kaspresso.internal

import android.content.Context
import org.hamcrest.Matchers
import org.junit.Assert

/**
 * Encapsulates all work with SQLite database.
 */
internal class SQLiteDB(
    private val contextGetter: () -> Context
) {
    /**
     * Clears all databases.
     * Only works if all database connections are closed. Does not produce error if connection still open.
     */
    fun clearAll() {
        val context = contextGetter.invoke()

        context.databaseList().forEach { database ->
            val databasePath = context.getDatabasePath(database)
            context.deleteDatabase(database)
            val exists = databasePath.exists()

            Assert.assertThat(
                "db exist not anymore at $databasePath",
                exists,
                Matchers.`is`(false)
            )
        }
    }
}