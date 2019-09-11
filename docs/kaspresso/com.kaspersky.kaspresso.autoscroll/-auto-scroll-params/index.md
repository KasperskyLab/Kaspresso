[kaspresso](../../index.md) / [com.kaspersky.kaspresso.autoscroll](../index.md) / [AutoScrollParams](./index.md)

# AutoScrollParams

`class AutoScrollParams`

The class that holds all the necessary for [AutoScrollProvider](../-auto-scroll-provider/index.md) parameters.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AutoScrollParams(allowedExceptions: `[`MutableSet`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>> = mutableSetOf(
            PerformException::class.java,
            AssertionFailedError::class.java
        ))`<br>The class that holds all the necessary for [AutoScrollProvider](../-auto-scroll-provider/index.md) parameters. |

### Properties

| Name | Summary |
|---|---|
| [allowedExceptions](allowed-exceptions.md) | `var allowedExceptions: `[`MutableSet`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>>`<br>The set of exceptions, if caught, the [AutoScrollProvider](../-auto-scroll-provider/index.md) will autoscroll. |
