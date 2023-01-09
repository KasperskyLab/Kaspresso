package com.kaspersky.kaspressample.recycler

import com.kaspersky.kaspressample.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.recycler.KRecyclerView

object RecyclerScreen : KScreen<RecyclerScreen>() {
    override val layoutId: Int = R.layout.fragment_recycler
    override val viewClass: Class<*> = RecyclerFragment::class.java

    val recycler = KRecyclerView(builder = { withId(R.id.recycler) }, {})
}
