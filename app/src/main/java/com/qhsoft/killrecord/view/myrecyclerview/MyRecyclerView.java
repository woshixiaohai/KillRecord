package com.qhsoft.killrecord.view.myrecyclerview;

/**
 * Description:
 * Author:lin
 * Date:2017-10-28
 */


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class MyRecyclerView extends RecyclerView {

    private LinearLayoutManager mLinearLayoutManager;
    private LoadMoreAdapter mAdapter;

    public MyRecyclerView(Context context) {
        this(context, null);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        int endCompletelyPosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
        if (endCompletelyPosition == mAdapter.getItemCount() - 1&&mAdapter.getCount()>0) {

            if(onLoadMoreListener!=null){
                onLoadMoreListener.onloadMore();
            }
        }
    }

    public void setManager() {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        setLayoutManager(mLinearLayoutManager);
    }

    public LinearLayoutManager getLayoutManager() {
        return mLinearLayoutManager;
    }

    public void setLoadMoreAdapter(LoadMoreAdapter mAdapter) {
        this.mAdapter = mAdapter;
        setAdapter(mAdapter);
    }
    private OnLoadMoreListener onLoadMoreListener;
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener){
        this.onLoadMoreListener=onLoadMoreListener;
    }
    public interface OnLoadMoreListener{
         void onloadMore();
    }

}