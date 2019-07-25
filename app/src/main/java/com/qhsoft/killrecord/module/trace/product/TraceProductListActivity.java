package com.qhsoft.killrecord.module.trace.product;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.base.MyListAdapter;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.TraceProductListBean;
import com.qhsoft.killrecord.model.remote.service.TraceProductService;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.util.TimeUtil;
import com.qhsoft.killrecord.view.FreeToolbar;
import com.qhsoft.killrecord.view.decoration.SpaceDividerDecoration;
import com.qhsoft.killrecord.view.myrecyclerview.MyRecyclerView;

import java.util.List;

import butterknife.Bind;
import io.reactivex.disposables.Disposable;

public class TraceProductListActivity extends BaseActivity {


    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;
    @Bind(R.id.mRecyclerView)
    MyRecyclerView mRecyclerView;
    private SpaceDividerDecoration spaceDividerDecoration;
    private KillListAdapter killListAdapter;
    private int page = 1;


    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_liststock_list;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {


        mFreeToolbar.setTitle(getIntent().getStringExtra("title"));


        killListAdapter = new KillListAdapter(KillListAdapter.STATE_LOADING);
        mRecyclerView.setLoadMoreAdapter(killListAdapter);
        mRecyclerView.setManager();
        getList(null, null);


    }

    private void getList(String billno, String supplyname) {
        requestData(HttpHandler.create(TraceProductService.class)
                .getList(UserHelper.getSessionId(), page, MyApp.PAGE_SIZE))
                .subscribe(new TaskObserver<TraceProductListBean>() {
                    @Override
                    public void onSuccess(TraceProductListBean value) {
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
                                    getList(null, null);
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

    private class KillListAdapter extends MyListAdapter<TraceProductListBean.RowsBean> {
        private List<TraceProductListBean.RowsBean> list;

        public KillListAdapter(int load_state, List<TraceProductListBean.RowsBean> list) {
            super(load_state, list);
            this.list = list;
        }

        public KillListAdapter(int load_state) {
            super(load_state);
        }


        @Override
        protected void onBindData(TraceProductListBean.RowsBean bean, TextView tv) {
            StringBuilder sb = new StringBuilder();
            sb.append("出场日期：" + TimeUtil.getDateStr(bean.getEnterDate()) + "\n");
            sb.append("出场单号：" + bean.getBillno() + "\n");
            sb.append("产品名称：" + bean.getProductName() + "\n");
            sb.append("畜禽单位：" + bean.getUnitName() + "\n");
            sb.append("畜禽货主：" + bean.getSupplyname() + "\n");
            sb.append("出场数量：" + bean.getDeliveryQty() + "");



            tv.setText(sb.toString());
        }

        @Override
        protected void onItemClick(final TraceProductListBean.RowsBean bean) {
            Intent intent=new Intent(TraceProductListActivity.this,TraceProductCheckActivity.class);
            intent.putExtra("id",bean.getId());
            intent.putExtra("zid",bean.getZid());
            intent.putExtra("title",getIntent().getStringExtra("title"));
            startActivity(intent);


        }
    }


}
