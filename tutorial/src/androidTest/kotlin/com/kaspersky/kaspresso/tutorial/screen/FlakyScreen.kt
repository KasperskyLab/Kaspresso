package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.progress.KProgressBar
import io.github.kakaocup.kakao.text.KButton

object FlakyScreen : KScreen<FlakyScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val text1 = KButton { withId(R.id.text_1) }
    val text2 = KButton { withId(R.id.text_2) }
    val text3 = KButton { withId(R.id.text_3) }
    val text4 = KButton { withId(R.id.text_4) }
    val text5 = KButton { withId(R.id.text_5) }

    val progressBar1 = KProgressBar { withId(R.id.progress_bar_1) }
    val progressBar2 = KProgressBar { withId(R.id.progress_bar_2) }
    val progressBar3 = KProgressBar { withId(R.id.progress_bar_3) }
    val progressBar4 = KProgressBar { withId(R.id.progress_bar_4) }
    val progressBar5 = KProgressBar { withId(R.id.progress_bar_5) }
}
