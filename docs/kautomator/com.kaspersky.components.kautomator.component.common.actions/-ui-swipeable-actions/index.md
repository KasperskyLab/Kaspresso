[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.common.actions](../index.md) / [UiSwipeableActions](./index.md)

# UiSwipeableActions

`interface UiSwipeableActions : `[`UiBaseActions`](../-ui-base-actions/index.md)

Provides swipeable actions for UiSwipeView

### Types

| Name | Summary |
|---|---|
| [UiSwipeableActionType](-ui-swipeable-action-type/index.md) | `enum class UiSwipeableActionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [swipeDown](swipe-down.md) | Swipes down on the view`open fun swipeDown(percent: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)` = 0.95f): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [swipeLeft](swipe-left.md) | Swipes left on the view`open fun swipeLeft(percent: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)` = 0.95f): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [swipeRight](swipe-right.md) | Swipes right on the view`open fun swipeRight(percent: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)` = 0.95f): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [swipeUp](swipe-up.md) | Swipes up on the view`open fun swipeUp(percent: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)` = 0.95f): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [UiScrollView](../../com.kaspersky.components.kautomator.component.scroll/-ui-scroll-view/index.md) | `class UiScrollView : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiScrollView`](../../com.kaspersky.components.kautomator.component.scroll/-ui-scroll-view/index.md)`>, `[`UiSwipeableActions`](./index.md)`, `[`UiScrollableActions`](../-ui-scrollable-actions/index.md) |
