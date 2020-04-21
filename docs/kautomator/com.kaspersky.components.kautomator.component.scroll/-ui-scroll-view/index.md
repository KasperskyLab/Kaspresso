[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.scroll](../index.md) / [UiScrollView](./index.md)

# UiScrollView

`class UiScrollView : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiScrollView`](./index.md)`>, `[`UiSwipeableActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-swipeable-actions/index.md)`, `[`UiScrollableActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-scrollable-actions/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UiScrollView(selector: `[`UiViewSelector`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md)`)`<br>`UiScrollView(builder: `[`UiViewBuilder`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)` |

### Inherited Properties

| Name | Summary |
|---|---|
| [view](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/view.md) | `val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Inherited Functions

| Name | Summary |
|---|---|
| [invoke](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/invoke.md) | `operator fun invoke(function: `[`T`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Operator that allows usage of DSL style |
| [scrollToEnd](../../com.kaspersky.components.kautomator.component.common.actions/-ui-scrollable-actions/scroll-to-end.md) | `open fun scrollToEnd(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [scrollToStart](../../com.kaspersky.components.kautomator.component.common.actions/-ui-scrollable-actions/scroll-to-start.md) | `open fun scrollToStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [scrollToView](../../com.kaspersky.components.kautomator.component.common.actions/-ui-scrollable-actions/scroll-to-view.md) | `open fun <T> scrollToView(to: `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`T`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-scrollable-actions/scroll-to-view.md#T)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [swipeDown](../../com.kaspersky.components.kautomator.component.common.actions/-ui-swipeable-actions/swipe-down.md) | `open fun swipeDown(percent: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)` = 0.95f): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Swipes down on the view |
| [swipeLeft](../../com.kaspersky.components.kautomator.component.common.actions/-ui-swipeable-actions/swipe-left.md) | `open fun swipeLeft(percent: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)` = 0.95f): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Swipes left on the view |
| [swipeRight](../../com.kaspersky.components.kautomator.component.common.actions/-ui-swipeable-actions/swipe-right.md) | `open fun swipeRight(percent: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)` = 0.95f): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Swipes right on the view |
| [swipeUp](../../com.kaspersky.components.kautomator.component.common.actions/-ui-swipeable-actions/swipe-up.md) | `open fun swipeUp(percent: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)` = 0.95f): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Swipes up on the view |
