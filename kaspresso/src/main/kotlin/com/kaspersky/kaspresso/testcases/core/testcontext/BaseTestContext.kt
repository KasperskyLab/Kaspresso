package com.kaspersky.kaspresso.testcases.core.testcontext

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.web.assertion.WebAssertion
import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.sugar.Web
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import com.agoda.kakao.web.WebActions
import com.agoda.kakao.web.WebAssertions
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.logger.KLogger
import com.kaspersky.kaspresso.testcases.core.compose.*

open class BaseTestContext internal constructor(
    configurator: Configurator
) : ComposeProvider, WebComposeProvider {

    private val composer: Composer = Composer(configurator)
    private val webComposer: WebComposer = WebComposer(configurator)

    val device: Device = Device(configurator)

    val kLogger: KLogger = KLogger(configurator.externalLogger)

    override fun <T> T.compose(block: ActionsPack<T>.() -> Unit): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> =
        composer.compose(this, block)

    override fun <T> T.compose(
        interceptable: Interceptable<Web.WebInteraction<*>, WebAssertion<*>, Atom<*>>,
        block: ActionsPack<T>.() -> Unit
    ): Unit where T : WebActions, T : WebAssertions = webComposer.compose(this, interceptable, block)
}