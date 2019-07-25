package com.qhsoft.killrecord.view;


import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;


public class RefreshLayout extends SwipeRefreshLayout {
    private OnRefreshListener mOnRefreshListener;

    public RefreshLayout(Context context) {
        super(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onRefreshComplete() {
        super.setRefreshing(false);
    }

    public void beginRefresh() {
        super.setRefreshing(true);
        if (mOnRefreshListener != null) {
            mOnRefreshListener.onRefresh();
        }
    }

    public void onCancel() {
        if (mOnRefreshListener != null && isRefreshing()) {
            onRefreshComplete();
            mOnRefreshListener.onCancel();
        }
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.mOnRefreshListener = onRefreshListener;
        super.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mOnRefreshListener != null) {
                    mOnRefreshListener.onRefresh();
                }
            }
        });

    }

    public interface OnRefreshListener {

        void onCancel();

        void onRefresh();

    }
}
