# Kaspresso-Nitrogen

## Main purpose

Since Robolectric 4.0, we can also run Espresso-like tests also on the JVM with Robolectric.
That is part of the [Project nitrogen from Google](https://www.youtube.com/watch?v=-_kZC29sWAo), where they want to allow developers to write UI test once, and run them everywhere.

However, if you try to run Kaspresso-like test extending TestCase on the JVM with Roboelectric, you will get the following error:
```
java.lang.NullPointerException
	at androidx.test.uiautomator.QueryController.<init>(QueryController.java:95)
	at androidx.test.uiautomator.UiDevice.<init>(UiDevice.java:109)
	at androidx.test.uiautomator.UiDevice.getInstance(UiDevice.java:261)
	at com.kaspersky.kaspresso.kaspresso.Kaspresso$Builder.<init>(Kaspresso.kt:297)
	at com.kaspersky.kaspresso.kaspresso.Kaspresso$Builder$Companion.simple(Kaspresso.kt:215)
	...
```
That is because Robolectric is just compatible with Espresso and not with uiAutomator, and TestCase class initialize both, Espresso through Kaspresso and UiAutomator through Kautomator.

In order to enable Kaspresso tests to run also on the JVM with Roboelectric, apart from all the [configuration required for Roboelectric](http://robolectric.org/blog/2018/10/25/robolectric-4-0/),
your test need to extend NitrogenTestCase() instead of TestCase(). NitrogenTestCase only initializes Kaspresso, leaving Kautomator aside. This also means, no adb-server possible either, as well as some other interceptors e.g. the "Close System Dialog interceptor"
or Screens using Kautomator under the hood

        
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

We also recommend not to define your Screens as Object but as Class instead, since we've observed some shared tests timing out otherwise.
Prefer to define your Screens like this

```kotlin
class NitrogenFragmentScrollingScreen : KScreen<NitrogenFragmentScrollingScreen>() {
...
}
```

to this

```kotlin
object NitrogenFragmentScrollingScreen : KScreen<NitrogenFragmentScrollingScreen>() {
...
}
```

In order to run your shared tests as Unit test on the JVM, you need to run a command looking like this:
```
./gradlew :MODULE:connectedVARIANTAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=PACKAGE.CLASS
```

For example, to run the sample NitrogenSharedTest on the JVM you need to run:
```
./gradlew :samples:kaspresso-sample:testDebugUnitTest --tests "com.kaspersky.kaspressample.nitrogen.test.NitrogenSharedTest"
```

To run them on a device/emulator, the command to run would look like this:
```
./gradlew :MODULE:testVARIANTUnitTest --tests "PACKAGE.CLASS"
```

For instance, to run the sample NitrogenSharedTest on a device/emulator, you need to run:
```
./gradlew :samples:kaspresso-sample:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.kaspersky.kaspressample.nitrogen.test.NitrogenSharedTest
```


**Further remarks**

As of Robolectric 5.4.1, there are some limitations to sharedTest: those tests run flawless on an emulator/device, but fail on the JVM
1) Robolectric-Espresso [does not support Idling resources](https://github.com/robolectric/robolectric/issues/4807) yet
2) Robolectric-Espresso will not support [tests that start new activities ](https://github.com/robolectric/robolectric/issues/5104)(i.e. activity jumping)
