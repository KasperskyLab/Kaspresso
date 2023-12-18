# Espresso as the basis
Kaspresso is based on Google testing framework Espresso (if you're not familiar with Espresso, check out the [official docs](https://developer.android.com/training/testing/espresso))
<br>Espresso allows you to work with the elements of your application as a white box ([white box testing](https://en.wikipedia.org/wiki/White-box_testing)). You can find the desired element on the screen using matchers, perform different actions or checks.
## Espresso is not enough
This framework has a lot of drawbacks and not all things in Android autotesting can be done with Espresso alone.
## What do we want:
1. ***Good readability***. Espresso has a problem with this because of the huge hierarchy of matchers. When we have a lot of matches, the code becomes difficult to read. Poor readability means difficult to maintain
2. ***Hight stability***. Espresso does not work well with interfaces whose elements are displayed asynchronously. You can configure Idling, but that still won't solve all problems.
3. ***Logging***. After completing the test with Espresso, you do not have a step-by-step workflow sequence of actions.
4. ***Screenshots***. We also want to have some screenshots for the test report.
5. ***Working with Android OS***. In some cases, we need to interact with the device. In this case you need UiAutomator (as a variant).
6. ***Сode architecture***. We want to have a clean code architecture in our tests, the ability to reuse code, move some blocks in abstractions. One code style for all developers.

## How does Kaspresso solve all these problems?
### Readability
Kaspresso is based on [Kakao](https://github.com/KakaoCup/Kakao) - Android framework for UI autotests. It is also based on Espresso. Kakao provides a simple Kotlin DSL. This makes the tests more readable. You no longer need to put long constructors with matchers for finding elements on the screen in the code of your test. The result of calling the `onView()` Espresso method is cached. You can then get the required view as a property.
<br> Kakao also provides an implementation of Page object pattern with a `Screen` object. You can describe all the interface elements that your test will interact with in one place (in one Screen object).
### Stability
Kaspresso has wrapped some Espresso calls into a more stable implementation. For example you can find `flakySafely()` method in the Kaspresso.
### Logging
Kaspresso has wrapped some Espresso calls not only for higher stability. We have also implemented an interceptor that prints more logs.
### Working with Android OS
We have created the Device interface as a facade for all devices to work with. UiAutomator can only help you in some cases, but more often you need the ability to execute various commands (adb, shell). For example, with the adb emu command, you can emulate various actions or events.
<br> Espresso tests are run directly on the android device, so we need some kind of external server to send the commands. In Kaspresso you can use `AdbServer`.
### Code architecture
Having described above implementations of Page object pattern, you can make your code in your test files more readable, maintainable, reusable, and understandable. Kaspresso also provides various methods and abstractions to improve the architecture (such as `step`, `Scenario`, test sections and more).
