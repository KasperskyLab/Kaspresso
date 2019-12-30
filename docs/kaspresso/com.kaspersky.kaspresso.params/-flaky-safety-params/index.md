[kaspresso](../../index.md) / [com.kaspersky.kaspresso.params](../index.md) / [FlakySafetyParams](./index.md)

# FlakySafetyParams

`class FlakySafetyParams`

The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-impl/index.md) parameters.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FlakySafetyParams(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = DEFAULT_TIMEOUT_MS, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = DEFAULT_INTERVAL_MS, allowedExceptions: `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>> = DEFAULT_ALLOWED_EXCEPTIONS)`<br>The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-impl/index.md) parameters. |

### Properties

| Name | Summary |
|---|---|
| [allowedExceptions](allowed-exceptions.md) | `val allowedExceptions: `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>>`<br>The set of exceptions, if caught, the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-impl/index.md) will continue to attempt. |
| [intervalMs](interval-ms.md) | `var intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>The interval at which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-impl/index.md). |
| [timeoutMs](timeout-ms.md) | `var timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>The timeout during which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-impl/index.md). |

### Companion Object Properties

| Name | Summary |
|---|---|
| [DEFAULT_ALLOWED_EXCEPTIONS](-d-e-f-a-u-l-t_-a-l-l-o-w-e-d_-e-x-c-e-p-t-i-o-n-s.md) | `val DEFAULT_ALLOWED_EXCEPTIONS: `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>>` |
