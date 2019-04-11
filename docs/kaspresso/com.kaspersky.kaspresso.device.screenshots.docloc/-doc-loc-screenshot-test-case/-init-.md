[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots.docloc](../index.md) / [DocLocScreenshotTestCase](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`DocLocScreenshotTestCase(screenshotsDirectory: `[`File`](https://developer.android.com/reference/java/io/File.html)`, locales: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?)`

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