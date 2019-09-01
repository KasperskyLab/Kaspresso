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
Also, when you use Model-View-Presenter architectural pattern, you are able to control UI state
directly through the View interface. So, there is no need to interact with the application interface 
and wait for changes. 
 
First create a base test activity with `setFragment(Fragment)` method in your application:
 
```kotlin
class FragmentTestActivity : AppCompatActivity() {

    fun setFragment(fragment: Fragment) = with(supportFragmentManager.beginTransaction()) {
        replace(android.R.id.content, fragment)
        commit()
    }
}
```

Then add a base product screenshot test case: 
 
 ```kotlin
open class ProductDocLocScreenshotTestCase : DocLocScreenshotTestCase(
    screenshotsDirectory = File("screenshots"),
    locales = "en,ru"
) {

    @get:Rule
    val activityTestRule = ActivityTestRule(FragmentTestActivity::class.java, false, true)

    protected val activity: FragmentTestActivity
        get() = activityTestRule.activity

}
```  

This test case would run your `FragmentTestActivity` on startup. Now you are able to write your screenshooter tests. 
For example, create a new test class which extends `ProductDocLocScreenshotTestCase`: 

```kotlin
@RunWith(AndroidJUnit4::class)
class AdvancedScreenshotSampleTest : ProductDocLocScreenshotTestCase() {

    private val fragment: FeatureFragment = FeatureFragment()
    private val view: FeatureView = getUiSafeProxy(fragment as FeatureView)

    @ScreenShooterTest
    @Test
    fun test() {
        before {
            activity.setFragment(fragment)
        }.after {
        }.run {

            step("1. Step 1") {
                // ... [view] calls
                captureScreenshot("Step 1")
            }

            step("2. Step 2") {
                // ... [view] calls
                captureScreenshot("Step 2")
            }

            step("3. Step 3") {
                // ... [view] calls
                captureScreenshot("Step 3")
            }
            
            // ... other steps
        }
    }
}
```

As you might notice, the `getUiSafeProxy` method called to get an instance of `FeatureView`. 
This method wraps your View interface and returns a proxy on it. 
The proxy guarantees that all the methods of the View interface you called, will be invoked on the main thread. 
There is also `getUiSafeProxyFromImplementation` which wraps an implementation rather than an interface. 

For full example, check [com.kaspersky.kaspressample.tests.docloc.advanced.AdvancedScreenshotSampleTest] class.
 