#Matchers, Actions and Assertions

As you all know Kaspresso is based on Espresso (if you're not familiar with espresso, check out the [official docs](https://developer.android.com/training/testing/espresso)).

According to [official docs](https://developer.android.com/training/testing/espresso/basics) the main components of Espresso include the following:
* `Espresso` – Entry point to interactions with views (via `onView()` and `onData()`). Also exposes APIs that are not necessarily tied to any view, such as `pressBack()`.
* `ViewMatchers` – A collection of objects that implement the `Matcher<? super View>` interface. You can pass one or more of these to the `onView()` method to locate a view within the current view hierarchy.
* `ViewActions` – A collection of ViewAction objects that can be passed to the `ViewInteraction.perform()` method, such as `click()`.
* `ViewAssertions` – A collection of ViewAssertion objects that can be passed the `ViewInteraction.check()` method. Most of the time, you will use the matches assertion, which uses a View matcher to assert the state of the currently selected view.

```kotlin
// withId(R.id.my_view) is a ViewMatcher
// click() is a ViewAction
// matches(isDisplayed()) is a ViewAssertion
onView(withId(R.id.my_view))
    .perform(click())
    .check(matches(isDisplayed()))
```
Most available instances of Matcher, ViewActions and ViewAssertions can be found in the [Google cheat-sheet](https://developer.android.com/training/testing/espresso/cheat-sheet)
<img src="../Images/Matchers_actions_assertions/Espresso_cheat_sheet.png" alt="Espresso cheat sheet"/>

In Kakao, the results of calling `onView()` methods (`ViewInteractors`) are cashed. You can get references to ViewInteractors and reuse them in your code. This makes your code in tests more readable and understandable.
Kakao also allows you to separate the search for an element and actions on it.

Kakao has introduced KView and various implementations for the most available Android widgets. This KView implements the BaseAssertions and BaseActions interfaces with some additional methods. Every inheritor of KView implements its own interfaces for assertions and actions for some widget-specific methods.
As a result, you can get a reference to specific views from your test code and make the necessary assertions and actions on it in the view block. 

Since Kasresso inherits all the best from these two frameworks, everything described above is available to you.
