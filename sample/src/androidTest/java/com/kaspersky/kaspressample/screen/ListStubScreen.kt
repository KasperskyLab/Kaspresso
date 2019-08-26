package com.kaspersky.kaspressample.screen

import androidx.test.espresso.DataInteraction
import com.agoda.kakao.list.KAbsListView
import com.agoda.kakao.list.KAdapterItem
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.kaspersky.kaspressample.R

class ListStubScreen : Screen<ListStubScreen>() {

    val listStub = KAbsListView(
        builder = { withId(R.id.list_stub) },
        itemTypeBuilder = { itemType(::StubItem) }
    )

    class StubItem(i: DataInteraction) : KAdapterItem<StubItem>(i) {
        val title = KTextView(i) { withId(R.id.title) }
    }
}