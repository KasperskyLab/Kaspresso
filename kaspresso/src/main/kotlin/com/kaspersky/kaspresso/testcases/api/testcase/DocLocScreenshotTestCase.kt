package com.kaspersky.kaspresso.testcases.api.testcase

import android.Manifest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.device.locales.Locales
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.DefaultScreenshotDirectoryProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.DefaultScreenshotNameProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.ScreenshotDirectoryProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.ScreenshotNameProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.DocLocScreenshotMaker
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.ExternalScreenshotMaker
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.InternalScreenshotMaker
import com.kaspersky.kaspresso.docloc.DocLocScreenshotCapturer
import com.kaspersky.kaspresso.docloc.MetadataSaver
import com.kaspersky.kaspresso.docloc.rule.LocaleRule
import com.kaspersky.kaspresso.docloc.rule.ToggleNightModeRule
import com.kaspersky.kaspresso.files.dirs.DefaultDirsProvider
import com.kaspersky.kaspresso.files.resources.ResourceFileNamesProvider
import com.kaspersky.kaspresso.files.resources.ResourcesDirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourceFileNamesProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourceFilesProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourcesDirNameProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourcesDirsProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourcesRootDirsProvider
import com.kaspersky.kaspresso.files.resources.impl.SupportLegacyResourcesDirNameProvider
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProviderFactory
import com.kaspersky.kaspresso.instrumental.exception.DocLocInUnitTestException
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot.ScreenshotStepWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot.TestRunnerScreenshotWatcherInterceptor
import com.kaspersky.kaspresso.internal.extensions.other.getAllInterfaces
import com.kaspersky.kaspresso.internal.invocation.UiInvocationHandler
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.ScreenshotParams
import org.junit.Before
import org.junit.Rule
import java.io.File
import java.lang.reflect.Proxy

/**
 *  The base class for all docloc screenshot tests.
 *
 *  All detailed information is presented in [wiki](https://github.com/KasperskyLab/Kaspresso/blob/master/wiki/07_DocLoc.md)
 *
 *  @see <a href="https://github.com/KasperskyLab/Kaspresso/blob/master/wiki/07_DocLoc.md">wiki</a>
 *
 *  @param resourcesRootDirsProvider provider of root directories to save different data including screenshots.
 *  @param resourcesDirsProvider directory provider inside the root directory to save different data including screenshots.
 *  @param resourceFileNamesProvider data file name provider including screenshots.
 *  @param changeSystemLocale change the system language, i.e. system dialogs (e.g. runtime permissions) will also be localized.
 *      Need permission in manifest file for a target app android.permission.CHANGE_CONFIGURATION
 *  @param locales comma-separated string with locales to run test with.
 *  @param toggleNightMode used to capture screenshots with night mode enabled
 */
