package com.kaspersky.uitest_framework.kakao.interceptors

/**
 * Created by egor.kurnikov on 05.03.2019
 */

interface ExecutingInterceptor {

    fun interceptAndExecute(function: () -> Unit)
}