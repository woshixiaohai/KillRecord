package com.qhsoft.killrecord.module.ledger.killcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.view.FreeToolbar;
import com.qhsoft.killrecord.view.query.CustomerQueryDialog;

import butterknife.Bind;

public class KillCheckListActivity extends BaseActivity {


    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;
    @Bind(R.id.mTabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.mViewPager)
    ViewPager mViewPager;


    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_killcheck_list;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {


        mFreeToolbar.setTitle(getIntent().getStringExtra("title"));
        mFreeToolbar.setActionText(R.id.toolbar_query, "搜索");
        mFreeToolbar.setOnActionClickListener(R.id.toolbar_query, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerQueryDialog customerQueryDialog = new CustomerQueryDialog(KillCheckListActivity.this);
                customerQueryDialog.showMenu(mFreeToolbar);
                customerQueryDialog.setTitleText("送检编号:", "畜禽货主:");
                customerQueryDialog.setOnQueryListener(new CustomerQueryDialog.OnQueryListener() {
                    @Override
                    public void onQuery(String value_1, String value_2) {
                        if(mViewPager.getCurrentItem()==0){
                            killCheckListFragment_1.queryList(value_1,value_2);
                        }else if(mViewPager.getCurrentItem()==1){
                            killCheckListFragment_2.queryList(value_1,value_2);
                        }else if(mViewPager.getCurrentItem()==2){
                            killCheckListFragment_3.queryList(value_1,value_2);
                        }




                    }
                });
            }
        });
        mFreeToolbar.setActionText(R.id.toolbar_add, "新增");
        mFreeToolbar.setOnActionClickListener(R.id.toolbar_add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KillCheckListActivity.this, KillCheckAddActivity.class);
                intent.putExtra("title",getIntent().getStringExtra("title")+"新增");
                startActivity(intent);

            }
        });
        KillCheckAdapter killCheckAdapter = new KillCheckAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(killCheckAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }


    private String[] title = {"全部", "未完成", "已完成"};
    private KillCheckListFragment killCheckListFragment_1;
    private KillCheckListFragment killCheckListFragment_2;
    private KillCheckListFragment killCheckListFragment_3;

    private class KillCheckAdapter extends FragmentPagerAdapter {

        public KillCheckAdapter(FragmentManager fm) {
            super(fm);
            killCheckListFragment_1 = new KillCheckListFragment();
            killCheckListFragment_2 = new KillCheckListFragment();
            killCheckListFragment_3 = new KillCheckListFragment();
        }

        @Override
        public Fragment getItem(int i) {

            Bundle bundle = new Bundle();
            if (i == 0) {
                bundle.putString("isSuccess", null);
                killCheckListFragment_1.setArguments(bundle);
                return killCheckListFragment_1;
            } else if (i == 1) {
                bundle.putString("isSuccess", "0");
                killCheckListFragment_2.setArguments(bundle);
                return killCheckListFragment_2;
            } else if (i == 2) {
                bundle.putString("isSuccess", "1");
                killCheckListFragment_3.setArguments(bundle);
                return killCheckListFragment_3;
            }
            bundle.putString("isSuccess", null);
            killCheckListFragment_1.setArguments(bundle);
            return killCheckListFragment_1;



        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }


}
