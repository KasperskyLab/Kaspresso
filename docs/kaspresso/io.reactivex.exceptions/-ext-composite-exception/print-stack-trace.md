[kaspresso](../../index.md) / [io.reactivex.exceptions](../index.md) / [ExtCompositeException](index.md) / [printStackTrace](./print-stack-trace.md)

# printStackTrace

`fun printStackTrace(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

All of the following `printStackTrace` functionality is derived from JDK ``[`Throwable`](https://developer.android.com/reference/java/lang/Throwable.html) `printStackTrace`. In particular, the `PrintStreamOrWriter` abstraction is copied wholesale. Changes from the official JDK implementation:
* no infinite loop detection
 * smaller critical section holding ``[`PrintStream`](https://developer.android.com/reference/java/io/PrintStream.html) lock
 * explicit knowledge about the exceptions ``[`List`](https://developer.android.com/reference/java/util/List.html) that this loops through


`fun printStackTrace(s: `[`PrintStream`](https://developer.android.com/reference/java/io/PrintStream.html)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)
`fun printStackTrace(s: `[`PrintWriter`](https://developer.android.com/reference/java/io/PrintWriter.html)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)