package com.tobiasschuerg.prefixpostfix;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

// This is for the prefix.
// It is a drawable for rendering text
public class TagDrawable extends Drawable {

    private PrefixSuffixEditText extendedEditText;
    public String text = "";

    public TagDrawable(PrefixSuffixEditText extendedEditText) {
        this.extendedEditText = extendedEditText;
    }

    public void setText(String s) {
        text = s;

        // Tell it we need to be as big as we want to be!
        setBounds(0, 0, getIntrinsicWidth(), getIntrinsicHeight());

        invalidateSelf();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        // I don't know why this y works here, but it does :)
        // (aka if you are from Google/are Jake Wharton and I have done it wrong, please tell me!)
        canvas.drawText(text, 0, extendedEditText.mLine0Baseline + canvas.getClipBounds().top, extendedEditText.mTextPaint);
    }

    @Override
    public void setAlpha(int i) {
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) extendedEditText.mFontHeight;
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) extendedEditText.mTextPaint.measureText(text);
    }
}
