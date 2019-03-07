package com.kaspersky.uitest_framework.kakao.interceptors

interface ExecutingInterceptor {

    fun interceptAndExecute(function: () -> Unit)
}