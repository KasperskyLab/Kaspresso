package com.kaspersky.kaspresso.compose

import io.github.kakaocup.kakao.web.WebElementBuilder
import com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack
import com.kaspersky.kaspresso.compose.pack.ActionsPack
import com.kaspersky.kaspresso.kaspresso.Kaspresso

/**
 * The implementation of the [WebComposeProvider] interface.
 */
class WebComposeProviderImpl(
    private val kaspresso: Kaspresso
) : WebComposeProvider {

    private val composeExecutor = ComposeExecutor(kaspresso)

    override fun WebElementBuilder.compose(
        timeoutMs: Long?,
        intervalMs: Long?,
        allowedExceptions: Set<Class<out Throwable>>?,
        block: ActionsOnWebElementsPack.() -> Unit
    ) {
        val composeBranches = ActionsOnWebElementsPack(this).apply(block).build()
        composeExecutor.executeComposeBranches(timeoutMs, intervalMs, allowedExceptions, composeBranches)
    }

    override fun WebElementBuilder.unsafeCompose(block: ActionsOnWebElementsPack.() -> Unit) {
        val composeBranches = ActionsOnWebElementsPack(this).apply(block).build()
        composeExecutor.executeComposeBranchesUnsafely(composeBranches)
    }

    override fun WebElementBuilder.KWebInteraction.compose(
        webElementBuilder: WebElementBuilder,
        timeoutMs: Long?,
        intervalMs: Long?,
        allowedExceptions: Set<Class<out Throwable>>?,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    ) {
        val composeBranches = ActionsPack(this).apply(block).build()
        composeExecutor.executeComposeBranches(timeoutMs, intervalMs, allowedExceptions, composeBranches)
    }

    override fun WebElementBuilder.KWebInteraction.unsafeCompose(
        webElementBuilder: WebElementBuilder,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    ) {
        val composeBranches = ActionsPack(this).apply(block).build()
        composeExecutor.executeComposeBranchesUnsafely(composeBranches)
    }
}
