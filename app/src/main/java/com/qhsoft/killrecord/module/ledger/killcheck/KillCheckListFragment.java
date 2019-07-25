package com.qhsoft.killrecord.module.ledger.killcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseFragment;
import com.qhsoft.killrecord.base.MyListAdapter;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.KillCheckListBean;
import com.qhsoft.killrecord.model.remote.service.KillCheckService;
import com.qhsoft.killrecord.net.BaseBean;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.util.DialogFactory;
import com.qhsoft.killrecord.util.TimeUtil;
import com.qhsoft.killrecord.view.decoration.SpaceDividerDecoration;
import com.qhsoft.killrecord.view.dialog.OnSheetSelectedListener;
import com.qhsoft.killrecord.view.myrecyclerview.MyRecyclerView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.List;

import butterknife.Bind;
import io.reactivex.disposables.Disposable;

public class KillCheckListFragment extends BaseFragment {
    @Bind(R.id.mRecyclerView)
    MyRecyclerView mRecyclerView;
    private SpaceDividerDecoration spaceDividerDecoration;
    private KillListAdapter killListAdapter;
    private int page = 1;
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_killcheck_all;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        killListAdapter = new KillListAdapter(KillListAdapter.STATE_LOADING);
        mRecyclerView.setLoadMoreAdapter(killListAdapter);
        mRecyclerView.setManager();
        getList(billno, supplyname);

    }
    private String billno;
    private String supplyname;
    public void queryList(String billno, String supplyname){
        this.billno=billno;
        this.supplyname=supplyname;
        page=1;
        if(killListAdapter!=null){
            killListAdapter.clearList();
        }
        getList(billno,supplyname);

    }
    @Subscriber(tag = MyApp.REFRESH_LIST)
    private void onRefulsh(String str){
        page = 1;
        killListAdapter.clearList();
        getList(null,null);
    }

    public void getList(final String billno, final String supplyname) {
        requestData(HttpHandler.create(KillCheckService.class)
                .getKillCheckList(UserHelper.getSessionId(), billno, supplyname,getArguments().getString("isSuccess"), page, MyApp.PAGE_SIZE))
                .subscribe(new TaskObserver<KillCheckListBean>() {
                    @Override
                    public void onSuccess(KillCheckListBean value) {
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
                                    getList(billno, supplyname);
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

    private class KillListAdapter extends MyListAdapter<KillCheckListBean.RowsBean> {
        private List<KillCheckListBean.RowsBean> list;

        public KillListAdapter(int load_state, List<KillCheckListBean.RowsBean> list) {
            super(load_state, list);
            this.list = list;
        }

        public KillListAdapter(int load_state) {
            super(load_state);
        }


        @Override
        protected void onBindData(KillCheckListBean.RowsBean bean, TextView tv) {
            StringBuilder sb = new StringBuilder();
            sb.append("检验时间：" + TimeUtil.getDateStr(bean.getCheckDate()) + "\n");
            sb.append("送检编号：" + bean.getBillno() + "\n");
            sb.append("宰后编号：" + bean.getZhBillno() + "\n");
            sb.append("畜禽名称：" + bean.getBruteName() + "\n");
            sb.append("待宰圈号：" + bean.getCircleNo()+"\n");
            sb.append("急宰数量：" + bean.getWorryQty() + "\n");
            sb.append("畜禽货主：" + bean.getSupplyname() + "\n");
            sb.append("畜禽种类：" + bean.getBruteType() + "\n");
            sb.append("准宰数量：" + bean.getAimQty() + "\n");
            if(bean.getIsSuccess()!=null&&!bean.getIsSuccess().equals("")){
                if(bean.getIsSuccess().equals("0")){
                    sb.append("屠宰状态：未完成");
                }else if(bean.getIsSuccess().equals("1")){
                    sb.append("屠宰状态：已完成");
                }
            }



            tv.setText(sb.toString());
        }

        @Override
        protected void onItemClick(final KillCheckListBean.RowsBean bean) {
            DialogFactory.createSheetDialogBuilder().addActionArray(new String[]{"查看","编辑","删除"})
                    .setOnSheetSelectedListener(new OnSheetSelectedListener() {
                        @Override
                        public void onSheetSelected(int itemPosition, String actionName) {
                            if(actionName.equals("查看")){
                                Intent intent = new Intent(getActivity(), KillCheckAddActivity.class);
                                intent.putExtra("id",bean.getId());
                                intent.putExtra("zhId",bean.getZhId());
                                intent.putExtra("title",getActivity().getIntent().getStringExtra("title"));
                                startActivity(intent);

                            }else if(actionName.equals("编辑")){
                                Intent intent = new Intent(getActivity(), KillCheckAddActivity.class);
                                intent.putExtra("id",bean.getId());
                                intent.putExtra("zhId",bean.getZhId());
                                intent.putExtra("title",getActivity().getIntent().getStringExtra("title")+actionName);
                                startActivity(intent);
                            }else if(actionName.endsWith("删除")){
                                delItem(bean.getId());
                            }

                        }
                    }).show(getActivity());




        }
    }
    private void delItem(String id) {
        requestData(HttpHandler.create(KillCheckService.class)
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
