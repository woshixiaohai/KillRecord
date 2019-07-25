package com.qhsoft.killrecord.photo.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

	public static void showToast(Context context, String text){
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}

	public static void showToast(Context context, int id){
		Toast.makeText(context, context.getResources().getString(id), Toast.LENGTH_LONG).show();
	}

}