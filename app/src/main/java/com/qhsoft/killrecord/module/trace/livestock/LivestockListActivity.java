package com.qhsoft.killrecord.module.trace.livestock;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.base.MyListAdapter;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.LivestockListBean;
import com.qhsoft.killrecord.model.remote.service.LivestockService;
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

public class LivestockListActivity extends BaseActivity {


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
        requestData(HttpHandler.create(LivestockService.class)
                .getList(UserHelper.getSessionId(), page, MyApp.PAGE_SIZE))
                .subscribe(new TaskObserver<LivestockListBean>() {
                    @Override
                    public void onSuccess(LivestockListBean value) {
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

    private class KillListAdapter extends MyListAdapter<LivestockListBean.RowsBean> {
        private List<LivestockListBean.RowsBean> list;

        public KillListAdapter(int load_state, List<LivestockListBean.RowsBean> list) {
            super(load_state, list);
            this.list = list;
        }

        public KillListAdapter(int load_state) {
            super(load_state);
        }


        @Override
        protected void onBindData(LivestockListBean.RowsBean bean, TextView tv) {
            StringBuilder sb = new StringBuilder();
            sb.append("送检时间：" + TimeUtil.getDateStr(bean.getCheckDate()) + "\n");
            sb.append("送检编号：" + bean.getBillno() + "\n");
            sb.append("畜禽名称：" + bean.getBruteName() + "\n");
            sb.append("畜禽单位：" + bean.getUnitName() + "\n");
            sb.append("畜禽货主：" + bean.getSupplyname() + "\n");
            sb.append("畜禽种类：" + bean.getBruteType() + "\n");
            if(bean.getAimQty()!=null&&!bean.getAimQty().equals("")&&bean.getWorryQty()!=null&&!bean.getWorryQty().equals("")){
                double qty=Double.valueOf(bean.getAimQty())+Double.valueOf(bean.getWorryQty());
                sb.append("屠宰数量："+qty);
            }else {
                sb.append("屠宰数量：");
            }



            tv.setText(sb.toString());
        }

        @Override
        protected void onItemClick(final LivestockListBean.RowsBean bean) {
            Intent intent=new Intent(LivestockListActivity.this,LivestockCheckActivity.class);
            intent.putExtra("id",bean.getId());
            intent.putExtra("title",getIntent().getStringExtra("title"));
            startActivity(intent);


        }
    }


}