abstract class DocLocScreenshotTestCase(
    private val resourcesRootDirsProvider: ResourcesRootDirsProvider =
        DefaultResourcesRootDirsProvider(),
    private val resourcesDirsProvider: ResourcesDirsProvider =
        DefaultResourcesDirsProvider(
            dirsProvider = DefaultDirsProvider(
                InstrumentalDependencyProviderFactory().getComponentProvider<Kaspresso>(InstrumentationRegistry.getInstrumentation())
            ),
            resourcesDirNameProvider = DefaultResourcesDirNameProvider()
        ),
    private val resourceFileNamesProvider: ResourceFileNamesProvider =
        DefaultResourceFileNamesProvider(
            addTimestamps = false
        ),
    private val changeSystemLocale: Boolean = false,
    private val toggleNightMode: Boolean = false,
    private val screenshotParams: ScreenshotParams = ScreenshotParams(),
    locales: String?,
    kaspressoBuilder: Kaspresso.Builder = Kaspresso.Builder.simple().apply {
        testRunWatcherInterceptors.add(TestRunnerScreenshotWatcherInterceptor(screenshots))
    }
) : TestCase(kaspressoBuilder = kaspressoBuilder) {

    @Deprecated(
        message = "It's a legacy option to create DocLoc Screenshot TestCase. \n " +
                "Please use the primary constructor. \n" +
                "See a mapping from old classes to new classes here to migrate your constructor. \n" +
                "Anyway, we give a guarantee that the old option will work correctly for a while. \n" +
                "You can check *Legacy screenshot tests in docloc_tests folder."
    )
    constructor(
        screenshotsDirectory: File,
        screenshotDirectoryProvider: ScreenshotDirectoryProvider = DefaultScreenshotDirectoryProvider(groupByRunNumbers = false),
        screenshotNameProvider: ScreenshotNameProvider = DefaultScreenshotNameProvider(addTimestamps = false),
        changeSystemLocale: Boolean = false,
        toggleNightMode: Boolean = false,
        locales: String?,
        screenshotParams: ScreenshotParams = ScreenshotParams(),
        kaspressoBuilder: Kaspresso.Builder = Kaspresso.Builder.simple().apply {
            stepWatcherInterceptors.add(ScreenshotStepWatcherInterceptor(screenshots))
        }
    ) : this(
        resourcesRootDirsProvider = object : ResourcesRootDirsProvider {
            override val logcatRootDir: File = File("logcat")
            override val screenshotsRootDir = screenshotsDirectory
            override val videoRootDir: File = File("video")
            override val viewHierarchy: File = File("view_hierarchy")
        },
        resourcesDirsProvider = DefaultResourcesDirsProvider(
            dirsProvider = DefaultDirsProvider(
                InstrumentalDependencyProviderFactory().getComponentProvider<Kaspresso>(InstrumentationRegistry.getInstrumentation())
            ),
            resourcesDirNameProvider = SupportLegacyResourcesDirNameProvider(screenshotDirectoryProvider)
        ),
        resourceFileNamesProvider = object : ResourceFileNamesProvider {
            override fun getFileName(tag: String, fileExtension: String): String =
                screenshotNameProvider.getScreenshotName(tag)
        },
        screenshotParams = screenshotParams,
        changeSystemLocale = changeSystemLocale,
        toggleNightMode = toggleNightMode,
        locales = locales,
        kaspressoBuilder = kaspressoBuilder
    )

    private lateinit var screenshotCapturer: DocLocScreenshotCapturer

    @PublishedApi
    internal val logger: UiTestLogger = kaspresso.libLogger

    private val confLocales: Locales = Locales(logger)

    @get:Rule
    val localeRule = LocaleRule(
        locales = locales?.let { confLocales.parseLocales(it) } ?: confLocales.getSupportedLocales(),
        changeSystemLocale = changeSystemLocale,
        device = kaspresso.device,
        logger = kaspresso.libLogger
    )

    @get:Rule
    val nightModeRule = ToggleNightModeRule(
        toggleNightMode = toggleNightMode,
        logger = kaspresso.libLogger,
        device = kaspresso.device
    )

    @get:Rule
    val storagePermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE)!!

    @Before
    fun setup() {
        if (!kaspresso.instrumentalDependencyProvider.isAndroidRuntime) {
            throw DocLocInUnitTestException()
        }

        val localedResourcesRootDirsProvider: ResourcesRootDirsProvider =
            object : ResourcesRootDirsProvider by resourcesRootDirsProvider {
                override val screenshotsRootDir: File =
                    resourcesRootDirsProvider.screenshotsRootDir.resolve(
                        if (!toggleNightMode) {
                            SCREENSHOTS_DIR_DEFAULT
                        } else if (nightModeRule.isNightMode) {
                            SCREENSHOTS_DIR_DARK
                        } else {
                            SCREENSHOTS_DIR_LIGHT
                        }
                    ).resolve(localeRule.currentLocaleName)
            }

        screenshotCapturer = DocLocScreenshotCapturer(
            logger = logger,
            resourceFilesProvider = DefaultResourceFilesProvider(
                localedResourcesRootDirsProvider,
                resourcesDirsProvider,
                resourceFileNamesProvider
            ),
            screenshotMaker = DocLocScreenshotMaker(
                screenshotMaker = ExternalScreenshotMaker(
                    kaspresso.instrumentalDependencyProvider,
                    screenshotParams
                ),
                fullWindowScreenshotMaker = InternalScreenshotMaker(kaspresso.device.activities, screenshotParams)
            ),
            metadataSaver = MetadataSaver(kaspresso.device.activities, kaspresso.device.apps, logger)
        )
    }

    /**
     * Captures a screenshot with a given [name] and saves it to
     * <device path for pictures>/<locale>/<screenshotsDirectory>.
     *
     * @param name screenshot name. English letters, spaces, numbers and dots are allowed.
     */
    protected open fun captureScreenshot(name: String) {
        screenshotCapturer.captureScreenshot(name.replace(Regex("[. ]"), "_").replace(".", "_"))
    }

    /**
     * Captures a full window screenshot with a given [name] and saves it to
     * <device path for pictures>/<locale>/<screenshotsDirectory>.
     *
     * @param name screenshot name. English letters, spaces, numbers and dots are allowed.
     */
    protected open fun captureFullWindowScreenshot(name: String) {
        screenshotCapturer.captureFullWindowScreenshot(name.replace(Regex("[. ]"), "_").replace(".", "_"))
    }

    /**
     * Return a dynamic proxy for a given view.
     * [I] must be interface.
     *
     * @param view proxy target.
     * @return a proxy over the given view.
     */
    inline fun <reified I : Any> getUiSafeProxy(view: I): I {
        if (!I::class.java.isInterface) {
            throw IllegalArgumentException(
                "Method was called with wrong generic parameter." +
                        " Consider upper casting argument to desired interface"
            )
        }
        return Proxy.newProxyInstance(
            view::class.java.classLoader,
            I::class.java.getAllInterfaces(),
            UiInvocationHandler(view as Any, logger)
        ) as I
    }

    /**
     * Return a dynamic proxy over all interfaces that [view] implements.
     *
     * @param view proxy target.
     * @return a proxy over the given view.
     */
    inline fun <reified T : Any> getUiSafeProxyFromImplementation(view: T): Any {
        return Proxy.newProxyInstance(
            view::class.java.classLoader,
            T::class.java.getAllInterfaces(),
            UiInvocationHandler(view as Any, logger)
        ) as T
    }

    private companion object {
        const val SCREENSHOTS_DIR_LIGHT = "light"
        const val SCREENSHOTS_DIR_DARK = "dark"
        const val SCREENSHOTS_DIR_DEFAULT = ""
    }
}
