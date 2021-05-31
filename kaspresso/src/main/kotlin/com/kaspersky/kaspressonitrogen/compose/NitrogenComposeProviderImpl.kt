package com.kaspersky.kaspressonitrogen.compose

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack
import com.kaspersky.kaspresso.compose.pack.ActionsPack
import com.kaspersky.kaspressonitrogen.kaspresso.KaspressoNitrogen

class NitrogenComposeProviderImpl(
        kaspresso: KaspressoNitrogen
) : NitrogenComposeProvider {

    private val composeExecutor = NitrogenComposeExecutor(kaspresso)

    override fun compose(
            timeoutMs: Long?,
            intervalMs: Long?,
            allowedExceptions: Set<Class<out Throwable>>?,
            block: ActionsOnElementsPack.() -> Unit
    ) {
        val composeBranches = ActionsOnElementsPack().apply(block).build()
        composeExecutor.executeComposeBranches(timeoutMs, intervalMs, allowedExceptions, composeBranches)
    }

    override fun unsafeCompose(block: ActionsOnElementsPack.() -> Unit) {
        val composeBranches = ActionsOnElementsPack().apply(block).build()
        composeExecutor.executeComposeBranchesUnsafely(composeBranches)
    }

    override fun <Type> Type.compose(
            timeoutMs: Long?,
            intervalMs: Long?,
            allowedExceptions: Set<Class<out Throwable>>?,
            block: ActionsPack<Type>.() -> Unit
    ) where Type : BaseActions,
            Type : BaseAssertions,
            Type : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val composeBranches = ActionsPack(this).apply(block).build()
        composeExecutor.executeComposeBranches(timeoutMs, intervalMs, allowedExceptions, composeBranches)
    }

    override fun <Type> Type.unsafeCompose(block: ActionsPack<Type>.() -> Unit)
            where Type : BaseActions,
                  Type : BaseAssertions,
                  Type : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val composeBranches = ActionsPack(this).apply(block).build()
        composeExecutor.executeComposeBranchesUnsafely(composeBranches)
    }
}

