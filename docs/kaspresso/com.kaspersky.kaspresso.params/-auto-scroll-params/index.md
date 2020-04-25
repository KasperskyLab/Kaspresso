[kaspresso](../../index.md) / [com.kaspersky.kaspresso.params](../index.md) / [AutoScrollParams](./index.md)

# AutoScrollParams

`class AutoScrollParams`

The class that holds all the necessary for [com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider-impl/index.md) and
[com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl](../../com.kaspersky.kaspresso.autoscroll/-web-auto-scroll-provider-impl/index.md) parameters.

### Properties

| Name | Summary |
|---|---|
| [allowedExceptions](allowed-exceptions.md) | The set of exceptions, if caught, the [com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider-impl/index.md) or [com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl](../../com.kaspersky.kaspresso.autoscroll/-web-auto-scroll-provider-impl/index.md) will autoscroll.`val allowedExceptions: Set<Class<out Throwable>>` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [default](default.md) | `fun default(): `[`AutoScrollParams`](./index.md) |
