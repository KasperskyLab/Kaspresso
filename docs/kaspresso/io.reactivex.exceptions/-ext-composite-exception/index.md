[kaspresso](../../index.md) / [io.reactivex.exceptions](../index.md) / [ExtCompositeException](./index.md)

# ExtCompositeException

`class ExtCompositeException : RuntimeException`

Represents an exception that is a composite of one or more other exceptions. A `ExtCompositeException` does not modify the structure of any exception it wraps, but at print-time it iterates through the list of Throwables contained in the composite in order to print them all. Its invariant is to contain an immutable, ordered (by insertion order), unique list of non-composite exceptions. You can retrieve individual exceptions in this list with ``[`#getExceptions()`](get-exceptions.md). The ``[`#printStackTrace()`](print-stack-trace.md) implementation handles the StackTrace in a customized way instead of using `getCause()` so that it can avoid circular references. If you invoke ``[`#getCause()`](cause.md), it will lazily create the causal chain but will stop if it finds any Throwable in the chain that it has already seen.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Constructs a ExtCompositeException with the given array of Throwables as the list of suppressed exceptions.`ExtCompositeException(vararg exceptions: Throwable!)`<br>`ExtCompositeException(errors: MutableIterable<Throwable!>)` |

### Properties

| Name | Summary |
|---|---|
| [cause](cause.md) | `val cause: Throwable` |
| [message](message.md) | `val message: String` |

### Functions

| Name | Summary |
|---|---|
| [getExceptions](get-exceptions.md) | Retrieves the list of exceptions that make up the `ExtCompositeException`.`fun getExceptions(): MutableList<Throwable!>` |
| [printStackTrace](print-stack-trace.md) | All of the following `printStackTrace` functionality is derived from JDK ``[`Throwable`](#) `printStackTrace`. In particular, the `PrintStreamOrWriter` abstraction is copied wholesale. Changes from the official JDK implementation:<ul> <li>no infinite loop detection</li> <li>smaller critical section holding ``[`PrintStream`](#) lock</li> <li>explicit knowledge about the exceptions ``[`List`](#) that this loops through</li> </ul>`fun printStackTrace(): Unit``fun printStackTrace(s: PrintStream!): Unit`<br>`fun printStackTrace(s: PrintWriter!): Unit` |
| [size](size.md) | Returns the number of suppressed exceptions.`fun size(): Int` |
