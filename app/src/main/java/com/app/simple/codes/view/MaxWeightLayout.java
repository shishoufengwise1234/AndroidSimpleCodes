package com.app.simple.codes.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.app.simple.codes.R;


/**
 * 最大权重布局，仅对子TextView最大宽度起作用
 * <p>
 * 未设置最大权重，默认为0，不改变maxWidth
 * 设置最大权重后按比例设置maxWidth
 *
 * @author yph
 * @date 2020/1/3
 */
public class MaxWeightLayout extends LinearLayout {

    private static final String TAG = "MaxWeightLayout";

    public MaxWeightLayout(Context context) {
        this(context, null);
    }

    public MaxWeightLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View child;
        LayoutParams params;
        int totalMaxWeight = 0;
        int usedWidth = getPaddingLeft() + getPaddingRight();
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            params = (LayoutParams) child.getLayoutParams();
            usedWidth += params.leftMargin;
            usedWidth += params.rightMargin;
            if (params.maxWeight != 0 && child instanceof TextView) {
                // 仅对TextView且最大权重大于0的情况做统计
                totalMaxWeight += params.maxWeight;
            } else {
                final int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, usedWidth, params.width);
                final int childHeightSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom() + params.topMargin
                        + params.bottomMargin, params.height);
                // 对其他情况多测量一次，以减去对应宽度
                child.measure(childWidthSpec, childHeightSpec);
                usedWidth += child.getMeasuredWidth();
            }
        }

        Log.d(TAG, "onMeasure: totalMaxWeight = "+totalMaxWeight);
        Log.d(TAG, "onMeasure: usedWidth = "+usedWidth);

        int maxAvailableSize = MeasureSpec.getSize(widthMeasureSpec) - usedWidth;
//        int maxAvailableSize = usedWidth;
        maxAvailableSize = Math.max(maxAvailableSize, 0);

        Log.d(TAG, "onMeasure: maxAvailableSize = "+maxAvailableSize);

        if (totalMaxWeight != 0) {
            final float multiple = (float) maxAvailableSize / totalMaxWeight;

            Log.d(TAG, "onMeasure: multiple = "+multiple);

            for (int i = 0; i < getChildCount(); i++) {
                child = getChildAt(i);
                if (child.getVisibility() == View.GONE) {
                    continue;
                }
                params = (LayoutParams) child.getLayoutParams();
                if (params.maxWeight != 0 && child instanceof TextView) {
                    float childMaxWidth = multiple * params.maxWeight;

                    ((TextView) child).setMaxWidth((int) childMaxWidth);

                    Log.d(TAG, "onMeasure: childMaxWidth = "+childMaxWidth );
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LinearLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return new LayoutParams(lp);
    }

    @Override
    public LinearLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        int maxWeight;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            final TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.MaxWeightLayout_Layout);
            maxWeight = a.getInteger(R.styleable.MaxWeightLayout_Layout_layout_max_weight, 0);
            a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(int width, int height, float weight) {
            super(width, height, weight);
        }

        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(LinearLayout.LayoutParams source) {
            super(source);
        }
    }
}
