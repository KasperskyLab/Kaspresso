@file:Suppress("unused")

package com.agoda.kakao.bottomnav

import android.view.View
import com.agoda.kakao.common.builders.ViewBuilder
import com.agoda.kakao.common.views.KBaseView
import com.agoda.kakao.delegates.DataInteractionDelegate
import org.hamcrest.Matcher

/**
 * View for acting and asserting on BottomNavigationView
 *
 * @see BottomNavigationViewActions
 * @see BottomNavigationViewAssertions
 */
class KBottomNavigationView : KBaseView<KBottomNavigationView>,
        BottomNavigationViewActions, BottomNavigationViewAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataInteractionDelegate, function: ViewBuilder.() -> Unit) : super(parent, function)
}