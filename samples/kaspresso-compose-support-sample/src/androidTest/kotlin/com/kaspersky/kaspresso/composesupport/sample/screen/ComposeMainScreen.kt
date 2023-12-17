package com.kaspersky.kaspresso.composesupport.sample.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.kaspersky.components.composesupport.core.KNode
import com.kaspersky.kaspresso.composesupport.sample.resources.C
import io.github.kakaocup.compose.node.element.ComposeScreen

class ComposeMainScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<ComposeMainScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(C.Tag.main_screen_container) }
    ) {

    val simpleFlakyButton: KNode = child {
        hasTestTag(C.Tag.main_screen_simple_flaky_button)
    }

    val sanityFlakyButton: KNode = child {
        hasTestTag(C.Tag.main_screen_sanity_flaky_button)
    }

    val scrollButton: KNode = child {
        hasTestTag(C.Tag.main_screen_scroll_button)
    }
}
