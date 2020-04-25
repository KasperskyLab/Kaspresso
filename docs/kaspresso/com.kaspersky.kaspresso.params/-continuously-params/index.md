[kaspresso](../../index.md) / [com.kaspersky.kaspresso.params](../index.md) / [ContinuouslyParams](./index.md)

# ContinuouslyParams

`class ContinuouslyParams`

The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl](../../com.kaspersky.kaspresso.flakysafety/-continuously-provider-impl/index.md) parameters.

### Properties

| Name | Summary |
|---|---|
| [intervalMs](interval-ms.md) | The interval at which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl](../../com.kaspersky.kaspresso.flakysafety/-continuously-provider-impl/index.md).`var intervalMs: Long` |
| [timeoutMs](timeout-ms.md) | The timeout during which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl](../../com.kaspersky.kaspresso.flakysafety/-continuously-provider-impl/index.md).`var timeoutMs: Long` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [custom](custom.md) | `fun custom(timeoutMs: Long, intervalMs: Long): `[`ContinuouslyParams`](./index.md) |
| [default](default.md) | `fun default(): `[`ContinuouslyParams`](./index.md) |
