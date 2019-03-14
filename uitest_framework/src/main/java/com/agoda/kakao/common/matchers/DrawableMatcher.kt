@file:Suppress("unused")

package com.agoda.kakao.common.matchers

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

/**
 * Matches given drawable with current one
 *
 * @param resId Drawable resource to be matched (default is -1)
 * @param drawable Drawable instance to be matched (default is null)
 * @param toBitmap Lambda with custom Drawable -> Bitmap converter (default is null)
 */
class DrawableMatcher(@DrawableRes private val resId: Int = -1, private val drawable: Drawable? = null,
                      private val toBitmap: ((drawable: Drawable) -> Bitmap)? = null)
    : TypeSafeMatcher<View>(View::class.java) {

    override fun describeTo(desc: Description) {
        desc.appendText("with drawable id $resId or provided instance")
    }

    override fun matchesSafely(view: View?): Boolean {
        if (view !is ImageView && drawable == null) {
            return false
        }

        if (resId < 0 && drawable == null) {
            return (view as ImageView).drawable == null
        }

        return view?.let {
            var expectedDrawable: Drawable? = ContextCompat.getDrawable(it.context, resId)

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && expectedDrawable != null) {
                expectedDrawable = DrawableCompat.wrap(expectedDrawable).mutate()
            }

            if (expectedDrawable == null) {
                return false
            }

            val convertDrawable = drawable ?: (it as ImageView).drawable
            val bitmap = toBitmap?.invoke(convertDrawable) ?: drawableToBitmap(convertDrawable)
            val otherBitmap = toBitmap?.invoke(expectedDrawable) ?: drawableToBitmap(expectedDrawable)

            return bitmap.sameAs(otherBitmap)
        } ?: false
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            if (drawable.bitmap != null) {
                return drawable.bitmap
            }
        }

        if (drawable is StateListDrawable) {
            if (drawable.getCurrent() is BitmapDrawable) {
                val bitmapDrawable = drawable.getCurrent() as BitmapDrawable
                if (bitmapDrawable.bitmap != null) {
                    return bitmapDrawable.bitmap
                }
            }
        }

        val bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) // Single color bitmap will be created of 1x1 pixel
        } else {
            Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        }

        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}