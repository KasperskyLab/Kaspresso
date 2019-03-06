@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.progress

import android.os.SystemClock
import android.util.Log
import android.support.test.espresso.UiController
import android.support.test.espresso.action.MotionEvents
import android.support.test.espresso.action.Swiper
import android.support.test.espresso.core.internal.deps.guava.base.Preconditions.checkElementIndex

object PreciseSwipe : Swiper {
    override fun sendSwipe(uiController: UiController, startCoordinates: FloatArray,
                           endCoordinates: FloatArray, precision: FloatArray): Swiper.Status {
        checkNotNull(uiController)
        checkNotNull(startCoordinates)
        checkNotNull(endCoordinates)
        checkNotNull(precision)

        val steps = interpolate(startCoordinates, endCoordinates, EVENT_COUNT)
        val delayBetweenMovements = DURATION_MS / steps.size

        val downEvent = MotionEvents.sendDown(uiController, startCoordinates, precision).down

        try {
            for (i in steps.indices) {
                if (!MotionEvents.sendMovement(uiController, downEvent, steps[i])) {
                    Log.e(TAG, "Injection of move event as part of the swipe failed. Sending cancel event.")
                    MotionEvents.sendCancel(uiController, downEvent)
                    return Swiper.Status.FAILURE
                }

                val desiredTime = downEvent.downTime + delayBetweenMovements * i
                val timeUntilDesired = desiredTime - SystemClock.uptimeMillis()

                if (timeUntilDesired > 10) {
                    uiController.loopMainThreadForAtLeast(timeUntilDesired)
                }
            }

            if (!MotionEvents.sendUp(uiController, downEvent, endCoordinates)) {
                Log.e(TAG, "Injection of up event as part of the swipe failed. Sending cancel event.")
                MotionEvents.sendCancel(uiController, downEvent)
                return Swiper.Status.FAILURE
            }
        } finally {
            downEvent.recycle()
        }

        return Swiper.Status.SUCCESS
    }

    private fun interpolate(start: FloatArray, end: FloatArray, steps: Int): Array<FloatArray> {
        checkElementIndex(1, start.size)
        checkElementIndex(1, end.size)

        val res = Array(steps) { FloatArray(2) }

        for (i in 1 until steps + 1) {
            res[i - 1][0] = start[0] + (end[0] - start[0]) * i / (steps + 1f)
            res[i - 1][1] = start[1] + (end[1] - start[1]) * i / (steps + 1f)
        }

        return res
    }

    private const val EVENT_COUNT = 40
    private const val DURATION_MS = 1000L
    private const val TAG = "PreciseSwipe"

}
