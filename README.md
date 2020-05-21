[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Kaspresso-green.svg?style=flat )](https://android-arsenal.com/details/1/7896)
[![Android Weekly](https://img.shields.io/badge/Android%20Weekly-383-green.svg)](http://androidweekly.net/issues/issue-383)
[![Android Weekly](https://img.shields.io/badge/Android%20Weekly-392-green.svg)](http://androidweekly.net/issues/issue-392)
[![Download](https://api.bintray.com/packages/ruslanmingaliev/Kaspresso/Kaspresso/images/download.svg?version=1.1.0) ](https://bintray.com/ruslanmingaliev/Kaspresso/Kaspresso/1.1.0/link)
![Build and Deploy](https://github.com/KasperskyLab/Kaspresso/workflows/Build%20and%20Deploy/badge.svg)

# Kaspresso

Kaspresso is a great framework for UI testing. Based on [Espresso](https://developer.android.com/training/testing/espresso) and [UI Automator](https://developer.android.com/training/testing/ui-automator), Kaspresso provides a wide range of additional amazing features, such as:
* 100% stability, no flakiness.
* Significantly faster execution of UI Automator commands.
With Kaspresso, some UI Automator commands run **10 times faster**!
* Excellent readability due to human DSL.
* Incredible mechanism of interceptors that allows you to catch all actions and assertions in one place.
* Full logging.
* Ability to call ADB commands.
* UI tests writing philosophy, implemented with DSL.
* Features screenshotting.

And many more!

![Kaspresso](https://habrastorage.org/webt/dw/jh/9k/dwjh9kypjl637kxj8tiaxwjvtp0.png)

## Capabilities of Kaspresso

#### Readability

We like the syntax that [Kakao](https://github.com/agoda-com/Kakao) applies to write UI tests. This wrapper over Espresso uses the Kotlin DSL approach, that makes the code significantly shorter and more readable. See the difference:

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
We used the same approach to develop our own wrapper over UI Automator, and we called it *Kautomator*. Take a look at the code below:

**UI Automator**:
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
**Kautomator**:
```kotlin
MainScreen {
    simpleEditText {
        replaceText("Kaspresso")
        hasText("Kaspresso")
    }
}
```
Since Kakao and Kautomator provide almost identical APIs, you don’t have to care about what is under the hood of your tests, either Espresso or UI Automator. With Kaspresso, you write the same tests for both.

Read about [Kakao](/wiki/01_Wrapper_over_Espresso.md) and [Kautomator](/wiki/02_Wrapper_over_UiAutomator.md) in details.

However, Kakao and Kautomator themselves don't help you to see the relation between the test and the corresponding test case. Also, a long test often becomes a giant piece of code that is impossible to split into smaller parts.
That's why we have created an additional Kotlin DSL that allows you to read your test more easily. 

See the example below:

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

#### Stability 

Sometimes your UI test passes ten times, then breaks on the eleventh attempt for some mysterious reason. It’s called *flakiness*. 

The most popular reason for flakiness is the instability of the UI tests libraries, such as Espresso and UI Automator. To eliminate this instability, Kaspresso uses DSL wrappers and [interceptors](#Interceptors). 

Also, [some Kaspresso features](/wiki/04_How_to_write_autotests.md#sweet-additional-features) can help you resolve a lot of typical problems in UI testing.

#### UI test libraries acceleration

Let’s watch some short video that shows the difference between the original UI Automator (on the right) and the accelerated one (on the left).

![](https://habrastorage.org/webt/ti/kv/ki/tikvkij1vjesnacrxqm-lk0coly.gif)

Here is [a short explanation](./wiki/02_Wrapper_over_UiAutomator.md#accelerate-ui-automator) of why it is possible.

<a name="Interceptors"></a>
#### Interceptors

We developed [Kaspresso behavior interceptors](/wiki/03_Kaspresso_configurator.md#some-words-about-behavior-interceptors) on the base of [Kakao/Kautomator Interceptors](/wiki/03_Kaspresso_configurator.md#kaspresso-interceptors-based-on-kakaokautomator-interceptors) to catch failures.

Thanks to interceptors, you can do a lot of useful things, such as:
* add custom actions to each library operation like writing a log or taking a screenshot;
* overcome flaky library operations by re-running failed actions, scrolling the parent layout or removing the android system dialog;

and many more (see [the manual](/wiki/03_Kaspresso_configurator.md#kaspresso-interceptors-based-on-kakaokautomator-interceptors)).

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
