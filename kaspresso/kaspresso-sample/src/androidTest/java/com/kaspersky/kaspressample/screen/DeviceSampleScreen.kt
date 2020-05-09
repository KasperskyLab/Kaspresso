package com.kaspersky.kaspressample.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.text.KButton
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspresso.screens.KScreen

object DeviceSampleScreen : KScreen<DeviceSampleScreen>() {

    override val layoutId: Int? = R.layout.activity_device_sample
    override val viewClass: Class<*>? = DeviceSampleActivity::class.java

    val input = KEditText { withId(R.id.input) }

    val requestPermissionButton = KButton { withId(R.id.request_permission_button) }
}