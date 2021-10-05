package com.kaspersky.kaspresso.instrumental

import android.app.Instrumentation
import android.app.UiAutomation
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.instrumental.exception.NotSupportedInstrumentalTestException

internal class InstrumentalDepsAssistantImpl(
    private val location: InstrumentalUsage,
    private val instrumentation: Instrumentation
) : InstrumentalDepsAssistant {

    override val isInstrumentalEnvironment: Boolean
        get() = checkInstrumentalStateOfEnvironment()

    override val uiDevice: UiDevice
        get() =
            if (isInstrumentalEnvironment) UiDevice.getInstance(instrumentation)
            else throw NotSupportedInstrumentalTestException(location, "UiDevice")

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getUiAutomation(flags: Int): UiAutomation {
        if (!isInstrumentalEnvironment) throw NotSupportedInstrumentalTestException(location, "UiAutomation")
        return instrumentation.getUiAutomation(flags)
    }

    override val uiAutomation: UiAutomation
        get() =
            if (isInstrumentalEnvironment) instrumentation.uiAutomation
            else throw NotSupportedInstrumentalTestException(location, "UiAutomation")
}

internal sealed class InstrumentalUsage {
    data class ComponentLocation(val componentName: String) : InstrumentalUsage()
    data class InterceptorLocation(val interceptorName: String) : InstrumentalUsage()
    object TestLocation : InstrumentalUsage()
}
