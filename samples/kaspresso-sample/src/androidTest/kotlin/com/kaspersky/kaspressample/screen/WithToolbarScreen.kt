package com.kaspersky.kaspressample.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspressample.R
import io.github.kakaocup.kakao.toolbar.KToolbar

object WithToolbarScreen : KScreen<WithToolbarScreen>() {
    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val collapsingToolbar = KToolbar { withId(R.id.collapsing_toolbar) }
}
