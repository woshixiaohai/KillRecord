package com.qhsoft.killrecord.net;

import android.content.Context;

import com.qhsoft.killrecord.util.Logger;
import com.qhsoft.killrecord.util.SpUtil;

/**
 * Description:
 * Author:lin
 * Date:2017-09-07
 */
public class HttpConfig {

    public static String baseUrl;
//    public static String content = "datacenter";
    //    public static String content="rsbd";
        public static String content = "bigdata";
    public static int PAGE_SIZE = 10;

    public static void initBaseUrl(Context context) {
        SpUtil spUtil = new SpUtil(context);
        spUtil.setContext(content);
        baseUrl = spUtil.getBaseUrl();
        Logger.d(HttpConfig.class.getSimpleName(), baseUrl);

    }

    public static String getPictureUrl(String relativeUrl) {
        relativeUrl = relativeUrl.replace("\\", "/");
        if (relativeUrl.startsWith("http://")) {
            return relativeUrl;
        }

        return baseUrl + relativeUrl;

    }

}
