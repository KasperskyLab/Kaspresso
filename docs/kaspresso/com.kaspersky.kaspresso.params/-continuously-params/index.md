[kaspresso](../../index.md) / [com.kaspersky.kaspresso.params](../index.md) / [ContinuouslyParams](./index.md)

# ContinuouslyParams

`class ContinuouslyParams`

The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl](../../com.kaspersky.kaspresso.flakysafety/-continuously-provider-impl/index.md) parameters.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ContinuouslyParams(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = DEFAULT_TIMEOUT_MS, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = DEFAULT_INTERVAL_MS)`<br>The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl](../../com.kaspersky.kaspresso.flakysafety/-continuously-provider-impl/index.md) parameters. |

### Properties

| Name | Summary |
|---|---|
| [intervalMs](interval-ms.md) | `var intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>The interval at which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl](../../com.kaspersky.kaspresso.flakysafety/-continuously-provider-impl/index.md). |
| [timeoutMs](timeout-ms.md) | `var timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>The timeout during which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl](../../com.kaspersky.kaspresso.flakysafety/-continuously-provider-impl/index.md). |
