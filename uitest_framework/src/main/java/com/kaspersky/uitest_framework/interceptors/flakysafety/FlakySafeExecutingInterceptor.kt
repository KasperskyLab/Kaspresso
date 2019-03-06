package com.kaspersky.uitest_framework.interceptors.flakysafety

import com.kaspersky.uitest_framework.device.Device
import com.kaspersky.uitest_framework.kakao.interceptors.ExecutingInterceptor

/**
 * Created by egor.kurnikov on 05.03.2019
 */

class FlakySafeExecutingInterceptor: ExecutingInterceptor {

    override fun interceptAndExecute(function: () -> Unit) = Device.attempt { function.invoke() }
}