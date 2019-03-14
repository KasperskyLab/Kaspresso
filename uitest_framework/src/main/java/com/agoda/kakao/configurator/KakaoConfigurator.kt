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
import java.lang.RuntimeException

object KakaoConfigurator {

    private var viewInteractionDelegateFactory:
            ((ViewInteraction) -> ViewInteractionDelegate)? = null

    private var dataInteractionDelegateFactory:
            ((DataInteraction) -> DataInteractionDelegate)? = null

    private var webInteractionDelegateFactory:
            ((Web.WebInteraction<*>) -> WebInteractionDelegate)? = null

    @Throws(RuntimeException::class)
    fun initViewInteractionDelegateFactory(
            factory: (ViewInteraction) -> ViewInteractionDelegate
    ) {
        viewInteractionDelegateFactory?.let {
            throw RuntimeException("Trying to re-init view interaction delegate factory")
        } ?: let { viewInteractionDelegateFactory = factory }
    }

    @Throws(RuntimeException::class)
    fun initDataInteractionDelegateFactory(
            factory: (DataInteraction) -> DataInteractionDelegate
    ) {
        dataInteractionDelegateFactory?.let {
            throw RuntimeException("Trying to re-init data interaction delegate factory")
        } ?: let { dataInteractionDelegateFactory = factory }
    }

    @Throws(RuntimeException::class)
    fun initWebInteractionDelegateFactory(
            factory: (Web.WebInteraction<*>) -> WebInteractionDelegate
    ) {
        webInteractionDelegateFactory?.let {
            throw RuntimeException("Trying to re-init web interaction delegate factory")
        } ?: let { webInteractionDelegateFactory = factory }
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