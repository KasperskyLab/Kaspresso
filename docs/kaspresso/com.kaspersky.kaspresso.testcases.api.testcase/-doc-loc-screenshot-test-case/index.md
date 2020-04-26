[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcase](../index.md) / [DocLocScreenshotTestCase](./index.md)

# DocLocScreenshotTestCase

`abstract class DocLocScreenshotTestCase : `[`TestCase`](../-test-case/index.md)

The base class for all docloc screenshot tests.

Project-wide ScreenshotTestCase should be implemented as following:

``` kotlin
    open class ProductDocLocScreenshotTestCase(testName: String) : DocLocScreenshotTestCase(
        File(testName), "comma-separated string of locales"
    ) {

        @get:Rule
        val activityTestRule = ActivityTestRule(FragmentTestActivity::class.java, true, false)

        protected lateinit var activity: FragmentTestActivity

        @Before
        open fun setUp() {
            activity = activityTestRule.launchActivity(null)
        }
    }
```

Screenshoter test extends the project-wide class:

``` kotlin
    @ScreenShooterTest
    class FeatureScreenshot : ProductDocLocScreenshotTestCase("feature_screenshot") {

        @Test
        fun featureScreen() {
            val featureView = FeatureFragment.newInstance()
            activity.setFragment(featureView)
            val view = getUiSafeProxy<FeatureView>(featureView) // Explicit type is important and must be interface

            view.showLoading()
            captureScreenshot("screenshot_description")
        }
    }
```

As you might have noticed, activity test rule is launched with `FragmentTestActivity`.
It's a special per-project empty activity for test with `setFragment(Fragment)` method.
E.g:

``` kotlin
    class FragmentTestActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_fragment_container)
        }

        fun setFragment(fragment: Fragment) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.content_container, fragment, "")
            fragmentTransaction.build()
        }
    }
```

### Parameters

`screenshotsDirectory` - directory to save screenshot.

`locales` - comma-separated string with locales to run test with.

`changeSystemLocale` - change the system language, i.e. system dialogs (e.g. runtime permissions) will also be localized.
    Need permission in manifest file for a target app android.permission.CHANGE_CONFIGURATION

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The base class for all docloc screenshot tests.`DocLocScreenshotTestCase(screenshotsDirectory: File, changeSystemLocale: Boolean = false, locales: String?, kaspressoBuilder: Builder = Kaspresso.Builder.simple())` |

### Properties

| Name | Summary |
|---|---|
| [localeRule](locale-rule.md) | `val localeRule: `[`LocaleRule`](../../com.kaspersky.kaspresso.docloc.rule/-locale-rule/index.md) |
| [storagePermissionRule](storage-permission-rule.md) | `val storagePermissionRule: GrantPermissionRule` |
| [testFailRule](test-fail-rule.md) | `val testFailRule: `[`TestFailRule`](../../com.kaspersky.kaspresso.docloc.rule/-test-fail-rule/index.md) |

### Functions

| Name | Summary |
|---|---|
| [captureScreenshot](capture-screenshot.md) | Captures a screenshot with a given [name](capture-screenshot.md#com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase$captureScreenshot(kotlin.String)/name) and saves it to //.`open fun captureScreenshot(name: String): Unit` |
| [getUiSafeProxy](get-ui-safe-proxy.md) | Return a dynamic proxy for a given view. [I](get-ui-safe-proxy.md#I) must be interface.`fun <I : Any> getUiSafeProxy(view: I): I` |
| [getUiSafeProxyFromImplementation](get-ui-safe-proxy-from-implementation.md) | Return a dynamic proxy over all interfaces that [view](get-ui-safe-proxy-from-implementation.md#com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase$getUiSafeProxyFromImplementation(com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase.getUiSafeProxyFromImplementation.T)/view) implements.`fun <T : Any> getUiSafeProxyFromImplementation(view: T): Any` |
| [setup](setup.md) | `fun setup(): Unit` |
