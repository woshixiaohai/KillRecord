package com.qhsoft.killrecord.module.baseinfo.supply;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.base.MyListAdapter;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.SupplyListBean;
import com.qhsoft.killrecord.model.remote.service.SupplyService;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.view.FreeToolbar;
import com.qhsoft.killrecord.view.decoration.SpaceDividerDecoration;
import com.qhsoft.killrecord.view.myrecyclerview.MyRecyclerView;
import com.qhsoft.killrecord.view.query.CustomerQueryDialog;

import org.simple.eventbus.EventBus;

import java.util.List;

import butterknife.Bind;
import io.reactivex.disposables.Disposable;

public class SupplyListActivity extends BaseActivity {


    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;
    @Bind(R.id.mRecyclerView)
    MyRecyclerView mRecyclerView;
    private SpaceDividerDecoration spaceDividerDecoration;
    private KillListAdapter killListAdapter;
    private int page = 1;


    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_supply_list;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {


        mFreeToolbar.setTitle(getIntent().getStringExtra("title"));
        mFreeToolbar.setActionText(R.id.toolbar_save, "搜索");
        mFreeToolbar.setOnActionClickListener(R.id.toolbar_save, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerQueryDialog customerQueryDialog = new CustomerQueryDialog(SupplyListActivity.this);
                customerQueryDialog.showMenu(mFreeToolbar);
                customerQueryDialog.setTitleText("经营者名称:", "");
                customerQueryDialog.setTwoTextInvisible();
                customerQueryDialog.setOnQueryListener(new CustomerQueryDialog.OnQueryListener() {
                    @Override
                    public void onQuery(String value_1, String value_2) {
                        page = 1;
                        killListAdapter.clearList();
                        getList(value_1);
                    }
                });
            }
        });

        killListAdapter = new KillListAdapter(KillListAdapter.STATE_LOADING);
        mRecyclerView.setLoadMoreAdapter(killListAdapter);
        mRecyclerView.setManager();
        getList(null);


    }

    private void getList(String fmanageName) {
        requestData(HttpHandler.create(SupplyService.class)
                .getList(UserHelper.getSessionId(), fmanageName, page, MyApp.PAGE_SIZE))
                .subscribe(new TaskObserver<SupplyListBean>() {
                    @Override
                    public void onSuccess(SupplyListBean value) {
                        if (value.getRows() == null || value.getRows().size() == 0) {
                            killListAdapter = new KillListAdapter(KillListAdapter.STATE_EMPTY);
                            mRecyclerView.setLoadMoreAdapter(killListAdapter);
                            mRecyclerView.setManager();
                            return;
                        }

                        if (killListAdapter.getCount() == 0) {
                            killListAdapter = new KillListAdapter(KillListAdapter.STATE_SUCCESS, value.getRows());
                            mRecyclerView.setLoadMoreAdapter(killListAdapter);
                            mRecyclerView.setManager();
                            if (spaceDividerDecoration != null) {
                                mRecyclerView.removeItemDecoration(spaceDividerDecoration);
                            }
                            spaceDividerDecoration = new SpaceDividerDecoration(true);
                            mRecyclerView.addItemDecoration(spaceDividerDecoration);

                            mRecyclerView.setOnLoadMoreListener(new MyRecyclerView.OnLoadMoreListener() {
                                @Override
                                public void onloadMore() {
                                    getList(null);
                                }
                            });
                        } else {
                            killListAdapter.addList(value.getRows());
                        }
                        if (value.getTotal() > killListAdapter.getCount()) {
                            page++;
                        } else {
                            mRecyclerView.setOnLoadMoreListener(null);
                            killListAdapter.setLoadMoreText("没有更多了");
                        }


                    }

                    @Override
                    public void onFailure(FailureMessage failureMessage) {
                        killListAdapter = new KillListAdapter(KillListAdapter.STATE_FAILURE);
                        mRecyclerView.setLoadMoreAdapter(killListAdapter);
                        mRecyclerView.setManager();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                });
    }

    private class KillListAdapter extends MyListAdapter<SupplyListBean.RowsBean> {
        private List<SupplyListBean.RowsBean> list;

        public KillListAdapter(int load_state, List<SupplyListBean.RowsBean> list) {
            super(load_state, list);
            this.list = list;
        }

        public KillListAdapter(int load_state) {
            super(load_state);
        }


        @Override
        protected void onBindData(SupplyListBean.RowsBean bean, TextView tv) {
            StringBuilder sb = new StringBuilder();
            sb.append("企业名称：" + bean.getFmanageName() + "\n");
            sb.append("企业类型：" + bean.getFmanageType() + "\n");
            if (bean.getFmanageType().equals("个人")) {
                sb.append("身份证号：" + bean.getFidCardNo() + "\n");
            } else {
                sb.append("信用代码：" + bean.getFcreditCode() + "\n");
            }
            sb.append("行政划分：" + bean.getFprovinceName() + bean.getFcityName() + bean.getFcountyDistrictName() + "");
            tv.setText(sb.toString());
        }

        @Override
        protected void onItemClick(SupplyListBean.RowsBean bean) {
            if(getIntent().getStringExtra("title").startsWith("选择")){
                EventBus.getDefault().post(bean,"getSupplyInfo");
                finish();
            }


        }
    }


}
