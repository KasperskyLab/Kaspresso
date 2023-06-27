package com.kaspersky.kaspressample.simple_tests

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.SimpleScreen
import com.kaspersky.kaspresso.files.dirs.DefaultDirsProvider
import com.kaspersky.kaspresso.files.resources.ResourceFileNamesProvider
import com.kaspersky.kaspresso.files.resources.ResourcesDirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourceFilesProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourcesDirNameProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourcesDirsProvider
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProviderFactory
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.DumpLogcatInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot.TestRunnerScreenshotWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.video.VideoRecordingInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.views.DumpViewsInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.ScreenshotParams
import com.kaspersky.kaspresso.params.VideoParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.models.info.TestInfo
import org.junit.Rule
import org.junit.Test
import java.io.File

class CustomizedSimpleTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.advanced(
        customize = {
            videoParams = VideoParams(bitRate = 10_000_000)
            screenshotParams = ScreenshotParams(quality = 1)

            dirsProvider = DefaultDirsProvider(
                InstrumentalDependencyProviderFactory().getComponentProvider<Kaspresso>(InstrumentationRegistry.getInstrumentation())
            )
            resourcesDirNameProvider = DefaultResourcesDirNameProvider()

            resourcesRootDirsProvider = object : ResourcesRootDirsProvider {
                override val logcatRootDir = File("custom_logcat")
                override val screenshotsRootDir = File("custom_screenshots")
                override val videoRootDir = File("custom_video")
                override val viewHierarchy = File("custom_view_hierarchy")
            }

            resourcesDirsProvider = object : ResourcesDirsProvider by DefaultResourcesDirsProvider(
                dirsProvider = dirsProvider,
                resourcesDirNameProvider = resourcesDirNameProvider
            ) {
                override fun provide(dest: File, subDir: String?): File =
                    dirsProvider.provideCleared(dirsProvider.provideNew(dest))
            }

            resourceFileNamesProvider = object : ResourceFileNamesProvider {
                private var counter = 0
                override fun getFileName(tag: String, fileExtension: String): String =
                    "resource#${counter++}_$tag$fileExtension"
            }

            resourceFilesProvider = DefaultResourceFilesProvider(
                resourcesRootDirsProvider,
                resourcesDirsProvider,
                resourceFileNamesProvider
            )
        }
    ).apply {
        testRunWatcherInterceptors.apply {
            clear()
            addAll(
                listOf(
                    DumpLogcatInterceptor(logcatDumper),
                    TestRunnerScreenshotWatcherInterceptor(screenshots),
                    VideoRecordingInterceptor(videos),
                    DumpViewsInterceptor(viewHierarchyDumper),
                    object : TestRunWatcherInterceptor {
                        override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
                            viewHierarchyDumper.dump("ViewHierarchy")
                        }
                    }
                )
            )
        }
    }
) {
    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = run {
        step("Open Simple Screen") {
            testLogger.i("I am testLogger")
            device.screenshots.take("Additional_screenshot")
            MainScreen {
                simpleButton {
                    isVisible()
                    click()
                }
            }
        }

        step("Click button_1 and check button_2") {
            SimpleScreen {
                button1 {
                    click()
                }
                button2 {
                    isVisible()
                }
            }
        }

        step("Click button_2 and check edit") {
            SimpleScreen {
                button2 {
                    click()
                }
                edit {
                    flakySafely(timeoutMs = 7000) { isVisible() }
                    hasText(R.string.simple_fragment_text_edittext)
                }
            }
        }

        step("Check all possibilities of edit") {
            scenario(
                CheckEditScenario()
            )
        }
    }
}
