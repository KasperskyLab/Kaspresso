package com.kaspersky.kaspressample.device_tests

import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.device.logcat.LogcatBufferSize
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assume
import org.junit.Rule
import org.junit.Test

class DeviceLogcatSampleTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun logcatTest() {
        Assume.assumeTrue(
            "Logcat can't connect to 'default' buffer on API < 24",
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
        )

        Assume.assumeTrue(
            "Due to Android 8 bug 'logcat -c' fails. To run this test please use another device",
            Build.VERSION.SDK_INT != Build.VERSION_CODES.O
        )

        before {
            device.logcat.setBufferSize(LogcatBufferSize(8, LogcatBufferSize.Dimension.MEGABYTES))
            device.logcat.disableChatty()
            device.logcat.clear()
        }.after {
        }.run {
            step("Get logcat from device as list of strings") {
                val logcatList = device.logcat.readLogcatRows()
                assertTrue(logcatList.isNotEmpty())
                assertTrue(logcatList.any { logcatRow ->
                    logcatRow.contains("beginning of main")
                })
            }

            step("Filter logcat by excludePattern and case sensitivity") {
                testLogger.i("Test1Row")

                val logcatListCaseSensitive = device.logcat.readLogcatRows(
                    excludePattern = "test1row"
                )
                assertTrue(logcatListCaseSensitive.any { logcatRow ->
                    logcatRow.contains("Test1Row")
                })

                val logcatListCaseInsensitive = device.logcat.readLogcatRows(
                    excludePattern = "test1row",
                    excludePatternIsIgnoreCase = true
                )
                assertTrue(logcatListCaseInsensitive.none { logcatRow ->
                    logcatRow.contains("Test1Row")
                })
            }

            step("Filter logcat by regex excludePattern") {
                repeat(10) { testLogger.i("test2row$it") }

                // exclude all rows
                val logcatListIntervalFilter = device.logcat.readLogcatRows(
                    excludePattern = "test2row[0-9]"
                )
                assertTrue(logcatListIntervalFilter.none { logcatRow ->
                    logcatRow.contains("test2row")
                })

                // exclude only 2 rows
                val logcatListOrFilter = device.logcat.readLogcatRows(
                    excludePattern = "test2row2|test2row4"
                )
                assertTrue(logcatListOrFilter.none { logcatRow ->
                    logcatRow.contains("test2row2") ||
                            logcatRow.contains("test2row4")
                })
                assertTrue(logcatListOrFilter.any { logcatRow ->
                    logcatRow.contains("test2row3") ||
                            logcatRow.contains("test2row5")
                })
            }

            step("Filter logcat by includePattern") {
                testLogger.i("Test3Row")

                val logcatList = device.logcat.readLogcatRows(
                    includePattern = "test3row",
                    includePatternIsIgnoreCase = true
                )
                assertTrue(logcatList.filter { logcatRow ->
                    logcatRow.contains("Test3Row")
                }.size == 1)
            }

            step("Combine include and exclude patterns") {
                repeat(10) { testLogger.i("Test4Row$it") }
                testLogger.i("Test4RowOnlyYou")

                val logcatList = device.logcat.readLogcatRows(
                    includePattern = "test4row",
                    includePatternIsIgnoreCase = true,
                    excludePattern = "test4row[0-9]",
                    excludePatternIsIgnoreCase = true
                )
                assertTrue(
                    logcatList.size == 1 &&
                            logcatList.first().contains("Test4RowOnlyYou")
                )
            }

            step("Limit rows output") {
                repeat(100) { testLogger.i("Test5Row$it") }

                val logcatList10Limit = device.logcat.readLogcatRows(
                    rowLimit = 10
                )
                assertEquals(11, logcatList10Limit.size)

                val logcatList50Limit = device.logcat.readLogcatRows(
                    rowLimit = 50
                )
                assertEquals(51, logcatList50Limit.size)
            }

            step("Using reader block") {
                repeat(100) { testLogger.i("Test6Row$it") }

                val fullLogcatList = device.logcat.readLogcatRows()

                var innerContainsSize = 0
                val doesContainBreaked = device.logcat.readLogcatRows { logcatRow ->
                    innerContainsSize++
                    logcatRow.contains("Test6Row42")
                }

                var innerNotContainsSize = 0
                val isNotContainsBreaked = device.logcat.readLogcatRows { logcatRow ->
                    innerNotContainsSize++
                    logcatRow.contains("LogcatWillNotContainThisRow :)")
                }

                var indexOfBeginningRow = 0
                val isBreakedOnBeginningRow = device.logcat.readLogcatRows { logcatRow ->
                    indexOfBeginningRow++
                    logcatRow.contains("beginning of")
                }

                assertTrue(doesContainBreaked && fullLogcatList.size > innerContainsSize)
                assertTrue(!isNotContainsBreaked && fullLogcatList.size <= innerNotContainsSize)

                assertTrue(isBreakedOnBeginningRow && indexOfBeginningRow == 1)
            }
        }
    }
}
