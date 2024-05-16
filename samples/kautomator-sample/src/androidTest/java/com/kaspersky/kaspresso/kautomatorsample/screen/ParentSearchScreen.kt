package com.kaspersky.kaspresso.kautomatorsample.screen

import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.components.kautomator.screen.UiScreen
import com.kaspersky.kaspresso.kautomatorsample.R

object ParentSearchScreen : UiScreen<ParentSearchScreen>() {
    override val packageName = "com.kaspersky.kaspresso.kautomatorsample"

    val textByParentId = UiTextView {
        isFocusable()
        withParent { withId(R.id.container1) }
    }

    val textByParentChild = UiTextView {
        isFocusable()
        withParent {
            withChild { withId(R.id.title2) }
        }
    }
}
