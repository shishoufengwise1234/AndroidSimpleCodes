package com.app.simple.codes.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.simple.codes.R;
import com.app.simple.codes.utils.ScreenUtils;
import com.app.simple.codes.utils.SpannableStringUtils;
import com.app.simple.codes.view.MTextView;
import com.app.simple.codes.view.VerticalCenterImageSpan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shishoufeng on 2020/8/19.
 * email:shoufengshi@lagou.com
 * <p>
 * desc:
 */
public class SpanTextViewActivity extends AppCompatActivity {

    private static final String TAG = "SpanTextViewActivity";

    TextView textView;
    private MTextView mTextView;
    private TextView tvSchoolName;

    private ImageView ivTag;

//    String text = "android开发、：Java 、北swift大北Java京北Java京";
    String text = "aksdjgflakjsdglakjgdlkjalskjdflaskjsdglsakjgdlkasj";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span_text_view);


//        mTextView = (MTextView) this.findViewById(R.id.mtextview);
        textView = (TextView) this.findViewById(R.id.textview);

        ivTag = findViewById(R.id.iv_show_tag);

        tvSchoolName = findViewById(R.id.tv_school_name);

        //mTextView方式
//        Test();
//        TestNormal();



//        setSpanTextView();


        setSpanableStringUtils();

//        setSpanText();

