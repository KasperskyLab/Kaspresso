package com.kaspersky.kaspresso.params

import io.github.kakaocup.kakao.common.actions.clicks.ClickAction
import io.github.kakaocup.kakao.common.actions.clicks.EspressoDoubleClick
import io.github.kakaocup.kakao.common.actions.clicks.EspressoLongClick
import io.github.kakaocup.kakao.common.actions.clicks.EspressoSingleClick
import io.github.kakaocup.kakao.ext.clicks.KakaoDoubleClick
import io.github.kakaocup.kakao.ext.clicks.KakaoLongClick
import io.github.kakaocup.kakao.ext.clicks.KakaoSingleClick
import io.github.kakaocup.kakao.ext.clicks.visualization.VisualClicksConfig

/**
 * This class provides the possibility to override default espresso clicks.
 * All details are described in https://github.com/KakaoCup/Kakao/tree/master/kakao-ext-clicks
 */
data class ClickParams(
    val singleClickAction: ClickAction,
    val doubleClickAction: ClickAction,
    val longClickAction: ClickAction,
) {

    companion object {
        /**
         * Default Espresso clicks
         */
        fun default() = ClickParams(
            singleClickAction = EspressoSingleClick(),
            doubleClickAction = EspressoDoubleClick(),
            longClickAction = EspressoLongClick()
        )

        /**
         * Custom Kakao clicks
         */
        fun kakao() = ClickParams(
            singleClickAction = KakaoSingleClick(),
            doubleClickAction = KakaoDoubleClick(),
            longClickAction = KakaoLongClick()
        )

        /**
         * Custom Kakao clicks with visualisation
         */
        fun kakaoVisual() = ClickParams(
            singleClickAction = KakaoSingleClick(VisualClicksConfig()),
            doubleClickAction = KakaoDoubleClick(VisualClicksConfig()),
            longClickAction = KakaoLongClick(VisualClicksConfig())
        )

        /**
         * Custom clicks
         */
        fun customise(
            singleClickAction: ClickAction,
            doubleClickAction: ClickAction,
            longClickAction: ClickAction
        ) = ClickParams(
            singleClickAction = singleClickAction,
            doubleClickAction = doubleClickAction,
            longClickAction = longClickAction
        )
    }
}
