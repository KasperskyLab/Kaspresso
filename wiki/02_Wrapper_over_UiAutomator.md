# Kautomator: wrapper over UI Automator

**Kautomator** - Nice and simple DSL for UI Automator in Kotlin. <br>
Inspired by [Kakao](https://github.com/agoda-com/Kakao)

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
This is an example just to input and check the text. Because we have a successful experience of Kakao
using we decided to wrap UI Automator over in the same manner and called it **Kautomator**.

#### Benefits
- Readability
- Reusability
- Extensible DSL

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
    val phone = UiView { withId(this@FormScreen.packageName, "phone" }
    val email = UiEditText { withId(this@FormScreen.packageName, "email_edit" }
    val submit = UiButton { withId(this@FormScreen.packageName, "submit_button" }
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

You have the ability to provide interceptors at 3 different levels: Kautomator runtime, your 'UiScreen' classes
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
For more detailed info please refer to the documentation.
