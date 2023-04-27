package com.kaspersky.kaspressample.screen

import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.systemlanguage.ChangeLocaleActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KTextView

object ChangeLocaleScreen : KScreen<ChangeLocaleScreen>() {
    override val layoutId: Int = R.layout.activity_change_locale
    override val viewClass: Class<*> = ChangeLocaleActivity::class.java

    val text = KTextView { withId(R.id.change_locale_text) }
}
