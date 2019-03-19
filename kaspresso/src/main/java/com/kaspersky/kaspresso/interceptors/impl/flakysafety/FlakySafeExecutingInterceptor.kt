package com.kaspersky.kaspresso.interceptors.impl.flakysafety

import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.attempting.attempt
import com.kaspersky.kaspresso.interceptors.ExecutingInterceptor

class FlakySafeExecutingInterceptor : ExecutingInterceptor {

    override fun interceptAndExecute(
        function: () -> ViewInteraction
    ): ViewInteraction = attempt { function.invoke() }

    override fun interceptAndExecuteWeb(
        function: () -> Web.WebInteraction<*>
    ): Web.WebInteraction<*> = attempt { function.invoke() }
}