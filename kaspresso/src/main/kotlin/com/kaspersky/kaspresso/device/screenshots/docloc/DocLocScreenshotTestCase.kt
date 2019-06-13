package com.kaspersky.kaspresso.device.screenshots.docloc

import android.Manifest
import android.support.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.device.locales.Locales
import com.kaspersky.kaspresso.extensions.other.getAllInterfaces
import com.kaspersky.kaspresso.reflect.proxy.UiInvocationHandler
import com.kaspersky.kaspresso.rule.LocaleRule
import com.kaspersky.kaspresso.rule.TestFailRule
import com.kaspersky.kaspresso.testcases.api.TestCase
import org.junit.Before
import org.junit.Rule
import java.io.File
import java.lang.reflect.Proxy

/**
 *  Base class for all docloc screenshot tests.
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
 *  @param screenshotsDirectory directory to save screenshot. Will be cleared before launching the test.
 *  @param locales comma-separated string with locales to run test with.
 */
abstract class DocLocScreenshotTestCase(
    private val screenshotsDirectory: File,
    locales: String?
) : TestCase() {

    private lateinit var screenshotsDir: File
    private lateinit var screenshotCapturer: DocLocScreenshotCapturer

    @PublishedApi internal val logger = configurator.logger
    private val confLocales: Locales = Locales(logger)

    @get:Rule
    val localeRule = LocaleRule(
        locales?.let { confLocales.parseLocales(it) }
            ?: confLocales.getSupportedLocales()
    )

    @get:Rule
    val storagePermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE)!!

    @get:Rule
    val testFailRule = TestFailRule()

    @Before
    fun setup() {
        screenshotsDir = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            .resolve(localeRule.locale.toString())
            .resolve(screenshotsDirectory)

        screenshotCapturer = DocLocScreenshotCapturer(
            screenshotsDir,
            logger,
            configurator.activities,
            configurator.apps
        )

        testFailRule.screenshotCapturer = screenshotCapturer
    }

    /**
     *  Captures screenshot with a given [name] and saves it to
     *  <device path for pictures>/<locale>/<screenshotsDirectory>
     *
     *  @param name screenshot name. English letters, spaces, numbers and dots are allowed.
     */
    protected open fun captureScreenshot(name: String) {
        screenshotCapturer.captureScreenshot(name.replace(Regex("[. ]"), "_").replace(".", "_"))
    }

    /**
     *  Return a dynamic proxy for a given view.
     *  [I] must be interface
     *
     *  @param view proxy target
     *  @return a proxy over the given view
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
     *  Return a dynamic proxy over all interfaces that [view] implements
     *
     *  @param view proxy target
     *  @return a proxy over the given view
     */
    inline fun <reified T : Any> getUiSafeProxyFromImplementation(view: T): Any {
        return Proxy.newProxyInstance(
            view::class.java.classLoader,
            T::class.java.getAllInterfaces(),
            UiInvocationHandler(view as Any, logger)
        ) as T
    }
}
