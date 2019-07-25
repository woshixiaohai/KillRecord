package com.qhsoft.killrecord.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.luyinbros.combineview.SimpleListView;
import com.qhsoft.killrecord.R;

import butterknife.Bind;

public abstract class RightFragment extends BaseFragment {
    @Bind(R.id.mSimpleListView)
    SimpleListView mSimpleListView;
    @Bind(R.id.iv_add)
    ImageView iv_add;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_right;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        d("right");
        View itemView=inflate(getLayoutId());

    }
    protected abstract int getLayoutId();
    protected abstract <T> void onBindData(View itemView,T t);

}
