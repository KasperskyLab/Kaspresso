package com.kaspersky.kaspresso.alluresupport

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.components.alluresupport.files.resources.AllureResourcesRootDirsProvider
import com.kaspersky.components.alluresupport.withForcedAllureSupport
import com.kaspersky.kaspresso.alluresupport.sample.MainActivity
import com.kaspersky.kaspresso.alluresupport.sample.screen.MainScreen
import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.json.JSONObject
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File

private lateinit var testDirsProvider: DirsProvider
private lateinit var testRootDirsProvider: AllureResourcesRootDirsProvider

private const val STEPS_JSON_FIELD = "steps"
private const val ATTACHMENTS_JSON_FIELD = "attachments"
private const val STATUS_JSON_FIELD = "status"
private const val PASSED = "passed"
private const val JSON_EXTENSION = "json"

/**
 * Checks that allure support is sane
 */
class AllureSupportSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withForcedAllureSupport().apply {
        testDirsProvider = dirsProvider
        testRootDirsProvider = resourcesRootDirsProvider as AllureResourcesRootDirsProvider
    }
) {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Before
    fun before() {
        cleanUpReportDir()
    }

    @After
    fun after() {
        checkSanity()
        cleanUpReportDir()
    }

    @Test
    fun counter() = run {
        step("Launch the app") {

            MainScreen {
                incrementButton.isDisplayed()
                decrementButton.isDisplayed()
                clearButton.isDisplayed()
                valueText.isDisplayed()
            }
        }

        step("Check increase and decrease buttons") {
            step("Increase value up to five") {
                MainScreen {
                    incrementButton {
                        repeat(5) {
                            click()
                        }
                    }

                    assertValue(5)
                }
            }

            step("Decrease value down to three") {
                MainScreen {
                    decrementButton {
                        repeat(2) {
                            click()
                        }
                    }

                    assertValue(3)
                }
            }
        }

        step("Clear the value") {
            MainScreen {
                clearButton.click()
                assertValue(0)
            }
        }
    }

    private fun checkSanity() {
        val reportDir = testDirsProvider.provideNew(testRootDirsProvider.allureRootDir)
        val reportDirFilesList = getReportDirFilesList(reportDir)

        validateReport(readReport(reportDirFilesList, reportDir), reportDirFilesList)
    }

    private fun validateReport(reportJson: JSONObject, reportDirFilesList: List<String>) {
        validateSteps(reportJson)

        assertEquals(PASSED, reportJson.getString(STATUS_JSON_FIELD))
        assertEquals("Report itself should contain 2 attachments: log and video", 2, reportJson.getJSONArray(ATTACHMENTS_JSON_FIELD).length())
        reportJson.toString().let { reportText ->
            reportDirFilesList
                .filter { !it.endsWith(JSON_EXTENSION) }
                .forEach { assertTrue("Report doesn't contain $it", reportText.contains(it)) }
        }
    }

    private fun validateSteps(reportJson: JSONObject) {
        assertEquals("There should be 3 root steps", 3, reportJson.getJSONArray(STEPS_JSON_FIELD).length()) // 3 main steps
        reportJson.getJSONArray(STEPS_JSON_FIELD).run { // validate steps
            for (i in 0 until length()) {
                checkStepAttachments(getJSONObject(i))
            }
            validateNestedStep(getJSONObject(1))
        }
    }

    private fun validateNestedStep(stepJSONObject: JSONObject) {
        stepJSONObject.getJSONArray(STEPS_JSON_FIELD).run {
            assertEquals("Second step should contain 2 inner steps", 2, length()) // second main step has 2 inner steps
            for (j in 0 until length()) {
                checkStepAttachments(getJSONObject(j))
            }
        }
    }

    private fun getReportDirFilesList(reportDir: File): List<String> {
        return device.uiDevice.executeShellCommand("ls ${reportDir.absolutePath}").run {
            val filesList = split('\n').filter { it.isNotEmpty() }
            assertEquals("There should be 8 files in report dir: video, 5 screenshots, logs and report itself", 8, filesList.size)
            filesList
        }
    }

    private fun readReport(reportDirFilesList: List<String>, reportDir: File): JSONObject {
        val reportFileName = reportDirFilesList.find { it.endsWith(JSON_EXTENSION) } ?: throw IllegalStateException("Report file not found under ${reportDir.absolutePath}")
        val reportFile = reportDir.resolve(reportFileName)
        val reportText = reportFile.reader().use { it.readText() }
        return JSONObject(reportText)
    }

    private fun checkStepAttachments(stepJSONObject: JSONObject) {
        assertEquals("Each step should have 1 attachment - screenshot", 1, stepJSONObject.getJSONArray(ATTACHMENTS_JSON_FIELD).length())
    }

    private fun cleanUpReportDir() {
        testDirsProvider.provideCleared(testDirsProvider.provideNew(testRootDirsProvider.allureRootDir))
    }
}
