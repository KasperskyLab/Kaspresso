package com.kaspersky.kaspresso.testcases.core.testcontext

import com.agoda.kakao.common.views.KBaseView
import com.kaspersky.kaspresso.compose.*
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.flakysafety.ContinuouslyProvider
import com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.testcases.core.testassistants.TestAssistantsProvider
import com.kaspersky.kaspresso.testcases.core.testassistants.TestAssistantsProviderImpl

/**
 * Provides the Kaspresso functionality for "run" section: [Device], [AdbServer], the [UiTestLogger] implementation
 * for external developers. Also provides flaky safety, composing and web composing functionalities via
 * implementing [FlakySafetyProvider], [ComposeProvider] and [WebComposeProvider] interfaces.
 */
open class BaseTestContext internal constructor(
    kaspresso: Kaspresso
) : FlakySafetyProvider by FlakySafetyProviderImpl(kaspresso.params.flakySafetyParams, kaspresso.libLogger),
    ContinuouslyProvider by ContinuouslyProviderImpl(kaspresso.params.continuouslyParams, kaspresso.libLogger),
    ViewComposeProvider by ViewComposeProviderImpl(kaspresso),
    ObjectComposeProvider by ObjectComposeProviderImpl(kaspresso),
    WebComposeProvider by WebComposeProviderImpl(kaspresso),
    TestAssistantsProvider by TestAssistantsProviderImpl(kaspresso)