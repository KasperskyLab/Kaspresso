[kaspresso](../../index.md) / [io.reactivex.exceptions](../index.md) / [ExtCompositeException](./index.md)

# ExtCompositeException

`class ExtCompositeException : `[`RuntimeException`](https://developer.android.com/reference/java/lang/RuntimeException.html)

Represents an exception that is a composite of one or more other exceptions. A `ExtCompositeException` does not modify the structure of any exception it wraps, but at print-time it iterates through the list of Throwables contained in the composite in order to print them all. Its invariant is to contain an immutable, ordered (by insertion order), unique list of non-composite exceptions. You can retrieve individual exceptions in this list with ``[`#getExceptions()`](get-exceptions.md). The ``[`#printStackTrace()`](print-stack-trace.md) implementation handles the StackTrace in a customized way instead of using `getCause()` so that it can avoid circular references. If you invoke ``[`#getCause()`](cause.md), it will lazily create the causal chain but will stop if it finds any Throwable in the chain that it has already seen.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ExtCompositeException(vararg exceptions: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`!)`<br>`ExtCompositeException(errors: `[`MutableIterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-iterable/index.html)`<`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`!>)`<br>Constructs a ExtCompositeException with the given array of Throwables as the list of suppressed exceptions. |

### Properties

| Name | Summary |
|---|---|
| [cause](cause.md) | `val cause: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [message](message.md) | `val message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [getExceptions](get-exceptions.md) | `fun getExceptions(): `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`!>`<br>Retrieves the list of exceptions that make up the `ExtCompositeException`. |
| [printStackTrace](print-stack-trace.md) | `fun printStackTrace(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>All of the following `printStackTrace` functionality is derived from JDK ``[`Throwable`](https://developer.android.com/reference/java/lang/Throwable.html) `printStackTrace`. In particular, the `PrintStreamOrWriter` abstraction is copied wholesale. Changes from the official JDK implementation:<ul> <li>no infinite loop detection</li> <li>smaller critical section holding ``[`PrintStream`](https://developer.android.com/reference/java/io/PrintStream.html) lock</li> <li>explicit knowledge about the exceptions ``[`List`](https://developer.android.com/reference/java/util/List.html) that this loops through</li> </ul>`fun printStackTrace(s: `[`PrintStream`](https://developer.android.com/reference/java/io/PrintStream.html)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun printStackTrace(s: `[`PrintWriter`](https://developer.android.com/reference/java/io/PrintWriter.html)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [size](size.md) | `fun size(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Returns the number of suppressed exceptions. |
