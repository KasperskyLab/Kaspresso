package com.kaspersky.kaspresso.instrumental

import android.app.Instrumentation

internal class InstrumentalDepAssisFactory {

    fun getComponentAssistant(componentName: String, instrumentation: Instrumentation): InstrumentalDepsAssistant =
        InstrumentalDepsAssistantImpl(InstrumentalUsage.ComponentLocation(componentName), instrumentation)

    fun getInterceptorAssistant(interceptorName: String, instrumentation: Instrumentation): InstrumentalDepsAssistant =
        InstrumentalDepsAssistantImpl(InstrumentalUsage.InterceptorLocation(interceptorName), instrumentation)

    fun getTestAssistant(instrumentation: Instrumentation): InstrumentalDepsAssistant =
        InstrumentalDepsAssistantImpl(InstrumentalUsage.TestLocation, instrumentation)
}
