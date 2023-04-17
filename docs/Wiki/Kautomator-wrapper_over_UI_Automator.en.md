# Kautomator: wrapper over UI Automator

**Kautomator** - Nice and simple DSL for UI Automator in Kotlin that allows to accelerate UI Automator to amazing. <br>
Inspired by [Kakao](https://github.com/KakaoCup/Kakao) and [russian talk about UI Automator](https://youtu.be/bqNguUHK3SM) (thanks to Svetlana Smelchakova).

### Introduction
Tests written with UI Automator are so complex, non-readble and hard to maintain especially for testers.
Have a look at a typical piece of code written with UI Automator:
```Kotlin
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
This is an example just to input and check the text. Because we have a successful experience of Kakao using we decided to wrap UI Automator over in the same manner and called it **Kautomator**:
```kotlin
MainScreen {
    simpleEditText {
        replaceText("Kaspresso")
        hasText("Kaspresso")
    }
}
```

Another big advantage of **Kautomator** is a possibility to accelerate UI Automator. <br>
Have a glance at video below:

![](https://habrastorage.org/webt/ti/kv/ki/tikvkij1vjesnacrxqm-lk0coly.gif) <br>
The left video is boosted UI Automator, the right video is default UI Automator.

Why is it possible? The details are [available a little bit later](https://kasperskylab.github.io/Kaspresso/en/Wiki/Kautomator-wrapper_over_UI_Automator/#accelerate-ui-automator).

#### Benefits
- Readability
- Reusability
- Extensible DSL
- Amazing speed!

### How to use it
#### Create Screen
Create your entity `UiScreen` where you will add the views involved in the interactions of the tests:
```Kotlin
class FormScreen : UiScreen<FormScreen>()
```
`UiScreen` can represent the whole user interface or a portion of UI.
If you are using [Page Object pattern](https://martinfowler.com/bliki/PageObject.html) you can put the interactions of Kautomator inside the Page Objects.

#### Create UiView
`UiScreen` contains `UiView`, these are the Android Framework views where you want to do the interactions:
```Kotlin
class FormScreen : UiScreen<FormScreen>() {
    val phone = UiView { withId(this@FormScreen.packageName, "phone") }
    val email = UiEditText { withId(this@FormScreen.packageName, "email_edit") }
    val submit = UiButton { withId(this@FormScreen.packageName, "submit_button") }
}
```
Kautomator provides different types depending on the type of view:

* `UiView`
* `UiEditText`
* `UiTextView`
* `UiButton`
* `UiCheckbox`
* `UiChipGroup`
* `UiSwitchView`
* `UiScrollView`
* <b>and more</b>


Every `UiView` contains matchers to retrieve the view involved in the `ViewInteraction`. Some examples of matchers provided
by Kakao:

* `withId`
* `withText`
* `withPackage`
* `withContentDescription`
* `textStartsWith`
* <b>and more</b>

Like in Ui Automator you can combine different matchers:
```Kotlin
val email = UiEditText {
    withId(this@FormScreen.packageName, "email")
    withText(this@FormScreen.packageName, "matsyuk@kaspresso.com")
}
```

#### Write the interaction

The syntax of the test with Kautomator is very easy, once you have the `UiScreen` and the `UiView` defined, you only have to apply
the actions or assertions like in UI Automator:
```Kotlin
FormScreen {
    phone {
       hasText("971201771")
    }
    button {
       click()
    }
}
```

#### The difference from Kakao-Espresso

In Espresso, all interaction with a `View` is processing through `ViewInteraction` that has two main methods:
`onCheck` and `onPerform` which take `ViewAction` and `ViewAssertion` as arguments. Kakao was written based on this architecture.

So, we have set a goal to write Kautomator which would be like Kakao as much as possible. That's why we have introduced an additional layer over UiObject2 and UiDevice and that is so similar to `ViewInteraction`. This layer is represented by `UiObjectInteraction` and `UiDeviceInteraction` that have two methods: `onCheck` and `onPerform` taking UiObjectAssertion and UiObjectAction or UiDeviceAssertion and UiDeviceAction as arguments.

`UiObjectInteraction` is designed to work with concrete `View` like `ViewInteraction`. `UiDeviceInteraction` has been created because UI Automator has a featureallowing you to do some system things like a click on Home button or on hard Back button, open Quick Setttings, open Notifications and so on. All such things are hidden by `UiSystem` class.

So, enjoy it =)

#### Advanced

##### Custom UiView

If you have custom Views in your tests and you want to create your own `UiView`, we have `UiBaseView`. Just extend
this class and implement as much additional Action/Assertion interfaces as you want.
You also need to override constructors that you need.

```Kotlin
class UiMyView : UiBaseView<KView>, UiMyActions, UiMyAssertions {
    constructor(selector: UiViewSelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}
```

##### Intercepting

If you need to add custom logic during the `Kautomator -> UI Automator` call chain (for example, logging) or
if you need to completely change the `UiAssertion` or `UiAction` that are being sent to UI Automator
during runtime in some cases, you can use the intercepting mechanism.

Interceptors are lambdas that you pass to a configuration DSL that will be invoked before real calls
inside `UiObject2` and `UiDevice` classes in UI Automator.

You have the ability to provide interceptors at 3 different levels: Kautomator runtime, your `UiScreen` classes
and any individual `UiView` instance.

On each invocation of UI Automator function that can be intercepted, Kautomator will aggregate all available interceptors
for this particular call and invoke them in descending order: `UiView interceptor -> Active Screens interceptors ->
Kautomator interceptor`.

Each of the interceptors in the chain can break the chain call by setting `isOverride` to true during configuration.
In that case Kautomator will not only stop invoking remaining interceptors in the chain, **but will not perform the UI Automator
call**. It means that in such case, the responsibility to actually invoke Kautomator lies on the shoulders
of the developer.

Here's the examples of intercepting configurations:
```Kotlin
class SomeTest {
    @Before
    fun setup() {
        KautomatorConfigurator { // Kautomator runtime
            intercept {
                onUiInteraction { // Intercepting calls on UiInteraction classes across whole runtime
                    onPerform { uiInteraction, uiAction -> // Intercept perform() call
                        testLogger.i("KautomatorIntercept", "interaction=$uiInteraction, action=$uiAction")
                    }
                }
            }
        }
    }

    @Test
    fun test() {
        MyScreen {
            intercept {
                onUiInteraction { // Intercepting calls on UiInteraction classes while in the context of MyScreen
                    onCheck { uiInteraction, uiAssert -> // Intercept check() call
                        testLogger.i("KautomatorIntercept", "interaction=$uiInteraction, assert=$uiAssert")
                    }
                }
            }

            myView {
                intercept { // Intercepting ViewInteraction calls on this individual view
                    onPerform(true) { uiInteraction, uiAction -> // Intercept perform() call and overriding the chain
                        // When performing actions on this view, Kautomator level interceptor will not be called
                        // and we have to manually call UI Automator now.
                        Log.d("KAUTOMATOR_VIEW", "$uiInteraction is performing $uiAction")
                        uiInteraction.perform(uiAction)
                    }
                }
            }
        }
    }
}
```

### Accelerate UI Automator
As you remember we told about the possible acceleration of UI Automator. How does it become a reality? <br>
UI Automator has an inner mechanism to prevent potential flakiness. Under the hood, the library listens and gives commands through AccessibilityManagerService. AccessibilityManagerService is a single point for all accessibility events in the system. At one moment, creators of UI Automator faced with the flakiness problem. One of the most popular reasons for such undetermined behavior is a big count of events processing in the System at the current moment. But UI Automator has a connection with AccessibilityManagerService. Such a connection gives an opportunity to listen to all accessibility events in the System and to wait for a calm state when there are no actions. The calm state leads to determined system behavior and decreases the possibility of flakiness. <br>
All of this pushed UI Automator authors to introduce the following algorithm: UI Automator waits 500ms (`waitForIdleTimeout` and `waitForSelectorTimeout` in `androidx.test.uiautomator.Configurator`) window during 10 seconds for **each action**. EACH ACTION.

Perhaps, described solution made UI Automator more stable. But, the speed crashed, no doubts.

Kautomator is a DSL over UI Automator that provides a [mechanism of interceptors](../wiki/03_Kaspresso_configurator.md#kaspresso-interceptors-based-on-kakaokautomator-interceptors). Kaspresso offers a big set of default interceptors which eliminates any potential flaky action. So, Kaspresso + Kautomator helps UI Automator to struggle with flakiness.

After some time, we thought why we need to save artificial timeouts inside UI Automator while Kaspresso + Kautomator does the same work. Have a look at the measure example:
```kotlin
@RunWith(AndroidJUnit4::class)
class KautomatorMeasureTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        kautomatorWaitForIdleSettings = KautomatorWaitForIdleSettings.boost()
    }
) {

    companion object {
        private val RANGE = 0..20
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test() =
        before {
            activityTestRule.launchActivity(null)
        }.after { }.run {
    
    ======> UI Automator:        0 minutes, 1 seconds and 252 millis
    ======> UI Automator boost:  0 minutes, 0 seconds and 310 millis
            step("MainScreen. Click on `measure fragment` button") {
                UiMainScreen {
                    measureButton {
                        isDisplayed()
                        click()
                    }
                }
            }
    
    ======> UI Automator:        0 minutes, 11 seconds and 725 millis
    ======> UI Automator boost:  0 minutes, 1 seconds and 50 millis
            step("Measure screen. Button_1 clicks comparing") {
                UiMeasureScreen {
                    RANGE.forEach { _ ->
                        button1 {
                            click()
                            hasText(device.targetContext.getString(R.string.measure_fragment_text_button_1).toUpperCase())
                        }
                    }
                }
            }

    ======> UI Automator:        0 minutes, 11 seconds and 789 millis
    ======> UI Automator boost:  0 minutes, 1 seconds and 482 millis
            step("Measure screen. Button_2 clicks and TextView changes comparing") {
                UiMeasureScreen {
                    RANGE.forEach { index ->
                        button2 {
                            click()
                            hasText(device.targetContext.getString(R.string.measure_fragment_text_button_2).toUpperCase())
                        }
                        textView {
                            hasText(
                                "${device.targetContext.getString(R.string.measure_fragment_text_textview)}${index + 1}"
                            )
                        }
                    }
                }
            }

    ======> UI Automator:        0 minutes, 45 seconds and 903 millis
    ======> UI Automator boost:  0 minutes, 2 seconds and 967 millis
            step("Measure fragment. EditText updates comparing") {
                UiMeasureScreen {
                    edit {
                        isDisplayed()
                        hasText(device.targetContext.getString(R.string.measure_fragment_text_edittext))
                        RANGE.forEach { _ ->
                            clearText()
                            typeText("bla-bla-bla")
                            hasText("bla-bla-bla")
                            clearText()
                            typeText("mo-mo-mo")
                            hasText("mo-mo-mo")
                            clearText()
                        }
                    }
                }
            }

    ======> UI Automator:        0 minutes, 10 seconds and 901 millis
    ======> UI Automator boost:  0 minutes, 1 seconds and 23 millis
            step("Measure fragment. Checkbox clicks comparing") {
                UiMeasureScreen {
                    RANGE.forEach { index ->
                        checkBox {
                            if (index % 2 == 0) {
                                setChecked(true)
                                isChecked()
                            } else {
                                setChecked(false)
                                isNotChecked()
                            }
                        }
                    }
                }
            }
        }
}
```
It's a great deal!

Also, there are cases when UI Automator can't catch 500ms window. For example, when one element is updating too fast (one update in 100 ms). Just have a look at [this test](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/idlingwait_tests/WaitForIdleTest.kt). Only `KautomatorWaitForIdleSettings.boost()` allows to pass the test.

As you see, we have introduced a special `kautomatorWaitForIdleSettings` property in Kaspresso configurator. By default, this property is **not** boost. Why? Because:
1. You can have tests where you use UI Automator directly. But mentioned timeouts are global parameters. Resetting of these timeouts can lead to an undetermined state.
2. We want to take time collecting data from the world and then to analyze potential problems of our solutions (but, we believe it's a stable and brilliant solution).

Another important remark is about `kaspressoBuilder = Kaspresso.Builder.simple` configuration. This configuration is faster than `advanced` because of each step's screenshots interceptor absence. If you need, add them manually.

Anyway, it's a small change for a developer, but it's a big step for the world =)
