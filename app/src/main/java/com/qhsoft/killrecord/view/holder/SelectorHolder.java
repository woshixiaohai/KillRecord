package com.qhsoft.killrecord.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qhsoft.killrecord.R;

/**
 * Description:
 * Author:hong
 * Date:2017-09-14
 */

public final class SelectorHolder extends RecyclerView.ViewHolder {
    private TextView tvName;
    private View selectorTag;

    public SelectorHolder(LayoutInflater mInflater, ViewGroup parent) {
        super(mInflater.inflate(R.layout.list_item_dialog_select, parent, false));
        tvName =  itemView.findViewById(R.id.tv_name);
        selectorTag = itemView.findViewById(R.id.selector_tag);
    }

    public void setNameText(String name) {
        tvName.setText(name);
    }

    public void setSelected(boolean isSelected) {
        selectorTag.setVisibility(isSelected ? View.VISIBLE : View.INVISIBLE);
    }
}
