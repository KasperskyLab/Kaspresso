package com.kaspersky.uitest_framework.interceptors.impl.flakysafety

import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.web.sugar.Web
import com.kaspersky.uitest_framework.attempting.attempt
import com.kaspersky.uitest_framework.interceptors.ExecutingInterceptor

class FlakySafeExecutingInterceptor : ExecutingInterceptor {

    override fun interceptAndExecute(
            function: () -> ViewInteraction
    ): ViewInteraction = attempt { function.invoke() }

    override fun interceptAndExecuteWeb(
            function: () -> Web.WebInteraction<*>
    ): Web.WebInteraction<*> = attempt { function.invoke() }
}