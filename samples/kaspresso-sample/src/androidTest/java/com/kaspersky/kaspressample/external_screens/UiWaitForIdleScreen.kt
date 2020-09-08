package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.component.edit.UiEditText
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.idlingwait.WaitForIdleActivity

object UiWaitForIdleScreen : UiSampleScreen<UiWaitForIdleScreen>() {

    override val layoutId: Int? = R.layout.activity_idlewaiting
    override val viewClass: Class<*>? = WaitForIdleActivity::class.java

    val edit = UiEditText { withId(this@UiWaitForIdleScreen.packageName, "edit") }
}