package com.kaspersky.kaspressample.device_tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.device.logcat.LogcatBufferSize
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceLogcatSampleTest : TestCase() {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Test
    fun logcatTest() {
        before {
            device.logcat.setBufferSize(LogcatBufferSize(8, LogcatBufferSize.Dimension.MEGABYTES))
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
                assertTrue(logcatList10Limit.size == 11)

                val logcatList50Limit = device.logcat.readLogcatRows(
                    rowLimit = 50
                )
                assertTrue(logcatList50Limit.size == 51)
            }

            step("Using reader block") {
                repeat(100) { testLogger.i("Test6Row$it") }

                var fullLogcatList = device.logcat.readLogcatRows()

                var inneContainsSize = 0
                val isContainsBreaked = device.logcat.readLogcatRows { logcatRow ->
                    inneContainsSize++
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

                assertTrue(isContainsBreaked && fullLogcatList.size > inneContainsSize)
                assertTrue(!isNotContainsBreaked && fullLogcatList.size <= innerNotContainsSize)

                assertTrue(isBreakedOnBeginningRow && indexOfBeginningRow == 1)
            }
        }
    }
}