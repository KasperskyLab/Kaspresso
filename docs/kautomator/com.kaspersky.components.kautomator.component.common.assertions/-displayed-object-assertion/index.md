[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.common.assertions](../index.md) / [DisplayedObjectAssertion](./index.md)

# DisplayedObjectAssertion

`class DisplayedObjectAssertion : `[`UiOperation`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)`<UiObject2>`

Special separate Assertion to determine UiAutomator View is displayed or not

### Types

| Name | Summary |
|---|---|
| [UiDisplayedAssertionType](-ui-displayed-assertion-type/index.md) | `enum class UiDisplayedAssertionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Properties

| Name | Summary |
|---|---|
| [description](description.md) | `val description: String?` |
| [type](type.md) | `val type: UiDisplayedAssertionType` |

### Functions

| Name | Summary |
|---|---|
| [execute](execute.md) | `fun execute(innerView: UiObject2): Unit` |
| [toString](to-string.md) | `fun toString(): String` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [assertDisplayed](assert-displayed.md) | `fun assertDisplayed(): `[`DisplayedObjectAssertion`](./index.md) |
| [assertNotDisplayed](assert-not-displayed.md) | `fun ~~assertNotDisplayed~~(): `[`DisplayedObjectAssertion`](./index.md) |
