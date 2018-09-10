package com.tobiasschuerg.prefixpostfix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextPaint;
import android.util.AttributeSet;

/**
 * Inspired by https://gist.github.com/morristech/5480419
 */
public class PrefixSuffixEditText extends AppCompatEditText {

    private final Paint debugPaint;

    // Stuff to do with our rendering
    TextPaint mTextPaint = new TextPaint();
    float       mFontHeight;
    TagDrawable left;

    // The actual suffix
    String mSuffix = "";

    // These are used to store details obtained from the EditText's rendering process
    Rect line0bounds = new Rect();
    int mLine0Baseline;

    public PrefixSuffixEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        left = new TagDrawable(this);

        mFontHeight = getTextSize();

        mTextPaint.setColor(getCurrentHintTextColor());
        mTextPaint.setTextSize(mFontHeight);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.setAntiAlias(true);

        debugPaint = new Paint();
        debugPaint.setStyle(Paint.Style.STROKE);
        debugPaint.setColor(Color.BLUE);


        // Setup the left side
        setCompoundDrawablesRelative(left, null, null, null);
    }

    @Override
    public void setTypeface(Typeface typeface) {
        super.setTypeface(typeface);
        if (mTextPaint != null) {
            // Sometimes TextView itself calls me when i'm naked
            mTextPaint.setTypeface(typeface);
        }

        postInvalidate();
    }

    public void setPrefix(String s) {
        left.setText(s);
        setCompoundDrawablesRelative(left, null, null, null);
    }

    public void setSuffix(String s) {
        mSuffix = s;
        setCompoundDrawablesRelative(left, null, null, null);
    }

    @Override
    public void onDraw(Canvas c) {
        mLine0Baseline = getLineBounds(0, line0bounds);

        super.onDraw(c);

        // Now we can calculate what we need!
        String text = getText().toString();
        float textWidth = mTextPaint.measureText(left.text + text) + getPaddingLeft();


        c.drawRect(line0bounds, debugPaint);

        // We need to draw this like this because
        // setting a right drawable doesn't work properly and we want this
        // just after the text we are editing (but untouchable)
        float y2 = line0bounds.bottom - mTextPaint.descent();
        c.drawText(mSuffix, textWidth, y2, mTextPaint);
    }

}
