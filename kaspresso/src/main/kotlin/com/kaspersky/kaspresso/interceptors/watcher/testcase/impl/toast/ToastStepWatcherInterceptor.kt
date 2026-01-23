package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.toast

import android.content.Context
import android.widget.Toast
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.StepInfo
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Experimental
class ToastStepWatcherInterceptor(
    private val context: Context,
    private val toastContent: ToastContent = ToastContent.DESCRIPTION,
) : StepWatcherInterceptor {
    override fun interceptBefore(stepInfo: StepInfo) {
        when (toastContent) {
            ToastContent.NUMBER -> toast("Step #${getNumber(stepInfo)}")
            ToastContent.DESCRIPTION -> toast(stepInfo.description)
            ToastContent.FULL_INFO -> toast(
                "${getNumber(stepInfo)} ${stepInfo.description}"
            )
        }
    }

    private fun toast(content: CharSequence) = Toast.makeText(context.applicationContext, content, Toast.LENGTH_SHORT).show()

    private fun getNumber(stepInfo: StepInfo) = stepInfo.number ?: stepInfo.ordinal.toString()

    enum class ToastContent {
        DESCRIPTION,
        NUMBER,
        FULL_INFO
    }
}
