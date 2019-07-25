package com.qhsoft.killrecord.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.qhsoft.killrecord.R;

import butterknife.ButterKnife;

public abstract class BaseQueryDialog {

	private PopupWindow popupWindow;

	private Activity context;
	private LinearLayout base_query_content;
	private TextView base_tv_query;
	private TextView base_tv_reset;



	public BaseQueryDialog(final Activity context) {

		this.context = context;

		View view = View.inflate(context, R.layout.layout_base_query_dialog, null);
		base_query_content= view.findViewById(R.id.base_query_content);
		base_tv_query= view.findViewById(R.id.base_tv_query);
		base_tv_reset=view.findViewById(R.id.base_tv_reset);

		
		popupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		
		popupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				// TODO Auto-generated method stub
				WindowManager.LayoutParams params = context.getWindow()
						.getAttributes();
				params.alpha = 1f;
				

				context.getWindow().setAttributes(params);
			}
		});
		base_tv_query.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onQuery();
				popupWindow.dismiss();
				
			}
		});
		base_tv_reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onReset();
			}
		});


        int layoutId=getLayoutId();
		View v=LayoutInflater.from(context).inflate(layoutId,null);
		base_query_content.addView(v);
		ButterKnife.bind(this,v);
		onAddView(context,v);
		

	}

	/**
	 * 显示菜单
	 */
	public void showMenu(View parent) {
		if (!popupWindow.isShowing()) {

			popupWindow.showAsDropDown(parent, 0, 0);
			popupWindow.setFocusable(true);
			popupWindow.setOutsideTouchable(true);
			popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
			popupWindow.update();

			WindowManager.LayoutParams params = context.getWindow()
					.getAttributes();
			params.alpha = 1f;

			context.getWindow().setAttributes(params);
			InputMethodManager imm = (InputMethodManager) context
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			if (imm.isActive()) {
				imm.hideSoftInputFromWindow(parent.getWindowToken(), 0);
			}
		}

	}

	/**
	 * 显示菜单
	 */
	public void showMenu(View parent, int x, int y) {
		if (!popupWindow.isShowing()) {

			popupWindow.showAsDropDown(parent, x, y);
			popupWindow.setFocusable(true);
			popupWindow.setOutsideTouchable(true);
			popupWindow.update();
			WindowManager.LayoutParams params = context.getWindow()
					.getAttributes();
			params.alpha = 1f;

			context.getWindow().setAttributes(params);
			InputMethodManager imm = (InputMethodManager) context
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			if (imm.isActive()) {
				imm.hideSoftInputFromWindow(parent.getWindowToken(), 0);
			}
		}

	}

	public void hideMenu() {
		if (popupWindow != null) {
			popupWindow.dismiss();
		}
	}
    protected abstract void onQuery();
	protected abstract void onReset();

    protected abstract void onAddView(Activity context,View itemView);

	protected abstract int getLayoutId();





	
}
