[kautomator](../../index.md) / [com.kaspersky.components.kautomator.intercept.operation](../index.md) / [UiOperation](./index.md)

# UiOperation

`interface UiOperation<View>`

Responsible for executing an interaction on the element of UiAutomator

### Properties

| Name | Summary |
|---|---|
| [description](description.md) | `abstract val description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [type](type.md) | `abstract val type: `[`UiOperationType`](../-ui-operation-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [execute](execute.md) | `abstract fun execute(innerView: `[`View`](index.md#View)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [DisplayedObjectAssertion](../../com.kaspersky.components.kautomator.component.common.assertions/-displayed-object-assertion/index.md) | `class DisplayedObjectAssertion : `[`UiOperation`](./index.md)`<UiObject2>`<br>Special separate Assertion to determine UiAutomator View is displayed or not |
| [UiOperationBaseImpl](../-ui-operation-base-impl/index.md) | `class UiOperationBaseImpl<View> : `[`UiOperation`](./index.md)`<`[`View`](../-ui-operation-base-impl/index.md#View)`>` |
