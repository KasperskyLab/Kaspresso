[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Kaspresso-green.svg?style=flat )](https://android-arsenal.com/details/1/7896)
[![Android Weekly](https://img.shields.io/badge/Android%20Weekly-383-green.svg)](http://androidweekly.net/issues/issue-383)
[![Android Weekly](https://img.shields.io/badge/Android%20Weekly-392-green.svg)](http://androidweekly.net/issues/issue-392)
[![CircleCI](https://circleci.com/gh/KasperskyLab/Kaspresso.svg?style=svg)](https://circleci.com/gh/KasperskyLab/Kaspresso)

# Kaspresso

Kaspresso is a great UiTest framework based on [Espresso](https://developer.android.com/training/testing/espresso) and 
[UI Automator](https://developer.android.com/training/testing/ui-automator), and providing a wide set of such amazing features as
- 100% stability 
- increased speed of Espresso and UiAutomator command execution. <br>
In case of UiAutomator, we get **10x** acceleration for some commands!
- beautiful readability through human DSL
- incredible mechanism of interceptors allowing you to catch all action and assertions in one place!
- understandable and full logging
- ability to call ADB commands
- philosophy how to write ui-tests and DSL providing this philosophy
- light and convenient feature's screenshoting
- etc.

![](https://habrastorage.org/webt/dw/jh/9k/dwjh9kypjl637kxj8tiaxwjvtp0.png)

Deep in dive into capabilities of Kaspresso!

## Capabilities

// todo put the relevant references to more detailed info at the end of each section

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
As you mentioned Kakao and Kautomator offer almost identical API to work.
So, a developer doesn't feel the difference while he/she writes a test and uses Espresso or UiAutomator under the hood. <br><br>

But, the existance of only suitable Kotlin DSL wrappers over libraries doesn't help to correlate your test with the test-case
on which this test is based. Also, the long test often transforms into an inseparable code wall. It's a problem.
That's why we have created an additional Kotlin DSL allowing you to make the perception of your test at absolutely another level.<br><br>

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

#### Stability 

Flakiness in UI tests means that one test can pass 10 times in a row, but breaks on the eleventh attempt.
The reason for failure is so mysterious and unpredictable, and all of these magic sometimes forces you to cry when nobody sees you. <br>
Unfortunately, the most popular reason for such behavior is the instability of ui-tests libraries (Espresso and UI Automator). <br>
**Kaspresso eliminates instability of ui-tests libraries** thanks to DSL wrappers and the new mechanism of interceptors
where all failures are caught.

#### UI-test libraries acceleration

Shut up and watch the video!
// todo make the video

#### Interceptors

We have introduced a mechanism of interceptors giving an ability to catch all actions going to Espresso or UI Automator. <br>
Thanks to this mechanism, a developer can add additional custom actions to each library operation (a logging, a screenshoting),
try to struggle flaky library operations (re-run failed actions, scroll the parent layout, remove the android system dialog) and do other interesting things. <br>
// todo detailed info reference

#### Logging
Kaspresso transform your tests' logs into understandable and pleasant text:

<img src="https://habrastorage.org/webt/03/nn/qg/03nnqgupdqnwa_i4jwyz1uqq6r0.png" />
<img src="https://habrastorage.org/webt/tq/az/3v/tqaz3vjsgpw0-ivylrfbnuqyiqa.png" />

#### Ability to call Adb commands

Espresso and UI Automator don't allow to call adb commands inside a test. <br>
That's why we have written special [AdbServer repository](https://github.com/KasperskyLab/AdbServer) fixing mentioned problem. <br>

In Kaspresso, the developer can call adb and cmd commands by ```AdbServer``` class.

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

All of this a developer can use through [Device class](kaspresso/src/main/kotlin/com/kaspersky/kaspresso/device/Device.kt)
// todo check the reference
// todo more detailed info

#### Feature's screenshoting

If you develop an application that is available across the world then you have to support a lot of localizations.
Translation of a word or a phrase is not a big challenge. But, the most important task is to translate in according to
context of a specific screen and language culture. <br>
That's why a translator must have not only the set of new words/phrases but, also, a set of screens where these words/phrases are using. <br>
Kaspresso offers a possibility to make screenshots fastly and automatically.
Also, we prepared a manual and an example how to make screenshots even for legacy screens almost immediately. <br>
The spoiler: you don't have to refactor or to mock all of this =)

#### Configurability

We give broad opportunities to tune any part of Kaspresso. Enjoy it =)
// todo ref to detailed info

#### The philosophy

The suitable tool to write UI-tests is a necessary requirement but only tool doesn't resolve all problems.
Another big bunch of questions is how to write tests and how to organize the entire process.
Our team has great experience in introducing autotests in different companies. That's why we have prepared a lot of articles
which are devoted to these problems.
// todo ref and some examples maybe

## Wiki

For all information check [Kaspresso wiki](https://github.com/KasperskyLab/Kaspresso/blob/master/wiki/00.%20Home.md)

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
androidTestImplementation 'com.kaspersky.android-components:kaspresso:1.0.1'
```

If you are still using old Android Support libraries, use the `<version>-support` artifact:

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
