package com.kaspersky.uitest_framework.kakaoext

import android.view.View
import com.kaspersky.uitest_framework.kakao.check.CheckableActions
import com.kaspersky.uitest_framework.kakao.common.builders.ViewBuilder
import com.kaspersky.uitest_framework.kakao.common.views.KBaseView
import com.kaspersky.uitest_framework.kakao.delegates.DataInteractionDelegate
import org.hamcrest.Matcher

class KSwitch : KBaseView<KSwitch>, CheckableActions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataInteractionDelegate, function: ViewBuilder.() -> Unit) : super(parent, function)
}