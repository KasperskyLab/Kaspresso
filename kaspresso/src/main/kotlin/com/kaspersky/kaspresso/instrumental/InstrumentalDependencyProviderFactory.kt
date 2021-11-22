package com.kaspersky.kaspresso.instrumental

import android.app.Instrumentation

class InstrumentalDependencyProviderFactory {

    inline fun <reified T : Any> getComponentProvider(instrumentation: Instrumentation): InstrumentalDependencyProvider =
        InstrumentalDependencyProviderImpl(InstrumentalUsage.ComponentLocation(T::class.java.name), instrumentation)

    inline fun <reified T : Any> getInterceptorProvider(instrumentation: Instrumentation): InstrumentalDependencyProvider =
        InstrumentalDependencyProviderImpl(InstrumentalUsage.InterceptorLocation(T::class.java.name), instrumentation)

    fun getTestProvider(instrumentation: Instrumentation): InstrumentalDependencyProvider =
        InstrumentalDependencyProviderImpl(InstrumentalUsage.TestLocation, instrumentation)
}
