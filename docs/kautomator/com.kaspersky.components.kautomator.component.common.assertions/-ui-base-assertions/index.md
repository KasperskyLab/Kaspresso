[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.common.assertions](../index.md) / [UiBaseAssertions](./index.md)

# UiBaseAssertions

`interface UiBaseAssertions`

Base interface for asserting UiAutomator views

Provides basic assertions that can be performed on any view

**See Also**

[com.kaspersky.components.kautomator.component.text.UiTextViewAssertions](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md)

### Types

| Name | Summary |
|---|---|
| [UiBaseAssertionType](-ui-base-assertion-type/index.md) | `enum class UiBaseAssertionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Properties

| Name | Summary |
|---|---|
| [view](view.md) | `abstract val view: `[`UiObjectInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-object-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [isClickable](is-clickable.md) | `open fun isClickable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is clickable |
| [isDisabled](is-disabled.md) | `open fun isDisabled(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is disabled |
| [isDisplayed](is-displayed.md) | `open fun isDisplayed(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is completely displayed |
| [isEnabled](is-enabled.md) | `open fun isEnabled(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is enabled |
| [isFocusable](is-focusable.md) | `open fun isFocusable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is focusable |
| [isFocused](is-focused.md) | `open fun isFocused(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is focused |
| [isNotClickable](is-not-clickable.md) | `open fun isNotClickable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not clickable |
| [isNotDisplayed](is-not-displayed.md) | `open fun isNotDisplayed(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not completely displayed |
| [isNotFocusable](is-not-focusable.md) | `open fun isNotFocusable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not focusable |
| [isNotFocused](is-not-focused.md) | `open fun isNotFocused(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not focused |
| [isNotSelected](is-not-selected.md) | `open fun isNotSelected(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is not selected |
| [isSelected](is-selected.md) | `open fun isSelected(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the view is selected |

### Inheritors

| Name | Summary |
|---|---|
| [UiBaseView](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md) | `open class UiBaseView<out T> : `[`UiBaseActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md)`, `[`UiBaseAssertions`](./index.md)`, `[`UiInterceptable`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md)`<`[`UiObjectInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)`, `[`UiObjectAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`, `[`UiObjectAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`>`<br>Base class for all UiAutomator DSL views |
| [UiBottomNavigationViewAssertions](../../com.kaspersky.components.kautomator.component.bottomnav/-ui-bottom-navigation-view-assertions/index.md) | `interface UiBottomNavigationViewAssertions : `[`UiBaseAssertions`](./index.md)<br>Provides assertions for BottomNavigationview |
| [UiCheckableAssertions](../../com.kaspersky.components.kautomator.component.check/-ui-checkable-assertions/index.md) | `interface UiCheckableAssertions : `[`UiBaseAssertions`](./index.md)<br>Provides assertions for checkable views |
| [UiChipGroupAssertions](../../com.kaspersky.components.kautomator.component.chip/-ui-chip-group-assertions/index.md) | `interface UiChipGroupAssertions : `[`UiBaseAssertions`](./index.md)<br>Provides assertions for a ChipGroup |
| [UiSwitch](../../com.kaspersky.components.kautomator.component.switch/-ui-switch/index.md) | `class UiSwitch : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiSwitch`](../../com.kaspersky.components.kautomator.component.switch/-ui-switch/index.md)`>, `[`UiSwitchableActions`](../../com.kaspersky.components.kautomator.component.switch/-ui-switchable-actions/index.md)`, `[`UiBaseAssertions`](./index.md)<br>Ui View with [UiSwitchableActions](../../com.kaspersky.components.kautomator.component.switch/-ui-switchable-actions/index.md) and [UiBaseAssertions](./index.md) |
| [UiTextView](../../com.kaspersky.components.kautomator.component.text/-ui-text-view/index.md) | `class UiTextView : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiTextView`](../../com.kaspersky.components.kautomator.component.text/-ui-text-view/index.md)`>, `[`UiBaseAssertions`](./index.md)`, `[`UiTextViewAssertions`](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md)<br>Ui View with UiBaseActions and UiTextViewAssertions |
| [UiTextViewAssertions](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md) | `interface UiTextViewAssertions : `[`UiBaseAssertions`](./index.md)<br>Provides assertions for UiTextView |
