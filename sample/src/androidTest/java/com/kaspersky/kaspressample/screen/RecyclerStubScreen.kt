package com.kaspersky.kaspressample.screen

import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.text.KTextView
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.RecyclerStubActivity
import org.hamcrest.Matcher

object RecyclerStubScreen : KScreen<RecyclerStubScreen>() {

    override val layoutId: Int? = R.layout.activity_recycler_stub
    override val viewClass: Class<*>? = RecyclerStubActivity::class.java

    val recyclerStub = KRecyclerView(
        { withId(R.id.recycler_stub) },
        { itemType(::StubItem) }
    )

    class StubItem(parent: Matcher<View>) : KRecyclerItem<StubItem>(parent) {
        val title = KTextView(parent) { withId(R.id.title) }
    }
}