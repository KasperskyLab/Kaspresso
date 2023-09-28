package com.kaspersky.kaspresso.tutorial.differenttypes.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

private const val TAG = "SwipeMenuRecyclerView"

/**
 * Created by Aitsuki on 2017/2/23.
 * Swipe library for recycler view menus
 * Note: See for details https://github.com/aitsuki/SwipeMenuRecyclerView
 */
internal open class SwipeMenuRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : RecyclerView(context, attrs) {

    private val rect = Rect()
    private val touchSlop = ViewConfiguration.get(context).scaledTouchSlop

    private var initMotionX = FloatArray(1)
    private var initMotionY = FloatArray(1)
    private var cancelUp = false
    private var pointersDown = 0
    private var pastSlopPointerId = MotionEvent.INVALID_POINTER_ID

    fun closeMenus() {
        for (openItem in findOpenItems()) {
            findSwipeLayout(openItem)?.closeMenu(true)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                pastSlopPointerId = MotionEvent.INVALID_POINTER_ID
                val pointerId = ev.getPointerId(0)
                saveInitialMotion(ev.x, ev.y, pointerId)
                val touchItem = getTouchItem(ev.x.toInt(), ev.y.toInt())
                for (openItem in findOpenItems()) {
                    if (openItem != touchItem) {
                        findSwipeLayout(openItem)?.closeMenu(true)
                        cancelUp = true
                    }
                }
            }

            MotionEvent.ACTION_POINTER_DOWN -> {
                val x = ev.getX(ev.actionIndex)
                val y = ev.getY(ev.actionIndex)
                saveInitialMotion(x, y, ev.getPointerId(ev.actionIndex))
                if (findOpenItems().isNotEmpty()) {
                    return false
                }
            }

            MotionEvent.ACTION_MOVE -> {
                if (initMotionX.isNotEmpty()) {
                    for (i in 0 until ev.pointerCount) {
                        val pointerId = ev.getPointerId(i)
                        if (!isValidPointerForActionMove(pointerId)) continue

                        val x = ev.getX(i)
                        val y = ev.getY(i)
                        val dx = x - initMotionX[pointerId]
                        val dy = y - initMotionY[pointerId]

                        if (dx * dx + dy * dy > touchSlop * touchSlop) {
                            cancelUp = false
                        }

                        if (pastSlopPointerId == MotionEvent.INVALID_POINTER_ID) {
                            val touchItem = getTouchItem(x.toInt(), y.toInt())
                            val swipeLayout = if (touchItem != null) {
                                findSwipeLayout(touchItem)
                            } else null

                            if (swipeLayout != null && swipeLayout.swipeEnable
                                && abs(dx) > touchSlop && abs(dx) > abs(dy)
                            ) {
                                pastSlopPointerId = pointerId
                            }
                        } else if (pastSlopPointerId != MotionEvent.INVALID_POINTER_ID
                            && pastSlopPointerId != pointerId
                        ) {
                            ev.action = MotionEvent.ACTION_CANCEL
                        }
                    }
                }
            }

            MotionEvent.ACTION_POINTER_UP -> {
                clearMotionHistory(ev.getPointerId(ev.actionIndex))
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                clearMotionHistory()
                if (cancelUp) {
                    ev.action = MotionEvent.ACTION_CANCEL
                    cancelUp = false
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun saveInitialMotion(x: Float, y: Float, pointerId: Int) {
        ensureMotionHistorySizeForId(pointerId)
        initMotionX[pointerId] = x
        initMotionY[pointerId] = y
        pointersDown = pointersDown or (1 shl pointerId)
    }

    private fun ensureMotionHistorySizeForId(pointerId: Int) {
        if (initMotionX.size <= pointerId) {
            val imx = FloatArray(pointerId + 1)
            val imy = FloatArray(pointerId + 1)
            initMotionX = initMotionX.copyInto(imx)
            initMotionY = initMotionY.copyInto(imy)
        }
    }

    private fun isValidPointerForActionMove(pointerId: Int): Boolean {
        if (!isPointerDown(pointerId)) {
            Log.e(
                TAG,
                "Ignoring pointerId=$pointerId because ACTION_DOWN was not received "
                        + "for this pointer before ACTION_MOVE. It likely happened because "
                        + " SwipeMenuRecyclerView did not receive all the events in the event stream."
            )
            return false
        }
        return true
    }

    private fun isPointerDown(pointerId: Int): Boolean {
        return (pointersDown and (1 shl pointerId)) != 0
    }

    private fun clearMotionHistory(pointerId: Int) {
        if (initMotionX.isEmpty() || !isPointerDown(pointerId)) {
            return
        }
        initMotionX[pointerId] = 0f
        initMotionY[pointerId] = 0f
        pointersDown = pointersDown and (1 shl pointerId).inv()
    }

    private fun clearMotionHistory() {
        if (initMotionX.isEmpty()) {
            return
        }
        initMotionX.fill(0f)
        initMotionY.fill(0f)
        pointersDown = 0
    }

    private fun getTouchItem(x: Int, y: Int): View? {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility == VISIBLE) {
                child.getHitRect(rect)
                if (rect.contains(x, y)) {
                    return child
                }
            }
        }
        return null
    }

    private fun findOpenItems(): List<View> {
        val openItems = arrayListOf<View>()
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val swipeLayout = findSwipeLayout(child)
            if (swipeLayout != null && swipeLayout.onScreen > 0f) {
                openItems.add(child)
            }
        }
        return openItems
    }

    private fun findSwipeLayout(view: View): SwipeLayout? {
        if (view is SwipeLayout) {
            return view
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                return findSwipeLayout(view.getChildAt(i))
            }
        }
        return null
    }
}
