package com.kaspersky.uitest_framework.kakaoext

import android.support.test.espresso.assertion.ViewAssertions
import android.view.View
import com.kaspersky.uitest_framework.kakao.KBaseView
import com.kaspersky.uitest_framework.kakao.TextViewActions
import com.kaspersky.uitest_framework.kakao.TextViewAssertions
import com.kaspersky.uitest_framework.kakao.ViewBuilder
import com.kaspersky.uitest_framework.kakao.dispatchers.DataDispatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Created by egor.kurnikov on 04.03.2019
 */

open class KLBaseView<out T> : KBaseView<KLBaseView<T>>, TextViewActions {

    constructor(
            function: ViewBuilder.() -> Unit
    ) : super(configuredViewBuilder.apply(function).getViewDispatcher())

    constructor(
            parent: Matcher<View>,
            function: ViewBuilder.() -> Unit
    ) : this({
        isDescendantOfA { withMatcher(parent) }
        function(this)
    })

    constructor(
            parent: DataDispatcher,
            function: ViewBuilder.() -> Unit
    ) : super(
            parent
                    .onChildView(configuredViewBuilder.apply(function).getViewMatcher())
                    .check(ViewAssertions.matches(Matchers.anything()))
    )
}

class KLView : KLBaseView<KLView> {

    constructor(
            function: ViewBuilder.() -> Unit
    ) : super(function)

    constructor(
            parent: Matcher<View>,
            function: ViewBuilder.() -> Unit
    ) : super(parent, function)

    constructor(
            parent: DataDispatcher,
            function: ViewBuilder.() -> Unit
    ) : super(parent, function)
}

class KLButton : KLBaseView<KLButton>, TextViewAssertions {

    constructor(
            function: ViewBuilder.() -> Unit
    ) : super(function)

    constructor(
            parent: Matcher<View>,
            function: ViewBuilder.() -> Unit
    ) : super(parent, function)

    constructor(
            parent: DataDispatcher,
            function: ViewBuilder.() -> Unit
    ) : super(parent, function)
}

class KLTextView : KLBaseView<KLTextView>, TextViewActions {

    constructor(
            function: ViewBuilder.() -> Unit
    ) : super(function)

    constructor(
            parent: Matcher<View>,
            function: ViewBuilder.() -> Unit
    ) : super(parent, function)

    constructor(
            parent: DataDispatcher,
            function: ViewBuilder.() -> Unit
    ) : super(parent, function)
}