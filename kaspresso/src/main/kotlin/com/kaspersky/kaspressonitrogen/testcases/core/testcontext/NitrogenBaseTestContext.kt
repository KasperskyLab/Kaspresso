package com.kaspersky.kaspressonitrogen.testcases.core.testcontext

import com.kaspersky.kaspresso.compose.*
import com.kaspersky.kaspresso.flakysafety.*
import com.kaspersky.kaspressonitrogen.kaspresso.KaspressoNitrogen
import com.kaspersky.kaspressonitrogen.testcases.core.testassistants.NitrogenTestAssistantsProvider
import com.kaspersky.kaspresso.testcases.core.testassistants.NitrogenTestAssistantsProviderImpl
import com.kaspersky.kaspressonitrogen.compose.NitrogenComposeProvider
import com.kaspersky.kaspressonitrogen.compose.NitrogenComposeProviderImpl
import com.kaspersky.kaspressonitrogen.compose.NitrogenWebComposeProviderImpl
import com.kaspersky.kaspressonitrogen.flakysafety.NitrogenContinuouslyProviderImpl
import com.kaspersky.kaspressonitrogen.flakysafety.NitrogenFlakySafetyProviderGlobalImpl

open class NitrogenBaseTestContext internal constructor(
        kaspresso: KaspressoNitrogen
) : FlakySafetyProvider by NitrogenFlakySafetyProviderGlobalImpl(kaspresso),
        ContinuouslyProvider by NitrogenContinuouslyProviderImpl(
            kaspresso
        ),
        NitrogenComposeProvider by NitrogenComposeProviderImpl(kaspresso),
        WebComposeProvider by NitrogenWebComposeProviderImpl(kaspresso),
        NitrogenTestAssistantsProvider by NitrogenTestAssistantsProviderImpl(kaspresso)
