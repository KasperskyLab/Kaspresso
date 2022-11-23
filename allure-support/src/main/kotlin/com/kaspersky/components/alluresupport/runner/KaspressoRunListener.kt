package com.kaspersky.components.alluresupport.runner

import androidx.test.platform.app.InstrumentationRegistry
import com.kaspersky.components.alluresupport.report.AttachVideoToReportHack
import org.junit.runner.Result
import org.junit.runner.notification.RunListener

class KaspressoRunListener : RunListener() {

    private val hack: AttachVideoToReportHack by lazy {
        AttachVideoToReportHack(InstrumentationRegistry.getInstrumentation())
    }

    override fun testRunFinished(result: Result?) {
        hack.hack()
    }
}
