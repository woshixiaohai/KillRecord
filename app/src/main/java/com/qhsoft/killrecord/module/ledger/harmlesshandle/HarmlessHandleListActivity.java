package com.qhsoft.killrecord.module.ledger.harmlesshandle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.base.MyListAdapter;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.HarmlessHandlerListBean;
import com.qhsoft.killrecord.model.remote.service.HarmlessHandleService;
import com.qhsoft.killrecord.net.BaseBean;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.util.DialogFactory;
import com.qhsoft.killrecord.util.TimeUtil;
import com.qhsoft.killrecord.view.FreeToolbar;
import com.qhsoft.killrecord.view.decoration.SpaceDividerDecoration;
import com.qhsoft.killrecord.view.dialog.OnSheetSelectedListener;
import com.qhsoft.killrecord.view.myrecyclerview.MyRecyclerView;
import com.qhsoft.killrecord.view.query.CustomerQueryDialog;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.List;

import butterknife.Bind;
import io.reactivex.disposables.Disposable;

public class HarmlessHandleListActivity extends BaseActivity {


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
                CustomerQueryDialog customerQueryDialog = new CustomerQueryDialog(HarmlessHandleListActivity.this);
                customerQueryDialog.showMenu(mFreeToolbar);
                customerQueryDialog.setTitleText("编号:", "负责人:");
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
        mFreeToolbar.setActionText(R.id.toolbar_add, "新增");
        mFreeToolbar.setOnActionClickListener(R.id.toolbar_add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HarmlessHandleListActivity.this, HarmlessHandleAddActivity.class);
                intent.putExtra("title",getIntent().getStringExtra("title")+"新增");
                startActivity(intent);

            }
        });
        killListAdapter = new KillListAdapter(KillListAdapter.STATE_LOADING);
        mRecyclerView.setLoadMoreAdapter(killListAdapter);
        mRecyclerView.setManager();
        getList(null, null);


    }
    @Subscriber(tag = MyApp.REFRESH_LIST)
    private void onRefulsh(String str){
        page = 1;
        killListAdapter.clearList();
        getList(null,null);
    }

    private void getList(String billno, String supplyname) {
        requestData(HttpHandler.create(HarmlessHandleService.class)
                .getList(UserHelper.getSessionId(), billno, supplyname, page, MyApp.PAGE_SIZE))
                .subscribe(new TaskObserver<HarmlessHandlerListBean>() {
                    @Override
                    public void onSuccess(HarmlessHandlerListBean value) {
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

    private class KillListAdapter extends MyListAdapter<HarmlessHandlerListBean.RowsBean> {
        private List<HarmlessHandlerListBean.RowsBean> list;

        public KillListAdapter(int load_state, List<HarmlessHandlerListBean.RowsBean> list) {
            super(load_state, list);
            this.list = list;
        }

        public KillListAdapter(int load_state) {
            super(load_state);
        }


        @Override
        protected void onBindData(HarmlessHandlerListBean.RowsBean bean, TextView tv) {
            StringBuilder sb = new StringBuilder();
            sb.append("处理时间：" + TimeUtil.getDateStr(bean.getHandleDate()) + "\n");
            sb.append("处理编号：" + bean.getBillno() + "\n");
            sb.append("负责人：" + bean.getFunctionary() + "\n");
            sb.append("监督人：" + bean.getSuperviseName() + "");




            tv.setText(sb.toString());
        }

        @Override
        protected void onItemClick(final HarmlessHandlerListBean.RowsBean bean) {
            DialogFactory.createSheetDialogBuilder().addActionArray(new String[]{"查看","编辑","删除"})
                    .setOnSheetSelectedListener(new OnSheetSelectedListener() {
                        @Override
                        public void onSheetSelected(int itemPosition, String actionName) {
                            if(actionName.equals("查看")){
                                Intent intent = new Intent(HarmlessHandleListActivity.this, HarmlessHandleAddActivity.class);
                                intent.putExtra("id",bean.getId());
                                intent.putExtra("title",getIntent().getStringExtra("title"));
                                startActivity(intent);

                            }else if(actionName.equals("编辑")){
                                Intent intent = new Intent(HarmlessHandleListActivity.this, HarmlessHandleAddActivity.class);
                                intent.putExtra("id",bean.getId());
                                intent.putExtra("title",getIntent().getStringExtra("title")+actionName);
                                startActivity(intent);
                            }else if(actionName.endsWith("删除")){
                                delItem(bean.getId());
                            }

                        }
                    }).show(HarmlessHandleListActivity.this);

        }
    }
    private void delItem(String id) {
        requestData(HttpHandler.create(HarmlessHandleService.class)
                .doDel(UserHelper.getSessionId(),
                        id))
                .subscribe(new TaskObserver<BaseBean>() {
                    @Override
                    public void onSuccess(BaseBean value) {
                        showToast(value.getMsg());
                        if(value.isSuccess()){
                            EventBus.getDefault().post("", MyApp.REFRESH_LIST);

                        }

                    }

                    @Override
                    public void onFailure(FailureMessage failureMessage) {
                        showToast(failureMessage.getMessageText());
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                });


    }



}
