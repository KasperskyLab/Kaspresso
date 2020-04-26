[kaspresso](../../index.md) / [io.reactivex.exceptions](../index.md) / [ExtCompositeException](./index.md)

# ExtCompositeException

`class ExtCompositeException : `[`RuntimeException`](https://docs.oracle.com/javase/6/docs/api/java/lang/RuntimeException.html)

Represents an exception that is a composite of one or more other exceptions. A `ExtCompositeException` does not modify the structure of any exception it wraps, but at print-time it iterates through the list of Throwables contained in the composite in order to print them all. Its invariant is to contain an immutable, ordered (by insertion order), unique list of non-composite exceptions. You can retrieve individual exceptions in this list with ``[`#getExceptions()`](get-exceptions.md). The ``[`#printStackTrace()`](print-stack-trace.md) implementation handles the StackTrace in a customized way instead of using `getCause()` so that it can avoid circular references. If you invoke ``[`#getCause()`](cause.md), it will lazily create the causal chain but will stop if it finds any Throwable in the chain that it has already seen.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Constructs a ExtCompositeException with the given array of Throwables as the list of suppressed exceptions.`ExtCompositeException(vararg exceptions: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`!)`<br>`ExtCompositeException(errors: `[`MutableIterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-iterable/index.html)`<`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`!>)` |

### Properties

| Name | Summary |
|---|---|
| [cause](cause.md) | `val cause: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [message](message.md) | `val message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [getExceptions](get-exceptions.md) | Retrieves the list of exceptions that make up the `ExtCompositeException`.`fun getExceptions(): `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`!>` |
| [printStackTrace](print-stack-trace.md) | All of the following `printStackTrace` functionality is derived from JDK ``[`Throwable`](https://docs.oracle.com/javase/6/docs/api/java/lang/Throwable.html) `printStackTrace`. In particular, the `PrintStreamOrWriter` abstraction is copied wholesale. Changes from the official JDK implementation:<ul> <li>no infinite loop detection</li> <li>smaller critical section holding ``[`PrintStream`](https://docs.oracle.com/javase/6/docs/api/java/io/PrintStream.html) lock</li> <li>explicit knowledge about the exceptions ``[`List`](https://docs.oracle.com/javase/6/docs/api/java/util/List.html) that this loops through</li> </ul>`fun printStackTrace(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`fun printStackTrace(s: `[`PrintStream`](https://docs.oracle.com/javase/6/docs/api/java/io/PrintStream.html)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun printStackTrace(s: `[`PrintWriter`](https://docs.oracle.com/javase/6/docs/api/java/io/PrintWriter.html)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [size](size.md) | Returns the number of suppressed exceptions.`fun size(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
