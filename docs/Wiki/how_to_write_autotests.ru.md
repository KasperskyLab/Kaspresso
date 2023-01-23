# How to write autotests

Anyone who starts to write UI-tests is facing with a problem of how to write UI-tests correctly.
At the beginning of our great way, we had three absolutely different UI-test code styles from four developers. It was amazing.
At that moment, we decided to do something to prevent it. <br>
That's why we have created rules on how to write UI-tests and we have tried to make Kaspresso helping to follow these rules. All rules are divided into two groups: abstractions and structure. Also, we have added a third part containing convenient things resolving the most common problems.

### Abstractions

#### How many abstractions can you have in your tests?
Only one! It's a page object (PO), the term explained well by Martin Fowler in [this article](https://martinfowler.com/bliki/PageObject.html). <br>
In Kakao a ```Screen``` class (in Kautomator a ```UiScreen```) is the implementation of PO. Each screen visible by the user even a simple dialog is a separate PO. <br>
Yes, there are cases when you need new abstraction and it's ok. But our advice is to think well before you introduce new abstraction.

#### How to determine whether View (fragment, dialog, anything) in the project has its description in some Kakao ```Screen```?
In a big project with a lot of UI-tests, it's not an easy challenge.
That's why we have implemented an extended version of the Kakao ```Screen``` - ```KScreen``` ([KScreen](../kaspresso/src/main/kotlin/com/kaspersky/kaspresso/screens/KScreen.kt)). In ```KScreen``` you have to implement two properties: ```layoutId``` and ```viewClass```. So your search if the View has its description in some Kakao ```Screen``` becomes easier. <br>
In Kautomator, there is general ```UiScreen```([UiScreen](../kautomator/src/main/kotlin/com/kaspersky/components/kautomator/screen/UiScreen.kt)) that has an obligatory field - ```packageName```.

#### Is it ok that your PO contains helper methods?
If these methods help to understand what the test is doing then it's ok. <br>
For example, compare two parts of code:
```kotlin
MainScreen {
    shieldView {
        click()
    }
}
```
and
```kotlin
MainScreen {
    navigateToTasksScreen()
}

object MainScreen : KScreen<MainScreen>() {
    //...
    fun navigateToTasksScreen() {
        shieldView {
            click()
        }
    }
    //...
}
```
I am sure that method ```navigateToTasksScreen()``` is more "talking" than the simple click on some ```shieldView```. <br>

#### Can ```Screen``` contain inner state or logic?
No! PO doesn't have any inner state or logic. It's only a description of the UI of concrete View.

#### Assert help methods inside of PO. Is it ok?
We think it's ok because it simplifies the code and puts all info that is about Screen into one class.
The chosen approach doesn't lead to an uncontrolled grow of class size because even a dialog is a separate ```Screen```, so we don't have a huge ```Screen``` describing half of all UI in the app. <br>
Just compare three parts of code executing the same thing:
```kotlin
ReportsScreen {
    assertQuarantinedDetectsCountAfterScan(0)
}
```
```kotlin
ReportsScreen {
    reportsListView {
        childAt<ReportsScreen.ReportsItem>(1) {
            body {
                containsText("Detected: 0")
                containsText("Quarantined: 0")
                containsText("Deleted: 0")
            }
        }
    }
}
```
```kotlin
ReportsScreen {
    val detectsCount = getDetectsCountAfterScan()
    ReportsScreenAssertions.assertQuarantinedDetectsCountAfterScan(
        detectsCount
    )
}
```
We prefer the first variant. But we follow the next naming convention of such methods: ```assert<YourCheckName>```.

### Test structure

#### Test and Test-case correlation
First of all, let's consider the above-mentioned terms. <br>
**Test-case** is a scenario written in human language by a tester to check some feature. <br>
**Test** is an implementation of **Test-case** written in program language by developer/autotester. <br>
Terms were learned. Let's observe some test:
```kotlin
@Test
fun test() {
    MainScreen {
        nextButton {
            isVisible()
            click()
        }
    }
    SimpleScreen {
        button1 {
            click()
        }
        button2 {
            isVisible()
        }
    }
    SimpleScreen {
        button2 {
            click()
        }
        edit {
            attempt(timeoutMs = 7000) { isVisible() }
            hasText(R.string.text_edit_text)
        }
    }
}
```
Not bad. But can you correlate this code with the test-case easy?
No, you need to read the code of the test and the text of the test-case very attentively. It's not comfortable. <br>
So we want to have a structure of the test that would suggest what step of the test-case we are looking at in the particular area of the test.

#### Before/after state of a test
Sometimes you have to change the state of a device (edit contacts, phones, put files into storage and more) while you are running a test. <br>
What to do with a changed state? There are two variants:
1. Create a universal method that sets a device to a consistent state.
2. Clean the state after each test.

