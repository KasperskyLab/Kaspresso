[kaspresso](../../index.md) / [com.kaspersky.kaspresso.params](../index.md) / [AutoScrollParams](./index.md)

# AutoScrollParams

`class AutoScrollParams`

The class that holds all the necessary for [com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider-impl/index.md) and
[com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl](../../com.kaspersky.kaspresso.autoscroll/-web-auto-scroll-provider-impl/index.md) parameters.

### Properties

| Name | Summary |
|---|---|
| [allowedExceptions](allowed-exceptions.md) | `val allowedExceptions: `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>>`<br>The set of exceptions, if caught, the [com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider-impl/index.md) or [com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl](../../com.kaspersky.kaspresso.autoscroll/-web-auto-scroll-provider-impl/index.md) will autoscroll. |

### Companion Object Functions

| Name | Summary |
|---|---|
| [default](default.md) | `fun default(): `[`AutoScrollParams`](./index.md) |
