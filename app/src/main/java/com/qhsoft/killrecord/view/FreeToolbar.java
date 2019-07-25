package com.qhsoft.killrecord.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.util.Logger;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Description:
 * Author:lin
 * Date:2017-07-14
 */

public class FreeToolbar extends AutoLinearLayout {
    private static final String TAG = FreeToolbar.class.getSimpleName();
    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvSave;


    public FreeToolbar(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FreeToolBar);
        int resId = a.getResourceId(R.styleable.FreeToolBar_layoutId, R.layout.toolbar_common);
        inflate(context, resId, this);
        ivBack = findViewById(R.id.toolbar_back);
        tvTitle = findViewById(R.id.toolbar_title);
        tvSave = findViewById(R.id.toolbar_save);
        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = getActivity();
                if (activity == null) {
                    Logger.e(TAG, "返回键失效");
                } else {
                    activity.finish();
                }
            }
        });
        String title = a.getString(R.styleable.FreeToolBar_freeTitle);
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
        setBackIconVisible(a.getBoolean(R.styleable.FreeToolBar_isShowBack, true));
        a.recycle();

    }


    /**
     * 设置返回键监听事件
     *
     * @param onClickListener
     */
    public void setOnBackClickListener(OnClickListener onClickListener) {
        if (ivBack != null) {
            ivBack.setOnClickListener(onClickListener);
        }

    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        if (tvTitle != null&&title!=null) {
            tvTitle.setText(title);
        }
    }

    /**
     * 获取标题
     *
     * @return
     */
    public String getTitle() {
        if (tvTitle != null) {
            return tvTitle.getText().toString();
        }
        return "";
    }

    /**
     * 设置返回键可见性
     *
     * @param visible
     */
    public void setBackIconVisible(boolean visible) {
        if (ivBack != null) {
            ivBack.setVisibility(visible ? VISIBLE : GONE);
        }
    }

    /**
     * 设置标题
     *
     * @param resId 文本资源文件
     */
    public void setTitle(@StringRes int resId) {
        if (tvTitle != null) {
            tvTitle.setText(resId);
        }
    }

    /**
     * 设置默认的动作文本可见性
     *
     * @param visible
     */
    public void setActionTextVisible(boolean visible) {

        if (tvSave != null) {

            tvSave.setVisibility(visible ? VISIBLE : GONE);
        }
    }

    /**
     * 设置默认的动作文本
     *
     * @param resId
     */
    public void setTvSave(@StringRes int resId) {
        if (tvSave != null) {
            tvSave.setText(resId);
        }
    }

    public void setActionText(String str) {
        if (tvSave != null) {
            tvSave.setText(str);
        }
    }

    public void setActionText(int resId, String str) {
        if(resId>0&&str!=null){
            TextView tv= findViewById(resId);
            if(tv!=null){
                tv.setText(str);
            }
        }

    }

    /**
     * 设置默认动作文本点击事件
     *
     * @param onClickListener
     */
    public void setActionTextClickListener(OnClickListener onClickListener) {
        if (tvSave != null) {
            tvSave.setOnClickListener(onClickListener);
        }
    }

    /**
     * 设置
     *
     * @param id              控件id
     * @param onClickListener 视图点击事件
     */
    public void setOnActionClickListener(@IdRes int id, OnClickListener onClickListener) {
        View view = findViewById(id);
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
    }

    public void setActionViewVisible(int id, int visible) {
        View view = findViewById(id);
        if (view != null) {
            view.setVisibility(visible);
        }
    }
    public void setActionViewVisible(int id, boolean visible) {
        View view = findViewById(id);
        if (view != null) {
            view.setVisibility(visible ? VISIBLE : GONE);
        }
    }

    private Activity getActivity() {
        Context context = getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }
}