The first approach doesn't look like a safe case because you need to remember about all the tests in one huge method. <br>
That's why we prefer the second approach. But it would be nice if the structure of a test forced us to remember about a state.

#### Test structure
All of the above mentioned inspired us to create the test's structure like below:
```kotlin
@Test
fun shouldPassOnNoInternetScanTest() =
    before {
        activityTestRule.launchActivity(null)
        // some things with the state
    }.after {
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
                    attempt(timeoutMs = 7000) { isVisible() }
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
Let's describe the structure: <br>
1. ``` before - after - run``` <br>
   In the beginning, we think about a state. After the state, we begin to consider the test body.
2. ```step``` <br>
   ```step``` in the test is similar to *step* in the test-case. That's why test reading is easier and understandable.
3. ```scenario``` <br>
   There are cases when some sentences of steps are absolutely identical and occur very often in tests.
   For these sentences we have introduced a ```scenario``` where you can replace your sequences of steps.

How is this API enabled? <br>
Let's look at [SimpleTest](../samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/simple_tests/SimpleTest.kt) and
[SimpleTestWithRule](../samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/simple_tests/SimpleTestWithRule.kt). <br>
In the first example we inherit ```SimpleTest``` from ```TestCase```. In the second example we use ```TestCaseRule``` field.
Also you can use ```BaseTestCase``` and ```BaseTestCaseRule```. <br>

#### Test data for the test
A developer, while he is writing a test, needs to prepare some data for the test. It's a common case. Where do you locate test data preparing?
Usually, it's the beginning of the test. <br>
But, first, we want to divide test data preparing and test data usage. Second, we want to guarantee that test data were prepared **before** the test.
That's why we decided to introduce a special DSL to help and to highlight the work with test data preparing. <br>
Please look at the example - [InitTransformDataTest](../samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/dsl_tests/InitTransformDataTest.kt). <br>
Updated DSL looks like:
```kotlin
before {
    // ...
}.after {
   // ...
}.init {
    company {
        name = "Microsoft"
        city = "Redmond"
        country = "USA"
    }
    company {
        name = "Google"
        city = "Mountain View"
        country = "USA"
    }
    owner {
        firstName = "Satya"
        secondName = "Nadella"
        country = "India"
    }
    owner {
        firstName = "Sundar"
        secondName = "Pichai"
        country = "India"
    }
}.transform {
    makeOwner(ownerSurname = "Nadella", companyName = "Microsoft")
    makeOwner(ownerSurname = "Pichai", companyName = "Google")
}.run {
    // ...
}
```
1. ```init``` <br>
   Here, you prepare only sets of data without any transforms and connections. Also, you can make requests to your test server, for example. <br>
   It's an optional block.
2. ```transform``` <br>
   This construction is for transforming of our test data. In our example we join the *owner* and *company*. <br>
   It's an optional block. The block is enabled only after the ```init``` block.

Alexander Blinov wrote a good article about *init-transform* DSL in [russian article](https://habr.com/ru/company/hh/blog/455042/) where he explains all DSL details very well. You are welcome!

#### Available Test DSL forms
Finally, let's look at all available Test DSL in Kaspresso:
1. ```before-after-init-transform-run```
1. ```before-after-init-transform-transform-run```. It's possible to add multiple *transform* blocks.
2. ```before-after-init-run```
3. ```before-after-run```
4. ```init-transform-run```
5. ```init-transform-transform-run```. It's possible to add multiple *transform* blocks.
6. ```init-run```
7. ```run```

#### Examples
You can have a look at examples of how to [use and configure Kaspresso](../samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/configurator_tests)
and how to [use different forms of DSL](../samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/dsl_tests).

### Sweet additional features

#### Some words about *BaseTestContext* method
You can notice an existing of some `BaseTestContext` in `before`, `after` and `run` methods. `BaseTestContext` gives you access to all Kaspresso's entities that a developer can need during the test. Also, `BaseTestContext` gives you insurance that all of these entities were created correctly for the current session and with actual Kaspresso configurator. <br>
So, let's consider what `BaseTestContext` offers.

#### flakySafely
It's a method that receives a lambda and invokes it in the same manner as FlakySafeInterceptors group. <br>
If you disabled this interceptor or if you want to set some special flaky safety params for any view, you can use this method. The most common case is when the default timeout (10 seconds) for flakySafety is not enough, because, for example, the appearance of a view is blocked by long background operation.
```kotlin
step("Check tv6's text") {
    CommonFlakyScreen {
        tv6 {
            flakySafely(timeoutMs = 16_000) {
                hasText(R.string.common_flaky_final_textview)
            }
        }
    }
}
```
More detailed examples are [here](../samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/flaky_tests). Please, observe a [documentation](../kaspresso/src/main/kotlin/com/kaspersky/kaspresso/flakysafety/FlakySafetyProviderGlobalImpl.kt) about implementation details.

### continuously
This function is similar to what `flakySafely` does, but for negative scenarios, where you need all the time to check that something does not happen.
```kotlin
ContinuouslyDialogScreen {
    continuously() {
        dialogTitle {
            doesNotExist()
        }
    }
}
```
The example is [here](../samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/continuously_tests).

### compose
This is a method to make a composed action from multiple actions or assertions, and this action succeeds if at least one of its components succeeds.
`compose` is useful in cases when we don't know an accurate sequence of events and can't influence it. Such cases are possible when a test is performed outside the application.
When a test is performed inside the application we strongly recommend to make your test linear and don't put any conditions in tests that are possible thanks to `compose`. <br>
It is available as an extension function for any `KView`, `UiBaseView` and as just a regular method (in this case it can take actions on different views as well). <br>

The key words using in compose:
- `compose` - marks the beginning of "compose", turn on all needed logic
- `or` - marks the possible branches. The lambda after `or` has a context of concrete element. Just have a look at the simple below.
- `thenContinue` - is an action that will be executed if a branch (the code into lambda of `or`) is completed successfully. The context of a lambda after `thenContinue` is a context of concrete element described in `or` section.
- `then` - is almost the same construction as `thenContinue` excepting the context after `then`. The context after `then` is not restricted.

Have a glance at the example below:
```kotlin
step("Handle potential unexpected behavior") {
    // simple compose
    CommonFlakyScreen {
        btn5.compose {
            or {
                // the context of this lambda is `btn5`
                hasText("Something wrong")
            } thenContinue {
                // here, the context of this lambda is a context of KButton(btn5),
                // that's why we can call KButton's methods inside the lambda directly
                click()
            }
            or {
                // the context of this lambda is `btn5`
                hasText(R.string.common_flaky_final_button)
            } then {
                // here, there is not any special context of this lambda
                // that's why we can't call KButton's methods inside the lambda directly
                btn5.click()
            }
        }
    }
    // complex compose
    compose {
        // the first potential branch when ComplexComposeScreen.stage1Button is visible
        or(ComplexComposeScreen.stage1Button) {
            // the context of this lambda is `ComplexComposeScreen.stage1Button`
            isVisible()
        } then {
            // if the first branch was succeed then we execute some special flow
            step("Flow is over the product") {
                ComplexComposeScreen {
                    stage1Button {
                        click()
                    }
                    stage2Button {
                        isVisible()
                        click()
                    }
                }
            }
        }
        // the second potential branch when UiComposeDialog1.title is visible
        // just imagine that is some unexpected system or product behavior and we cannot fix it now
        or(UiComposeDialog1.title) {
            // the context of this lambda is `UiComposeDialog1.title`
            isDisplayed()
        } then {
            // if the second branch was succeed then we execute some special flow
            step("Flow is over dialogs") {
                UiComposeDialog1 {
                    okButton {
                        isDisplayed()
                        click()
                    }
                }
                UiComposeDialog2 {
                    title {
                        isDisplayed()
                    }
                    okButton {
                        isDisplayed()
                        click()
                    }
                }
            }
        }
    }
}
```
The example is [here](../samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/compose_tests). <br>
Please, observe additional opportunities and documentation: [common info](../kaspresso/src/main/kotlin/com/kaspersky/kaspresso/compose), [ComposeProvider](../kaspresso/src/main/kotlin/com/kaspersky/kaspresso/compose/ComposeProvider.kt) and [WebComposeProvider](../kaspresso/src/main/kotlin/com/kaspersky/kaspresso/compose/WebComposeProvider.kt).

#### data
If you set your test data by ```init-transform``` methods then this test data is available by a `data` field.

#### testAssistants
Special assistants to write tests. Pay attention to the fact that these assistants are available in `BaseTestCase` also. <br>
1. `testLogger` <br>
   It's a logger for tests allowed to output logs by a more appropriate and readable form.
2. `device` <br>
   An instance of `Device` class is available in this context. It's a special interface given beautiful possibilities to do a lot of useful things at the test. <br>
   More detailed info about `Device` is [here](./05_Device.md).
3. `adbServer` <br>
   You have access to AdbServer instance used in `Device`'s interfaces via `adbServer` property. <br>
   More detailed info about `AdbServer` is [here](./06_AdbServer.md).
4. `params` <br>
   `Params` is the facade class for all Kaspresso parameters. <br>
   Please, observe the [source code](../kaspresso/src/main/kotlin/com/kaspersky/kaspresso/params/Params.kt).
