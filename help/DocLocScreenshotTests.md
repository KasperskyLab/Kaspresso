## Main purpose

Sometimes when developing new features, there is a need to check if the application works 
properly in all supported languages. Manual locale setting changes could take a long time and 
require the efforts of developers, QA engineers, and etc. Also it could increase the duration of 
the localisation process. 

In order to avoid that, Kaspresso provides ```DocLocScreenshotTestCase``` ([com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase]) 
which allows to take screenshots in all locales you specified. `DocLocScreenshotTestCase` extends
default Kaspresso `TestCase` and offers the opportunity to make screenshots out the box by 
calling `DocLocScreenshotTestCase#captureScreenshot(String)` method. 
        
## Usage

To create a single test, you should extend `DocLocScreenshotTestCase` class as shown below: 

```kotlin
@RunWith(AndroidJUnit4::class)
class ScreenshotSampleTest : DocLocScreenshotTestCase(
    screenshotsDirectory = File("screenshots"),
    locales = "en,ru"
) {

    @ScreenShooterTest
    @Test
    fun test() {
        before{
        }.after {
        }.run {
            
            step("1. Do the first step") {
                // ...
                captureScreenshot("First step")
            } 
            
            step("2. Do the second step") {
                // ... 
                captureScreenshot("Second step")
            }
        }
    }        
}
```

There are two parameters passed in the base constructor: 
- locales - comma-separated string with locales to run test with;
- screenshotsDirectory - directory to save screenshot.

For full example, check the [com.kaspersky.kaspressample.tests.docloc.ScreenshotSampleTest]. 

Notice, that the test is marked with `@ScreenShooterTest` annotation. This is intended to 
filter only screenshooter tests to be run. For example, you could pass the 
annotation to default `AndroidJUnitRunner` with command: 

```
adb shell am instrument -w -e annotation com.kaspersky.kaspresso.annotations.ScreenShooterTest your.package.name/android.support.test.runner.AndroidJUnitRunner
```

**Screenshot files location**

All screenshot files are stored in the directory, that you passed in the base class constructor. 
They are sorted by locale and test name:  

`<base directory>/<test class canonical name>/<locale>/<your tag>.png`

For the sample test case, the files tree should be like: 

    - screenshots
        -  com.kaspersky.kaspressample.tests.docloc.ScreenshotSampleTest
            - en
                // screenshot files
            - ru
                // screenshot files
                
So, in order to save screenshots at external storage, the test application requires 
`android.permission.WRITE_EXTERNAL_STORAGE` permission. 

## Advanced usage

In most cases there is no need to launch certain activity, do a lot of steps before reaching 
necessary functionality. Often showing fragments will be sufficient to make required screenshots.
 So you could create a FragmentTestActivity and manage fragment transactions: 
 
```kotlin
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

Then create a base product screenshot test case: 
 
 ```kotlin
open class ProductDocLocScreenshotTestCase(testName: String) : DocLocScreenshotTestCase(
    File(testName), "en,ru"
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



**Methods provided by DocLocScreenshotTestCase class**

Except of `captureScreenshot(String)` method, `DocLocScreenshotTestCase` also provides 
`getUiSafeProxy(...)` and `getUiSafeProxyFromImplementation(...)` methods. They return dynamic 
proxies on the interfaces or implementations which allows to call methods in the main thread and 
ignore all exceptions thrown. For more information, see [com.kaspersky.kaspresso.reflect.proxy.UiInvocationHandler] 
implementation. 