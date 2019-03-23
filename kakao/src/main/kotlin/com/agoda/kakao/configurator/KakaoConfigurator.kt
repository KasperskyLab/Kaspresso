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

/**
 * Holds interaction delegates factories and is responsible for creating interaction delegates instances.
 * By default creates stub interaction wrappers without any changes in behavior.
 */
object KakaoConfigurator {

    private var viewInteractionDelegateFactory:
            ((ViewInteraction) -> ViewInteractionDelegate)? = null

    private var dataInteractionDelegateFactory:
            ((DataInteraction) -> DataInteractionDelegate)? = null

    private var webInteractionDelegateFactory:
            ((Web.WebInteraction<*>) -> WebInteractionDelegate)? = null

    /**
     * Setter for [viewInteractionDelegateFactory]. Allows you to set the value only once.
     * If [viewInteractionDelegateFactory] is null, you can set your custom factory,
     * but if it is already set, you can not overwrite it.
     *
     * @param factory the function that returns [ViewInteractionDelegate] as a result of the invocation.
     * @throws RuntimeException the exception is thrown if you are trying to overwrite the set factory.
     */
    @Throws(RuntimeException::class)
    fun initViewInteractionDelegateFactory(
            factory: (ViewInteraction) -> ViewInteractionDelegate
    ) {
        viewInteractionDelegateFactory?.let {
            throw RuntimeException("Trying to re-init view interaction delegate factory")
        } ?: let { viewInteractionDelegateFactory = factory }
    }

    /**
     * Setter for [dataInteractionDelegateFactory]. Allows you to set the value only once.
     * If [dataInteractionDelegateFactory] is null, you can set your custom factory,
     * but if it is already set, you can not overwrite it.
     *
     * @param factory the function that returns [DataInteractionDelegate] as a result of the invocation.
     * @throws RuntimeException the exception is thrown if you are trying to overwrite the set factory.
     */
    @Throws(RuntimeException::class)
    fun initDataInteractionDelegateFactory(
            factory: (DataInteraction) -> DataInteractionDelegate
    ) {
        dataInteractionDelegateFactory?.let {
            throw RuntimeException("Trying to re-init data interaction delegate factory")
        } ?: let { dataInteractionDelegateFactory = factory }
    }

    /**
     * Setter for [webInteractionDelegateFactory]. Allows you to set the value only once.
     * If [webInteractionDelegateFactory] is null, you can set your custom factory,
     * but if it is already set, you can not overwrite it.
     *
     * @param factory the function that returns [WebInteractionDelegate] as a result of the invocation.
     * @throws RuntimeException the exception is thrown if you are trying to overwrite the set factory.
     */
    @Throws(RuntimeException::class)
    fun initWebInteractionDelegateFactory(
            factory: (Web.WebInteraction<*>) -> WebInteractionDelegate
    ) {
        webInteractionDelegateFactory?.let {
            throw RuntimeException("Trying to re-init web interaction delegate factory")
        } ?: let { webInteractionDelegateFactory = factory }
    }

    /**
     * Creates an instance of [ViewInteractionDelegate]. If [viewInteractionDelegateFactory] is null, returns
     * stub implementation, that repeats the [ViewInteraction] behavior.
     *
     * @param viewInteraction the view interaction to be wrapped.
     * @return [ViewInteractionDelegate] the wrapper of [viewInteraction].
     */
    fun createViewInteractionDelegate(
            viewInteraction: ViewInteraction
    ): ViewInteractionDelegate {
        return viewInteractionDelegateFactory
                ?.invoke(viewInteraction)
                ?: ViewInteractionDelegateEmptyImpl(viewInteraction)
    }

    /**
     * Creates an instance of [DataInteractionDelegate]. If [dataInteractionDelegateFactory] is null, returns
     * stub implementation, that repeats the [DataInteraction] behavior.
     *
     * @param dataInteraction the data interaction to be wrapped.
     * @return [DataInteractionDelegate] the wrapper of [dataInteraction].
     */
    fun createDataInteractionDelegate(
            dataInteraction: DataInteraction
    ): DataInteractionDelegate {
        return dataInteractionDelegateFactory
                ?.invoke(dataInteraction)
                ?: DataInteractionDelegateEmptyImpl(dataInteraction)
    }

    /**
     * Creates an instance of [WebInteractionDelegate]. If [webInteractionDelegateFactory] is null, returns
     * stub implementation, that repeats the [Web.WebInteraction] behavior.
     *
     * @param webInteraction the web interaction to be wrapped.
     * @return [WebInteractionDelegate] the wrapper of [webInteraction].
     */
    fun createWebInteractionDelegate(
            webInteraction: Web.WebInteraction<*>
    ): WebInteractionDelegate {
        return webInteractionDelegateFactory
                ?.invoke(webInteraction)
                ?: WebInteractionDelegateEmptyImpl(webInteraction)
    }
}