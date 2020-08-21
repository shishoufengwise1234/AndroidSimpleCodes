package com.app.simple.codes.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by shishoufeng on 2020/8/17.
 * email:shoufengshi@lagou.com
 * <p>
 * desc:
 */
public class TagSpan extends ReplacementSpan {

    private static final float PADDING = 50.0f;
    private RectF mRect;
    private int mBackgroundColor;
    private int mForegroundColor;

    public TagSpan(int backgroundColor, int foregroundColor) {
        this.mRect = new RectF();
        this.mBackgroundColor = backgroundColor;
        this.mForegroundColor = foregroundColor;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        return Math.round(paint.measureText(text, start, end) + PADDING);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        // Background
        mRect.set(x, top, x + paint.measureText(text, start, end) + PADDING, bottom);
        paint.setColor(mBackgroundColor);
        canvas.drawRect(mRect, paint);

        // Text
        paint.setColor(mForegroundColor);
        int xPos = Math.round(x + (PADDING / 2));
        int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
        canvas.drawText(text, start, end, xPos, yPos, paint);
    }


}


