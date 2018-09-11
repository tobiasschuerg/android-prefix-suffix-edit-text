package com.tobiasschuerg.prefixsuffix

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.os.Build
import android.support.v7.widget.AppCompatEditText
import android.text.TextPaint
import android.util.AttributeSet

/**
 * [AppCompatEditText] with easy prefix and suffix support.
 *
 * Inspired by https://gist.github.com/morristech/5480419
 */
class PrefixSuffixEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    private val textPaint: TextPaint by lazy {
        TextPaint().apply {
            color = currentHintTextColor
            textAlign = Paint.Align.LEFT
            isAntiAlias = true
            this.typeface = typeface
        }
    }

    private val prefixDrawable: PrefixDrawable by lazy { PrefixDrawable(paint) }

    var prefix: String? = null
        set(value) {
            field = value
            prefixDrawable.text = value
            updatePrefixDrawable()
        }

    var suffix: String? = null
        set(value) {
            field = value
            invalidate()
        }

    // These are used to store details obtained from the EditText's rendering process
    private val firstLineBounds = Rect()

    private var isInitialized = false

    init {
        textPaint.textSize = textSize

        updatePrefixDrawable()
        isInitialized = true
    }

    override fun setTypeface(typeface: Typeface) {
        super.setTypeface(typeface)

        if (isInitialized) {
            // this is first called from the constructor when it's not initialized, yet
            textPaint.typeface = typeface
        }

        postInvalidate()
    }

    public override fun onDraw(c: Canvas) {
        val lineBounds = getLineBounds(0, firstLineBounds)
        prefixDrawable.lineBounds = lineBounds
        prefixDrawable.paint = textPaint

        super.onDraw(c)

        // Now we can calculate what we need!
        val text = text.toString()
        val textWidth: Float = textPaint.measureText(prefixDrawable.text + text) + paddingLeft

        if (suffix != null) {
            // We need to draw this like this because
            // setting a right drawable doesn't work properly and we want this
            // just after the text we are editing (but untouchable)
            val y2 = firstLineBounds.bottom - textPaint.descent()
            c.drawText(suffix, textWidth, y2, textPaint)
        }
    }

    private fun updatePrefixDrawable() {
        if (prefix != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                setCompoundDrawablesRelative(prefixDrawable, null, null, null)
            } else {
                setCompoundDrawables(prefixDrawable, null, null, null)
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                setCompoundDrawablesRelative(null, null, null, null)
            } else {
                setCompoundDrawables(null, null, null, null)
            }
        }
    }

}
