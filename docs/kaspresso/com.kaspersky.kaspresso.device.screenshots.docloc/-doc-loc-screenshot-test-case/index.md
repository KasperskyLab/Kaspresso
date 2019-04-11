[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots.docloc](../index.md) / [DocLocScreenshotTestCase](./index.md)

# DocLocScreenshotTestCase

`abstract class DocLocScreenshotTestCase : `[`TestCase`](../../com.kaspersky.kaspresso.testcases/-test-case/index.md)

Base class for all docloc screenshot tests.

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
            fragmentTransaction.commit()
        }
    }
```

### Parameters

`screenshotsDirectory` - directory to save screenshot. Will be cleared before launching the test.

`locales` - comma-separated string with locales to run test with.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DocLocScreenshotTestCase(screenshotsDirectory: `[`File`](https://developer.android.com/reference/java/io/File.html)`, locales: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?)`<br>Base class for all docloc screenshot tests. |

### Properties

| Name | Summary |
|---|---|
| [localeRule](locale-rule.md) | `val localeRule: `[`LocaleRule`](../../com.kaspersky.kaspresso.rule/-locale-rule/index.md) |
| [storagePermissionRule](storage-permission-rule.md) | `val storagePermissionRule: GrantPermissionRule` |
| [testFailRule](test-fail-rule.md) | `val testFailRule: `[`TestFailRule`](../../com.kaspersky.kaspresso.rule/-test-fail-rule/index.md) |

### Functions

| Name | Summary |
|---|---|
| [captureScreenshot](capture-screenshot.md) | `open fun captureScreenshot(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Captures screenshot with a given [name](capture-screenshot.md#com.kaspersky.kaspresso.device.screenshots.docloc.DocLocScreenshotTestCase$captureScreenshot(kotlin.String)/name) and saves it to // |
| [getUiSafeProxy](get-ui-safe-proxy.md) | `fun <I : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> getUiSafeProxy(view: `[`I`](get-ui-safe-proxy.md#I)`): `[`I`](get-ui-safe-proxy.md#I)<br>Return a dynamic proxy for a given view. [I](get-ui-safe-proxy.md#I) must be interface |
| [getUiSafeProxyFromImplementation](get-ui-safe-proxy-from-implementation.md) | `fun <T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> getUiSafeProxyFromImplementation(view: `[`T`](get-ui-safe-proxy-from-implementation.md#T)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)<br>Return a dynamic proxy over all interfaces that [view](get-ui-safe-proxy-from-implementation.md#com.kaspersky.kaspresso.device.screenshots.docloc.DocLocScreenshotTestCase$getUiSafeProxyFromImplementation(com.kaspersky.kaspresso.device.screenshots.docloc.DocLocScreenshotTestCase.getUiSafeProxyFromImplementation.T)/view) implements |
| [setup](setup.md) | `fun setup(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [beforeTest](../../com.kaspersky.kaspresso.testcases/-test-case/before-test.md) | `fun beforeTest(actions: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`AfterTestSection`](../../com.kaspersky.kaspresso.testcases/-after-test-section/index.md)<br>Starts the building a test, sets the [BeforeTestSection](../../com.kaspersky.kaspresso.testcases/-before-test-section/index.md) actions and returns an existing instance of [AfterTestSection](../../com.kaspersky.kaspresso.testcases/-after-test-section/index.md) to continue building a test. |
