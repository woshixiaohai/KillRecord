package com.qhsoft.killrecord.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Description:
 * Author:hong
 * Date:2017-05-10
 */

public class DisplayUtils {

    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取手机的宽高
     *
     * @param activity
     * @return int[0] 宽  int[1]高
     */
    public static int[] getPhoneScreenParam(Activity activity) {
        int[] param = new int[2];
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        param[0] = rect.width();
        param[1] = rect.height();
//        DisplayMetrics displayMetrics = getWindowDisplayMetrics(activity);
//
//        boolean isPortrait = displayMetrics.widthPixels < displayMetrics.heightPixels;
//        param[0] = isPortrait ? displayMetrics.widthPixels : displayMetrics.heightPixels;
//        param[1] = isPortrait ? displayMetrics.heightPixels : displayMetrics.widthPixels;

        return param;
    }

    private static DisplayMetrics getWindowDisplayMetrics(Activity activity) {
        final WindowManager windowManager = activity.getWindowManager();
        final Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return displayMetrics;
    }
}
