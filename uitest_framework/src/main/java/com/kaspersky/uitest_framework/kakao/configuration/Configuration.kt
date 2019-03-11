package com.kaspersky.uitest_framework.kakao.configuration

import android.support.test.espresso.*
import android.support.test.espresso.web.assertion.WebAssertion
import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.ElementReference
import android.support.test.espresso.web.sugar.Web
import android.view.View
import com.kaspersky.uitest_framework.kakao.delegates.DataInteractionDelegate
import com.kaspersky.uitest_framework.kakao.delegates.ViewInteractionDelegate
import com.kaspersky.uitest_framework.kakao.delegates.WebInteractionDelegate
import org.hamcrest.Matcher

object Configuration {

    var viewInteractionDelegateFactory: (ViewInteraction) -> ViewInteractionDelegate = {

        object : ViewInteractionDelegate {

            override val viewInteraction: ViewInteraction = it

            override fun perform(viewAction: ViewAction): ViewInteractionDelegate {
                viewInteraction.perform(viewAction)
                return this
            }

            override fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate {
                viewInteraction.check(viewAssertion)
                return this
            }

            override fun check(
                    function: (View, NoMatchingViewException) -> Unit
            ): ViewInteractionDelegate {
                viewInteraction.check(function)
                return this
            }

            override fun withFailureHandler(
                    function: (Throwable, Matcher<View>) -> Unit
            ): ViewInteractionDelegate {
                viewInteraction.withFailureHandler(function)
                return this
            }

            override fun inRoot(rootMatcher: Matcher<Root>): ViewInteractionDelegate {
                viewInteraction.inRoot(rootMatcher)
                return this
            }
        }
    }

    var dataInteractionDelegateFactory: (DataInteraction) -> DataInteractionDelegate = {

        object : DataInteractionDelegate {

            override val dataInteraction: DataInteraction = it

            override fun onChildView(childMatcher: Matcher<View>): DataInteractionDelegate {
                dataInteraction.onChildView(childMatcher)
                return this
            }

            override fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate {
                return DelegatesFactory.createViewInteractionDelegate(
                        dataInteraction.check(viewAssertion)
                )
            }
        }
    }

    var webInteractionDelegateFactory: (Web.WebInteraction<*>) -> WebInteractionDelegate = {

        object : WebInteractionDelegate {

            override val webInteraction: Web.WebInteraction<*> = it

            override fun withElement(ref: Atom<ElementReference>): WebInteractionDelegate {
                return DelegatesFactory.createWebInteractionDelegate(
                        webInteraction.withElement(ref)
                )
            }

            override fun perform(webAction: Atom<*>): WebInteractionDelegate {
                return DelegatesFactory.createWebInteractionDelegate(
                        webInteraction.perform(webAction)
                )
            }

            override fun <E> check(
                    webAssertion: WebAssertion<E>,
                    atom: Atom<E>,
                    matcher: Matcher<E>
            ): WebInteractionDelegate {
                return DelegatesFactory.createWebInteractionDelegate(
                        webInteraction.check(webAssertion)
                )
            }
        }
    }
}

object DelegatesFactory {

    fun createViewInteractionDelegate(
            viewInteraction: ViewInteraction
    ): ViewInteractionDelegate {
        return Configuration.viewInteractionDelegateFactory.invoke(viewInteraction)
    }

    fun createDataInteractionDelegate(
            dataInteraction: DataInteraction
    ): DataInteractionDelegate {
        return Configuration.dataInteractionDelegateFactory.invoke(dataInteraction)
    }

    fun createWebInteractionDelegate(
            webInteraction: Web.WebInteraction<*>
    ): WebInteractionDelegate {
        return Configuration.webInteractionDelegateFactory.invoke(webInteraction)
    }
}