package com.kaspersky.kaspresso.composesupport.sample.screen

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.kaspersky.kaspresso.composesupport.sample.features.lazylist.ListItemPositionSemantics
import com.kaspersky.kaspresso.composesupport.sample.features.lazylist.ListLengthSemantics
import com.kaspersky.kaspresso.composesupport.sample.resources.C
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListItemNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListNode

class ComposeLazyListScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
ComposeScreen<ComposeLazyListScreen>(
semanticsProvider = semanticsProvider,
) {
    val list: KLazyListNode =
        KLazyListNode(
            semanticsProvider = semanticsProvider,
            viewBuilderAction = { hasTestTag(C.Tag.scroll_screen_multi_text_list) },
            itemTypeBuilder = {
                itemType(::LazyListScreenItemNode)
            },
            positionMatcher = { position -> SemanticsMatcher.expectValue(ListItemPositionSemantics, position) },
            lengthSemanticsPropertyKey = ListLengthSemantics
        )
}
class LazyListScreenItemNode(
    semanticsNode: SemanticsNode,
    semanticsProvider: SemanticsNodeInteractionsProvider
) : KLazyListItemNode<LazyListScreenItemNode>(
    semanticsNode,
    semanticsProvider
) {
    val text1: KNode =
        child {
            useUnmergedTree = true
            hasTestTag(C.Tag.multi_text_text1)
        }
    val text2: KNode =
        child {
            useUnmergedTree = true
            hasTestTag(C.Tag.multi_text_text2)
        }
    val text3: KNode =
        child {
            useUnmergedTree = true
            hasTestTag(C.Tag.multi_text_text3)
        }
}
