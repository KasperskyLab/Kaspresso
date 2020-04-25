[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.common.actions](../index.md) / [UiScrollableActions](./index.md)

# UiScrollableActions

`interface UiScrollableActions : `[`UiBaseActions`](../-ui-base-actions/index.md)

### Types

| Name | Summary |
|---|---|
| [UiScrollableActionType](-ui-scrollable-action-type/index.md) | `enum class UiScrollableActionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [scrollToEnd](scroll-to-end.md) | `open fun scrollToEnd(): Unit` |
| [scrollToStart](scroll-to-start.md) | `open fun scrollToStart(): Unit` |
| [scrollToView](scroll-to-view.md) | `open fun <T> scrollToView(to: `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<T>): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [UiScrollView](../../com.kaspersky.components.kautomator.component.scroll/-ui-scroll-view/index.md) | `class UiScrollView : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiScrollView`](../../com.kaspersky.components.kautomator.component.scroll/-ui-scroll-view/index.md)`>, `[`UiSwipeableActions`](../-ui-swipeable-actions/index.md)`, `[`UiScrollableActions`](./index.md) |
