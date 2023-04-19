# Compose support
Jetpack Compose support consists of two parts: [Kakao Compose library](https://github.com/KakaoCup/Compose) and Kaspresso Interceptors mechanism.

## Kakao Compose library
All detailed information is available in the [README](https://github.com/KakaoCup/Compose) of the library.

Jetpack Compose support is provided by a separate module to not force developers to up their minSDK version to 21.

So, first of all, add a dependency to `build.gradle`:
```groovy
dependencies {
    androidTestImplementation "com.kaspersky.android-components:kaspresso-compose-support:<latest_version>"
}
```

In a nutshell, let's see at how Kakao Compose DSL looks like:
```kotlin
// Screen class
class ComposeMainScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<ComposeMainScreen>(
        semanticsProvider = semanticsProvider,
        // Screen in Kakao Compose can be a Node too due to 'viewBuilderAction' param.
        // 'viewBuilderAction' param is nullable.
        viewBuilderAction = { hasTestTag("ComposeMainScreen") }
) {

    // You can set clear parent-child relationship due to 'child' extension
    // Here, 'simpleFlakyButton' is a child of 'ComposeMainScreen' (that is Node too)
    val simpleFlakyButton: KNode = child {
        hasTestTag("main_screen_simple_flaky_button")
    }
}

// This annotation is here to make the test is appropriate for JVM environment (with Robolectric)
@RunWith(AndroidJUnit4::class)
// Test class declaration
class ComposeSimpleFlakyTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport()
) {

    // Special rule for Compose tests
    @get:Rule
    val composeTestRule = createAndroidComposeRule<JetpackComposeActivity>()

    // Test DSL. It's so similar to Kakao or Kautomator DSL
    @Test
    fun test() = run {
        step("Open Flaky screen") {
            onComposeScreen<ComposeMainScreen>(composeTestRule) {
                simpleFlakyButton {
                    assertIsDisplayed()
                    performClick()
                }
            }
        }

        step("Click on the First button") {
            onComposeScreen<ComposeSimpleFlakyScreen>(composeTestRule) {
                firstButton {
                    assertIsDisplayed()
                    performClick()
                }
            }
        }
        
        // ...
    }
}
```
Again, all related to DSL information is available in [the docs](https://github.com/KakaoCup/Compose).

## Kaspresso Interceptors mechanism
Interceptors are one of the main advantages and powers of Kaspresso library.<br>
How interceptors work is described
at the [article](https://proandroiddev.com/kaspresso-the-autotest-framework-that-you-have-been-looking-forward-to-part-i-e102ed384d11) (look the chapter *"Flaky tests and logging"*).

The same principles are using in Kaspresso for Jetpack Compose.
Let's enumerate default interceptors that work under the hood by default when you write tests with Kaspresso.

### Behavior interceptors
1. `FailureLoggingSemanticsBehaviorInterceptor`<br>
   Build the clear and undestandable exception in case of the test failure.
2. `FlakySafeSemanticsBehaviorInterceptor`<br>
   Tries to repeat the failed action or assertion during defined timeout. All params for this interceptor are at `FlakySafetyParams`.
3. `SystemDialogSafetySemanticsBehaviorInterceptor`<br>
   Eliminates various system dialogs that prevent correct execution of a test.
4. `AutoScrollSemanticsBehaviorInterceptor`<br>
   Performs autoscrolling to an element if the element is not visible on the screen.
5. `ElementLoaderSemanticsBehaviorInterceptor`<br>
   Requests the related `SemanticNodeInteraction` using saved `Matcher` when the element is not found.

### Watcher interceptors
`LoggingSemanticsWatcherInterceptor`. The Interceptor produces human-readable logs. The example:
```
I/KASPRESSO: TEST STEP: "1. Open Flaky screen" in ComposeSimpleFlakyTest SUCCEED. It took 0 minutes, 0 seconds and 212 millis. 
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: TEST STEP: "2. Click on the First button" in ComposeSimpleFlakyTest
I/KASPRESSO: Operation: Check=IS_DISPLAYED(description={null}).
    ComposeInteraction: matcher: (hasParentThat(TestTag = 'simple_flaky_screen_container')) && (TestTag = 'simple_flaky_screen_simple_first_button'); position: 0; useUnmergedTree: false.
I/KASPRESSO: Reloading of the element is started
I/KASPRESSO: Reloading of the element is finished
I/KASPRESSO: Repeat action again with the reloaded element
I/KASPRESSO: Operation: Check=IS_DISPLAYED(description={null}).
    ComposeInteraction: matcher: (hasParentThat(TestTag = 'simple_flaky_screen_container')) && (TestTag = 'simple_flaky_screen_simple_first_button'); position: 0; useUnmergedTree: false.
I/KASPRESSO: SemanticsNodeInteraction autoscroll successfully performed.
I/KASPRESSO: Operation: Check=IS_DISPLAYED(description={null}).
    ComposeInteraction: matcher: (hasParentThat(TestTag = 'simple_flaky_screen_container')) && (TestTag = 'simple_flaky_screen_simple_first_button'); position: 0; useUnmergedTree: false.
I/KASPRESSO: Operation: Perform=PERFORM_CLICK(description={null}).
    ComposeInteraction: matcher: (hasParentThat(TestTag = 'simple_flaky_screen_container')) && (TestTag = 'simple_flaky_screen_simple_first_button'); position: 0; useUnmergedTree: false.
I/KASPRESSO: TEST STEP: "2. Click on the First button" in ComposeSimpleFlakyTest SUCCEED. It took 0 minutes, 0 seconds and 123 millis. 
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: TEST STEP: "3. Click on the Second button" in ComposeSimpleFlakyTest
```

## Caveats
Remember, that Jetpack Compose and all relative tools are developing.
It means Jetpack Compose is not learned very well and some things can be unexpected after "Old fashioned View World" experience.
Let me show the interesting case.

For example, this code
```kotlin
composeSimpleFlakyScreen(composeTestRule) {
    firstButton {
        performClick()
    }
}
``` 
can be the source of flakiness behavior if `firstButton` is located in non visible for a user area
(you just need to scroll to see the element).

But, this code will always work stably:
```kotlin
composeSimpleFlakyScreen(composeTestRule) {
    firstButton {
        assertIsDisplayed()
        performClick()
    }
}
``` 

The explanation is in the nature of SemanticsNode Tree and Jetpack Compose. `firstButton` is a Node and presented in the Tree.
It means that `performClick()` may work and nothing bad doesn't happen. But, `firstButton` is not visible physically and a real click doesn't occur.
Such behavior causes the crash of a test a little bit later.<br>
But, `assertIsDisplayed()` check doesn't pass on the first try (we don't see the element on the screen) and
launches work of all Interceptors including Autoscroll interceptor which scrolls the Screen to the desired element.

Please, [share your experience](https://github.com/KasperskyLab/Kaspresso/issues/new) to help other developers.

## What else

### Configuration
Jetpack Compose support is fully configurable. Have a look at various options to configure:
```kotlin
// We edit only semanticsBehaviorInterceptors
// Now, semanticsBehaviorInterceptors contains only FailureLoggingSemanticsBehaviorInterceptor
class ComposeCustomizeTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport { composeBuilder ->
        composeBuilder.semanticsBehaviorInterceptors = composeBuilder.semanticsBehaviorInterceptors.filter {
            it is FailureLoggingSemanticsBehaviorInterceptor
        }.toMutableList()
    }
)

// We edit flakySafetyParams and semanticsBehaviorInterceptors
// Also, we change semanticsBehaviorInterceptors where we exclude SystemDialogSafetySemanticsBehaviorInterceptor
class ComposeCustomizeTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport(
        // It's very important to change flakySafetyParams in customize section
        // Otherwise, all interceptors will use a default version of flakySafetyParams
        customize = {
            flakySafetyParams = FlakySafetyParams.custom(timeoutMs = 5000, intervalMs = 1000)
        },
        lateComposeCustomize = { composeBuilder ->
            composeBuilder.semanticsBehaviorInterceptors = composeBuilder.semanticsBehaviorInterceptors.filter {
                it !is SystemDialogSafetySemanticsBehaviorInterceptor
            }.toMutableList()
        }
    ).apply {
        // Remember, It's better to customize ComposeSupport only after Kaspresso customizing
        // Because ComposeSupport interceptors can be dependent on some Kaspresso entities
        // For example, changing flakySafetyParams in this section will not affect ComposeSupport interceptors
    }
)

// There is another way to do exactly the same
class ComposeCustomizeTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        flakySafetyParams = FlakySafetyParams.custom(timeoutMs = 5000, intervalMs = 1000)
    }.apply {
        addComposeSupport { composeBuilder ->
            composeBuilder.semanticsBehaviorInterceptors = composeBuilder.semanticsBehaviorInterceptors.filter {
                it !is SystemDialogSafetySemanticsBehaviorInterceptor
            }.toMutableList()
        }
    }
)
```

### Robolectric support
You can run your Compose tests on the JVM environment with Robolectric.<br>
Run `ComposeSimpleFlakyTest` (from "kaspresso-sample" module) on the JVM right now:
```
./gradlew :samples:kaspresso-compose-support-sample:testDebugUnitTest --info --tests "com.kaspersky.kaspresso.composesupport.sample.test.ComposeSimpleFlakyTest"  
```
All information about Robolectric support is available [here](https://kasperskylab.github.io/Kaspresso/Wiki/Kaspresso_Robolectric/).

### Compose is compatible with all sweet Kaspresso extensions
Sweet Kaspresso extensions means using of the such constructions as:

1. `flakySafely`
2. `continuously`

The support of some constructions is in progress: [issue-317](https://github.com/KasperskyLab/Kaspresso/issues/317).

