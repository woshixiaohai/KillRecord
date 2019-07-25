package com.qhsoft.killrecord.util;

import android.util.Log;

/**
 * Description:
 * Author: jiang
 * Date: 2017-07-03 16:53
 */

public class Logger {
	public static int LOG_LEVEL = 6;
	public static int ERROR = 1;
	public static int WARN = 2;
	public static int INFO = 3;
	public static int DEBUG = 4;
	public static int VERBOS = 5;

	public static void e(String tag, String msg) {
		if (msg == null) {
			Log.e(tag, "日志为空");
			return;
		}
		if (LOG_LEVEL > ERROR)
			Log.e(tag, msg);
	}



	public static void w(String tag, String msg) {
		if (msg == null) {
			Log.w(tag, "日志为空");
			return;
		}
		if (LOG_LEVEL > WARN)
			Log.w(tag, msg);
	}


	public static void i(String tag, String msg) {
		if (msg == null) {
			Log.i(tag, "日志为空");
			return;
		}
		if (LOG_LEVEL > INFO)
			Log.i(tag, msg);
	}



	public static void d(String tag, String msg) {
		if (msg == null) {
			Log.d(tag, "日志为空");
			return;
		}
		if (LOG_LEVEL > DEBUG) {
			Log.d(tag, msg);
		}

	}



	public static void v(String tag, String msg) {
		if (msg == null) {
			Log.v(tag, "日志为空");
			return;
		}
		if (LOG_LEVEL > VERBOS)
			Log.v(tag, msg);
	}



}
