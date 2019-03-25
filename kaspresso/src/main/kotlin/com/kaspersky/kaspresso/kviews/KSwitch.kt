package com.kaspersky.kaspresso.kviews

import android.view.View
import com.kaspersky.klkakao.check.CheckableActions
import com.kaspersky.klkakao.check.CheckableAssertions
import com.kaspersky.klkakao.common.builders.ViewBuilder
import com.kaspersky.klkakao.common.views.KBaseView
import com.kaspersky.klkakao.delegates.DataInteractionDelegate
import org.hamcrest.Matcher

/**
 * View with [CheckableActions] and [CheckableAssertions].
 */
class KSwitch : KBaseView<KSwitch>, CheckableActions, CheckableAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataInteractionDelegate, function: ViewBuilder.() -> Unit) : super(parent, function)
}