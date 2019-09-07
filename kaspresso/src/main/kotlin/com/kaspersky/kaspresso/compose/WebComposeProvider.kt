package com.kaspersky.kaspresso.compose

import com.agoda.kakao.web.WebElementBuilder
import com.kaspersky.kaspresso.compose.pack.ActionsPack
import com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack

interface WebComposeProvider {

    fun WebElementBuilder.compose(block: ActionsOnWebElementsPack.() -> Unit)

    fun WebElementBuilder.KWebInteraction.compose(
        webElementBuilder: WebElementBuilder,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    )
}