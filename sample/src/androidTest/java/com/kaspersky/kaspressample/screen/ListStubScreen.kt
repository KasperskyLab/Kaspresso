package com.kaspersky.kaspressample.screen

import androidx.test.espresso.DataInteraction
import com.agoda.kakao.list.KAbsListView
import com.agoda.kakao.list.KAdapterItem
import com.agoda.kakao.text.KTextView
import com.kaspersky.kaspressample.ListStubActivity
import com.kaspersky.kaspressample.R

object ListStubScreen : KScreen<ListStubScreen>() {

    override val layoutId: Int? = R.layout.activity_list_stub
    override val viewClass: Class<*>? = ListStubActivity::class.java

    val listStub = KAbsListView(
        builder = { withId(R.id.list_stub) },
        itemTypeBuilder = { itemType(::StubItem) }
    )

    class StubItem(i: DataInteraction) : KAdapterItem<StubItem>(i) {
        val title = KTextView(i) { withId(R.id.title) }
    }
}