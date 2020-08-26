package com.app.simple.codes.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.app.simple.codes.R;
import com.app.simple.codes.utils.AlignTopImageSpan;
import com.app.simple.codes.utils.ScreenUtils;
import com.app.simple.codes.utils.TagSpan;
import com.app.simple.codes.view.VerticalCenterImageSpan;

/**
 * Created by shishoufeng on 2020/8/12.
 * email:shoufengshi@lagou.com
 * <p>
 * desc:
 */
public class TextViewActivity extends AppCompatActivity {

    private static final String TAG = "TextViewActivity";


//    String showText = ".监控滴滴代驾各项业务数据，解读异常指标，指导改进业务短板，提升业务体验参与线上，线下用户体验调研，提供高质量原型等设计方案和相关文档分析、解构产品，优化功能，提升用户体验，制定交互设计规范；";
    String showText = ".监控滴滴代驾各项业务数据，解读异常指标异常指常指标异常指";

    Context mContext;
    TextView tvText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_test);


        tvText = findViewById(R.id.tv_span_text);


        mContext = TextViewActivity.this;


//        testPaddingText();

//        setExpandableTextView();


        setMaxWeightLayoutText();
    }

    private void setMaxWeightLayoutText() {

        String text1 = "监控监控监监控监控监监控监控监监控监控监监控监控监监控监控监";
        String text2 = "·高级开";

        TextView tvCompanyName = findViewById(R.id.tv_company_name);
        TextView tvText2 = findViewById(R.id.tv_position_name);

        tvCompanyName.setText(text1);
        tvText2.setText(text2);


        TextView tv1 = findViewById(R.id.tv_text_1);
        TextView tv2 = findViewById(R.id.tv_text_2);

        tv1.setText(text1);
        tv2.setText(text2);


        for (int i = 0; i < 10; i++) {
            Fragment f = new Fragment();
            Log.d(TAG, "setMaxWeightLayoutText: f" + f + " this " + this);
        }




    }


    private void testPaddingText() {

        TextView tagsTextView = (TextView) findViewById(R.id.tagsTextView);
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();

        SpannableString tag1 = new SpannableString("9.5");
        stringBuilder.append(tag1);
        stringBuilder.setSpan(new TagSpan(getResources().getColor(R.color.colorPrimaryDark), getResources().getColor(R.color.colorAccent)), stringBuilder.length() - tag1.length(), stringBuilder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString tag2 = new SpannableString("excellent!");
        stringBuilder.append(tag2);
        stringBuilder.setSpan(new TagSpan(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent)), stringBuilder.length() - tag2.length(), stringBuilder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tagsTextView.setText(stringBuilder, TextView.BufferType.SPANNABLE);

    }

    // 计算文字超出部分做精确截取
    private String spanStr = "  (精)";
    private String text = showText + spanStr;

    private void setExpandableTextView(){
        tvText.setText(text);

        tvText.post(new Runnable() {
            @Override
            public void run() {

                Layout layout = tvText.getLayout();
                if (layout == null){
                    Log.d(TAG, "run: layout is null");
                }
                int linCount = tvText.getLineCount();

                int ellipsisCount = layout.getEllipsisCount(linCount - 1);
                Log.d(TAG, "run: linCount = "+ linCount +"= ellipsisCount = "+ellipsisCount );

                if (ellipsisCount > 0) {
                    text = text.substring(0, text.length() - ellipsisCount - 1) + "…(精)";
                }else if (linCount == 2){
                    int lastLineIndex = Math.max(0, tvText.getLineCount() - 1);

                    int end = layout.getLineEnd(lastLineIndex);
                    int start = layout.getLineStart(lastLineIndex);
                    String lastLineText = text.substring(start, end); //最后一行的内容

                    if (spanStr.contains(lastLineText)){
                        StringBuilder sb = new StringBuilder(text);
                        text = sb.insert(Math.max(0, sb.length() - spanStr.length() - 1), "\n").toString();

                        Log.d(TAG, "run: text = "+text);

                    }

                    Log.d(TAG, "run: lastLineText = "+lastLineText);
                }

                // 创建SpannableString对象
                SpannableString imageString = new SpannableString(text);

                // 获取图片资源并设置绘制边界
                Drawable image = mContext.getResources().getDrawable(R.drawable.jd_ic_ask_b);

                int left = (int) ScreenUtils.Companion.dp2px(0);
                int top = (int) ScreenUtils.Companion.dp2px(0);
                int right = (int) ScreenUtils.Companion.dp2px(16);
                int bottom = (int) ScreenUtils.Companion.dp2px(16);
                image.setBounds(left, top, right, bottom);


                // 创建图片样式对象替换占位符
                AlignTopImageSpan imageSpan = new AlignTopImageSpan(image, AlignTopImageSpan.ALIGN_CENTER);
                imageString.setSpan(imageSpan, imageString.length() - 3, imageString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

                imageString.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        Log.d(TAG, "onClick: ");

                        Toast.makeText(mContext,"click",Toast.LENGTH_SHORT).show();
                    }
                },imageString.length() - 3, imageString.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                tvText.setText(imageString);

                tvText.setMovementMethod(LinkMovementMethod.getInstance());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();



    }

    public static final String SPACE = "\t\t.";

    public static SpannableStringBuilder getSpanWithLabel(String src, Drawable drawable) {
        SpannableStringBuilder spannableString = new SpannableStringBuilder(src);
        spannableString.append(SPACE);
        ImageSpan imageSpan = new VerticalCenterImageSpan(drawable);
        spannableString.setSpan(imageSpan, spannableString.length() - 1, spannableString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return spannableString;
    }

    /**
     * 最后一行 没显示文字 仅有图片，需要掰下来一个字符
     *
     * @param textView 显示控件
     * @param src      职位名
     * @param drawable 需要提前设置好w和h
     */
    public static void setTextDrawable(TextView textView, String src, Drawable drawable) {
        textView.setText(getSpanWithLabel(src, drawable));

        Layout layout = textView.getLayout();
        String text = textView.getText().toString();

        int lastLineIndex = Math.max(0, textView.getLineCount() - 1);
        int end = layout.getLineEnd(lastLineIndex);
        int start = layout.getLineStart(lastLineIndex);
        String line = text.substring(start, end); //最后一行的内容

        if (SPACE.contains(line)) {//最后一行 没显示文字 仅有图片，需要掰下来一个字符
            StringBuilder sb = new StringBuilder(src);
            String s = sb.insert(Math.max(0, sb.length() - 1), "\n").toString();
            textView.setText(getSpanWithLabel(s, drawable));
        }
    }
}
