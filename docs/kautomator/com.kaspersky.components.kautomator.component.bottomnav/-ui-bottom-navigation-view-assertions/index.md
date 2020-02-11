[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.bottomnav](../index.md) / [UiBottomNavigationViewAssertions](./index.md)

# UiBottomNavigationViewAssertions

`interface UiBottomNavigationViewAssertions : `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)

Provides assertions for BottomNavigationview

### Types

| Name | Summary |
|---|---|
| [UiBottomNavigationViewAssertionType](-ui-bottom-navigation-view-assertion-type/index.md) | `enum class UiBottomNavigationViewAssertionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [view](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/view.md) | `abstract val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [hasNotSelectedItemWithId](has-not-selected-item-with-id.md) | `open fun hasNotSelectedItemWithId(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view's selected menu item id does not match given one. |
| [hasNotSelectedItemWithIndex](has-not-selected-item-with-index.md) | `open fun hasNotSelectedItemWithIndex(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view's selected menu item index does not match given one. Note that this method uses view hierarchy which could be changed at any time. |
| [hasNotSelectedItemWithTitle](has-not-selected-item-with-title.md) | `open fun hasNotSelectedItemWithTitle(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view's selected menu item title does not match given one. Note that this method uses view hierarchy which could be changed at any time. |
| [hasSelectedItemWithId](has-selected-item-with-id.md) | `open fun hasSelectedItemWithId(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view's selected menu item id matches given one |
| [hasSelectedItemWithIndex](has-selected-item-with-index.md) | `open fun hasSelectedItemWithIndex(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view's selected menu item index matches given one. Note that this method uses view hierarchy which could be changed at any time. |
| [hasSelectedItemWithTitle](has-selected-item-with-title.md) | `open fun hasSelectedItemWithTitle(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view's selected menu item title matches given one. Note that this method uses view hierarchy which could be changed at any time. |

### Inherited Functions

| Name | Summary |
|---|---|
| [isClickable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-clickable.md) | `open fun isClickable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is clickable |
| [isDisabled](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-disabled.md) | `open fun isDisabled(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is disabled |
| [isDisplayed](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-displayed.md) | `open fun isDisplayed(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is completely displayed |
| [isEnabled](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-enabled.md) | `open fun isEnabled(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is enabled |
| [isFocusable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-focusable.md) | `open fun isFocusable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is focusable |
| [isFocused](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-focused.md) | `open fun isFocused(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is focused |
| [isNotClickable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-clickable.md) | `open fun isNotClickable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not clickable |
| [isNotDisplayed](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-displayed.md) | `open fun isNotDisplayed(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not completely displayed |
| [isNotFocusable](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-focusable.md) | `open fun isNotFocusable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not focusable |
| [isNotFocused](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-focused.md) | `open fun isNotFocused(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not focused |
| [isNotSelected](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-not-selected.md) | `open fun isNotSelected(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not selected |
| [isSelected](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/is-selected.md) | `open fun isSelected(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is selected |

### Inheritors

| Name | Summary |
|---|---|
| [UiBottomNavigationView](../-ui-bottom-navigation-view/index.md) | `class UiBottomNavigationView : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiBottomNavigationView`](../-ui-bottom-navigation-view/index.md)`>, `[`UiBottomNavigationViewActions`](../-ui-bottom-navigation-view-actions/index.md)`, `[`UiBottomNavigationViewAssertions`](./index.md)<br>View for acting and asserting on BottomNavigationView |
