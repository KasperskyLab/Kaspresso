/**
 * Copyright (c) 2016-present, RxJava Contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.reactivex.exceptions

import java.io.PrintStream
import java.io.PrintWriter
import java.util.Collections

/**
 * Represents an exception that is a composite of one or more other exceptions. A `ExtCompositeException`
 * does not modify the structure of any exception it wraps, but at print-time it iterates through the list of
 * Throwables contained in the composite in order to print them all.
 *
 * Its invariant is to contain an immutable, ordered (by insertion order), unique list of non-composite
 * exceptions. You can retrieve individual exceptions in this list with [.getExceptions].
 *
 * The [.printStackTrace] implementation handles the StackTrace in a customized way instead of using
 * `getCause()` so that it can avoid circular references.
 *
 * If you invoke [.getCause], it will lazily create the causal chain but will stop if it finds any
 * Throwable in the chain that it has already seen.
 */
class ExtCompositeException(errors: Iterable<Throwable?>) : RuntimeException() {
    /**
     * Retrieves the list of exceptions that make up the `ExtCompositeException`.
     *
     * @return the exceptions that make up the `ExtCompositeException`, as a [List] of [Throwable]s
     */
    val exceptions: List<Throwable>
    override var message: String = ""

    @get:Synchronized
    override var cause: Throwable? = null
        get() { // NOPMD
            if (field == null) {
                // we lazily generate this causal chain if this is called
                val localCause = CompositeExceptionCausalChain()
                val seenCauses: MutableSet<Throwable?> = HashSet()

                var chain: Throwable? = localCause
                for (ex in exceptions) {
                    var e = ex
                    if (seenCauses.contains(e)) {
                        // already seen this outer Throwable so skip
                        continue
                    }
                    seenCauses.add(e)

                    val listOfCauses = getListOfCauses(e)
                    // check if any of them have been seen before
                    for (child in listOfCauses) {
                        if (seenCauses.contains(child)) {
                            // already seen this outer Throwable so skip
                            e = RuntimeException("Duplicate found in causal chain so cropping to prevent loop ...")
                            continue
                        }
                        seenCauses.add(child)
                    }

                    // we now have 'e' as the last in the chain
                    try {
                        chain?.initCause(e)
                    } catch (t: Throwable) { // NOPMD
                        // ignore
                        // the JavaDocs say that some Throwables (depending on how they're made) will never
                        // let me call initCause without blowing up even if it returns null
                    }
                    chain = getRootCause(chain)
                }
                field = localCause
            }
            return field
        }
        private set

    /**
     * Constructs a ExtCompositeException with the given array of Throwables as the
     * list of suppressed exceptions.
     * @param exceptions the Throwables to have as initially suppressed exceptions
     *
     * @throws IllegalArgumentException if `exceptions` is empty.
     */
    @Suppress("SpreadOperator")
    constructor(vararg exceptions: Throwable) : this(listOf<Throwable>(*exceptions))

    /**
     * Constructs a ExtCompositeException with the given array of Throwables as the
     * list of suppressed exceptions.
     * @param errors the Throwables to have as initially suppressed exceptions
     *
     * @throws IllegalArgumentException if `errors` is empty.
     */
    init {
        val deDupedExceptions: MutableSet<Throwable> = LinkedHashSet()
        val localExceptions: MutableList<Throwable> = ArrayList()
        for (ex in errors) {
            if (ex is ExtCompositeException) {
                deDupedExceptions.addAll(ex.exceptions)
            } else if (ex != null) {
                deDupedExceptions.add(ex)
            } else {
                deDupedExceptions.add(NullPointerException("Throwable was null!"))
            }
        }
        require(!deDupedExceptions.isEmpty()) { "errors is empty" }
        localExceptions.addAll(deDupedExceptions)
        this.exceptions = Collections.unmodifiableList(localExceptions)
        this.message = exceptions.size.toString() + " exceptions occurred. "
    }

    /**
     * All of the following `printStackTrace` functionality is derived from JDK [Throwable]
     * `printStackTrace`. In particular, the `PrintStreamOrWriter` abstraction is copied wholesale.
     *
     * Changes from the official JDK implementation:
     *  * no infinite loop detection
     *  * smaller critical section holding [PrintStream] lock
     *  * explicit knowledge about the exceptions [List] that this loops through
     *
     */
    override fun printStackTrace() {
        printStackTrace(System.err)
    }

    override fun printStackTrace(s: PrintStream) {
        printStackTrace(WrappedPrintStream(s))
    }

    override fun printStackTrace(s: PrintWriter) {
        printStackTrace(WrappedPrintWriter(s))
    }

    /**
     * Special handling for printing out a `ExtCompositeException`.
     * Loops through all inner exceptions and prints them out.
     *
     * @param s
     * stream to print to
     */
    private fun printStackTrace(s: PrintStreamOrWriter) {
        val b = StringBuilder()
        b.append(this).append('\n')
        for (myStackElement in stackTrace) {
            b.append("\tat ").append(myStackElement).append('\n')
        }
        var i = 1
        for (ex in exceptions) {
            b.append("  ComposedException ").append(i).append(" :\n")
            appendStackTrace(b, ex, "\t")
            i++
        }
        s.println(b.toString())
    }

    private fun appendStackTrace(b: StringBuilder, ex: Throwable?, prefix: String) {
        b.append(prefix).append(ex).append('\n')
        ex?.stackTrace?.forEach { stackElement ->
            b.append("\t\tat ").append(stackElement).append('\n')
        }
        if (ex?.cause != null) {
            b.append("\tCaused by: ")
            appendStackTrace(b, ex.cause, "")
        }
    }

    internal interface PrintStreamOrWriter {
        /** Prints the specified string as a line on this StreamOrWriter.  */
        fun println(o: Any?)
    }

    /**
     * Same abstraction and implementation as in JDK to allow PrintStream and PrintWriter to share implementation.
     */
    internal class WrappedPrintStream(private val printStream: PrintStream) : PrintStreamOrWriter {
        override fun println(o: Any?) {
            printStream.println(o)
        }
    }

    internal class WrappedPrintWriter(private val printWriter: PrintWriter) : PrintStreamOrWriter {
        override fun println(o: Any?) {
            printWriter.println(o)
        }
    }

    internal class CompositeExceptionCausalChain : RuntimeException() {

        override val message = MESSAGE

        companion object {
            private const val serialVersionUID = 3875212506787802066L

            /* package-private */
            const val MESSAGE: String = "Chain of Causes for ExtCompositeException In Order Received =>"
        }
    }

    private fun getListOfCauses(ex: Throwable): List<Throwable?> {
        val list: MutableList<Throwable?> = ArrayList()
        var root = ex.cause
        if (root == null || root === ex) {
            return list
        } else {
            while (true) {
                list.add(root)
                val cause = root?.cause
                if (cause == null || cause === root) {
                    return list
                } else {
                    root = cause
                }
            }
        }
    }

    /**
     * Returns the number of suppressed exceptions.
     * @return the number of suppressed exceptions
     */
    fun size(): Int {
        return exceptions.size
    }

    /**
     * Returns the root cause of `e`. If `e.getCause()` returns `null` or `e`, just return `e` itself.
     *
     * @param e the [Throwable] `e`.
     * @return The root cause of `e`. If `e.getCause()` returns `null` or `e`, just return `e` itself.
     */
    /*private */
    fun getRootCause(e: Throwable?): Throwable? {
        var root = e?.cause
        if (root == null || e === root) {
            return e
        }
        while (true) {
            val cause = root?.cause
            if (cause == null || cause === root) {
                return root
            }
            root = cause
        }
    }
}
