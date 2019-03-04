package com.kaspersky.uitest_framework.logger

import android.util.Log

/**
 * Created by egor.kurnikov on 03.03.2019
 */

object DefaultUiTestLogger: UiTestLogger {

    private const val DEFAULT_TAG: String = "UI_TESTING"

    override fun i(text: String) {
        Log.i(DEFAULT_TAG, text)
    }

    override fun d(text: String) {
        Log.d(DEFAULT_TAG, text)
    }

    override fun e(text: String) {
        Log.e(DEFAULT_TAG, text)
    }

    fun i(tag: String, text: String) {
        Log.i(tag, text)
    }

    fun d(tag: String, text: String) {
        Log.d(tag, text)
    }

    fun e(tag: String, text: String) {
        Log.e(tag, text)
    }
}