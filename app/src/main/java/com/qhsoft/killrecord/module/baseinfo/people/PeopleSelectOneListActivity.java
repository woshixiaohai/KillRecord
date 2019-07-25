package com.qhsoft.killrecord.module.baseinfo.people;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.base.MyListAdapter;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.PeopleListBean;
import com.qhsoft.killrecord.model.remote.service.PeopleService;
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

public class PeopleSelectOneListActivity extends BaseActivity {


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
                CustomerQueryDialog customerQueryDialog = new CustomerQueryDialog(PeopleSelectOneListActivity.this);
                customerQueryDialog.showMenu(mFreeToolbar);
                customerQueryDialog.setTitleText("姓名:", "");
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
        mFreeToolbar.setActionText(R.id.toolbar_add, "新增");
        mFreeToolbar.setActionViewVisible(R.id.toolbar_add,View.INVISIBLE);

        killListAdapter = new KillListAdapter(KillListAdapter.STATE_LOADING);
        mRecyclerView.setLoadMoreAdapter(killListAdapter);
        mRecyclerView.setManager();
        getList(null);


    }
    @Subscriber(tag = MyApp.REFRESH_LIST)
    private void onRefulsh(String str){
        page = 1;
        killListAdapter.clearList();
        getList(null);
    }

    private void getList(String name) {
        requestData(HttpHandler.create(PeopleService.class)
                .getCoronerList(UserHelper.getSessionId(), name, getIntent().getStringExtra("certificateType"),page, MyApp.PAGE_SIZE))
                .subscribe(new TaskObserver<PeopleListBean>() {
                    @Override
                    public void onSuccess(PeopleListBean value) {
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

    private class KillListAdapter extends MyListAdapter<PeopleListBean.RowsBean> {
        private List<PeopleListBean.RowsBean> list;

        public KillListAdapter(int load_state, List<PeopleListBean.RowsBean> list) {
            super(load_state, list);
            this.list = list;
        }

        public KillListAdapter(int load_state) {
            super(load_state);
        }


        @Override
        protected void onBindData(PeopleListBean.RowsBean bean, TextView tv) {
            StringBuilder sb = new StringBuilder();
            sb.append("人员姓名：" + bean.getName()+ "\n");
            sb.append("人员类型：" + bean.getCertificateType() + "\n");
            sb.append("身份证号：" + bean.getIdCard() + "\n");
            sb.append("联系方式：" + bean.getMobilePhone() );



            tv.setText(sb.toString());
        }

        @Override
        protected void onItemClick(final PeopleListBean.RowsBean bean) {
            EventBus.getDefault().post(bean,"getPeopleInfo");
            finish();

        }
    }




}
