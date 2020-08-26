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
 * Created by shishoufeng on 2020/8/26.
 * email:shoufengshi@lagou.com
 * <p>
 * desc:
 */
public class WeightTextViewLayout extends LinearLayout {

    private static final String TAG = "WeightTextViewLayout";

    private int mTotalWeight;


    public WeightTextViewLayout(Context context) {
        this(context,null);
    }

    public WeightTextViewLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WeightTextViewLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(HORIZONTAL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        View child;
        WeightTextViewLayout.LayoutParams params;

        //权重总数
        mTotalWeight = getTotalWeight();

        //viewGroup宽度
        int groupWidth = MeasureSpec.getSize(widthMeasureSpec);

        //计算每一个设置权重的textView 比例
        float multiple = 1.0f;
        if (mTotalWeight != 0) {
            multiple = (groupWidth * 1.0f) / mTotalWeight;
        }

        Log.d(TAG, "onMeasure: mTotalWeight = "+mTotalWeight + " groupWidth = "+groupWidth +" multiple = "+multiple);

        //子view 剩余可用宽度
        int usedWidth = groupWidth;

        MarginLayoutParams viewParams = (MarginLayoutParams) getLayoutParams();

        usedWidth -= viewParams.leftMargin;
        usedWidth -= viewParams.rightMargin;
        usedWidth -= getPaddingLeft();
        usedWidth -= getPaddingRight();

        int count = getChildCount();

        int totalChildWidth = 0;

        Log.d(TAG, "onMeasure: usedWidth = "+usedWidth +" count = "+count);
        
        for (int i = 0; i < count; i++) {
            child = getChildAt(i);
            if (child == null || child.getVisibility() == View.GONE) {
                continue;
            }
            params = (WeightTextViewLayout.LayoutParams) child.getLayoutParams();

            final int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, usedWidth, params.width);
            final int childHeightSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom(), params.height);
            // 对其他情况多测量一次，以减去对应宽度
            child.measure(childWidthSpec, childHeightSpec);

            totalChildWidth += child.getMeasuredWidth();

            //存在设置权重


            if (params.maxWeight != 0 && i != count-1){

                int childWidth = child.getMeasuredWidth();
                int multipleWidth = (int) (multiple * params.maxWeight);

                Log.d(TAG, "onMeasure: childWidth = "+childWidth + " multipleWidth = "+multipleWidth);

                //实际测量的宽度 大于约束的宽度
                if (childWidth > multipleWidth && child instanceof TextView){
                    TextView textView = (TextView) child;

                    textView.setMaxWidth(multipleWidth);
                }

            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }



    //获取总的权重数量
    private int getTotalWeight() {
        if (getChildCount() <= 0) {
            return 0;
        }
        if (mTotalWeight > 0){
            return mTotalWeight;
        }
        int totalMaxWeight = 0;
        View child;
        WeightTextViewLayout.LayoutParams params;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            params = (LayoutParams) child.getLayoutParams();
            if (params.maxWeight != 0 && child instanceof TextView) {
                // 仅对TextView且最大权重大于0的情况做统计
                totalMaxWeight += params.maxWeight;
            }
        }
        mTotalWeight = totalMaxWeight;
        return mTotalWeight;
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


