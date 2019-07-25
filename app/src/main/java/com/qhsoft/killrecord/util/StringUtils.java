package com.qhsoft.killrecord.util;

import android.widget.EditText;
import android.widget.TextView;

public class StringUtils {
    public static boolean isEmpty(String value) {
        return value == null || "".equals(value) ? true : false;
    }

    public static String getEdValue(EditText editText) {
        return editText == null || editText.getText().toString() == null ? "" : editText.getText().toString().trim();
    }

    public static String getEdTag(EditText editText) {
        return editText == null || editText.getTag() == null ? "" : (String) editText.getTag();
    }

    public static String getTvValue(TextView textView) {
        return textView == null || textView.getText().toString() == null ? "" : textView.getText().toString().trim();
    }

    public static String getTvTag(TextView textView) {
        return textView == null || textView.getTag() == null ? "" : (String) textView.getTag();
    }

    public static void setTvEmpty(TextView... textViews) {
        if (textViews != null) {
            for (TextView view : textViews) {
                if (view != null) {
                    view.setText("");
                    view.setTag("");
                }
            }
        }
    }

    public static void setEdEmpty(EditText... editTexts) {
        if (editTexts != null) {
            for (EditText view : editTexts) {
                if (view != null) {
                    view.setText("");
                    view.setTag("");
                }
            }
        }
    }


}
