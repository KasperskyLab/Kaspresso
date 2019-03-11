package com.kaspersky.uitest_framework.interceptors

import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.web.sugar.Web

interface ExecutingInterceptor {

    fun interceptAndExecute(function: () -> ViewInteraction): ViewInteraction

    fun interceptAndExecuteWeb(function: () -> Web.WebInteraction<*>): Web.WebInteraction<*>
}