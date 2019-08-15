package com.kaspersky.kaspressample.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.kaspersky.kaspressample.R

object DeviceSampleScreen : Screen<DeviceSampleScreen>() {

    val input = KEditText { withId(R.id.input) }
}