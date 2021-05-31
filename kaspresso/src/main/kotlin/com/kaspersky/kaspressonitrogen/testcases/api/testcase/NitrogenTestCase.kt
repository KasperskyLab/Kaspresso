package com.kaspersky.kaspressonitrogen.testcases.api.testcase

import com.kaspersky.kaspressonitrogen.kaspresso.KaspressoNitrogen

abstract class NitrogenTestCase(
        kaspressoBuilder: KaspressoNitrogen.Builder = KaspressoNitrogen.Builder.simple()
) : NitrogenBaseTestCase<Unit, Unit>(
        kaspressoBuilder = kaspressoBuilder,
        dataProducer = { action -> action?.invoke(Unit) }
)
