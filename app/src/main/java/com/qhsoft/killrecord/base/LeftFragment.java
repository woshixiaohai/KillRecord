package com.qhsoft.killrecord.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.qhsoft.killrecord.R;

public abstract class LeftFragment extends BaseFragment {
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_trace;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        d("left");

    }
}
