package com.qhsoft.killrecord.base;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.view.FreeToolbar;
import com.qhsoft.killrecord.view.myrecyclerview.MyRecyclerView;

import org.simple.eventbus.Subscriber;

import butterknife.Bind;
import butterknife.OnClick;

public abstract   class BaseListActivity extends BaseActivity {
    @Bind(R.id.mFreeToolbar)
    public FreeToolbar mFreeToolbar;
    @Bind(R.id.ll_refresh)
    public LinearLayout ll_refresh;

    @Bind(R.id.ll_add)
    public LinearLayout ll_add;

    @Bind(R.id.ll_query)
    public LinearLayout ll_query;

    @Bind(R.id.ll_tool)
    public LinearLayout ll_tool;

    @Bind(R.id.mRecyclerView)
    public MyRecyclerView mRecyclerView;
    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_base_list;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        if(getIntent().getStringExtra("title")!=null){
            mFreeToolbar.setTitle(getIntent().getStringExtra("title"));
        }


    }






    @OnClick(R.id.ll_refresh)
    public void onClickRefresh(){
        onRefresh();
    }
    @OnClick(R.id.ll_add)
    public void onClickAdd(){
        onAdd();

    }
    @OnClick(R.id.ll_query)
    public void onClickQuery(){
        onQuery();

    }
    @Subscriber(tag = MyApp.REFRESH_LIST)
    private void getList(String str){
        onRefreshList();
    }

    protected abstract void onRefresh();

    protected abstract void onAdd();
    protected abstract void onQuery();

    protected abstract void onRefreshList();



}
