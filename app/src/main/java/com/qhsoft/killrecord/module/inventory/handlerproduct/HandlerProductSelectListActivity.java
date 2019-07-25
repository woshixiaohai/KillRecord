package com.qhsoft.killrecord.module.inventory.handlerproduct;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.base.MyListAdapter;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.HandlerProductListBean;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.reactivex.disposables.Disposable;

public class HandlerProductSelectListActivity extends BaseActivity {


    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;
    @Bind(R.id.mRecyclerView)
    MyRecyclerView mRecyclerView;
    private SpaceDividerDecoration spaceDividerDecoration;
    private KillListAdapter killListAdapter;
    private int page = 1;


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
                CustomerQueryDialog customerQueryDialog = new CustomerQueryDialog(HandlerProductSelectListActivity.this);
                customerQueryDialog.showMenu(mFreeToolbar);
                customerQueryDialog.setTitleText("检验编号:", "畜禽货主:");
                customerQueryDialog.setOnQueryListener(new CustomerQueryDialog.OnQueryListener() {
                    @Override
                    public void onQuery(String value_1, String value_2) {
                        page = 1;
                        killListAdapter.clearList();
                        getList(value_1, value_2);
                    }
                });
            }
        });
        mFreeToolbar.setActionText(R.id.toolbar_add, "确定");
        mFreeToolbar.setOnActionClickListener(R.id.toolbar_add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post( killListAdapter.getSelectList(),"getHandlerProductList");
                finish();



            }
        });
        killListAdapter = new KillListAdapter(KillListAdapter.STATE_LOADING);
        mRecyclerView.setLoadMoreAdapter(killListAdapter);
        mRecyclerView.setManager();
        getList(null, null);


    }

    private void getList(String billno, String supplyname) {
        requestData(HttpHandler.create(InventoryService.class)
                .getHandlerProductList(UserHelper.getSessionId(), billno, supplyname, page, MyApp.PAGE_SIZE))
                .subscribe(new TaskObserver<HandlerProductListBean>() {
                    @Override
                    public void onSuccess(HandlerProductListBean value) {
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

    private class KillListAdapter extends MyListAdapter<HandlerProductListBean.RowsBean> {
        private List<HandlerProductListBean.RowsBean> list;

        public KillListAdapter(int load_state, List<HandlerProductListBean.RowsBean> list) {
            super(load_state, list);
            this.list = list;
        }

        public KillListAdapter(int load_state) {
            super(load_state);
        }


        @Override
        protected void onBindData(HandlerProductListBean.RowsBean bean, TextView tv) {
            StringBuilder sb = new StringBuilder();
            sb.append("转入日期：" + TimeUtil.getDateStr(bean.getJcdate()) + "\n");
            sb.append("检验编号：" + bean.getBillno() + "\n");
            sb.append("类型：" + bean.getAnimType() + "\n");
            sb.append("待宰圈号：" + bean.getCircleNo() + "\n");
            sb.append("货主：" + bean.getSupplyname() + "\n");
            sb.append("处理原因：" + bean.getBearNote() + "\n");
            sb.append("检验类型：" + bean.getTestType() + "\n");
            sb.append("产品名称：" + bean.getBruteName() + "\n");
            sb.append("数量：" + bean.getBearInvenQty() + "");


            tv.setText(sb.toString());
        }

        @Override
        protected void onItemClick(HandlerProductListBean.RowsBean rowsBean) {

        }

        @Override
        protected int getResId() {
            return R.layout.layout_select_list;
        }

        @Override
        protected void onCheck(HandlerProductListBean.RowsBean rowsBean, boolean isChecked) {
            rowsBean.setSelect(isChecked);
        }

        public List<HandlerProductListBean.RowsBean> getSelectList() {
            List<HandlerProductListBean.RowsBean> list_select = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isSelect()) {
                    list_select.add(list.get(i));
                }


            }


            return list_select;
        }


    }


}
