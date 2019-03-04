package com.kaspersky.uitest_framework.interception

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.view.View
import com.kaspersky.uitest_framework.Configuration
import org.hamcrest.Matcher

/**
 * Created by egor.kurnikov on 03.03.2019
 */

class InterceptedViewAction(
        private val viewAction: ViewAction
) : ViewAction {

    override fun getDescription(): String = viewAction.description

    override fun getConstraints(): Matcher<View> = viewAction.constraints

    override fun perform(uiController: UiController, view: View) {

        Configuration.viewActionInterceptors.forEach { viewActionInterceptor ->
            viewActionInterceptor.intercept(viewAction, view)
        }

        viewAction.perform(uiController, view)
    }
}