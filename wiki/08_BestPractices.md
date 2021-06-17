# Best Practices

## File structure

Here is shown an example structure of packages. It's not strong rule but we higly recommend to organize your files.

```
androidTest
└── java
    └── com
        ├── actions
        ├── app_providers
        ├── common
        ├── functional
        ├── scenarios
        ├── screens
        ├── screenshot_tests
        ├── tests
        └── views
```

1. **App_providers**
   Put here all helper classes wich contains some business logic of main application. For example, if you need to use some Dagger component and check state of your interactor. **Don't use this interactors directly,** put accessing to interactors into method-wrappers witch returns some primitive or void for some simple actions and then call this methods in your test cases.

2. **Common**
   Here is collected all helper classes wich you can reuse in multiple test cases. We highly recommend to make it stateless (for example as kotlin Object). For example you can enable Accessibility with one row.

   Some hint - if you want to call methods directly (without method `.run {}`) you can override operator `invoke`

   ```kotlin
   object Accessibility {
       fun BaseTestContext.enable() = 
           device.accessibility.enable(
               YOUR_PACKAGE_NAME,
               ACCESSIBILITY_CLASS_NAME
           )
     
     	// this is optional
     	operator fun invoke(function: Accessibility.() -> Unit) {
           return function.invoke(this)
       }
   }
   ```

   ```kotlin
   ...
   step("some step") {
       MainScreen {
         	Accessibility.run { enable() } // without invoke override
           Accessibility { enable() } // with invoke override
       }
   }
   ....
   ```

