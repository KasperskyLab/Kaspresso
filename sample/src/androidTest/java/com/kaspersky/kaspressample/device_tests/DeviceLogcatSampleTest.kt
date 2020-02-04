package com.kaspersky.kaspressample.device_tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspressample.MainActivity
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
            device.logcat.clear()
            Thread.sleep(1000)
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

                var logcatList = device.logcat.readLogcatRows(
                    excludePattern = "test1row"
                )
                assertTrue(logcatList.any { logcatRow ->
                    logcatRow.contains("Test1Row")
                })

                logcatList = device.logcat.readLogcatRows(
                    excludePattern = "test1row",
                    excludeIgnoreCase = true
                )
                assertTrue(logcatList.none { logcatRow ->
                    logcatRow.contains("Test1Row")
                })
            }

            step("Filter logcat by regex excludePattern") {
                repeat(10) { testLogger.i("test2row$it") }

                // exclude all rows
                var logcatList = device.logcat.readLogcatRows(
                    excludePattern = "test2row[0-9]"
                )
                assertTrue(logcatList.none { logcatRow ->
                    logcatRow.contains("test2row")
                })

                // exclude only 2 rows
                logcatList = device.logcat.readLogcatRows(
                    excludePattern = "test2row2|test2row4"
                )
                assertTrue(logcatList.none { logcatRow ->
                    logcatRow.contains("test2row2") ||
                            logcatRow.contains("test2row4")
                })
                assertTrue(logcatList.any { logcatRow ->
                    logcatRow.contains("test2row3") ||
                            logcatRow.contains("test2row5")
                })
            }

            step("Filter logcat by includePattern") {
                testLogger.i("Test3Row")

                val logcatList = device.logcat.readLogcatRows(
                    includePattern = "test3row",
                    includeIgnoreCase = true
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
                    includeIgnoreCase = true,
                    excludePattern = "test4row[0-9]",
                    excludeIgnoreCase = true
                )
                assertTrue(
                    logcatList.size == 1 &&
                            logcatList.first().contains("Test4RowOnlyYou")
                )
            }

            step("Limit rows output") {
                repeat(100) { testLogger.i("Test5Row$it") }

                var logcatList = device.logcat.readLogcatRows(
                    rowLimit = 10
                )
                assertTrue(logcatList.size == 11)

                logcatList = device.logcat.readLogcatRows(
                    rowLimit = 50
                )
                assertTrue(logcatList.size == 51)
            }

            step("Using analyzer block") {
                repeat(100) { testLogger.i("Test6Row$it") }

                var fullLogcatList = device.logcat.readLogcatRows()

                var innerSizeContains = 0
                val isContainsBreaked = device.logcat.readLogcatRows { logcatRow ->
                    innerSizeContains++
                    logcatRow.contains("Test6Row42")
                }

                var innerSizeNotContains = 0
                val isNotContainsBreaked = device.logcat.readLogcatRows { logcatRow ->
                    innerSizeNotContains++
                    logcatRow.contains("LogcatWillNotContainThisRow :)")
                }

                var indexOfBeginningRow = 0
                val isBreakedOnBeginningRow = device.logcat.readLogcatRows { logcatRow ->
                    indexOfBeginningRow++
                    logcatRow.contains("beginning of")
                }

                assertTrue(isContainsBreaked && fullLogcatList.size > innerSizeContains)
                assertTrue(!isNotContainsBreaked && fullLogcatList.size <= innerSizeNotContains)

                assertTrue(isBreakedOnBeginningRow && indexOfBeginningRow == 1)
            }
        }
    }
}