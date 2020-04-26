[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Kaspresso-green.svg?style=flat )](https://android-arsenal.com/details/1/7896)
[![Android Weekly](https://img.shields.io/badge/Android%20Weekly-383-green.svg)](http://androidweekly.net/issues/issue-383)
[![Android Weekly](https://img.shields.io/badge/Android%20Weekly-392-green.svg)](http://androidweekly.net/issues/issue-392)
[![Download](https://api.bintray.com/packages/ruslanmingaliev/Kaspresso/Kaspresso/images/download.svg?version=1.1.0) ](https://bintray.com/ruslanmingaliev/Kaspresso/Kaspresso/1.1.0/link)
[![CircleCI](https://circleci.com/gh/KasperskyLab/Kaspresso.svg?style=svg)](https://circleci.com/gh/KasperskyLab/Kaspresso)

# Kaspresso

Kaspresso is a great UiTest framework based on [Espresso](https://developer.android.com/training/testing/espresso) and 
[UI Automator](https://developer.android.com/training/testing/ui-automator), and providing a wide set of such amazing features as:
1. 100% stability.
2. Increased speed of Espresso and UiAutomator command execution. <br>
In case of UiAutomator, we get **10x** acceleration for some commands!
3. Beautiful readability through human DSL.
4. Incredible mechanism of interceptors allowing you to catch all action and assertions in one place!
5. Understandable and full logging.
6. Ability to call ADB commands.
7. Philosophy how to write ui-tests and DSL providing this philosophy.
8. Light and convenient feature's screenshoting.
9. Etc.

![](https://habrastorage.org/webt/dw/jh/9k/dwjh9kypjl637kxj8tiaxwjvtp0.png)

Dive deep into capabilities of Kaspresso!

## Capabilities

#### Readability

We like a syntax to write ui-tests providing by [Kakao](https://github.com/agoda-com/Kakao) (a wrapper over Espresso offering Kotlin DSL approach). 
Just compare.

**Espresso**:
```kotlin
@Test
fun testFirstFeature() {
    onView(withId(R.id.toFirstFeature))
        .check(ViewAssertions.matches(
               ViewMatchers.withEffectiveVisibility(
                       ViewMatchers.Visibility.VISIBLE)))
    onView(withId(R.id.toFirstFeature)).perform(click())
}
```
**Kakao**:
```kotlin
@Test
fun testFirstFeature() {
    mainScreen {
        toFirstFeatureButton {
            isVisible()
            click()
        }
    }
}
```
In according to this approach, we decided to write the same wrapper over UI Automator and called it **Kautomator**.
Have a look at the code below. <br>
**UiAutomator**:
```kotlin
val instrumentation: Instrumentation = InstrumentationRegistry.getInstrumentation()
val uiDevice = UiDevice.getInstance(instrumentation)

val uiObject = uiDevice.wait(
    Until.findObject(
        By.res(
            "com.kaspersky.kaspresso.sample_kautomator",
            "editText"
        )
    ),
    2_000
)

uiObject.text = "Kaspresso"
assertEquals(uiObject.text, "Kaspresso")
```
**Kautomator**
```kotlin
MainScreen {
    simpleEditText {
        replaceText("Kaspresso")
        hasText("Kaspresso")
    }
}
```
As we mentioned Kakao and Kautomator offer almost identical API to work.
So, a developer doesn't feel the difference while they write a test and use Espresso or UiAutomator under the hood. <br>
Please, read about [Kakao](/wiki/01_Wrapper_over_Espresso.md) and [Kautomator](/wiki/02_Wrapper_over_UiAutomator.md) in details.

But, the existance of only suitable Kotlin DSL wrappers over libraries doesn't help to correlate your test with the test-case
on which this test is based. Also, the long test often transforms into an inseparable code wall. It's a problem.
That's why we have created an additional Kotlin DSL allowing you to make the perception of your test at absolutely another level.<br>

Please, observe the code below:
```kotlin
@Test
fun shouldPassOnNoInternetScanTest() =
    beforeTest {
        activityTestRule.launchActivity(null)
        // some things with the state
    }.afterTest {
        // some things with the state
    }.run {
        step("Open Simple Screen") {
            MainScreen {
                nextButton {
                    isVisible()
                    click()
                }
            }
        }

        step("Click button_1 and check button_2") {
            SimpleScreen {
                button1 {
                    click()
                }
                button2 {
                    isVisible()
                }
            }
        }

        step("Click button_2 and check edit") {
            SimpleScreen {
                button2 {
                    click()
                }
                edit {
                    flakySafely(timeoutMs = 7000) { isVisible() }
                    hasText(R.string.text_edit_text)
                }
            }
        }

        step("Check all possibilities of edit") {
            scenario(
                CheckEditScenario()
            )
        }
    }
```
A great manual about how to write tests correctly is availbable [here](/wiki/04_How_to_write_autotests.md).

#### Stability 

Flakiness in UI tests means that one test can pass 10 times in a row, but breaks on the eleventh attempt. The reason for failure is so mysterious and unpredictable, and all of this magic sometimes forces you to cry when nobody sees you. Unfortunately, the most popular reason for such behavior is the instability of ui-tests libraries (Espresso and UI Automator). <br>
**Kaspresso eliminates instability of ui-tests libraries** thanks to DSL wrappers and the new mechanism of interceptors
where all failures are caught. <br>
More detailed info about interceptors is [here](/wiki/03_Kaspresso_configurator.md#kaspresso-interceptors-based-on-kakaokautomator-interceptors) and [here](/wiki/03_Kaspresso_configurator.md#some-words-about-behavior-interceptors). Also, [some sweet features](/wiki/04_How_to_write_autotests.md#sweet-additional-features) helping to resolve a lot of typical problems are available in Kaspresso.

#### UI-test libraries acceleration

Shut up and watch the video =)

![](https://habrastorage.org/webt/ti/kv/ki/tikvkij1vjesnacrxqm-lk0coly.gif) <br>
The left video is a boosted UI Automator, the right video is a default UI Automator.

How is it possible? Please, review [the short explanation](./wiki/02_Wrapper_over_UiAutomator.md#accelerate-ui-automator).

#### Interceptors

We have introduced a mechanism of interceptors giving an ability to catch all actions going to Espresso or UI Automator. <br>
Thanks to this mechanism, a developer can add additional custom actions to each library operation (a logging, a screenshot), try to overcome flaky library operations (re-run failed actions, scroll the parent layout, remove the android system dialog) and do other interesting things. <br>
Please, read the [comprehensive manual](/wiki/03_Kaspresso_configurator.md#kaspresso-interceptors-based-on-kakaokautomator-interceptors).

#### Logging
Kaspresso transform your tests' logs into understandable and pleasant text:

<img src="https://habrastorage.org/webt/03/nn/qg/03nnqgupdqnwa_i4jwyz1uqq6r0.png" />
<img src="https://habrastorage.org/webt/tq/az/3v/tqaz3vjsgpw0-ivylrfbnuqyiqa.png" />

#### Ability to call Adb commands

Espresso and UI Automator don't allow to call adb commands inside a test. <br>
That's why we have written special [AdbServer repository](https://github.com/KasperskyLab/AdbServer) fixing mentioned problem. <br>

In Kaspresso, the developer can call ADB and CMD commands by ```AdbServer``` class. <br>
The manual is [here](/wiki/06_AdbServer.md)

#### Ability to work with Android System

There are a lot of useful classes in Kaspresso to work with Android System. 

Examples of such work: <br> 
    1. push/pull files, <br>
    2. enable/disable network, <br>
    3. give permissions like a user does, <br>
    4. emulate phone calls, <br>
    5. make screenshots, <br>
    6. enable/disable gps, <br>
    7. set geo location, <br>
    8. enable/disable accessibility, <br>
    9. change app language, <br>
    10. collect and parse logcat, <br>
    11. etc.<br>

A developer can use all of this through [Device class](/wiki/05_Device.md).

#### Feature's screenshotting

If you develop an application that is available across the world then you have to support a lot of localizations. Translation of a word or a phrase is not a big challenge. But, the most important task is to translate according to
context of a specific screen and language culture. That's why a translator must not only have the set of new words/phrases but, also, a set of screens where these words/phrases are used. <br>
Kaspresso offers a possibility to make screenshots fast and automatically. <br>
Also, we prepared a [comprehensive manual and an example](/wiki/07_DocLoc.md) of how to make screenshots even for legacy screens almost immediately. <br>
The spoiler: you don't have to refactor or to mock all of this =)

#### Configurability

We give broad opportunities to tune any part of Kaspresso. Read [info](/wiki/03_Kaspresso_configurator.md) and enjoy it =)

#### The philosophy

The suitable tool to write UI-tests is a necessary requirement but this tool doesn't resolve all problems. Another big bunch of questions is how to write tests and how to organize the entire process. Our team has great experience in introducing autotests in different companies. That's why we have prepared a lot of articles which are devoted to these problems. <br>
Welcome to [learn our lessons](/wiki/04_How_to_write_autotests.md) =)

## Wiki

For all information check [Kaspresso wiki](/wiki/00_Home.md)

## Integration

Include the `jcenter` repository to your root `build.gradle` file (if it does not exist already):

```
allprojects {
    repositories {
        jcenter()
    }
}
```

And then add dependency to your module `build.gradle`:

```
androidTestImplementation 'com.kaspersky.android-components:kaspresso:1.1.0'
```

If you are still using old Android Support libraries we strongly recommend to migrate to AndroidX. <br>
The last version with Android Support libraries is:

```
androidTestImplementation 'com.kaspersky.android-components:kaspresso:1.0.1-support'
```

## Support
English support in telegram - t.me/kaspresso_en <br>
Russian support in telegram - t.me/kaspresso

## Contribution Policy
Kaspresso is an open source project, and depends on its users to improve it. We are more than happy to find you interested in taking the project forward. <br>
Kindly refer to the [Contribution Guidelines](https://github.com/KasperskyLab/Kaspresso/blob/master/CONTRIBUTING.md) for detailed information. <br>

## License
Kaspresso is open source and available under the [Apache License, Version 2.0](https://github.com/KasperskyLab/Kaspresso/blob/master/LICENSE).
