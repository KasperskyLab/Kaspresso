package com.kaspersky.uitest_framework.kakao.interceptors

import android.support.test.espresso.ViewAction
import android.view.View

/**
 * Created by egor.kurnikov on 03.03.2019
 */

interface ViewActionInterceptor {

    fun intercept(viewAction: ViewAction, view: View)
}