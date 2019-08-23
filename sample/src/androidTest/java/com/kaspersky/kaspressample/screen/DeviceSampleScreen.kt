package com.kaspersky.kaspressample.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.kaspersky.kaspressample.R

object DeviceSampleScreen : Screen<DeviceSampleScreen>() {

    val input = KEditText { withId(R.id.input) }

    val requestPermissionButton = KButton { withId(R.id.request_permission_button) }
}