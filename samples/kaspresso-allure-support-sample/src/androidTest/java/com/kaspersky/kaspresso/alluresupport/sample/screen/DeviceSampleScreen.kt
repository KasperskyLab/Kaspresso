package com.kaspersky.kaspresso.alluresupport.sample.screen

import com.kaspersky.kaspresso.R
import com.kaspersky.kaspresso_sample_core.device.DeviceSampleActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton

object DeviceSampleScreen : KScreen<DeviceSampleScreen>() {

    override val layoutId: Int? = R.layout.activity_device_sample
    override val viewClass: Class<*>? = DeviceSampleActivity::class.java

    val input = KEditText { withId(R.id.input) }

    val requestPermissionButton = KButton { withId(R.id.request_permission_button) }
}
