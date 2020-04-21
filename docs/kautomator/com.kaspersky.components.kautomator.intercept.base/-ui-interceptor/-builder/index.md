[kautomator](../../../index.md) / [com.kaspersky.components.kautomator.intercept.base](../../index.md) / [UiInterceptor](../index.md) / [Builder](./index.md)

# Builder

`class Builder<Interaction, Assertion, Action>`

Builder class that is used to build a single instance of [UiInterceptor](../index.md).

**See Also**

[UiInterceptor](../index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Builder()`<br>Builder class that is used to build a single instance of [UiInterceptor](../index.md). |

### Functions

| Name | Summary |
|---|---|
| [onAll](on-all.md) | `fun onAll(isOverride: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, interceptor: (`[`Interaction`](index.md#Interaction)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the interceptor for the `check` and `execute` operations for a given interaction. If overridden, breaks the call chain of operation and transfers the responsibility to invoke the UiAutomator on the developer. |
| [onCheck](on-check.md) | `fun onCheck(isOverride: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, interceptor: (`[`Interaction`](index.md#Interaction)`, `[`Assertion`](index.md#Assertion)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the interceptor for the `check` operation for a given interaction. If overridden, breaks the call chain of operation and transfers the responsibility to invoke the UiAutomator on the developer. |
| [onPerform](on-perform.md) | `fun onPerform(isOverride: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, interceptor: (`[`Interaction`](index.md#Interaction)`, `[`Action`](index.md#Action)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the interceptor for the `execute` operation for a given interaction. If overridden, breaks the call chain of operation and transfers the responsibility to invoke the UiAutomator on the developer. |
