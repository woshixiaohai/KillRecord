package com.qhsoft.killrecord.view.myrecyclerview;

/**
 * Description:
 * Author:lin
 * Date:2017-10-28
 */


import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;

public class CommonRcViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views = new SparseArray<>();
    private View view;

    public CommonRcViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        ButterKnife.bind(this,itemView);
    }

    public <T extends View> T getView(int viewId) {
        View v = views.get(viewId);

        if (v == null) {
            v = view.findViewById(viewId);
            views.put(viewId, v);
        }
        return (T) v;
    }

    public <T extends View> T getViewWithLayoutParams(int viewId, ViewGroup.LayoutParams lp) {
        View v = views.get(viewId);

        if (v == null) {
            v = view.findViewById(viewId);
            v.setLayoutParams(lp);
            views.put(viewId, v);
        }
        return (T) v;
    }

    public CommonRcViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }
}