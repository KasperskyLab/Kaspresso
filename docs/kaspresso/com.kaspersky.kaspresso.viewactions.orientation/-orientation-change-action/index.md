[kaspresso](../../index.md) / [com.kaspersky.kaspresso.viewactions.orientation](../index.md) / [OrientationChangeAction](./index.md)

# OrientationChangeAction

`class OrientationChangeAction : ViewAction`

An implementation of device's orientation change.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `OrientationChangeAction(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, orientation: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = null)`<br>An implementation of device's orientation change. |

### Functions

| Name | Summary |
|---|---|
| [getConstraints](get-constraints.md) | `fun getConstraints(): Matcher<`[`View`](https://developer.android.com/reference/android/view/View.html)`>` |
| [getDescription](get-description.md) | `fun getDescription(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [perform](perform.md) | `fun perform(uiController: UiController, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [orientationLandscape](orientation-landscape.md) | `fun orientationLandscape(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`): ViewAction` |
| [orientationPortrait](orientation-portrait.md) | `fun orientationPortrait(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`): ViewAction` |
| [toggle](toggle.md) | `fun toggle(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`): ViewAction` |
