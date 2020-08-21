package com.app.simple.codes.utils;

import android.text.Editable;
import android.text.Html.TagHandler;
import android.text.TextUtils;

import org.xml.sax.XMLReader;

/**
 * Created by lagou on 2015/10/27.
 */
public class HtmlTagHandleUtils implements TagHandler {
    private int index = 0;
    //0：不是ol,1：ol开始，2：ol结束
    private int olState = 0;
    //0：不是ul,1：ul开始，2：ul结束
    private int ulState = 0;
    //-1：其他标签，0：ol标签，1：ul标签
    private int tagState = -1;
    String prex = null;
    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if (tag.equalsIgnoreCase("olTag")) {
            // 1 2 3
            tagState = 0;
            if (opening) {
                index = 0;
                olState = 1;
            } else {
                olState = 2;
            }
        } else if (tag.equalsIgnoreCase("ulTag")) {
            // 点 ●
            tagState = 1;
            if (opening) {
                ulState = 1;
            } else {
                ulState = 2;
            }
        } else {
            if (tag.equalsIgnoreCase("liTag")) {
                if (opening) {
                    handleLine(output);
                    if (tagState == 0 && olState == 1) {
                        //是ol标签，并且该标签未结束
                        index++;
                        prex  = "\t"+index+". ";
                        output.append(prex);
                    } else if (tagState == 1 && ulState == 1) {
                        //是ul标签，并且该标签未结束
                        prex  = "\t"+"• ";
                        output.append(prex);
                    }

                }

            } else {
                tagState = -1;
            }
        }
    }

    private void handleLine(Editable output) {
        try {
            if (!TextUtils.isEmpty(prex)){
                int startIndex = output.toString().lastIndexOf(prex)+prex.length();
                char ch = output.charAt(startIndex);
                if (ch == '\n') {
                    output.delete(startIndex, startIndex+1);
                    handleLine(output);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
