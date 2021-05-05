package com.kaspersky.kaspressonitrogen.testcases.core.testassistants

import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.Params

interface NitrogenTestAssistantsProvider {
    val testLogger: UiTestLogger
    val params: Params
}
