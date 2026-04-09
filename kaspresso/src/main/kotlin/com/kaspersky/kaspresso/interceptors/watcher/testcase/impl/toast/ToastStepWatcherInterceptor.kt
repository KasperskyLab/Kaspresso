package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.toast

import android.content.Context
import android.os.Handler
import android.widget.Toast
import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.StepInfo
import org.jetbrains.annotations.ApiStatus

/**
 * Shows a toast with step info before each step execution.
 */
@ApiStatus.Experimental
class ToastStepWatcherInterceptor(
    private val context: Context,
    private val toastContentProvider: (StepInfo) -> CharSequence = ToastContentProviders.description,
) : StepWatcherInterceptor {
    override fun interceptBefore(stepInfo: StepInfo) {
        toast(toastContentProvider.invoke(stepInfo))
    }

    private fun toast(content: CharSequence) {
        Handler(context.mainLooper).post {
            Toast.makeText(context.applicationContext, content, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * A set of default implementations of a toast content provider
     */
    object ToastContentProviders {
        val description: (StepInfo) -> CharSequence = { stepInfo ->
            stepInfo.description
        }
        val number: (StepInfo) -> CharSequence = { stepInfo ->
            "Step #${getNumber(stepInfo)}"
        }
        val fullInfo: (StepInfo) -> CharSequence = { stepInfo ->
            "${getNumber(stepInfo)} ${stepInfo.description}"
        }

        private fun getNumber(stepInfo: StepInfo) = stepInfo.number ?: stepInfo.ordinal.toString()
    }
}
