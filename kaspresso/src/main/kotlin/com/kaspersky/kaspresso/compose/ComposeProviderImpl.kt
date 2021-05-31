package com.kaspersky.kaspresso.compose

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import io.github.kakaocup.kakao.common.actions.BaseActions
import io.github.kakaocup.kakao.common.assertions.BaseAssertions
import io.github.kakaocup.kakao.intercept.Interceptable
import com.kaspersky.components.kautomator.component.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.component.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.intercept.base.UiInterceptable
import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion
import com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack
import com.kaspersky.kaspresso.compose.pack.ActionsPack
import com.kaspersky.kaspresso.kaspresso.Kaspresso

/**
 * The implementation of the [ComposeProvider] interface.
 */
class ComposeProviderImpl(
    kaspresso: Kaspresso
) : ComposeProvider {

    private val composeExecutor = ComposeExecutor(kaspresso)

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

    override fun <Type> Type.compose(
        timeoutMs: Long?,
        intervalMs: Long?,
        allowedExceptions: Set<Class<out Throwable>>?,
        block: ActionsPack<Type>.() -> Unit
    ) where Type : UiBaseActions,
            Type : UiBaseAssertions,
            Type : UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction> {

        val composeBranches = ActionsPack(this).apply(block).build()
        composeExecutor.executeComposeBranches(timeoutMs, intervalMs, allowedExceptions, composeBranches)
    }

    override fun <Type> Type.unsafeCompose(block: ActionsPack<Type>.() -> Unit)
            where Type : UiBaseActions,
                  Type : UiBaseAssertions,
                  Type : UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction> {

        val composeBranches = ActionsPack(this).apply(block).build()
        composeExecutor.executeComposeBranchesUnsafely(composeBranches)
    }
}
