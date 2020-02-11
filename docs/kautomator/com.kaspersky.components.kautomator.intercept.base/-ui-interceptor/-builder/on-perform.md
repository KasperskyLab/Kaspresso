[kautomator](../../../index.md) / [com.kaspersky.components.kautomator.intercept.base](../../index.md) / [UiInterceptor](../index.md) / [Builder](index.md) / [onPerform](./on-perform.md)

# onPerform

`fun onPerform(isOverride: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, interceptor: (`[`Interaction`](index.md#Interaction)`, `[`Action`](index.md#Action)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Sets the interceptor for the `execute` operation for a given interaction.
If overridden, breaks the call chain of operation and transfers the responsibility
to invoke the UiAutomator on the developer.

### Parameters

`isOverride` - if `true` - breaks the call chain, false otherwise

`interceptor` - lambda with intercepting logic