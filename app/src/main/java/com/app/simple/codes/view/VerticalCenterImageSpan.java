package com.app.simple.codes.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 垂直居中的ImageSpan
 *
 * @author yph
 * @date 2019/3/13
 */
public class VerticalCenterImageSpan extends ImageSpan {
    /**
     * 占位标记
     */
    public static final String SPACE = "{space}";

    public VerticalCenterImageSpan(@NonNull Drawable drawable) {
        super(drawable);
    }

    public VerticalCenterImageSpan(@NonNull Context context, int resourceId) {
        super(context, resourceId);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable drawable = getDrawable();
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int transY = (y + fontMetricsInt.descent + y + fontMetricsInt.ascent) / 2 - drawable.getBounds().bottom / 2;
        canvas.save();
        canvas.translate(x, transY);
        drawable.draw(canvas);
        canvas.restore();
    }

    /**
     * 将图片注入到SpannableStringBuilder中
     */
    public static void inject(@NonNull Context context, @NonNull SpannableStringBuilder builder, @DrawableRes int resourceId) {
        builder.append(SPACE);
        ImageSpan span = new VerticalCenterImageSpan(context, resourceId);
        builder.setSpan(span, builder.length() - VerticalCenterImageSpan.SPACE.length(), builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 将图片及文字注入到SpannableStringBuilder中
     */
    public static void inject(@NonNull Context context, @NonNull SpannableStringBuilder builder, @DrawableRes int resourceId, String... strs) {
        if (strs == null || strs.length == 0) {
            return;
        }
        List<String> list = new ArrayList<>(strs.length);
        for (String str : strs) {
            if (!TextUtils.isEmpty(str)) {
                // 删除无效数据
                list.add(str);
            }
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            builder.append(list.get(i));
            if (i != size - 1) {
                inject(context, builder, resourceId);
            }
        }
    }
}
