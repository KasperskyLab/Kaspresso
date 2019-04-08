[kaspresso](../../index.md) / [com.kaspersky.kaspresso.matchers.canscroll](../index.md) / [CanScrollMatcher](./index.md)

# CanScrollMatcher

`class CanScrollMatcher : BaseMatcher<`[`View`](https://developer.android.com/reference/android/view/View.html)`>`

An implementation of [BaseMatcher](#) that answers if [View](https://developer.android.com/reference/android/view/View.html) can be scrolled vertically.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `CanScrollMatcher(scrollDirection: `[`ScrollDirection`](../-scroll-direction/index.md)`)`<br>An implementation of [BaseMatcher](#) that answers if [View](https://developer.android.com/reference/android/view/View.html) can be scrolled vertically. |

### Functions

| Name | Summary |
|---|---|
| [describeTo](describe-to.md) | `fun describeTo(description: Description?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [matches](matches.md) | `fun matches(item: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Extension Functions

| Name | Summary |
|---|---|
| [describe](../../com.kaspersky.kaspresso.extensions.espressoext/org.hamcrest.-matcher/describe.md) | `fun Matcher<`[`View`](https://developer.android.com/reference/android/view/View.html)`>?.describe(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
