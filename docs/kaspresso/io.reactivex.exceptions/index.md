[kaspresso](../index.md) / [io.reactivex.exceptions](./index.md)

## Package io.reactivex.exceptions

### Exceptions

| Name | Summary |
|---|---|
| [ExtCompositeException](-ext-composite-exception/index.md) | Represents an exception that is a composite of one or more other exceptions. A `ExtCompositeException` does not modify the structure of any exception it wraps, but at print-time it iterates through the list of Throwables contained in the composite in order to print them all. Its invariant is to contain an immutable, ordered (by insertion order), unique list of non-composite exceptions. You can retrieve individual exceptions in this list with ``[`#getExceptions()`](-ext-composite-exception/get-exceptions.md). The ``[`#printStackTrace()`](-ext-composite-exception/print-stack-trace.md) implementation handles the StackTrace in a customized way instead of using `getCause()` so that it can avoid circular references. If you invoke ``[`#getCause()`](-ext-composite-exception/cause.md), it will lazily create the causal chain but will stop if it finds any Throwable in the chain that it has already seen.`class ExtCompositeException : `[`RuntimeException`](https://docs.oracle.com/javase/6/docs/api/java/lang/RuntimeException.html) |
