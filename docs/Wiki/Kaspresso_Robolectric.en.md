# Kaspresso tests running on the JVM with Robolectric

## Main purpose

Since Robolectric 4.0, we can also run Espresso-like tests also on the JVM with Robolectric.
That is part of the [Project nitrogen from Google](https://www.youtube.com/watch?v=-_kZC29sWAo) (which became Unified Test Platform), where they want to allow developers to write UI test once, and run them everywhere.

However, before Kaspresso 1.3.0, if you tried to run Kaspresso-like test extending TestCase on the JVM with Robolectric, you got the following error:
```
java.lang.NullPointerException
	at androidx.test.uiautomator.QueryController.<init>(QueryController.java:95)
	at androidx.test.uiautomator.UiDevice.<init>(UiDevice.java:109)
	at androidx.test.uiautomator.UiDevice.getInstance(UiDevice.java:261)
	at com.kaspersky.kaspresso.kaspresso.Kaspresso$Builder.<init>(Kaspresso.kt:297)
	at com.kaspersky.kaspresso.kaspresso.Kaspresso$Builder$Companion.simple(Kaspresso.kt:215)
	...
```
That is because Robolectric is just compatible with Espresso and not with UI Automator.

Now, all Kaspresso tests are allowed to be executed correctly on the JVM with Robolectric with the following restrictions:

1. Easy configuration of your project according to [Robolectric guideline](http://robolectric.org/blog/2018/10/25/robolectric-4-0/).
2. Not possible to use adb-server because there is no a term like "Desktop" on the JVM environment. Tests that use adb-server will crash on the JVM with Robolectric with very explaining error message.
3. Not possible to work with `UiDevice` and `UiAutomation` classes. That's why a lot of (not all!) implementations in `Device` will crash on the JVM with Robolectric with `NotSupportedInstrumentalTestException`.
4. Non working Kautomator. Mentioned problem with `UiDevice` and `UiAutomation` classes affect the entire Kautomator. So, tests using Kautomator will crash on the JVM with Robolectric with `KautomatorInUnitTestException`.
5. Interceptors that use `UiDevice`, `UiAutomation` or adb-server are turning off on the JVM with Robolectric automatically.
6. `DocLocScreenshotTestCase` will crash on the JVM with Robolectric with `DocLocInUnitTestException`.

## Usage
To create a test that can run on a device/emulator and on the JVM, we recommend to create a `sharedTest` folder, and configure `sourceSets` in gradle.

```kotlin
sourceSets {
   ...
   //configure shared test folder
   val sharedTestFolder = "src/sharedTest/kotlin"
   val androidTest by getting {
       java.srcDirs("src/androidTest/java", sharedTestFolder)
   }
   val test by getting {
       java.srcDirs("src/test/java", sharedTestFolder)
   }
}
```

It is also important that such tests use ``@RunWith(AndroidJUnit4::class)``, since it is required by Robolectric.

In order to run your shared tests as Unit Tests on the JVM, you need to run a command looking like this:
```
./gradlew :MODULE:testVARIANTUnitTest --info --tests "PACKAGE.CLASS"
```

For example, to run the sample RobolectricTest on the JVM you need to run:
```
./gradlew :samples:kaspresso-sample:testDebugUnitTest --info --tests "com.kaspersky.kaspressample.sharedtest.SharedSimpleFlakyTest"
```

To run them on a device/emulator, the command to run would look like this:
```
./gradlew :MODULE:connectedVARIANTAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=PACKAGE.CLASS
```

For instance, to run the sample SharedTest on a device/emulator, you need to run:
```
./gradlew :samples:kaspresso-sample:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.kaspersky.kaspressample.sharedtest.SharedSimpleFlakyTest
```

## Accommodation of tests to work on the JVM (with Robolectric) environment

We've prepared a bunch of tools and advices to accommodate your tests for the JVM (with Robolectric) environment.

Let's consider the most popular problem when a test uses a class containing calls to `UiDevice`/`UiAutomation`/`AdbServer` or other not working in JVM environment things.

For example, your test looks like below:
```kotlin
@RunWith(AndroidJUnit4::class)
class FailingSharedTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(DeviceSampleActivity::class.java, false, true)

    @Test
    fun exploitSampleTest() =
        run {
            step("Press Home button") {
                device.exploit.pressHome()
            }
            //...
        }
}
```

`device.exploit.pressHome()` calls `UiDevice` under the hood and it leads to a crash the JVM environment.

There is following possible solution:
``` kotlin
// change an implementation of Exploit class
@RunWith(AndroidJUnit4::class)
class FailingSharedTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        exploit = 
            if (isAndroidRuntime) ExploitImpl() // old implementation
            else ExploitUnit() // new implementation without UiDevice
    }
) { ... }

// isAndroidRuntime property is available in Kaspresso.Builder.
``` 

Also, if your custom Interceptor uses `UiDevice`/`UiAutomation`/`AdbServer` then you can turn off this Interceptor for JVM. The example:
```kotlin
class KaspressoConfiguringTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        viewBehaviorInterceptors = if (isAndroidRuntime) mutableListOf(
           YourCustomInterceptor(),
           FlakySafeViewBehaviorInterceptor(flakySafetyParams, libLogger)
       ) else mutableListOf(
           FlakySafeViewBehaviorInterceptor(flakySafetyParams, libLogger)
       )
    }
) { ... }
``` 

Of course, there is a very obvious last option. Just don't include the test in a set of Unit tests.

**Further remarks**

As of Robolectric 4.8.1, there are some limitations to sharedTest: those tests run flawless on an emulator/device, but fail on the JVM

1. Robolectric-Espresso supports Idling resources, but [doesn't support posting delayed messages to the Looper](https://github.com/robolectric/robolectric/issues/4807#issuecomment-1075863097)
2. Robolectric-Espresso will not support [tests that start new activities](https://github.com/robolectric/robolectric/issues/5104) (i.e. activity jumping)
