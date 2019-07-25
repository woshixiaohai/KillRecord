package com.qhsoft.killrecord.base;

import android.view.View;

import com.luyinbros.combineview.common.ViewHolder;

import butterknife.ButterKnife;

public class BaseViewHolder extends ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
