package com.kaspersky.uitest_framework.kakao.configuration

import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.web.sugar.Web
import com.kaspersky.uitest_framework.kakao.delegates.DataInteractionDelegate
import com.kaspersky.uitest_framework.kakao.delegates.ViewInteractionDelegate
import com.kaspersky.uitest_framework.kakao.delegates.WebInteractionDelegate
import com.kaspersky.uitest_framework.kakao.delegates.impl.DataInteractionDelegateEmptyImpl
import com.kaspersky.uitest_framework.kakao.delegates.impl.ViewInteractionDelegateEmptyImpl
import com.kaspersky.uitest_framework.kakao.delegates.impl.WebInteractionDelegateEmptyImpl

object Configurator {

    var viewInteractionDelegateFactory: (ViewInteraction) -> ViewInteractionDelegate = { viewInteraction ->
        ViewInteractionDelegateEmptyImpl(viewInteraction)
    }
        private set

    var dataInteractionDelegateFactory: (DataInteraction) -> DataInteractionDelegate = { dataInteraction ->
        DataInteractionDelegateEmptyImpl(dataInteraction)
    }
        private set

    var webInteractionDelegateFactory: (Web.WebInteraction<*>) -> WebInteractionDelegate = {webInteraction ->
        WebInteractionDelegateEmptyImpl(webInteraction)
    }
        private set

    fun setupConfiguration(
        viewInteractionDelegateFactory: (ViewInteraction) -> ViewInteractionDelegate,
        dataInteractionDelegateFactory: (DataInteraction) -> DataInteractionDelegate,
        webInteractionDelegateFactory: (Web.WebInteraction<*>) -> WebInteractionDelegate
    ) {
        this.viewInteractionDelegateFactory = viewInteractionDelegateFactory
        this.dataInteractionDelegateFactory = dataInteractionDelegateFactory
        this.webInteractionDelegateFactory = webInteractionDelegateFactory
    }

}