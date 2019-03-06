package com.kaspersky.uitest_framework.kakao.proxy

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.view.View
import com.kaspersky.uitest_framework.kakao.interceptors.ExecutingInterceptor
import com.kaspersky.uitest_framework.kakao.interceptors.ViewActionInterceptor
import org.hamcrest.Matcher

/**
 * Created by egor.kurnikov on 03.03.2019
 */

class ViewActionProxy(
        private val viewAction: ViewAction,
        private val interceptors: List<ViewActionInterceptor>,
        private val executingInterceptor: ExecutingInterceptor?
) : ViewAction {

    override fun getDescription(): String = viewAction.description

    override fun getConstraints(): Matcher<View> = viewAction.constraints

    override fun perform(uiController: UiController, view: View) {

        interceptors.forEach { viewActionInterceptor ->
            viewActionInterceptor.intercept(viewAction, view)
        }

        val actionToExecute = { viewAction.perform(uiController, view) }

        executingInterceptor?.interceptAndExecute(actionToExecute) ?: actionToExecute.invoke()
    }
}