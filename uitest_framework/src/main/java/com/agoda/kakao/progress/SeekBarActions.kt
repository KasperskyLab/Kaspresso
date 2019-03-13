@file:Suppress("unused")

package com.agoda.kakao.progress

import android.view.View
import android.widget.SeekBar
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.CoordinatesProvider
import android.support.test.espresso.action.GeneralSwipeAction
import android.support.test.espresso.action.Press
import android.support.test.espresso.matcher.ViewMatchers

/**
 * Provides action for SeekBar
 */
interface SeekBarActions : ProgressBarActions {
    /**
     * Drags progress to defined position.
     * Please note that this dragging is emulated via Espresso's swipe action
     * and might not be accurate, if progress max value is too high or device's
     * density is too low.
     *
     * @param number of progress to drag to
     *
     * @see GeneralSwipeAction
     */
    fun dragProgressTo(number: Int) {
        view.perform(object : ViewAction {
            override fun getDescription() = "drags progress of seek bar to: $number"

            override fun getConstraints() = ViewMatchers.isAssignableFrom(SeekBar::class.java)

            override fun perform(uiController: UiController, view: View) {
                if (view is SeekBar) {
                    view.takeIf { it.width > 0 }?.run {
                        val position = IntArray(2).apply {
                            view.getLocationOnScreen(this)
                        }

                        val realWidth = (width - paddingLeft - paddingRight).toFloat()
                        val realHeight = (height - paddingTop - paddingBottom).toFloat()

                        position[0] += paddingLeft
                        position[1] += paddingTop

                        val start = CoordinatesProvider {
                            floatArrayOf(
                                    position[0].toFloat() + realWidth / max * progress,
                                    position[1].toFloat() + realHeight / 2
                            )
                        }

                        val end = CoordinatesProvider {
                            floatArrayOf(
                                    position[0].toFloat() + realWidth / max * number,
                                    position[1].toFloat() + realHeight / 2
                            )
                        }

                        GeneralSwipeAction(PreciseSwipe, start, end, Press.PINPOINT)
                                .perform(uiController, view)
                    }
                }
            }
        })
    }
}