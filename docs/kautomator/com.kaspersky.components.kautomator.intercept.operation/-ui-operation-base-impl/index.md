[kautomator](../../index.md) / [com.kaspersky.components.kautomator.intercept.operation](../index.md) / [UiOperationBaseImpl](./index.md)

# UiOperationBaseImpl

`class UiOperationBaseImpl<View> : `[`UiOperation`](../-ui-operation/index.md)`<View>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UiOperationBaseImpl(type: `[`UiOperationType`](../-ui-operation-type/index.md)`, description: String?, action: View.() -> Unit)` |

### Properties

| Name | Summary |
|---|---|
| [description](description.md) | `val description: String?` |
| [type](type.md) | `val type: `[`UiOperationType`](../-ui-operation-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [execute](execute.md) | `fun execute(innerView: View): Unit` |
| [toString](to-string.md) | `fun toString(): String` |
