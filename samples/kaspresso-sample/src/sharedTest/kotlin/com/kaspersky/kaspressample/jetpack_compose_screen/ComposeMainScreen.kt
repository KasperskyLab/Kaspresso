package com.kaspersky.kaspressample.jetpack_compose_screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.kaspersky.kaspressample.jetpack_compose.resources.C
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class ComposeMainScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<ComposeMainScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(C.Tag.main_screen_container) }
    ) {

    val simpleFlakyButton: KNode = child {
        hasTestTag(C.Tag.main_screen_simple_flaky_button)
    }
}
