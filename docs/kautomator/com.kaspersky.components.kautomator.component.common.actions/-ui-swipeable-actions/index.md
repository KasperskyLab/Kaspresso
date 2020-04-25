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
| [swipeDown](swipe-down.md) | Swipes down on the view`open fun swipeDown(percent: Float = 0.95f): Unit` |
| [swipeLeft](swipe-left.md) | Swipes left on the view`open fun swipeLeft(percent: Float = 0.95f): Unit` |
| [swipeRight](swipe-right.md) | Swipes right on the view`open fun swipeRight(percent: Float = 0.95f): Unit` |
| [swipeUp](swipe-up.md) | Swipes up on the view`open fun swipeUp(percent: Float = 0.95f): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [UiScrollView](../../com.kaspersky.components.kautomator.component.scroll/-ui-scroll-view/index.md) | `class UiScrollView : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiScrollView`](../../com.kaspersky.components.kautomator.component.scroll/-ui-scroll-view/index.md)`>, `[`UiSwipeableActions`](./index.md)`, `[`UiScrollableActions`](../-ui-scrollable-actions/index.md) |
