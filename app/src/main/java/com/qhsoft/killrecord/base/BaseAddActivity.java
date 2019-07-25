package com.qhsoft.killrecord.base;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;


import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.view.FreeToolbar;

import butterknife.Bind;
import butterknife.OnClick;

public  abstract class BaseAddActivity extends BaseActivity {
    @Bind(R.id.mFreeToolbar)
    public FreeToolbar mFreeToolbar;
    @Bind(R.id.add_rb_bill)
    public RadioButton add_rb_bill;
    @Bind(R.id.add_rb_line)
    public RadioButton add_rb_line;
    private LeftFragment leftFragment;
    private RightFragment rightFragment;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_base_add;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mFreeToolbar.setTitle("新增");
        mFreeToolbar.setActionText("完成");
        mFreeToolbar.setActionTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSave();
            }
        });
//         leftFragment=new LeftFragment();
//         rightFragment=new RightFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.mContent,leftFragment).add(R.id.mContent,rightFragment).show(leftFragment).hide(rightFragment).commitAllowingStateLoss();




    }
    @OnClick(R.id.add_rb_bill)
    public void onClickLeft(){
        getSupportFragmentManager().beginTransaction().show(leftFragment).hide(rightFragment).commitAllowingStateLoss();

        onLeft();
    }
    @OnClick(R.id.add_rb_line)
    public void onClickRight(){
        getSupportFragmentManager().beginTransaction().show(rightFragment).hide(leftFragment).commitAllowingStateLoss();
        onRight();
    }


    protected abstract void onSave();
    protected abstract void onLeft();
    protected abstract void onRight();




}
