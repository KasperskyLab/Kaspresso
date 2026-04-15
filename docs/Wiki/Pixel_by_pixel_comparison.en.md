## What is Pixel-by-Pixel Comparison?

This type of test involves the following process:
1) You write an autotest that brings your application to a certain state and takes a screenshot
2) The first run of the autotest is performed in screenshot recording mode. The screenshots are saved in your infrastructure.
3) Each subsequent run of the autotest is performed in a comparison mode. The autotest compares the screenshots from the previous step with the new ones.

If the differences between the screenshots do not exceed the threshold values, the test is considered to be successful. 
If the differences exceed the threshold values, the test fails. In case of a comparison failure, Kaspresso generates an image highlighting 
the differences between the screenshots.

In the example below, the first screenshot is the reference, the second is the new one, and the third is the comparison result.
![pixel-by-pixel-comparison](Images/pixel_by_pixel_comparison/flow.png)

## How to Use Pixel-by-Pixel Comparison
### Code
You can see an example of code in [VisualTestSample.kt](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/visual/VisualTestSample.kt)

Next, we will break down the key points to pay attention to.

A new autotest should be a subclass of VisualTestCase:
```kotlin
class VisualTest : VisualTestCase() {
    // ...
}
```

In the test method, instead of the usual `before{}.after{}.run{}` call, you need to use the `runScreenshotTest`:
```kotlin
@Test
fun test() = runScreenshotTest {
    step("Open Simple Screen") {
        MainScreen {
            simpleButton {
                isVisible()
                click()
                assertScreenshot("some_tag")
            }
        }
    }
}
```
If you need to perform any actions before or after the test, you can pass the `before` 
and `after` blocks to `runScreenshotTest`:
```kotlin
@Test
fun test() = runScreenshotTest(
    before = {},
    after = {},
) {
    // Test code
}
```

Note the call to `assertScreenshot()`. Ше will save a new screenshot in the recording mode and compare it with the reference in the comparison mode.

### Running
Pixel-by-pixel comparison tests can work in the two modes - `Record` and `Compare`. 
When you need to record the reference screenshots, use the `Record` mode. 
When the test code is stabilized, run it in `Compare` mode. The line which recorded 
a screenshot in the `Record` mode, now does the comparison with the reference.

There are two ways to choose the test mode:
1) Manually in the code, by passing a parameter to the VisualTestCase constructor:
```kotlin
class VisualTestSample : VisualTestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        visualTestParams = VisualTestParams(testType = VisualTestType.Record)
    }
)
```
2) Set the Gradle property:
```groovy
kaspresso.visualTestType="Record"
```

**Important** - before running the test, you need to start the adb server. You can find out how to do this [here](Executing_adb_commands.en.md)

After running the test in the recording mode, the screenshots will be saved to the device's memory. 
By default, it will be saved in the `/sdcard/Documents/original_screenshots/` folder. 
Save those screenshots somewhere in your infrastructure. The easiest way is to put them in 
a repository along with the application sources or the same place where you store 
the adb-server jar file.

To make subsequent test runs compare screenshots, you need to switch the test mode 
to `Compare`. If significant differences are found between the screenshots, 
Kaspresso will create a `screenshot_diffs` folder containing auxiliary screenshots 
with differences between the references and new screenshots, and the test will fail.

## How Comparison Works
Each screenshot is represented as a two-dimensional array. Each value in the array is 
the color of a pixel. The test goes through two arrays (reference and new), 
compares individual pixels and calculates the ratio of the number of the 
pixels that do differ to the total number of pixels.

This type of test should be run on the same device configuration (preferably on the same AVD), 
but even in this case, the same test can generate the screenshots that differ from each 
other by several pixels. This is mainly due to the peculiarities of rendering on 
different hardware.

To solve this problem, you can set a threshold value for comparison. If the difference 
between the reference and the new screenshot is less than the specified value, the 
test will be considered successful. The default threshold value is 0.3%.

The threshold value is set by the `tolerance` field in the `VisualTestParams` class.

To determine if an individual pixel differs, a comparison of each of the RGB channels of 
the reference and the original is performed. If the difference between at least one of the 
channels is greater than the value in the `colorTolerance` field of the `VisualTestParams`
class, the pixel is considered different. Note that the value in the channel ranges from 
0 to 255.

## Test Configuration
For more fine-grained configuration of screenshot comparison, the `VisualTestParams` class 
is used. You need to set it up in the Kaspresso builder:
```kotlin
class VisualTestSample : VisualTestCase(kaspressoBuilder = Kaspresso.Builder.simple {
    visualTestParams = VisualTestParams(
        testType = VisualTestType.Compare,
        hostScreenshotsDir = "original_screenshots",
        colorTolerance = 1,
        tolerance = 0.3f
    )
}) {
    // ...
}
```
You can read more about what each parameter is responsible for in the Javadoc.

## Allure Support
If you use Allure to generate reports and want to configure pixel-by-pixel comparison tests, 
you need to make the test class a subclass of `AllureVisualTestCase`:

```kotlin
class VisualTest : AllureVisualTestCase() {
    // ...
}
```
A step with a failed comparison will be marked in the report. To make the test fail on 
the first failed comparison, you need to pass the `failEarly` parameter with a value 
of `true` to the `AllureVisualTestCase` constructor:
```kotlin
class VisualTest : AllureVisualTestCase(failEarly = true) {
    // ...
}
```
