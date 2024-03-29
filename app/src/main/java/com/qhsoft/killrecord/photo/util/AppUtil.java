package com.qhsoft.killrecord.photo.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Environment;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class AppUtil {

    public static int getStatusBarHeight() {
        Class<?> c;
        Object obj;
        Field field;
        int x = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return x;
    }

    public static void hideTitleBar(Activity activity, int resource, int steepToolBarColor) {
        if (android.os.Build.VERSION.SDK_INT > 18) {
            Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            LinearLayout whole_layout = (LinearLayout) activity.findViewById(resource);
            whole_layout.setBackgroundColor(steepToolBarColor);
            whole_layout.setPadding(0, activity.getResources().getDimensionPixelSize(getStatusBarHeight()), 0, 0);
        }
    }


    public static boolean existSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static String getImageName() {
        String PATTERN = "yyyyMMddHHmmss";
        return new SimpleDateFormat(PATTERN, Locale.CHINA).format(new Date()) + ".jpg";
    }
    public static String getVersionName(Context context){
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode+"";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";

    }


}