package com.kaspersky.kaspresso.composesupport.sample.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.kaspersky.components.composesupport.core.KNode
import com.kaspersky.kaspresso.composesupport.sample.resources.C
import io.github.kakaocup.compose.node.element.ComposeScreen

class ComposeSanityFlakyScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<ComposeSanityFlakyScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(C.Tag.sanity_flaky_screen_container) }
    ) {

    val firstButton: KNode = child {
        hasTestTag(C.Tag.sanity_flaky_screen_simple_first_button)
    }

    val secondButton: KNode = child {
        hasTestTag(C.Tag.sanity_flaky_screen_simple_second_button)
    }
}
