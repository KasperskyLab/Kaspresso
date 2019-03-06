package com.kaspersky.uitest_framework.kakao.dispatchers

import android.support.test.espresso.web.sugar.Web

/**
 * Created by egor.kurnikov on 04.03.2019
 */

open class WebDispatcher(
        private val webInteraction: Web.WebInteraction<*>
)