3. **Functional**
   This package needed for converting end-to-end tests into functional, for example it's may be working with DI, stub's and Advice. (Here is more about [Advice](#Advices)) 
4. **Scenarios**
   There you can hold your mostly repeated steps wrapped into scenarios. In common it created with kotlin stateless objects. But you can hold some parameters for some logic and in this case create classic class with constructor parameters. We highly recommend to separate scenarios if it contains so many steps and logic. (Here is some more about [`scenario`](https://github.com/KasperskyLab/Kaspresso/blob/master/wiki/04_How_to_write_autotests.md#test-structure-1))
5. **Screens**
   Here is located decriptions of screens. This classes must not contain business logic or states, only description of screen base operations or view checks. Current package also uses for keeping description of external classes described with uiAutomator dsl.
6. **Tests**
   This package needs no introduction. Contains only test cases. All other classes moved to packages listed before. 

## Screens

Here is some hints and info about screens in autotests.

### KScreen

You can extend base class KScreen<> with method `waitForScreen()`. This method used when screen may appear with delay. We recommend use [`flakySafely`](https://github.com/KasperskyLab/Kaspresso/blob/master/wiki/04_How_to_write_autotests.md#flakysafely),with custom delay and intervals. Inside you can use `isVisible` method or even [`compose`](https://github.com/KasperskyLab/Kaspresso/blob/master/wiki/04_How_to_write_autotests.md#compose) with several checks for difficult screens.

`layoutId` and `viewClass` used only for connection with source code. New developer (or maybe even you after some time) who will read your code can easily find real screen in your project by this fields.

For describing views use childs of KView (kakao views).

### KExternalScreen

If you want write autotests with external screens (when your app use another app, like Chrome or Gmail) you can create class for external screens, for example 

```kotlin
abstract class KExternalScreen<out T : KExternalScreen<T>> {

    abstract val packageName: String

    open fun BaseTestContext.waitForScreen() = Unit

    operator fun invoke(function: T.() -> Unit) {
        function.invoke(this as T)
    }

    fun pressBack() {
        val instrumentation: Instrumentation = InstrumentationRegistry.getInstrumentation()
        UiDevice.getInstance(instrumentation).pressBack()
    }
}
```

Or if you don't want to override `invoke` you may not use generics.

Field `packageName` (like `layoutId` and `viewClass` from `KScreen`) uses for linking screen object to real application. Also it needed to remove code duplication (UiView needs packageName for describing views)

For describing views use childs of UiView ([uiAutomator DSL](https://github.com/KasperskyLab/Kaspresso/blob/master/wiki/02_Wrapper_over_UiAutomator.md)).

## Advices

If you want to separate end-to-end and functional test cases in single code of test we recommend to use interfaces and we named it `Advice` (but you can name it as you want). For example we want to pass registration on web-portal in our application. In e2e we must perform real registration but on functional we can stub it. So let's write some code:

```kotlin
interface RegistrationAdvice {
	fun register()
	fun passCaptcha() = Unit
}
```

This advice contains methods that will have different implementation for each type of test. Also we used stub for `passCaptcha` because in functional we don't need to pass captcha.

```kotlin
class RegistrationAdviceReal : RegistrationAdvice {
	override fun register() {
    // some real logic for register
  }
	override fun passCaptcha() {
  	// some real logic for captcha
  }
}

class RegistrationAdviceFake : RegistrationAdvice {
	override fun register() {
    // some fake logic for register
  }
  // note that we don't needed to override passCaptcha()
}
```

So in test case we can write wrapper-methods for our test cases and run needed via marathon and filter by annotations

```kotlin
class RegistrationTestCase : TestCase() {
    @Test
    @E2e // marker annotation for marathon runner
    fun registrationTestCase_e2e() =
        registrationTestCase(RegistrationAdviceReal())

    @Test
    @Functional // marker annotation for marathon runner
    fun registrationTestCase_functional() =
        registrationTestCase(RegistrationAdviceFake())

    private fun registrationTestCase(testAdvice: RegistrationAdvice) {
        run {
        		...
            step("pass registration") {
                ...
                testAdvice { 
                		register()
                		passCaptcha()
                }
								...
```

Sometimes your e2e test must do some work and functional test must not do it at all. In that case you create  `interface A` with empty default implementations of all methods, manually override all methods in real advice `class B : A` and for functional advice create empty `class C : A`. (examples in kotlin syntax)

## Test cases

1. First rule of writing best test cases: inheritors of `TestCase` class must be exactly test cases or class wich overrides **must be applied to all test cases**. If you create multiple parent classes you may get pain with modifying and ordering classes or your new cases may do some work wich them must not to do.

   But if you still needed to add some actions to group of cases you can create lamda-function with `Kaspresso.Builder` (here is more about [`Kaspresso.Bulder`](https://github.com/KasperskyLab/Kaspresso/blob/master/wiki/03_Kaspresso_configurator.md)) receiver and pass it as class parameter. You still can use `before` and `after` sections in test case but also you will have `beforeEachTest` and `afterEachTest`. Example:

```kotlin
object SomeTestBuilder {
    fun create(): Kaspresso.Builder.() -> Unit = {
        beforeEachTest {
           // some work
           // for example: install some external app via adb
        }

        afterEachTest {
            // some work
        }
    }
}
```

```kotlin
class MySomeTestCase : TestCase(
    kaspressoBuilderAdditional =
    SomeTestBuilder.create()
) {
```

2. Separate your test onto steps and shortly name them. You will have readable logs and easily realise where your test failed
3. Separate your screens. For example: move your dialog into separate `Screen` object. It will be easy to read your code
4. If some code used **only** in single test-case - leave it code as private methods inside your test class. But if your code duplicates two or more times - move it into some stateless object in `common` package.

## Other advices

1. Don't be afraid to read source code of called methods. For example you can discover that method `androidx.test.uiautomator.UiDevice.pressBack()` calling `waitForIdle()` before perfoming click. This waiting by default keep 10 seconds timeout and may not execute if screen still drawing some changes. 
   And if you go deeper in code researching you can find configurator of uiAutomator and change default timeout of  `waitForIdle()`. 
2. Try to avoid calling `Thread.sleep()`, use instead [`flakySafely`](https://github.com/KasperskyLab/Kaspresso/blob/master/wiki/04_How_to_write_autotests.md#flakysafely), [`compose`](https://github.com/KasperskyLab/Kaspresso/blob/master/wiki/04_How_to_write_autotests.md#compose),[`continuosly`](https://github.com/KasperskyLab/Kaspresso/blob/master/wiki/04_How_to_write_autotests.md#continuously). If waiting is really needed and construction above don't help you - better use this snippet:

```kotlin
/**
 * More efficient simple waiting then Thread.sleep
 */
fun simpleWait(timeoutMs: Long) {
    val lock = ReentrantLock()
    val condition = lock.newCondition()
    lock.withLock {
        Timer().schedule(timeoutMs) {
            lock.withLock {
                condition.signalAll()
            }
        }
        condition.await()
    }
}
```

3. There is difference of `device.targetContext` and `device.context`. First one used if you need context of **application witch will be tested** (host application) and second one if you need context of **application wich testing your main application** (slave application). 

4. [`flakySafely`](https://github.com/KasperskyLab/Kaspresso/blob/master/wiki/04_How_to_write_autotests.md#flakysafely) and [`continuosly`](https://github.com/KasperskyLab/Kaspresso/blob/master/wiki/04_How_to_write_autotests.md#continuously) have defailt parameters but you still can override on each use. You may know `timeoutMs` and `intervalMs` but there is `allowedExceptions` and you override list of allowed exceptions or take list of exceptions from configurator and modify it. May be helpful with some flaking of adbServer for example:

   ```kotlin
   flakySafely(
       timeoutMs = 1.min,
       allowedExceptions = (params.flakySafetyParams.allowedExceptions + AdbServerException::class.java).toMutableSet()
   ) {
       adbServer.performShell("settings put global heads_up_notifications_enabled 0")
   }
   ```

5. Use `assertSafely` constructions. From naming you can realise that it's [`flakySafely`](https://github.com/KasperskyLab/Kaspresso/blob/master/wiki/04_How_to_write_autotests.md#flakysafely), for assertions and used when your assert may be appear with some delay. Also you can set custom message of error.