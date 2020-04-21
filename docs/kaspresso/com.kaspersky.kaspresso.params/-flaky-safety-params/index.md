[kaspresso](../../index.md) / [com.kaspersky.kaspresso.params](../index.md) / [FlakySafetyParams](./index.md)

# FlakySafetyParams

`class FlakySafetyParams`

The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-simple-impl/index.md) parameters.

### Properties

| Name | Summary |
|---|---|
| [allowedExceptions](allowed-exceptions.md) | `val allowedExceptions: `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>>`<br>The set of exceptions, if caught, the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-simple-impl/index.md) will continue to attempt. |
| [intervalMs](interval-ms.md) | `var intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>The interval at which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-simple-impl/index.md). |
| [timeoutMs](timeout-ms.md) | `var timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>The timeout during which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-simple-impl/index.md). |

### Companion Object Functions

| Name | Summary |
|---|---|
| [custom](custom.md) | `fun custom(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, allowedExceptions: `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>>): `[`FlakySafetyParams`](./index.md) |
| [default](default.md) | `fun default(): `[`FlakySafetyParams`](./index.md) |
