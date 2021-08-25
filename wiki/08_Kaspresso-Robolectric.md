# Kaspresso tests running on the JVM with Robolectric

## Main purpose

Since Robolectric 4.0, we can also run Espresso-like tests also on the JVM with Robolectric.
That is part of the [Project nitrogen from Google](https://www.youtube.com/watch?v=-_kZC29sWAo) (which became Unified Test Platform), where they want to allow developers to write UI test once, and run them everywhere.

However, if you try to run Kaspresso-like test extending TestCase on the JVM with Robolectric, you will get the following error:
```
java.lang.NullPointerException
	at androidx.test.uiautomator.QueryController.<init>(QueryController.java:95)
	at androidx.test.uiautomator.UiDevice.<init>(UiDevice.java:109)
	at androidx.test.uiautomator.UiDevice.getInstance(UiDevice.java:261)
	at com.kaspersky.kaspresso.kaspresso.Kaspresso$Builder.<init>(Kaspresso.kt:297)
	at com.kaspersky.kaspresso.kaspresso.Kaspresso$Builder$Companion.simple(Kaspresso.kt:215)
	...
```
That is because Robolectric is just compatible with Espresso and not with uiAutomator.

In order to enable Kaspresso tests to run also on the JVM with Robolectric, apart from all the [configuration required for Robolectric](http://robolectric.org/blog/2018/10/25/robolectric-4-0/),
the Kaspresso Builder of your test class needs to contain sharedTest = true

```kotlin
SharedTest : TestCase(Kaspresso.Builder.simple(sharedTest = true)){...}
```

However, this option have some caveats:
1. Not possible to use adb-server. Some interceptors that require Kautomator e.g. the "Close System Dialog interceptor" are also disabled.
2. You cannot use Kautomator `UiScreen<S>` anymore. If you do so, your test will fail with a `KautomatorInSharedTestException`.
3. All the interactions with `Device` are mocked. This is done to allow Kaspresso tests run on the JVM. If you use call any method from `device`, e.g. `device.hackPermission.grant(permission)`, the test fails with an `ActionNotSupportedInSharedTestException`


## Usage
To create a test that can run on a device/emulator and on the JVM, we recommend to create a `sharedTest` folder, and configure `sourceSets` in gradle accordingly, similar to what you can see under the `build.gradle.kts` :samples:kaspresso-sample

```kotlin
sourceSets {
   ...
   //configure shared test folder
   val sharedTestFolder = "src/sharedTest/kotlin"
   val androidTest by getting {
       java.srcDirs("src/andoroidTest/java", sharedTestFolder )
   }
   val test by getting {
       java.srcDirs("src/test/java", sharedTestFolder )
   }
}
```

It is also important that such tests use ``@RunWith(AndroidJUnit4::class)``, since it is required by Robolectric. 

We also recommend not to define your Screens as `object` but as `class` instead, since we've observed some shared tests timing out otherwise.
Prefer to define your Screens like this

```kotlin
class FragmentScrollingScreen : KScreen<FragmentScrollingScreen>() {
...
}
```

to this

```kotlin
object FragmentScrollingScreen : KScreen<FragmentScrollingScreen>() {
...
}
```

In order to run your shared tests as Unit Tests on the JVM, you need to run a command looking like this:
```
./gradlew :MODULE:testVARIANTUnitTest --tests "PACKAGE.CLASS"
```

For example, to run the sample RobolectricTest on the JVM you need to run:
```
./gradlew :samples:kaspresso-sample:testDebugUnitTest --tests "com.kaspersky.kaspressample.sharedtest.SharedTest"
```

To run them on a device/emulator, the command to run would look like this:
```
./gradlew :MODULE:connectedVARIANTAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=PACKAGE.CLASS
```

For instance, to run the sample SharedTest on a device/emulator, you need to run:
```
./gradlew :samples:kaspresso-sample:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.kaspersky.kaspressample.sharedtest.SharedTest
```


**Further remarks**

As of Robolectric 5.4.1, there are some limitations to sharedTest: those tests run flawless on an emulator/device, but fail on the JVM
1) Robolectric-Espresso [does not support Idling resources](https://github.com/robolectric/robolectric/issues/4807) yet
2) Robolectric-Espresso will not support [tests that start new activities](https://github.com/robolectric/robolectric/issues/5104)(i.e. activity jumping)
