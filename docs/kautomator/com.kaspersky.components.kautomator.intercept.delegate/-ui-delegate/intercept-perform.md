[kautomator](../../index.md) / [com.kaspersky.components.kautomator.intercept.delegate](../index.md) / [UiDelegate](index.md) / [interceptPerform](./intercept-perform.md)

# interceptPerform

`open fun interceptPerform(action: `[`Action`](index.md#Action)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Runs the interceptors available for the given delegate during the `execute` operation.

**Return**
`true` if the call chain has been interrupted and there is no need to do UiAutomator call,
    false otherwise.

