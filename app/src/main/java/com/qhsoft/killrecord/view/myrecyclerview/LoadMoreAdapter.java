package com.qhsoft.killrecord.view.myrecyclerview;

/**
 * Description:
 * Author:lin
 * Date:2017-10-28
 */


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qhsoft.killrecord.R;


public abstract class LoadMoreAdapter extends RecyclerView.Adapter<CommonRcViewHolder> {



    /**
     * 正在加载中
     */
    public static final int STATE_LOADING = 0;
    /**
     * 没有数据
     */
    public static final int STATE_EMPTY = 1;
    /**
     * 加载失败
     */
    public static final int STATE_FAILURE =2;
    public static final int STATE_SUCCESS =3;


    private int load_state = 0;


    public static final int ITEM_TYPE_FOOTER = 100;
    protected String loadMoreText = "加载更多";

    public LoadMoreAdapter(int load_state) {
        this.load_state = load_state;
    }






    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1&&getItemCount()>1){

            return ITEM_TYPE_FOOTER;
        }else {
            return 1;
        }
    }

    @Override
    public CommonRcViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (load_state == STATE_LOADING) {
                return new CommonRcViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_status_default_refresh, parent, false));
            }
            if(load_state== STATE_FAILURE){
                return new CommonRcViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_status_default_failure, parent, false));
            }

            if (load_state == STATE_EMPTY) {
                return new CommonRcViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_status_detault_empty, parent, false));
            }
            if (viewType == ITEM_TYPE_FOOTER) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_footer, parent, false);
                return new CommonRcViewHolder(view);
            }

            return getViewHolder(parent,viewType);






    }

    @Override
    public void onBindViewHolder(CommonRcViewHolder holder, int position) {
        if (getItemViewType(position) != ITEM_TYPE_FOOTER){
            loadData(holder, position);
        }else {

            TextView tv = holder.getView(R.id.tv);
            if(tv!=null){
                tv.setText(loadMoreText);
            }

        }
    }

    @Override
    public int getItemCount() {
        if (load_state == STATE_LOADING||load_state == STATE_EMPTY||load_state == STATE_FAILURE) {
            return 1;
        }

        return getCount()+1;
    }

    public void setLoadMoreText(String loadMoreText) {
        this.loadMoreText = loadMoreText;
        notifyItemChanged(getItemCount()-1);
    }
    public void setNoloadMoreText(){
        this.loadMoreText = "已经到底了";
        notifyItemChanged(getItemCount()-1);
    }



    public abstract int getCount();
    public abstract CommonRcViewHolder getViewHolder(ViewGroup parent, int viewType);
    public abstract void loadData(CommonRcViewHolder holder, int position);



}