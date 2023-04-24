# Screenshot tests

## Main purpose

Sometimes when developing new features, there is a need to check if the application works properly in all supported languages. Manual locale setting changes could take a long time and require the efforts of developers, QA engineers, and etc. Also, it could increase the duration of the localization process.

In order to avoid that, Kaspresso provides ```DocLocScreenshotTestCase```
which allows taking screenshots in all locales you specified. `DocLocScreenshotTestCase` extends
default Kaspresso `TestCase` and offers the opportunity to make screenshots out the box by
calling `DocLocScreenshotTestCase#captureScreenshot(String)` method.

## Usage

To create a single test, you should extend `DocLocScreenshotTestCase` class as shown below:

```kotlin
@RunWith(AndroidJUnit4::class)
class ScreenshotSampleTest : DocLocScreenshotTestCase(
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

There is one parameter passed in the base constructor:
- locales - comma-separated string with locales to run test with.
  Captured screenshots will be available in the device's storage at the path "/sdcard/screenshots/".

For full example, check the [ScreenshotSampleTest](../samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/docloc_tests/ScreenshotSampleTest.kt).

Notice, that the test is marked with `@ScreenShooterTest` annotation. This is intended to filter only screenshooter tests to be run. For example, you could pass the
annotation to default `AndroidJUnitRunner` with command:

```
adb shell am instrument -w -e annotation com.kaspersky.kaspresso.annotations.ScreenShooterTest your.package.name/android.support.test.runner.AndroidJUnitRunner
```

**Screenshot files location**

All screenshot files are stored in "screenshots" directory by default.
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

**Screenshot's additional meta-info**

When a developer calls ```captureScreenshot("la-la-la")``` method then Kaspresso creates not only a screenshot but also a special xml file. This xml file contains data about all ui elements with their id located on the screen. Example:
```
<Metadata>
    <Window Left="0" Top="0" Width="1440" Height="2560">
        <LocalizedString Text="Simple Fragment" LocValueDescription="com.kaspersky.kaspressample.test:id/text_view_title" Top="140" Left="307" Width="825" Height="146"/>
        <LocalizedString Text="Button 1" LocValueDescription="com.kaspersky.kaspressample.test:id/button_1" Top="370" Left="84" Width="1272" Height="168"/>
        <LocalizedString Text="Button 2" LocValueDescription="com.kaspersky.kaspressample.test:id/button_2" Top="622" Left="84" Width="1272" Height="168"/>
        <LocalizedString Text="Kaspresso" LocValueDescription="com.kaspersky.kaspressample.test:id/edit" Top="874" Left="84" Width="1272" Height="158"/>
        <LocalizedString Text="Simple screen" LocValueDescription="com.kaspersky.kaspressample.test:id/[id:ffffffff]" Top="51" Left="56" Width="446" Height="93"/>
    </Window>
</Metadata>
```
Similar data may be useful for different systems automating the process of localization of an application. The automating system saves xml for each screen and compares it with new versions received by new screenshot's runs. If some difference were revealed the system gives a signal to prepare and send a portion of new words to translate server.

**Screenshots of system dialogs/windows**

Sometimes you want to take screenshots of Android system dialogs or windows. That's why you have to change the language for the entire system. For this purpose, there is additional param in ```DocLocScreenshotTestCase``` constructor - ```changeSystemLocale```. Pay your attention to the fact that ```changeSystemLocale``` defined in true demands ```Manifest.permission.CHANGE_CONFIGURATION```. <br>
Have a look at the code below:
```kotlin
@RunWith(AndroidJUnit4::class)
class ChangeSysLanguageTestCase : DocLocScreenshotTestCase(
    screenshotsDirectory = File("screenshots"),
    locales = "en,ru",
    changeSystemLocale = true
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
The full example is located at [ChangeSysLanguageTestCase](../samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/docloc_tests/ChangeSysLanguageTestCase.kt).

## Important note

Please keep the strategy "one docloc test == one screen". If you will seek to capture screenshots from more than one screen during one test consequences may be unpredictable. Be aware.

## Advanced usage

In most cases, there is no need to launch certain activity, do a lot of steps before reaching necessary functionality. Often showing fragments will be sufficient to make required screenshots.
Also, when you use Model-View-Presenter architectural pattern, you are able to control UI state
directly through the View interface. So, there is no need to interact with the application interface and wait for changes.

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

    private lateinit var fragment: FeatureFragment
    private lateinit var view: FeatureView

    @ScreenShooterTest
    @Test
    fun test() {
        before {
            fragment = FeatureFragment()
            view = getUiSafeProxy(fragment as FeatureView)
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

For full example, check [AdvancedScreenshotSampleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/docloc_tests/advanced/AdvancedScreenshotSampleTest.kt) class.

## Modifying screenshots path and name

By default, all screenshots are stored at: <br>
```/sdcard/screenshots/<locale>/<full qualified test class name>/<method name>.``` <br>
You can change this behavior by providing custom
[ResourcesRootDirsProvider](https://github.com/KasperskyLab/Kaspresso/blob/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/files/resources/ResourcesRootDirsProvider.kt),
[ResourcesDirsProvider](https://github.com/KasperskyLab/Kaspresso/blob/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/files/resources/ResourcesDirsProvider.kt),
[ResourceFileNamesProvider](https://github.com/KasperskyLab/Kaspresso/blob/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/files/resources/ResourceFileNamesProvider.kt) and
[ResourcesDirNameProvider](https://github.com/KasperskyLab/Kaspresso/blob/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/files/resources/ResourcesDirNameProvider.kt) implementations.

Find out details [here](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/docloc_tests/customdirectory/CustomDirectoryScreenshotSampleTest.kt).

## Changes

We have been forced to redesign our resource providing system to support Allure.
That's why we changed the primary constructor of [DocLocScreenshotTestCase](https://github.com/KasperskyLab/Kaspresso/blob/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/testcases/api/testcase/DocLocScreenshotTestCase.kt).
But, we've kept the old option of using `DocLocScreenshotTestCase` with old resource providing system as a secondary constructor.
You can view the secondary constructor as an example of migration from old system to new system.
Also, we've retained tests using old resource providing system in samples to ensure that nothing is broken.

