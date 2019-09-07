package com.kaspersky.kaspresso.compose

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import com.kaspersky.kaspresso.compose.pack.ActionsPack
import com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack

interface ComposeProvider {

    fun compose(block: ActionsOnElementsPack.() -> Unit)

    fun <T> T.compose(block: ActionsPack<T>.() -> Unit): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction>
}