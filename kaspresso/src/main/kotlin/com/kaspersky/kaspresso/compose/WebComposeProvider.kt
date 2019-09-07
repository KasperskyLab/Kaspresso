package com.kaspersky.kaspresso.compose

import com.agoda.kakao.web.WebElementBuilder

interface WebComposeProvider {

    fun WebElementBuilder.webCompose(block: WebComponentPack.() -> Unit)

    fun WebElementBuilder.KWebInteraction.webCompose(
        webElementBuilder: WebElementBuilder,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    )
}