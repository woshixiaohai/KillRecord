package com.qhsoft.killrecord.module.ledger.killcheck;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.model.remote.bean.KillCheckOneDetailBean;
import com.qhsoft.killrecord.view.FreeToolbar;

import org.simple.eventbus.Subscriber;

import butterknife.Bind;
import butterknife.OnClick;

public class KillCheckAddActivity extends BaseActivity {
    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;
    private KillCheckAddOneFragment killCheckAddOneFragment;
    private KillCheckAddTwoFragment killCheckAddTwoFragment;
    @Bind(R.id.tv_step_one)
    TextView tv_step_one;
    @Bind(R.id.tv_step_two)
    TextView tv_step_two;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_kill_check_add;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mFreeToolbar.setTitle(getIntent().getStringExtra("title"));
        killCheckAddOneFragment=new KillCheckAddOneFragment();
        killCheckAddTwoFragment =new KillCheckAddTwoFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.mFlowLayout,killCheckAddOneFragment)
                .add(R.id.mFlowLayout, killCheckAddTwoFragment).show(killCheckAddOneFragment)
                .hide(killCheckAddTwoFragment).commitAllowingStateLoss();
        tv_step_one.setBackgroundResource(R.drawable.menu_1);
        tv_step_two.setBackgroundResource(R.drawable.menu_2);
        mFreeToolbar.setActionTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(killCheckAddOneFragment.isVisible()){
                    killCheckAddOneFragment.saveData();
                }else {
                    killCheckAddTwoFragment.saveData();
                }
            }
        });
        if(!getIntent().getStringExtra("title").endsWith("新增")&&!getIntent().getStringExtra("title").endsWith("编辑")){
            mFreeToolbar.setActionTextVisible(false);
        }





    }
    @Subscriber(tag = "toNext")
    private void toNext(KillCheckOneDetailBean value){
        if(killCheckAddOneFragment.getIsFinish()){
            tv_step_two.setBackgroundResource(R.drawable.step_two);
            tv_step_one.setBackgroundColor(getResources().getColor(R.color.activity_bg));
            tv_step_one.setTextColor(getResources().getColor(R.color.text_black_normal));
            tv_step_two.setTextColor(getResources().getColor(R.color.theme_bg));

            getSupportFragmentManager().beginTransaction().hide(killCheckAddOneFragment)
                    .show(killCheckAddTwoFragment).commitAllowingStateLoss();

            tv_step_one.setBackgroundResource(R.drawable.menu_3);
            tv_step_two.setBackgroundResource(R.drawable.menu_4);
            killCheckAddTwoFragment.initData(value);

        }else {
            showToast("送宰检验未完成");
        }
    }


    @OnClick({R.id.tv_step_one,R.id.tv_step_two})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_step_one:
                tv_step_one.setBackgroundResource(R.drawable.step_one);
                tv_step_one.setTextColor(getResources().getColor(R.color.theme_bg));
                tv_step_two.setBackgroundColor(getResources().getColor(R.color.activity_bg));
                tv_step_two.setTextColor(getResources().getColor(R.color.text_black_normal));
                getSupportFragmentManager().beginTransaction().show(killCheckAddOneFragment)
                        .hide(killCheckAddTwoFragment).commitAllowingStateLoss();


                tv_step_one.setBackgroundResource(R.drawable.menu_1);
                tv_step_two.setBackgroundResource(R.drawable.menu_2);

                if(getIntent().getStringExtra("zhId")!=null&&!getIntent().getStringExtra("zhId").equals("")){
                    mFreeToolbar.setActionTextVisible(false);
                }





                break;
            case R.id.tv_step_two:
                if(killCheckAddOneFragment.getIsFinish()){
                    tv_step_two.setBackgroundResource(R.drawable.step_two);
                    tv_step_one.setBackgroundColor(getResources().getColor(R.color.activity_bg));
                    tv_step_one.setTextColor(getResources().getColor(R.color.text_black_normal));
                    tv_step_two.setTextColor(getResources().getColor(R.color.theme_bg));
                    getSupportFragmentManager().beginTransaction().hide(killCheckAddOneFragment)
                            .show(killCheckAddTwoFragment).commitAllowingStateLoss();

                    tv_step_one.setBackgroundResource(R.drawable.menu_3);
                    tv_step_two.setBackgroundResource(R.drawable.menu_4);
                    mFreeToolbar.setActionTextVisible(true);
                    if(!getIntent().getStringExtra("title").endsWith("新增")&&!getIntent().getStringExtra("title").endsWith("编辑")){
                        mFreeToolbar.setActionTextVisible(false);
                    }
                }else {
                    showToast("送宰检验未完成");
                }


                break;
        }
    }
}
