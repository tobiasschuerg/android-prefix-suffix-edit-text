package com.tobiasschuerg.prefixpostfix

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import kotlin.properties.Delegates

// This is for the prefix.
// It is a drawable for rendering text
internal class PrefixDrawable(
        var paint: Paint,
        var lineBounds: Int = 0
) : Drawable() {

    var text: String? by Delegates.observable("") { _, oldValue: String?, newValue: String? ->
        // Tell it we need to be as big as we want to be!
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        invalidateSelf()
    }


    override fun draw(canvas: Canvas) {
        // I don't know why this y works here, but it does :)
        // (aka if you are from Google/are Jake Wharton and I have done it wrong, please tell me!)
        val y = (lineBounds + canvas.clipBounds.top).toFloat()
        canvas.drawText(text, 0f, y, paint)
    }

    override fun setAlpha(i: Int) {}

    override fun setColorFilter(colorFilter: ColorFilter?) {}

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun getIntrinsicHeight(): Int {
        return paint.textSize.toInt()
    }

    override fun getIntrinsicWidth(): Int {
        return paint.measureText(text).toInt()
    }
}
