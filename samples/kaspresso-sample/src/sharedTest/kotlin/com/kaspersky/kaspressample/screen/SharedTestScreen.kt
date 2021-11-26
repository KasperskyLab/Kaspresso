package com.kaspersky.kaspressample.screen

import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.sharedtest.SharedTestActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton

object SharedTestScreen : KScreen<SharedTestScreen>() {

    override val layoutId: Int = R.layout.activity_sharedtest
    override val viewClass: Class<*> = SharedTestActivity::class.java

    val findMeButton = KButton { withId(R.id.findMeButton) }

    val firstNameEditText = KEditText { withId(R.id.firstName) }
    val lastNameEditText = KEditText { withId(R.id.lastName) }
    val ageEditText = KEditText { withId(R.id.age) }

    val maleButton = KButton { withId(R.id.male) }
}
