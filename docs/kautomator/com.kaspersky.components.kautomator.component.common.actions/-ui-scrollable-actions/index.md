[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.common.actions](../index.md) / [UiScrollableActions](./index.md)

# UiScrollableActions

`interface UiScrollableActions : `[`UiBaseActions`](../-ui-base-actions/index.md)

### Types

| Name | Summary |
|---|---|
| [UiScrollableActionType](-ui-scrollable-action-type/index.md) | `enum class UiScrollableActionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [view](../-ui-base-actions/view.md) | `abstract val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [scrollToEnd](scroll-to-end.md) | `open fun scrollToEnd(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [scrollToStart](scroll-to-start.md) | `open fun scrollToStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [scrollToView](scroll-to-view.md) | `open fun <T> scrollToView(to: `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`T`](scroll-to-view.md#T)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [click](../-ui-base-actions/click.md) | `open fun click(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs click on view |
| [doubleClick](../-ui-base-actions/double-click.md) | `open fun doubleClick(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs double click on view |
| [longClick](../-ui-base-actions/long-click.md) | `open fun longClick(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs long click on view |

### Inheritors

| Name | Summary |
|---|---|
| [UiScrollView](../../com.kaspersky.components.kautomator.component.scroll/-ui-scroll-view/index.md) | `class UiScrollView : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiScrollView`](../../com.kaspersky.components.kautomator.component.scroll/-ui-scroll-view/index.md)`>, `[`UiSwipeableActions`](../-ui-swipeable-actions/index.md)`, `[`UiScrollableActions`](./index.md) |
