[kaspresso](../../index.md) / [io.reactivex.exceptions](../index.md) / [ExtCompositeException](index.md) / [printStackTrace](./print-stack-trace.md)

# printStackTrace

`fun printStackTrace(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

All of the following `printStackTrace` functionality is derived from JDK ``[`Throwable`](https://docs.oracle.com/javase/6/docs/api/java/lang/Throwable.html) `printStackTrace`. In particular, the `PrintStreamOrWriter` abstraction is copied wholesale. Changes from the official JDK implementation:
* no infinite loop detection
 * smaller critical section holding ``[`PrintStream`](https://docs.oracle.com/javase/6/docs/api/java/io/PrintStream.html) lock
 * explicit knowledge about the exceptions ``[`List`](https://docs.oracle.com/javase/6/docs/api/java/util/List.html) that this loops through


`fun printStackTrace(s: `[`PrintStream`](https://docs.oracle.com/javase/6/docs/api/java/io/PrintStream.html)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)
`fun printStackTrace(s: `[`PrintWriter`](https://docs.oracle.com/javase/6/docs/api/java/io/PrintWriter.html)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)