//        testSpanReplace();


    }

    private void testSpanReplace() {

        String originText = "北京工商大学\tABCDEFGGFSDSGASDGA";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(originText);
        Drawable drawable1 = fromText("985/211", "#00B38A");
        Drawable drawable2 = fromText("海外名校", "#00B38A");
        Drawable drawable3 = fromText("知名学校", "#00B38A");


        spannableStringBuilder.setSpan(new VerticalCenterImageSpan(drawable1),7,9,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        spannableStringBuilder.setSpan(new VerticalCenterImageSpan(drawable2),10,11,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        spannableStringBuilder.setSpan(new VerticalCenterImageSpan(drawable3),10,originText.length(),Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        ivTag.setImageDrawable(drawable2);

        tvSchoolName.setText(spannableStringBuilder);



    }

    private void setSpanText() {

//        com.app.simple.codes.utils.TextUtils.getBuilder("北京工商大学")
//                .setDrawable()


        //将标签文字 转换成drawable
        String originText = "北京工商大学";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(originText);


        List<String> tagList = new ArrayList<>(Arrays.asList("985/211","海外名校","知名学校"));
        int size = tagList.size();
        List<Drawable> drawables = new ArrayList<>(size);
        String tag;

        StringBuilder spanSb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            tag = tagList.get(i);
            if (TextUtils.isEmpty(tag)) {
                continue;
            }
            Drawable drawable = fromText(tag, "#00B38A");
            drawables.add(drawable);

            spanSb.append("A");
        }

        Log.d(TAG, "setSpanText: spanSb = "+ spanSb.toString());

        spannableStringBuilder.append(spanSb);

        Log.d(TAG, "setSpanText: spannableStringBuilder = "+ spannableStringBuilder);

        int count = spanSb.length();
        for (int i = 0; i < drawables.size(); i++) {
            Drawable drawable = drawables.get(i);
            if (drawable == null) {
                continue;
            }
            ImageSpan imageSpan = new VerticalCenterImageSpan(drawable);
            int start = originText.length() + i - 1;
            int end = spannableStringBuilder.length() - count;
            Log.d(TAG, "setSpanText: start = "+start + " end = "+end + " count = "+count);

            spannableStringBuilder.setSpan(imageSpan,  start,
                    end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            count--;
        }

        ivTag.setImageDrawable(drawables.get(1));

        tvSchoolName.setText(spannableStringBuilder);

    }

    private void setSpanableStringUtils() {

//        Drawable drawable = fromText("985/211","#00B38A");

//        ivTag.setImageDrawable(drawable);

//        setTextDrawable(textView,text,drawable);

//        addTagToTextView(textView,text,"985/211","海外名校");



//        SpannableStringBuilder mSchoolNameBuilder = new SpannableStringBuilder("北京工商大学");
        SpannableStringBuilder mSchoolNameBuilder;
        //将标签文字 转换成drawable
        List<String> tagList = new ArrayList<>(Arrays.asList("985/211","海外名校","知名学校"));
        int size = tagList.size();
        List<Drawable> drawables = new ArrayList<>(size);
        String tag;
        for (int i = 0; i < size; i++) {
            tag = tagList.get(i);
            if (TextUtils.isEmpty(tag)) {
                continue;
            }
            Drawable drawable = fromText(tag, "#00B38A");
            drawables.add(drawable);
        }
        SpannableStringUtils spannableStringUtils = new SpannableStringUtils();
        spannableStringUtils.append("北京工商大学");

        //构建SpannableString
        for (int i = 0; i < drawables.size(); i++) {
            Drawable drawable = drawables.get(i);
            if (drawable == null) {
                continue;
            }
            spannableStringUtils.append(" ");
//            spannableStringUtils.setSpans(new VerticalCenterImageSpan(drawable));
            spannableStringUtils.appendImage(drawable,SpannableStringUtils.ALIGN_CENTER);
            spannableStringUtils.append(" ");
        }
//        ImageSpan imageSpan = new VerticalCenterImageSpan(drawables.get(0));
//        mSchoolNameBuilder.setSpan(imageSpan, mSchoolNameBuilder.length() -1, mSchoolNameBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        spannableStringUtils.setVerticalAlign(SpannableStringUtils.ALIGN_CENTER);

        spannableStringUtils.setVerticalAlign(SpannableStringUtils.ALIGN_CENTER);
        mSchoolNameBuilder = spannableStringUtils.create();

        tvSchoolName.setText(mSchoolNameBuilder);

//        SpannableStringBuilder builder = SpannableStringUtils.with(textView)
//                .append(text)
//                .append("\t\t")
//                .appendImage(drawable)
//                .append("\t\t")
//                .appendImage(drawable)
//                .create();
//
//        Log.d(TAG, "setSpanableStringUtils: builder = "+builder);
//
//
////        textView.setText(getSpanWithLabel(text, drawable,drawable,drawable));
////
//        textView.post(new Runnable() {
//            @Override
//            public void run() {
//                Layout layout = textView.getLayout();
//                String text = textView.getText().toString();
//
//                int lastLineIndex = Math.max(0, textView.getLineCount() - 1);
//                int end = layout.getLineEnd(lastLineIndex);
//                int start = layout.getLineStart(lastLineIndex);
//                String line = text.substring(start, end); //最后一行的内容
//
//                Log.d(TAG, "run: lastLineIndex = "+lastLineIndex + " end = "+end + " start = "+start + " line = "+line);

//                if (SPAN.contains(line)) { //最后一行 没显示文字 仅有图片，需要掰下来一个字符
//                    StringBuilder sb = new StringBuilder(text);
//                    String s = sb.insert(Math.max(0, sb.length() - 1), "\n").toString();
//
//                    Log.d(TAG, "run: s = "+s);
//
////                    SpannableStringBuilder builder2 = SpannableStringUtils.with(textView)
////                            .append(s)
////                            .appendImage(drawable)
////                            .appendImage(drawable)
////                            .create();
//
//                    textView.setText(getSpanWithLabel(s,drawable,drawable,drawable));
//                }
//            }
//        });



    }

    private void setSpanTextView() {

//        String text = "北京航空航天大学";

//        Drawable drawable = fromText("985/211","#00B38A");
//
//        ivTag.setImageDrawable(drawable);
//
//        setTextDrawable(textView,text,drawable);

        addTagToTextView(textView,text,"985/211","海外名校");


    }


    private void Test() {
        mTextView.setBackgroundColor(Color.GREEN);
        String source = "撒反对飞王瑞芳芳vfxdsdf司法所我日 35忍32534太 地方个的服务 34个的服务 34太过分的电饭锅电饭锅打三国杀个的服务 34太过分的电饭锅电饭锅打三国杀太过分的电饭锅电饭锅打三国杀水电费歌曲筒袜上课5乳房炎啊啊。";
        SpannableString ss = new SpannableString(source);


        int plus = 1;
        for (int i = 0; i < source.length() - 2; i += plus) {
            plus = (int) (Math.random() * 5);
            ImageSpan is = new ImageSpan(this, R.drawable.emoji_29);
            ss.setSpan(is, i, i + 1, 0);
        }
        BackgroundColorSpan span = new BackgroundColorSpan(Color.YELLOW);
        ss.setSpan(span, 10, 20, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView.setMText(ss);
        mTextView.setTextSize(15);
        mTextView.setTextColor(Color.BLACK);

        mTextView.invalidate();


    }

    private void TestNormal() {
        textView.setBackgroundColor(Color.BLUE);
        String source = "撒反对飞王瑞芳芳vfxdsdf司法所我日 35忍32534太 地方个的服务 34个的服务 34太过分的电饭锅电饭锅打三国杀个的服务 34太过分的电饭锅电饭锅打三国杀太过分的电饭锅电饭锅打三国杀水电费歌曲筒袜上课5乳房炎啊啊。";
        SpannableString ss = new SpannableString(source);

        int plus = 1;
        for (int i = 0; i < source.length() - 2; i += plus) {
            plus = (int) (Math.random() * 5);
            ImageSpan is = new ImageSpan(this, R.drawable.emoji_29);
            ss.setSpan(is, i, i + 1, 0);
        }
        BackgroundColorSpan span = new BackgroundColorSpan(Color.YELLOW);
        ss.setSpan(span, 10, 20, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setTextSize(15);
        textView.setTextColor(Color.BLACK);

    }


    private void addTagToTextView(TextView target, String title, String tag,String tag2) {
        if (TextUtils.isEmpty(title)) {
            title = "";
        }

        String content = title + tag;


        /**
         * 创建TextView对象，设置drawable背景，设置字体样式，设置间距，设置文本等
         * 这里我们为了给TextView设置margin，给其添加了一个父容器LinearLayout。不过他俩都只是new出来的，不会添加进任何布局
         */
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        TextView textView = new TextView(this);
        textView.setText(tag);
        textView.setBackground(getResources().getDrawable(R.drawable.room_member_role_bg));
        textView.setTextSize(12);
        textView.setTextColor(Color.parseColor("#FDA413"));
        textView.setIncludeFontPadding(false);
        textView.setPadding((int)ScreenUtils.Companion.dp2px(6), 0, (int)ScreenUtils.Companion.dp2px(6), 0);

        textView.setHeight((int)ScreenUtils.Companion.dp2px( 17));
        textView.setGravity(Gravity.CENTER_VERTICAL);

        TextView textView2 = new TextView(this);
        textView2.setText(tag2);
        textView2.setBackground(getResources().getDrawable(R.drawable.room_member_role_bg));
        textView2.setTextSize(12);
        textView2.setTextColor(Color.parseColor("#FDA413"));
        textView2.setIncludeFontPadding(false);
        textView2.setPadding((int)ScreenUtils.Companion.dp2px(6), 0, (int)ScreenUtils.Companion.dp2px(6), 0);

        textView2.setHeight((int)ScreenUtils.Companion.dp2px( 17));
        textView2.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置左间距
        layoutParams.leftMargin = (int)ScreenUtils.Companion.dp2px(6);
        // 设置下间距，简单解决ImageSpan和文本竖直方向对齐的问题
        layoutParams.bottomMargin = (int)ScreenUtils.Companion.dp2px(3);

        layout.addView(textView, layoutParams);
        layout.addView(textView2, layoutParams);

        /**
         * 第二步，测量，绘制layout，生成对应的bitmap对象
         */
        layout.setDrawingCacheEnabled(true);
        layout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        // 给上方设置的margin留出空间
        layout.layout(0, 0, textView.getMeasuredWidth() + (int)ScreenUtils.Companion.dp2px( (6 + 3)), textView.getMeasuredHeight());
        // 获取bitmap对象
        Bitmap bitmap = Bitmap.createBitmap(layout.getDrawingCache());
        //千万别忘最后一步
        layout.destroyDrawingCache();

        /**
         * 第三步，通过bitmap生成我们需要的ImageSpan对象
         */
        ImageSpan imageSpan = new ImageSpan(this, bitmap);


        /**
         * 第四步将ImageSpan对象设置到SpannableStringBuilder的对应位置
         */
        SpannableStringBuilder ssb = new SpannableStringBuilder(content);
        //将尾部tag字符用ImageSpan替换
        ssb.setSpan(imageSpan, title.length(), content.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        target.setText(ssb);
    }



    /**
     * @param text      标签内容
     * @param textColor 标签颜色
     * @return
     */
    public static Drawable fromText(String text, String textColor) {
        int DP1 = (int) ScreenUtils.Companion.dp2px(1);

        Paint paint = new Paint();
        paint.setTextSize(ScreenUtils.Companion.sp2px(10));
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setAntiAlias(true);

        int color = Color.parseColor("#00B38A");
        paint.setColor(color);

        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        //Bitmap的宽和高
        int width = (int) paint.measureText(text) + 10 * DP1;
        int height = fm.descent - fm.ascent + 4 * DP1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        //写字
        canvas.drawText(text, 5 * DP1, 2 * DP1 + fm.leading - fm.ascent, paint);
        //画方框
        RectF rect = new RectF(0, 0, width - DP1, height - DP1);
        paint.setStrokeWidth(ScreenUtils.Companion.dp2px(0.5f));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        //圆角
        canvas.drawRoundRect(rect, 2 * DP1, 2 * DP1, paint);
        canvas.save();

        return new BitmapDrawable(Resources.getSystem(), bitmap);
    }

    public static final String SPACE = "\t\t.";
//    public static String SPAN = "\t\t";


    public static SpannableStringBuilder getSpanWithLabel(String src, Drawable drawable) {
        SpannableStringBuilder spannableString = new SpannableStringBuilder(src);
        spannableString.append(SPACE);
        ImageSpan imageSpan = new VerticalCenterImageSpan(drawable);
        spannableString.setSpan(imageSpan, spannableString.length() - 1, spannableString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return spannableString;
    }

//    public static SpannableStringBuilder getSpanWithLabel(String src, Drawable... drawable) {
//        SpannableStringBuilder spannableString = new SpannableStringBuilder(src);
//
//        for (int i = 0; i < drawable.length; i++) {
//            SPAN = SPAN + ".";
//        }
//        spannableString.append(SPAN);
//        int start = spannableString.length() - 1;
//
//        for (int i = 0; i < drawable.length; i++) {
//
//            ImageSpan imageSpan = new VerticalCenterImageSpan(drawable[i]);
//            spannableString.setSpan(imageSpan,start, spannableString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//            start+= i;
//        }
//        return spannableString;
//    }

    /**
     * 最后一行 没显示文字 仅有图片，需要掰下来一个字符
     *
     * @param textView 显示控件
     * @param src      职位名
     * @param drawable 需要提前设置好w和h
     */
    public static void setTextDrawable(TextView textView, String src, Drawable drawable) {
        textView.setText(getSpanWithLabel(src, drawable));

        textView.post(new Runnable() {
            @Override
            public void run() {
                Layout layout = textView.getLayout();
                String text = textView.getText().toString();

                int lastLineIndex = Math.max(0, textView.getLineCount() - 1);
                int end = layout.getLineEnd(lastLineIndex);
                int start = layout.getLineStart(lastLineIndex);
                String line = text.substring(start, end); //最后一行的内容

                if (SPACE.contains(line)) { //最后一行 没显示文字 仅有图片，需要掰下来一个字符
                    StringBuilder sb = new StringBuilder(src);
                    String s = sb.insert(Math.max(0, sb.length() - 1), "\n").toString();
                    textView.setText(getSpanWithLabel(s, drawable));
                }
            }
        });


    }


}


