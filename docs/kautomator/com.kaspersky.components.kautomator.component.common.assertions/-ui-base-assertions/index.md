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
| [isClickable](is-clickable.md) | Checks if the view is clickable`open fun isClickable(): Unit` |
| [isDisabled](is-disabled.md) | Checks if the view is disabled`open fun isDisabled(): Unit` |
| [isDisplayed](is-displayed.md) | Checks if the view is completely displayed`open fun isDisplayed(): Unit` |
| [isEnabled](is-enabled.md) | Checks if the view is enabled`open fun isEnabled(): Unit` |
| [isFocusable](is-focusable.md) | Checks if the view is focusable`open fun isFocusable(): Unit` |
| [isFocused](is-focused.md) | Checks if the view is focused`open fun isFocused(): Unit` |
| [isNotClickable](is-not-clickable.md) | Checks if the view is not clickable`open fun isNotClickable(): Unit` |
| [isNotDisplayed](is-not-displayed.md) | Checks if the view is not completely displayed`open fun isNotDisplayed(): Unit` |
| [isNotFocusable](is-not-focusable.md) | Checks if the view is not focusable`open fun isNotFocusable(): Unit` |
| [isNotFocused](is-not-focused.md) | Checks if the view is not focused`open fun isNotFocused(): Unit` |
| [isNotSelected](is-not-selected.md) | Checks if the view is not selected`open fun isNotSelected(): Unit` |
| [isSelected](is-selected.md) | Checks if the view is selected`open fun isSelected(): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [UiBaseView](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md) | Base class for all UiAutomator DSL views`open class UiBaseView<out T> : `[`UiBaseActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md)`, `[`UiBaseAssertions`](./index.md)`, `[`UiInterceptable`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md)`<`[`UiObjectInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)`, `[`UiObjectAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`, `[`UiObjectAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`>` |
| [UiBottomNavigationViewAssertions](../../com.kaspersky.components.kautomator.component.bottomnav/-ui-bottom-navigation-view-assertions/index.md) | Provides assertions for BottomNavigationview`interface UiBottomNavigationViewAssertions : `[`UiBaseAssertions`](./index.md) |
| [UiCheckableAssertions](../../com.kaspersky.components.kautomator.component.check/-ui-checkable-assertions/index.md) | Provides assertions for checkable views`interface UiCheckableAssertions : `[`UiBaseAssertions`](./index.md) |
| [UiChipGroupAssertions](../../com.kaspersky.components.kautomator.component.chip/-ui-chip-group-assertions/index.md) | Provides assertions for a ChipGroup`interface UiChipGroupAssertions : `[`UiBaseAssertions`](./index.md) |
| [UiSwitch](../../com.kaspersky.components.kautomator.component.switch/-ui-switch/index.md) | Ui View with [UiSwitchableActions](../../com.kaspersky.components.kautomator.component.switch/-ui-switchable-actions/index.md) and [UiBaseAssertions](./index.md)`class UiSwitch : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiSwitch`](../../com.kaspersky.components.kautomator.component.switch/-ui-switch/index.md)`>, `[`UiSwitchableActions`](../../com.kaspersky.components.kautomator.component.switch/-ui-switchable-actions/index.md)`, `[`UiBaseAssertions`](./index.md) |
| [UiTextView](../../com.kaspersky.components.kautomator.component.text/-ui-text-view/index.md) | Ui View with UiBaseActions and UiTextViewAssertions`class UiTextView : `[`UiBaseView`](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)`<`[`UiTextView`](../../com.kaspersky.components.kautomator.component.text/-ui-text-view/index.md)`>, `[`UiBaseAssertions`](./index.md)`, `[`UiTextViewAssertions`](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md) |
| [UiTextViewAssertions](../../com.kaspersky.components.kautomator.component.text/-ui-text-view-assertions/index.md) | Provides assertions for UiTextView`interface UiTextViewAssertions : `[`UiBaseAssertions`](./index.md) |
