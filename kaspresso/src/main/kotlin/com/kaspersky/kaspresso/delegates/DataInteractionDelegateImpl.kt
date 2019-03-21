package com.kaspersky.kaspresso.delegates

import android.view.View
import android.support.annotation.CheckResult
import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy
import com.kaspersky.klkakao.delegates.DataInteractionDelegate
import com.kaspersky.klkakao.delegates.ViewInteractionDelegate
import org.hamcrest.Matcher
import javax.annotation.CheckReturnValue

open class DataInteractionDelegateImpl(
    private val dataInteraction: DataInteraction
) : DataInteractionDelegate {

    @CheckResult
    @CheckReturnValue
    override fun onChildView(childMatcher: Matcher<View>): DataInteractionDelegate {
        dataInteraction.onChildView(childMatcher)
        return this
    }

    override fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate {

        val viewAssertionProxy = ViewAssertionProxy(
            viewAssertion,
            Configurator.viewAssertionInterceptors
        )

        return ViewInteractionDelegateImpl(
            execute { dataInteraction.check(viewAssertionProxy) }
        )
    }

    private fun execute(executable: () -> ViewInteraction): ViewInteraction {

        return Configurator.executingInterceptor
            ?.interceptAndExecute { executable.invoke() }
            ?: executable.invoke()
    }
}