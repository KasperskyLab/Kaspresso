package com.kaspersky.kaspresso.testcases.core.testassistants

import com.kaspersky.kaspressonitrogen.kaspresso.KaspressoNitrogen
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.Params
import com.kaspersky.kaspressonitrogen.testcases.core.testassistants.NitrogenTestAssistantsProvider

internal class NitrogenTestAssistantsProviderImpl(kaspresso: KaspressoNitrogen) : NitrogenTestAssistantsProvider {
    override val testLogger: UiTestLogger = kaspresso.testLogger
    override val params: Params = kaspresso.params
}
