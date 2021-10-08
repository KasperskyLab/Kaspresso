package com.kaspersky.kaspresso.instrumental

import android.app.Instrumentation

internal class InstrumentalDepAssisFactory {

    inline fun <reified T : Any> getComponentAssistant(instrumentation: Instrumentation): InstrumentalDepsAssistant =
        InstrumentalDepsAssistantImpl(InstrumentalUsage.ComponentLocation(T::class.java.name), instrumentation)

    inline fun <reified T : Any> getInterceptorAssistant(instrumentation: Instrumentation): InstrumentalDepsAssistant =
        InstrumentalDepsAssistantImpl(InstrumentalUsage.InterceptorLocation(T::class.java.name), instrumentation)

    fun getTestAssistant(instrumentation: Instrumentation): InstrumentalDepsAssistant =
        InstrumentalDepsAssistantImpl(InstrumentalUsage.TestLocation, instrumentation)
}
