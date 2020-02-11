[kautomator](../../index.md) / [com.kaspersky.components.kautomator.intercept.operation](../index.md) / [UiOperationBaseImpl](./index.md)

# UiOperationBaseImpl

`class UiOperationBaseImpl<View> : `[`UiOperation`](../-ui-operation/index.md)`<`[`View`](index.md#View)`>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UiOperationBaseImpl(type: `[`UiOperationType`](../-ui-operation-type/index.md)`, description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, action: `[`View`](index.md#View)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [description](description.md) | `val description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [type](type.md) | `val type: `[`UiOperationType`](../-ui-operation-type/index.md) |

### Functions

| Name | Summary |
|---|---|
| [execute](execute.md) | `fun execute(innerView: `[`View`](index.md#View)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
