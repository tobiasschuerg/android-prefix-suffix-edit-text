package com.tobiasschuerg.prefixpostfix

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.support.v7.widget.AppCompatEditText
import android.text.TextPaint
import android.util.AttributeSet

/**
 * Inspired by https://gist.github.com/morristech/5480419
 */
class PrefixSuffixEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    val textPaint: TextPaint by lazy {
        TextPaint().apply {
            color = currentHintTextColor
            textAlign = Paint.Align.LEFT
            isAntiAlias = true
            this.typeface = typeface
        }
    }

    private val prefixDrawable: TagDrawable by lazy { TagDrawable(this) }

    var prefix: String? = null
        set(value) {
            field = value
            prefixDrawable.setText(value)
            setCompoundDrawablesRelative(prefixDrawable, null, null, null)
        }

    var suffix: String? = null
        set(value) {
            field = value
            setCompoundDrawablesRelative(prefixDrawable, null, null, null)
        }

    // These are used to store details obtained from the EditText's rendering process
    private var line0bounds = Rect()
    var mLine0Baseline: Int = 0

    private var isInitialized = false

    init {
        textPaint.textSize = textSize

        // Setup the prefixDrawable side
        setCompoundDrawablesRelative(prefixDrawable, null, null, null)
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
        mLine0Baseline = getLineBounds(0, line0bounds)

        super.onDraw(c)

        // Now we can calculate what we need!
        val text = text.toString()
        val textWidth: Float = textPaint.measureText(prefixDrawable.text + text) + paddingLeft

        if (suffix != null) {
            // We need to draw this like this because
            // setting a right drawable doesn't work properly and we want this
            // just after the text we are editing (but untouchable)
            val y2 = line0bounds.bottom - textPaint.descent()
            c.drawText(suffix, textWidth, y2, textPaint)
        }
    }

}
