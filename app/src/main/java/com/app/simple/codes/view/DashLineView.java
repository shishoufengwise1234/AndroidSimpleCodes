package com.app.simple.codes.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.app.simple.codes.R;

/**
 * Created by shishoufeng on 2020-05-18.
 * email:shishoufeng1227@126.com
 * <p>
 * 虚线view
 */
public class DashLineView extends View {

    private static final String TAG = "DashLineView";

    private Paint mPaint;
    private Path mPath;

    @ColorInt
    private int lineColor = Color.BLACK;

    private float lineWidth;


    public DashLineView(Context context) {
        this(context, null);
    }

    public DashLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = null;
        try {
            array = context.obtainStyledAttributes(attrs, R.styleable.DashLineView);

            lineColor = array.getColor(R.styleable.DashLineView_line_color, Color.BLACK);
            lineWidth = array.getDimensionPixelSize(R.styleable.DashLineView_line_width, (int) dp2px(3));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(lineWidth);
        mPaint.setColor(lineColor);

        PathEffect pathEffect = new DashPathEffect(new float[]{5, 8, 5, 8},0 );
        mPaint.setPathEffect(pathEffect);

        mPath = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerY = getHeight() / 2;

        mPath.reset();

        mPath.moveTo(0,centerY);
        mPath.lineTo(getWidth(),centerY);

        canvas.drawPath(mPath,mPaint);
    }

    public void setLineColor(@ColorInt int lineColor) {
        this.lineColor = lineColor;

        this.invalidate();
    }

    private float dp2px(float dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                Resources.getSystem().getDisplayMetrics());
    }


}
