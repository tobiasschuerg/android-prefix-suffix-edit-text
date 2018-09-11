package com.tobiasschuerg.prefixpostfix

import android.content.Context
import android.graphics.*
import android.support.v7.widget.AppCompatEditText
import android.text.TextPaint
import android.util.AttributeSet

/**
 * Inspired by https://gist.github.com/morristech/5480419
 */
class PrefixSuffixEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    private val debugPaint: Paint

    // Stuff to do with our rendering
    var mTextPaint: TextPaint? = TextPaint()
    var mFontHeight: Float = 0.toFloat()
    var left: TagDrawable = TagDrawable(this)

    // The actual suffix
    private var mSuffix = ""

    // These are used to store details obtained from the EditText's rendering process
    private var line0bounds = Rect()
    var mLine0Baseline: Int = 0

    init {

        mFontHeight = textSize

        mTextPaint?.color = currentHintTextColor
        mTextPaint?.textSize = mFontHeight
        mTextPaint?.textAlign = Paint.Align.LEFT
        mTextPaint?.isAntiAlias = true

        debugPaint = Paint()
        debugPaint.style = Paint.Style.STROKE
        debugPaint.color = Color.BLUE


        // Setup the left side
        setCompoundDrawablesRelative(left, null, null, null)
    }

    override fun setTypeface(typeface: Typeface) {
        super.setTypeface(typeface)

        // Sometimes TextView itself calls me when i'm naked
        mTextPaint?.typeface = typeface

        postInvalidate()
    }

    fun setPrefix(s: String) {
        left.setText(s)
        setCompoundDrawablesRelative(left, null, null, null)
    }

    fun setSuffix(s: String) {
        mSuffix = s
        setCompoundDrawablesRelative(left, null, null, null)
    }

    public override fun onDraw(c: Canvas) {
        mLine0Baseline = getLineBounds(0, line0bounds)

        super.onDraw(c)

        // Now we can calculate what we need!
        val text = text.toString()
        val textWidth: Float = (mTextPaint?.measureText(left.text + text) ?: 0f) + paddingLeft


        // We need to draw this like this because
        // setting a right drawable doesn't work properly and we want this
        // just after the text we are editing (but untouchable)
        val y2 = line0bounds.bottom - (mTextPaint?.descent() ?: 0f)
        c.drawText(mSuffix, textWidth, y2, mTextPaint)
    }

}
