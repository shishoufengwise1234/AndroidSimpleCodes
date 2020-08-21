package com.app.simple.codes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.simple.codes.R;
import com.app.simple.codes.utils.HtmlTagHandleUtils;
import com.app.simple.codes.view.expandtext.ExpandableTextView2;

import java.util.Random;

/**
 * Created by shishoufeng on 2020/8/11.
 * email:shoufengshi@lagou.com
 * <p>
 * desc:
 */
public class ExpandableTextView2Activity extends AppCompatActivity implements View.OnClickListener {



    private TextView mTVComparison;
    private Button mBtnUpdateText;
    private Button mBtnToListView;

    private ExpandableTextView2 mETV;
    private CharSequence[] mPoems = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_text_view2);

        mPoems = getResources().getStringArray(R.array.poems);

        mTVComparison = (TextView)this.findViewById(R.id.tv_comparison);
        mBtnUpdateText = (Button)this.findViewById(R.id.button_update_text);
        mBtnToListView = (Button)this.findViewById(R.id.button_to_list_view);
        mETV = (ExpandableTextView2)this.findViewById(R.id.etv);

        mBtnUpdateText.setOnClickListener(this);
        mBtnToListView.setOnClickListener(this);

        // 测试添加OnClickListener的情况，功能正常。添加外部的onClick事件后，原来的点击toggle功能自动屏蔽，
        // 点击尾部的ClickableSpan仍然有效
        /*mETV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (mETV.getExpandState()){
                    case ExpandableTextView.STATE_SHRINK:
                        Toast.makeText(getApplicationContext(),"ExpandableTextView clicked, STATE_SHRINK",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case ExpandableTextView.STATE_EXPAND:
                        Toast.makeText(getApplicationContext(),"ExpandableTextView clicked, STATE_EXPAND",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });*/
