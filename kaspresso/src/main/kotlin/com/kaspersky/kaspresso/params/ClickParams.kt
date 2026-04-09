package com.kaspersky.kaspresso.params

import io.github.kakaocup.kakao.common.actions.clicks.ClickAction
import io.github.kakaocup.kakao.common.actions.clicks.EspressoDoubleClick
import io.github.kakaocup.kakao.common.actions.clicks.EspressoLongClick
import io.github.kakaocup.kakao.common.actions.clicks.EspressoSingleClick
import io.github.kakaocup.kakao.ext.clicks.KakaoDoubleClick
import io.github.kakaocup.kakao.ext.clicks.KakaoLongClick
import io.github.kakaocup.kakao.ext.clicks.KakaoSingleClick
import io.github.kakaocup.kakao.ext.clicks.visualization.VisualClicksConfig

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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
