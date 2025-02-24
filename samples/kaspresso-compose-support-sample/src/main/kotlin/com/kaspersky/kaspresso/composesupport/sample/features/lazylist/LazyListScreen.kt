package com.kaspersky.kaspresso.composesupport.sample.features.lazylist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.kaspersky.kaspresso.composesupport.sample.resources.C

@Composable
fun LazyListScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .testTag(C.Tag.scroll_screen_multi_text_list)
            .lazyListLength(C.Tag.scroll_screen_multi_text_items.size)
            .semantics { testTag = C.Tag.scroll_screen_multi_text }

    ) {
        itemsIndexed(
            C.Tag.scroll_screen_multi_text_items
        ) { index, item ->
            Row(modifier = Modifier.lazyListItemPosition(index).padding(8.dp).border(BorderStroke(1.dp, Color.Green))) {
                Column(
                    modifier = Modifier.fillMaxWidth().wrapContentSize().padding(8.dp)
                ) {
                    Text(
                        text = item.text1,
                        modifier = Modifier.testTag(C.Tag.multi_text_text1)
                    )
                    Text(
                        text = item.text2,
                        modifier = Modifier.testTag(C.Tag.multi_text_text2)
                    )
                    Text(
                        text = item.text3,
                        modifier = Modifier.testTag(C.Tag.multi_text_text3)
                    )
                }
            }
        }
    }
}

val ListItemPositionSemantics: SemanticsPropertyKey<Int> = SemanticsPropertyKey("ListItemPosition")
var SemanticsPropertyReceiver.lazyListItemPosition: Int by ListItemPositionSemantics

fun Modifier.lazyListItemPosition(position: Int): Modifier = semantics { lazyListItemPosition = position }

val ListLengthSemantics: SemanticsPropertyKey<Int> = SemanticsPropertyKey("ListLength")
var SemanticsPropertyReceiver.lazyListLength: Int by ListLengthSemantics

fun Modifier.lazyListLength(length: Int): Modifier = semantics { lazyListLength = length }