//        mETV.setText(mPoems[0]);//在ExpandableTextView在创建完成之前改变文字，功能正常

        String str = "        <item>1.醉里挑灯看剑，梦回吹角连营。八百里分麾下炙，五十弦翻塞外声。沙场秋点兵。马作的卢飞快，弓如霹雳弦惊。了却君王天下事，赢得生前身后" +
                "名。可怜白发生。千古江山，英雄无觅，孙仲谋处。舞榭歌台，风流总被、雨打风吹去。斜阳草树，寻常巷陌，人道寄奴曾住。想当年，金戈铁马，气吞万里如虎。元嘉草草，" +
                "封狼居胥，赢得仓皇北顾。四十三年，望中犹记封狼居胥，赢得仓皇北顾。四十三年，望中犹记，烽火扬州路。可堪回首，佛狸祠下，一片神鸦社鼓。凭谁问：廉颇老矣，尚能饭否？" +
                "封狼居胥，赢得仓皇北顾。四十三年，望中犹记封狼居胥，赢得仓皇北顾。四十三年，望中犹记，烽火扬州路。可堪回首，佛狸祠下，一片神鸦社鼓。凭谁问：廉颇老矣，尚能饭否？" +
                "封狼居胥，赢得仓皇北顾。四十三年，望中犹记封狼居胥，赢得仓皇北顾。四十三年，望中犹记，烽火扬州路。可堪回首，佛狸祠下，一片神鸦社鼓。凭谁问：廉颇老矣，尚能饭否？" +
                "气吞万里如虎。元嘉草草，封狼居胥，赢得仓皇北顾。四十三年，望中犹记封狼居胥，赢得仓皇北顾。四十三年，望中犹记，烽火扬州路。可堪回首，佛狸祠下，一片神鸦社鼓。凭谁问：廉颇老矣，</item>";

        str = "1.了解需求，编写测点与测试用例<br />\n2.合理安排测试任务，使用sql来辅助测试工作<br />\n3.测试过程中发现问题，及时与开发人员确认问题<br />\n4.测试结束，总结测试结果，提供测试报告<br />\n5.合理安排测试工作<br />"+
                "1.了解需求，编写测点与测试用例<br />\n2.合理安排测试任务，使用sql来辅助测试工作<br />\n3.测试过程中发现问题，及时与开发人员确认问题<br />\n4.测试结束，总结测试结果，提供测试报告<br />\n5.合理安排测试工作<br />"+
                "1.了解需求，编写测点与测试用例<br />\n2.合理安排测试任务，使用sql来辅助测试工作<br />\n3.测试过程中发现问题，及时与开发人员确认问题<br />\n4.测试结束，总结测试结果，提供测试报告<br />\n5.合理安排测试工作<br />"+
                "1.了解需求，编写测点与测试用例<br />\n2.合理安排测试任务，使用sql来辅助测试工作<br />\n3.测试过程中发现问题，及时与开发人员确认问题<br />\n4.测试结束，总结测试结果，提供测试报告<br />\n5.合理安排测试工作<br />";

        str = "\"<p>1.该项目主要是涉及整个公司的租赁合同的记录以及财务的数据统计。</p>\\n<p>2.在整个项目过程中，我负责测试工作，前期了解需求并整理测点</p>\\n<p>3.在测试过程中创建有效测试数据，使用redmine提出有效bug&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\\n<p>4.保证测试质量，提供测试报告</p>\"" +
                "<p>1.该项目主要是涉及整个公司的租赁合同的记录以及财务的数据统计。</p>\n<p>2.在整个项目过程中，我负责测试工作，前期了解需求并整理测点</p>\n<p>3.在测试过程中创建有效测试数据，使用redmine提出有效bug&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n<p>4.保证测试质量，提供测试报告</p>"+
                "<p>1.该项目主要是涉及整个公司的租赁合同的记录以及财务的数据统计。</p>\n<p>2.在整个项目过程中，我负责测试工作，前期了解需求并整理测点</p>\n<p>3.在测试过程中创建有效测试数据，使用redmine提出有效bug&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n<p>4.保证测试质量，提供测试报告</p>"+
                "<p>1.该项目主要是涉及整个公司的租赁合同的记录以及财务的数据统计。</p>\n<p>2.在整个项目过程中，我负责测试工作，前期了解需求并整理测点</p>\n<p>3.在测试过程中创建有效测试数据，使用redmine提出有效bug&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n<p>4.保证测试质量，提供测试报告</p>"+
                "<p>1.该项目主要是涉及整个公司的租赁合同的记录以及财务的数据统计。</p>\n<p>2.在整个项目过程中，我负责测试工作，前期了解需求并整理测点</p>\n<p>3.在测试过程中创建有效测试数据，使用redmine提出有效bug&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n<p>4.保证测试质量，提供测试报告</p>"+
                "<p>1.该项目主要是涉及整个公司的租赁合同的记录以及财务的数据统计。</p>\n<p>2.在整个项目过程中，我负责测试工作，前期了解需求并整理测点</p>\n<p>3.在测试过程中创建有效测试数据，使用redmine提出有效bug&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n<p>4.保证测试质量，提供测试报告</p>"+
                "<p>1.该项目主要是涉及整个公司的租赁合同的记录以及财务的数据统计。</p>\n<p>2.在整个项目过程中，我负责测试工作，前期了解需求并整理测点</p>\n<p>3.在测试过程中创建有效测试数据，使用redmine提出有效bug&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n<p>4.保证测试质量，提供测试报告</p>"+
                "<p>1.该项目主要是涉及整个公司的租赁合同的记录以及财务的数据统计。</p>\n<p>2.在整个项目过程中，我负责测试工作，前期了解需求并整理测点</p>\n<p>3.在测试过程中创建有效测试数据，使用redmine提出有效bug&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n<p>4.保证测试质量，提供测试报告</p>";


        mETV.setText(getHtmlStr(str));//效果显示

    }


    /**
     * 根据后台返回的Html获得String
     *
     * @param str
     * @return
     */
    public static String getHtmlStr(String str) {
        String formatStr = formatHtmlStr(str);
        if (isTextEmpty(formatStr)) {
            return "";
        }
        String htmlStr = Html.fromHtml(formatStr, null, new HtmlTagHandleUtils()).toString();
        //移除首尾所有的换行
        String endStr = htmlStr.replaceAll("(^(\r\n|\n|\r)*)|((?:\r\n|\n|\r)*$)", "");
        return endStr;
    }

    /**
     * 格式化源字符串
     *
     * @param source
     * @return
     */
    private static String formatHtmlStr(String source) {
        if (TextUtils.isEmpty(source)) {
            return "";
        }
        String dest = source;

        // changed by evanzhang at 2019-07-11, 不对p/br标签进行单独，直接使用html.fromhtml处理
        // p标签替换为换行
        dest = dest.replaceAll("<(p)([^>]*)>", "");
        //将<br></p>转为<br />
        dest = dest.replaceAll("<br\\s*/?>+\\s*+<(/p)([^>]*)>", "<br />");
        dest = dest.replaceAll("<(/p)([^>]*)>", "<br />");
        dest = dest.replaceAll("<br\\s*/?>", "<br />");
        dest = dest.replace("\u2028", ""); // 特殊字符无法识别显示小方块
        // dest = dest.replaceAll("&nbsp;", " ");
        // dest = dest.replaceAll("&amp;", "&");
        //用<urlTag> <liTag>替换标签<ul> <liTag>是为了在自定义的TagHandler中自行处理，否则回调中拿不到这两个标签
        dest = dest.replaceAll("<(ul)([^>]*)", "<ulTag").replaceAll("<(/ul)([^>]*)>", "</ulTag>")
                .replaceAll("<(ol)([^>]*)", "<olTag").replaceAll("<(/ol)([^>]*)>", "</olTag>")
                .replaceAll("<(li)([^>]*)", "<liTag").replaceAll("<(/li)([^>]*)>", "</liTag>");
        return dest;

    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isTextEmpty(String str) {
        if (str == null) {
            return true;
        } else if (str.equals("")) {
            return true;
        } else if (str.equals("null")) {
            return true;
        } else if (str.length() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_to_list_view:
                gotoCheckInListView();
                break;
            case R.id.button_update_text:
                updateText();
                break;
        }
    }

    private void gotoCheckInListView(){
        Intent intent = new Intent(ExpandableTextView2Activity.this, ActivityListView.class);
        startActivity(intent);
    }

    private Random mRandom = new Random();
    private int prevRandomInt = -1;
    private int currRandomInt = -1;

    private void updateText(){
        currRandomInt = mRandom.nextInt(mPoems.length);
        while (prevRandomInt == currRandomInt){
            currRandomInt = mRandom.nextInt(mPoems.length);
        }
        prevRandomInt = currRandomInt;
        CharSequence newCS = mPoems[currRandomInt];

        mTVComparison.setText(newCS);//作为对比示例
        mETV.setText(newCS);//效果显示
    }

}
