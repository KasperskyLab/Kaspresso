package com.kaspersky.uitest_framework.kakao.delegates.factory

import android.support.test.espresso.*
import android.support.test.espresso.web.sugar.Web
import com.kaspersky.uitest_framework.kakao.delegates.DataInteractionDelegate
import com.kaspersky.uitest_framework.kakao.delegates.ViewInteractionDelegate
import com.kaspersky.uitest_framework.kakao.delegates.WebInteractionDelegate
import com.kaspersky.uitest_framework.kakao.delegates.impl.DataInteractionDelegateImpl
import com.kaspersky.uitest_framework.kakao.delegates.impl.ViewInteractionDelegateImpl
import com.kaspersky.uitest_framework.kakao.delegates.impl.WebInteractionDelegateImpl

object DelegatesFactory {

    var viewInteractionDelegateFactory: (ViewInteraction) -> ViewInteractionDelegate = {
        ViewInteractionDelegateImpl(it)
    }

    var dataInteractionDelegateFactory: (DataInteraction) -> DataInteractionDelegate = {
        DataInteractionDelegateImpl(it)
    }

    var webInteractionDelegateFactory: (Web.WebInteraction<*>) -> WebInteractionDelegate = {
        WebInteractionDelegateImpl(it)
    }
}