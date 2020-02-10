package com.kaspersky.kaspresso.testcases.api.testcase

import android.Manifest
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.device.locales.Locales
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.DefaultScreenshotDirectoryProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.DefaultScreenshotNameProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.ExternalScreenshotMaker
import com.kaspersky.kaspresso.docloc.DocLocScreenshotCapturer
import com.kaspersky.kaspresso.docloc.MetadataSaver
import com.kaspersky.kaspresso.docloc.rule.LocaleRule
import com.kaspersky.kaspresso.docloc.rule.TestFailRule
import com.kaspersky.kaspresso.internal.extensions.other.getAllInterfaces
import com.kaspersky.kaspresso.internal.invocation.UiInvocationHandler
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File
import java.lang.reflect.Proxy
import org.junit.Before
import org.junit.Rule

/**
 *  The base class for all docloc screenshot tests.
 *
 *  Project-wide ScreenshotTestCase should be implemented as following:
 *
 *  ```kotlin
 *      open class ProductDocLocScreenshotTestCase(testName: String) : DocLocScreenshotTestCase(
 *          File(testName), "comma-separated string of locales"
 *      ) {
 *
 *          @get:Rule
 *          val activityTestRule = ActivityTestRule(FragmentTestActivity::class.java, true, false)
 *
 *          protected lateinit var activity: FragmentTestActivity
 *
 *          @Before
 *          open fun setUp() {
 *              activity = activityTestRule.launchActivity(null)
 *          }
 *      }
 *  ```
 *
 *  Screenshoter test extends the project-wide class:
 *  ```kotlin
 *      @ScreenShooterTest
 *      class FeatureScreenshot : ProductDocLocScreenshotTestCase("feature_screenshot") {
 *
 *          @Test
 *          fun featureScreen() {
 *              val featureView = FeatureFragment.newInstance()
 *              activity.setFragment(featureView)
 *              val view = getUiSafeProxy<FeatureView>(featureView) // Explicit type is important and must be interface
 *
 *              view.showLoading()
 *              captureScreenshot("screenshot_description")
 *          }
 *      }
 *  ```
 *  As you might have noticed, activity test rule is launched with ```FragmentTestActivity```.
 *  It's a special per-project empty activity for test with ```setFragment(Fragment)``` method.
 *  E.g:
 *  ```kotlin
 *      class FragmentTestActivity : AppCompatActivity() {
 *
 *          override fun onCreate(savedInstanceState: Bundle?) {
 *              super.onCreate(savedInstanceState)
 *              setContentView(R.layout.activity_fragment_container)
 *          }
 *
 *          fun setFragment(fragment: Fragment) {
 *              val fragmentTransaction = supportFragmentManager.beginTransaction()
 *              fragmentTransaction.replace(R.id.content_container, fragment, "")
 *              fragmentTransaction.build()
 *          }
 *      }
 *  ```
 *
 *  @param screenshotsDirectory directory to save screenshot.
 *  @param locales comma-separated string with locales to run test with.
 *  @param changeSystemLocale change the system language, i.e. system dialogs (e.g. runtime permissions) will also be localized.
 *      Need permission in manifest file for a target app android.permission.CHANGE_CONFIGURATION
 */
abstract class DocLocScreenshotTestCase(
    private val screenshotsDirectory: File,
    private val changeSystemLocale: Boolean = false,
    locales: String?,
    kaspressoBuilder: Kaspresso.Builder = Kaspresso.Builder.advanced()
) : TestCase(kaspressoBuilder = kaspressoBuilder) {

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
    val storagePermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE)!!

    @get:Rule
    val testFailRule = TestFailRule()

    @Before
    fun setup() {
        val screenshotsDir = screenshotsDirectory.resolve(localeRule.currentLocaleName)

        screenshotCapturer = DocLocScreenshotCapturer(
            logger,
            ExternalScreenshotMaker(),
            MetadataSaver(kaspresso.device.activities, kaspresso.device.apps, logger),
            DefaultScreenshotDirectoryProvider(groupByRunNumbers = false),
            DefaultScreenshotNameProvider(addTimestamps = false),
            screenshotsDir
        )

        testFailRule.screenshotCapturer = screenshotCapturer
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
}
