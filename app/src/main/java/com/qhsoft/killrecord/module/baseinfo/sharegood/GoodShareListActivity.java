package com.qhsoft.killrecord.module.baseinfo.sharegood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.base.MyListAdapter;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.ShareGoodListBean;
import com.qhsoft.killrecord.model.remote.service.GoodShareService;
import com.qhsoft.killrecord.net.BaseBean;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.view.FreeToolbar;
import com.qhsoft.killrecord.view.decoration.SpaceDividerDecoration;
import com.qhsoft.killrecord.view.myrecyclerview.MyRecyclerView;
import com.qhsoft.killrecord.view.query.CustomerQueryDialog;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.List;

import butterknife.Bind;
import io.reactivex.disposables.Disposable;

public class GoodShareListActivity extends BaseActivity {


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
                CustomerQueryDialog customerQueryDialog = new CustomerQueryDialog(GoodShareListActivity.this);
                customerQueryDialog.showMenu(mFreeToolbar);
                customerQueryDialog.setTitleText("商品名称:", "条形码:");

                customerQueryDialog.setOnQueryListener(new CustomerQueryDialog.OnQueryListener() {
                    @Override
                    public void onQuery(String value_1, String value_2) {
                        page = 1;
                        killListAdapter.clearList();
                        getList(value_1,value_2);
                    }
                });
            }
        });
        mFreeToolbar.setActionText(R.id.toolbar_add, "新增");
        mFreeToolbar.setOnActionClickListener(R.id.toolbar_add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodShareListActivity.this, GoodShareAddActivity.class);
                intent.putExtra("title",getIntent().getStringExtra("title")+"新增");
                startActivity(intent);

            }
        });
        killListAdapter = new KillListAdapter(KillListAdapter.STATE_LOADING);
        mRecyclerView.setLoadMoreAdapter(killListAdapter);
        mRecyclerView.setManager();
        getList(null,null);


    }
    @Subscriber(tag = MyApp.REFRESH_LIST)
    private void onRefulsh(String str){
        page = 1;
        killListAdapter.clearList();
        getList(null,null);
    }


    private void getList(String name,String barCode) {
        requestData(HttpHandler.create(GoodShareService.class)
                .getList(UserHelper.getSessionId(), name,barCode, page, MyApp.PAGE_SIZE))
                .subscribe(new TaskObserver<ShareGoodListBean>() {
                    @Override
                    public void onSuccess(ShareGoodListBean value) {
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
                                    getList(null,null);
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

    private class KillListAdapter extends MyListAdapter<ShareGoodListBean.RowsBean> {
        private List<ShareGoodListBean.RowsBean> list;

        public KillListAdapter(int load_state, List<ShareGoodListBean.RowsBean> list) {
            super(load_state, list);
            this.list = list;
        }

        public KillListAdapter(int load_state) {
            super(load_state);
        }


        @Override
        protected void onBindData(ShareGoodListBean.RowsBean bean, TextView tv) {
            StringBuilder sb = new StringBuilder();
            sb.append("条形码：" + bean.getBarCode()+ "\n");
            sb.append("商品名称：" + bean.getName() + "\n");
            sb.append("商品分类：" + bean.getIcitemType() + "\n");
            sb.append("保质期类型：" + bean.getKfDateType() + "");
            tv.setText(sb.toString());
        }

        @Override
        protected void onItemClick(final ShareGoodListBean.RowsBean bean) {
            Intent intent = new Intent(GoodShareListActivity.this, GoodShareAddActivity.class);
            intent.putExtra("id",bean.getId());
            intent.putExtra("title",getIntent().getStringExtra("title"));
            startActivity(intent);

//            DialogFactory.createSheetDialogBuilder().addActionArray(new String[]{"查看","编辑","删除"})
//                    .setOnSheetSelectedListener(new OnSheetSelectedListener() {
//                        @Override
//                        public void onSheetSelected(int itemPosition, String actionName) {
//                            if(actionName.equals("查看")){
//                                Intent intent = new Intent(GoodShareListActivity.this, GoodShareAddActivity.class);
//                                intent.putExtra("id",bean.getId());
//                                intent.putExtra("title",getIntent().getStringExtra("title"));
//                                startActivity(intent);
//
//                            }else if(actionName.equals("编辑")){
//                                Intent intent = new Intent(GoodShareListActivity.this, GoodShareAddActivity.class);
//                                intent.putExtra("id",bean.getId());
//                                intent.putExtra("title",getIntent().getStringExtra("title")+actionName);
//                                startActivity(intent);
//                            }else if(actionName.endsWith("删除")){
//                                delItem(bean.getId());
//                            }
//
//                        }
//                    }).show(GoodShareListActivity.this);

        }
    }
    private void delItem(String id) {
        requestData(HttpHandler.create(GoodShareService.class)
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
