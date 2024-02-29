package com.kaspersky.kaspresso.composesupport.sample.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.kaspersky.components.composesupport.core.KNode
import com.kaspersky.kaspresso.composesupport.sample.resources.C
import io.github.kakaocup.compose.node.element.ComposeScreen

class ComposeScrollScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<ComposeScrollScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(C.Tag.scroll_screen_container) }
    ) {

    val firstButton: KNode = child {
        hasTestTag(C.Tag.scroll_screen_buttons[0])
    }

    val lastButton: KNode = child {
        hasTestTag(C.Tag.scroll_screen_buttons.last())
    }
}
