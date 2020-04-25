[kaspresso](../../index.md) / [io.reactivex.exceptions](../index.md) / [ExtCompositeException](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`ExtCompositeException(@NonNull vararg exceptions: Throwable!)`

Constructs a ExtCompositeException with the given array of Throwables as the list of suppressed exceptions.

### Parameters

`exceptions` - Throwable!: the Throwables to have as initially suppressed exceptions

### Exceptions

`IllegalArgumentException` - if `exceptions` is empty.`ExtCompositeException(@NonNull errors: MutableIterable<Throwable!>)`

Constructs a ExtCompositeException with the given array of Throwables as the list of suppressed exceptions.

### Parameters

`errors` - MutableIterable&lt;Throwable!&gt;: the Throwables to have as initially suppressed exceptions

### Exceptions

`IllegalArgumentException` - if `errors` is empty.