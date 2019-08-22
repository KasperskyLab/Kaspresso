package com.kaspersky.kaspressample.screen

import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.kaspersky.kaspressample.R
import org.hamcrest.Matcher

class RecyclerStubScreen : Screen<RecyclerStubScreen>() {

    val recyclerStub = KRecyclerView(
        { withId(R.id.recycler_stub) },
        { itemType(::StubItem) }
    )

    class StubItem(parent: Matcher<View>) : KRecyclerItem<StubItem>(parent) {
        val title = KTextView(parent) { withId(R.id.title) }
    }
}