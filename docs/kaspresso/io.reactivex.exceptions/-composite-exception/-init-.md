[kaspresso](../../index.md) / [io.reactivex.exceptions](../index.md) / [CompositeException](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`CompositeException(@NonNull vararg exceptions: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`!)`

Constructs a CompositeException with the given array of Throwables as the list of suppressed exceptions.

### Parameters

`exceptions` - [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)!: the Throwables to have as initially suppressed exceptions

### Exceptions

`IllegalArgumentException` - if `exceptions` is empty.`CompositeException(@NonNull errors: `[`MutableIterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-iterable/index.html)`<`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`!>)`

Constructs a CompositeException with the given array of Throwables as the list of suppressed exceptions.

### Parameters

`errors` - [MutableIterable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-iterable/index.html)&lt;[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)!&gt;: the Throwables to have as initially suppressed exceptions

### Exceptions

`IllegalArgumentException` - if `errors` is empty.