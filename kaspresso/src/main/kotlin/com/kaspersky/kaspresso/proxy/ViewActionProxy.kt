package com.kaspersky.kaspresso.proxy

import android.view.View
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import com.kaspersky.kaspresso.interceptors.ViewActionInterceptor
import org.hamcrest.Matcher

/**
 * A proxy-wrapper of [ViewAction] for interceptors calls.
 */
class ViewActionProxy(
    private val viewAction: ViewAction,
    private val interceptors: List<ViewActionInterceptor>
) : ViewAction {

    /**
     * Simply calls [ViewAction.getDescription] on wrapped [viewAction].
     *
     * @return a description of the view action.
     */
    override fun getDescription(): String = viewAction.description

    /**
     * Simply calls [ViewAction.getConstraints] on wrapped [viewAction].
     *
     * @return a matcher that will be tested prior to calling perform
     */
    override fun getConstraints(): Matcher<View> = viewAction.constraints

    /**
     * Calls interceptors before [ViewAction.perform] on wrapped [viewAction] is called.
     *
     * @param uiController the controller to use to interact with the UI.
     * @param view the view to act upon. never null.
     */
    override fun perform(uiController: UiController, view: View) {
        interceptors.forEach { it.intercept(viewAction, view) }
        viewAction.perform(uiController, view)
    }
}