package com.kaspersky.kaspressample.recycler

import com.kaspersky.kaspressample.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView

object RecyclerScreen : KScreen<RecyclerScreen>() {
    override val layoutId: Int = R.layout.fragment_recycler
    override val viewClass: Class<*> = RecyclerFragment::class.java

    val recycler = KRecyclerView(builder = { withId(R.id.recycler) }, {})

    val element0 = KTextView { withText("0") }
    val element14 = KTextView { withText("14") }
    val element29 = KTextView { withText("29") }
}
