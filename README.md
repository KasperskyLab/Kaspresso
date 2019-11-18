[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Kaspresso-green.svg?style=flat )]( https://android-arsenal.com/details/1/7896)
[![Android Weekly](https://img.shields.io/badge/Android%20Weekly-383-green.svg)](http://androidweekly.net/issues/issue-383)

# Kaspresso

Kaspresso is a UiTest framework based on [Espresso](https://developer.android.com/training/testing/espresso), 
[UIAutomator](https://developer.android.com/training/testing/ui-automator) and 
[Kakao](https://github.com/agoda-com/Kakao) and assisting to write right and no-pain ui tests.

![](https://habrastorage.org/webt/dw/jh/9k/dwjh9kypjl637kxj8tiaxwjvtp0.png)
sjnfgijs

ldknfojksgjnov

## Capabilities

#### Stability 

Kaspresso provides a mechanism to handle a flakiness of Espresso. 

Flakiness in ui tests is when one test passes 50 times but breaks at 51 attempt without any understandable reason. 
Unfortunately, it's a disease of all ui-tests libraries.  
#### Readability

We like a syntax to write ui-tests providing by Kakao. Just compare: 

Espresso:
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
Kakao:
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
But we went even further and made Kakao syntax wider and correlated with the test-case you are writing the test on. 

Attention on the code below:
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
#### Logging
Kaspresso transform your tests' logs into understandable and pleasant text:

<img src="https://habrastorage.org/webt/03/nn/qg/03nnqgupdqnwa_i4jwyz1uqq6r0.png" />
<img src="https://habrastorage.org/webt/tq/az/3v/tqaz3vjsgpw0-ivylrfbnuqyiqa.png" />

#### Flexibility

We have introduced a mechanism of interceptors giving an ability to catch all actions going to Espresso. 

Thanks to this mechanism, the developer can add additional custom actions at each action of Espresso (a logging, a screenshoting), 
handle results of actions of Espresso and re-run failed actions (flaky tests handling), for example, and do other interesting things. 

Part of interceptors was introduced in Kakao version 2.1, another part of interceptors was introduced in Kaspresso.
#### Ability to call Adb commands

Espresso or UiAutomator doesn't allow to call adb commands inside a test. 
That's why we have written special [AdbServer repository](https://github.com/KasperskyLab/AdbServer) fixing mentioned problem. 

In Kaspresso, the developer can call adb and cmd commands by ```AdbServer``` class.  
#### Ability to work with Android System

There are a lot of useful classes in Kaspresso to work with Android System. 

Examples of such work: <br> 
    1. push files, <br>
    2. enable/disable network, <br>
    3. permissions' giving, <br>
    4. emulate phone calls, <br>
    5. make screenshots, <br>
    6. and other.<br>
#### Feature's screenshoting

Sometimes when developing new features, there is a need to check if the application works properly in all supported languages.
Kaspresso offers a possibility make feature's screenshots fast and automatically. 
#### Configurability

The developer can tune any part of Kaspresso thanks to ```Kaspresso.Builder```.
#### The philosophy

Kaspresso proposes such very important things for ui-tests as the set of rules on how to write ui-tests.

## Wiki
For all information check [Kaspresso wiki](https://github.com/KasperskyLab/Kaspresso/blob/master/wiki/00.%20Home.md)

## Integration

To use AdbServer device library, include the `jcenter` repository to your root `build.gradle` file (if it does not exist already):

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
