[kaspresso](../../index.md) / [io.reactivex.exceptions](../index.md) / [ExtCompositeException](index.md) / [printStackTrace](./print-stack-trace.md)

# printStackTrace

`fun printStackTrace(): Unit`

All of the following `printStackTrace` functionality is derived from JDK ``[`Throwable`](#) `printStackTrace`. In particular, the `PrintStreamOrWriter` abstraction is copied wholesale. Changes from the official JDK implementation:
* no infinite loop detection
 * smaller critical section holding ``[`PrintStream`](#) lock
 * explicit knowledge about the exceptions ``[`List`](#) that this loops through


`fun printStackTrace(s: PrintStream!): Unit`
`fun printStackTrace(s: PrintWriter!): Unit`