package com.kaspersky.kaspressample.screen

import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.devicefull.DeviceFullWindowSampleActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton

object DeviceFullWindowScreen : KScreen<DeviceFullWindowScreen>() {

    override val layoutId: Int = R.layout.activity_device_full_window_sample
    override val viewClass: Class<*> = DeviceFullWindowSampleActivity::class.java

    val showDialog = KButton { withId(R.id.showDialog) }
}
