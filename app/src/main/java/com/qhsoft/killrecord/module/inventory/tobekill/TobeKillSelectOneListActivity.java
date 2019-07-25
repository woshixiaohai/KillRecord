package com.qhsoft.killrecord.module.inventory.tobekill;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.base.MyListAdapter;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.TobeKillListBean;
import com.qhsoft.killrecord.model.remote.service.InventoryService;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.util.TimeUtil;
import com.qhsoft.killrecord.view.FreeToolbar;
import com.qhsoft.killrecord.view.decoration.SpaceDividerDecoration;
import com.qhsoft.killrecord.view.myrecyclerview.MyRecyclerView;
import com.qhsoft.killrecord.view.query.CustomerQueryDialog;

import org.simple.eventbus.EventBus;

import java.util.List;

import butterknife.Bind;
import io.reactivex.disposables.Disposable;

public class TobeKillSelectOneListActivity extends BaseActivity {


    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;
    @Bind(R.id.mRecyclerView)
    MyRecyclerView mRecyclerView;
    private SpaceDividerDecoration spaceDividerDecoration;
    private KillListAdapter killListAdapter;
    private int page=1;


    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_tobe_kill_list;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {


        mFreeToolbar.setTitle(getIntent().getStringExtra("title"));
        mFreeToolbar.setActionText(R.id.toolbar_query, "搜索");
        mFreeToolbar.setOnActionClickListener(R.id.toolbar_query, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerQueryDialog customerQueryDialog = new CustomerQueryDialog(TobeKillSelectOneListActivity.this);
                customerQueryDialog.showMenu(mFreeToolbar);
                customerQueryDialog.setTitleText("进场编号:","畜禽货主:");
                customerQueryDialog.setOnQueryListener(new CustomerQueryDialog.OnQueryListener() {
                    @Override
                    public void onQuery(String value_1, String value_2) {
                        page=1;
                        killListAdapter.clearList();
                        getList(value_1, value_2);
                    }
                });
            }
        });
        mFreeToolbar.setActionViewVisible(R.id.toolbar_add, false);

        killListAdapter = new KillListAdapter(KillListAdapter.STATE_LOADING);
        mRecyclerView.setLoadMoreAdapter(killListAdapter);
        mRecyclerView.setManager();
        getList(null, null);


    }

    private void getList(String billno, String supplyname) {
        requestData(HttpHandler.create(InventoryService.class)
                .getTobekillList(UserHelper.getSessionId(), billno, supplyname,page, MyApp.PAGE_SIZE))
                .subscribe(new TaskObserver<TobeKillListBean>() {
                    @Override
                    public void onSuccess(TobeKillListBean value) {
                        if(value.getRows()==null||value.getRows().size()==0){
                            killListAdapter = new KillListAdapter(KillListAdapter.STATE_EMPTY);
                            mRecyclerView.setLoadMoreAdapter(killListAdapter);
                            mRecyclerView.setManager();
                            return;
                        }

                        if (killListAdapter.getCount()==0) {
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
                                    getList(null,null);
                                }
                            });
                        }else {
                            killListAdapter.addList(value.getRows());
                        }
                        if(value.getTotal()>killListAdapter.getCount()){
                            page++;
                        }else {
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

    private class KillListAdapter extends MyListAdapter<TobeKillListBean.RowsBean> {
        private List<TobeKillListBean.RowsBean> list;

        public KillListAdapter(int load_state, List<TobeKillListBean.RowsBean> list) {
            super(load_state, list);
            this.list = list;
        }

        public KillListAdapter(int load_state) {
            super(load_state);
        }


        @Override
        protected void onBindData(TobeKillListBean.RowsBean bean, TextView tv) {
            StringBuilder sb = new StringBuilder();
            sb.append("进场日期：" + TimeUtil.getDateStr(bean.getJcdate()) + "\n");
            sb.append("进场编号：" + bean.getBillno() + "\n");
            sb.append("畜禽名称：" + bean.getBruteName() + "\n");
            sb.append("畜禽种类：" + bean.getBruteType() + "\n");
            sb.append("待宰圈号：" + bean.getCircleNo() + "\n");
            sb.append("数量：" + bean.getInvenQty() + "\n");
            sb.append("畜禽货主：" + bean.getSupplyname() + "\n");
            sb.append("畜禽产地：" + bean.getFprovinceName() + bean.getFcityName() + bean.getFcountyName() + "");

            tv.setText(sb.toString());
        }

        @Override
        protected void onItemClick(TobeKillListBean.RowsBean bean) {
            EventBus.getDefault().post(bean,"getTobeKillInfo");
            finish();

        }
    }


}
