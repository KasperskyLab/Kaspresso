package com.agoda.kakao.configurator

import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.web.sugar.Web
import com.agoda.kakao.delegates.DataInteractionDelegate
import com.agoda.kakao.delegates.ViewInteractionDelegate
import com.agoda.kakao.delegates.WebInteractionDelegate
import com.agoda.kakao.delegates.impl.DataInteractionDelegateEmptyImpl
import com.agoda.kakao.delegates.impl.ViewInteractionDelegateEmptyImpl
import com.agoda.kakao.delegates.impl.WebInteractionDelegateEmptyImpl

object KakaoConfigurator {

     private var viewInteractionDelegateFactory:
            ((ViewInteraction) -> ViewInteractionDelegate)? = null

     private var dataInteractionDelegateFactory:
            ((DataInteraction) -> DataInteractionDelegate)? = null

     private var webInteractionDelegateFactory:
            ((Web.WebInteraction<*>) -> WebInteractionDelegate)? = null

    fun setViewInteractionDelegateFactory(
            factory: (ViewInteraction) -> ViewInteractionDelegate
    ) {
        viewInteractionDelegateFactory ?: let { viewInteractionDelegateFactory = factory }
    }

    fun setDataInteractionDelegateFactory(
            factory: (DataInteraction) -> DataInteractionDelegate
    ) {
        dataInteractionDelegateFactory ?: let { dataInteractionDelegateFactory = factory }
    }

    fun setWebInteractionDelegateFactory(
            factory: (Web.WebInteraction<*>) -> WebInteractionDelegate
    ) {
        webInteractionDelegateFactory ?: let { webInteractionDelegateFactory = factory }
    }

    fun createViewInteractionDelegate(
            viewInteraction: ViewInteraction
    ): ViewInteractionDelegate {
        return viewInteractionDelegateFactory
                ?.invoke(viewInteraction)
                ?: ViewInteractionDelegateEmptyImpl(viewInteraction)
    }

    fun createDataInteractionDelegate(
            dataInteraction: DataInteraction
    ): DataInteractionDelegate {
        return dataInteractionDelegateFactory
                ?.invoke(dataInteraction)
                ?: DataInteractionDelegateEmptyImpl(dataInteraction)
    }

    fun createWebInteractionDelegate(
            webInteraction: Web.WebInteraction<*>
    ): WebInteractionDelegate {
        return webInteractionDelegateFactory
                ?.invoke(webInteraction)
                ?: WebInteractionDelegateEmptyImpl(webInteraction)
    }
}