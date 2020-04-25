[kaspresso](../../index.md) / [com.kaspersky.kaspresso.params](../index.md) / [FlakySafetyParams](./index.md)

# FlakySafetyParams

`class FlakySafetyParams`

The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-simple-impl/index.md) parameters.

### Properties

| Name | Summary |
|---|---|
| [allowedExceptions](allowed-exceptions.md) | The set of exceptions, if caught, the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-simple-impl/index.md) will continue to attempt.`val allowedExceptions: Set<Class<out Throwable>>` |
| [intervalMs](interval-ms.md) | The interval at which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-simple-impl/index.md).`var intervalMs: Long` |
| [timeoutMs](timeout-ms.md) | The timeout during which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-simple-impl/index.md).`var timeoutMs: Long` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [custom](custom.md) | `fun custom(timeoutMs: Long, intervalMs: Long, allowedExceptions: Set<Class<out Throwable>>): `[`FlakySafetyParams`](./index.md) |
| [default](default.md) | `fun default(): `[`FlakySafetyParams`](./index.md) |
