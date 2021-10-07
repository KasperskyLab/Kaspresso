package com.kaspersky.kaspresso.instrumental.exception

import com.kaspersky.kaspresso.instrumental.InstrumentalUsage
import java.lang.RuntimeException

class NotSupportedInstrumentalTestException(exceptionText: String) : RuntimeException(exceptionText) {

    internal constructor(instrumentalUsage: InstrumentalUsage, instrumentalProperty: String) : this(
        when (instrumentalUsage) {
            is InstrumentalUsage.ComponentLocation ->
                """

                    ${instrumentalUsage.componentName} is not adapted (or partially adapted only) to be executed like a Unit test (with Robolectric support on the JVM environment)
                        because ${instrumentalUsage.componentName} calls $instrumentalProperty inside its implementation.
                    We suggest next options:
                    1. Replace ${instrumentalUsage.componentName} with an implementation that doesn't call Instrumental classes.
                    2. If the reason of the crash is an Interceptor that uses ${instrumentalUsage.componentName} then there is recommendation to remove this Interceptor in case of
                        the test executing on the JVM environment.
                    3. Rewrite your test without ${instrumentalUsage.componentName}.
                    4. Rewrite your test with if-else (Instrumental/JVM) logic due to using of `InstrumentalDependenciesAssistant` class in your test (accessible in before-after-run DSL Contexts).
                    5. Don't use this test like Unit-test.

                """.trimIndent()
            is InstrumentalUsage.InterceptorLocation ->
                """

                    ${instrumentalUsage.interceptorName} has calls of $instrumentalProperty inside its implementation. It means that ${instrumentalUsage.interceptorName} is not adapted to
                        be executed in a Unit test (with Robolectric support on the JVM environment).
                    We suggest next options:
                    1. Remove ${instrumentalUsage.interceptorName} in a case when a test is executing on the JVM (with Robolectric support) environment.
                    2. Replace with an Unit-test adapted version of ${instrumentalUsage.interceptorName}.

                """.trimIndent()
            is InstrumentalUsage.TestLocation ->
                """

                    The test calls $instrumentalProperty inside its implementation.
                    We suggest next options:
                    1. Don't execute the test on the JVM (with Robolectric support) environment.
                    2. Handle using of $instrumentalProperty inside the test with `isInstrumentalEnvironment` flag.

                """.trimIndent()
        }
    )
}
