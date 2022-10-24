# Page object pattern in Kaspresso.
## What is a Page object pattern?
<br>Page object pattern is explained well by Martin Fowler in [this article](https://martinfowler.com/bliki/PageObject.html). Long in short this is a test abstraction that describes the screen with some view elements. These view items can be interacted with during tests. As a result the description of the screen elements will be in a separate class. You no longer need to constantly look for the desired UI element with several matchers in tests. This can be done once by saving a link to the screen.
## How is the page object pattern implemented in Kaspresso?
<br>Kaspresso provides `KScreen` and `UiScreen` as implementations for Page object pattern. 
### What is the difference between KScreen and UiScreen
<br>Kaspresso is based on Kakao and UiAutomator. 
<br>When we have all info about the application code(`white-box testing` cases) we should use KScreen to describe the structure of PageObject as Kakao does. This is a class in Kaspresso - extension for Kakao Screen class.
<br>When we don't have access to a source code of an application (it can be some system dialogs, windows or apps) we should use UiScreen.
<br>Here are two samples:
```kotlin
object SimpleScreen : KScreen<SimpleScreen>() {

    override val layoutId: Int? = R.layout.activity_simple
    override val viewClass: Class<*>? = SimpleActivity::class.java

    val button1 = KButton { withId(R.id.button_1) }

    val button2 = KButton { withId(R.id.button_2) }

    val edit = KEditText { withId(R.id.edit) }
}

object MainScreen : UiScreen<MainScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.kautomatorsample"

    val simpleEditText = UiEditText { withId(this@MainScreen.packageName, "editText") }
    val simpleButton = UiButton { withId(this@MainScreen.packageName, "button") }
    val checkBox = UiCheckBox { withId(this@MainScreen.packageName, "checkBox") }
}
```
<br>In KScreen's inheritors we should initialize the `layoutId` (layout file of a screen) and `viewClass`(screen activity class name) fields. But this is optional. These fields will help in cases of code refactoring not to forget about the associated tests screens
<br>In UiScreen's inheritors we must initialize `packageName` field (the full name of the application's package). 
## Benefits of the page object for refactoring
<br>Page object pattern allows you to exclude the description of the screen in a separate file and to reuse Screens and views in different tests. When you have some changes in the UI of the application you can only change the code in the Screen file without the need for a lot of refactoring of the tests. 
## Benefits of the Page Object for a work in a team
<br>In some teams autotests are written only by developers, in others by QA engineers. In some cases autotests are written by someone who does not know details of the code (source code is available, but is bad understandable). In this case developers can write Screens for additional autotests. Having Screens helps another person to write tests using Kotlin DSL. 
