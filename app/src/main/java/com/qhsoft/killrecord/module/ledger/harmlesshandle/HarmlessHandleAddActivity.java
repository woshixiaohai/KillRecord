package com.qhsoft.killrecord.module.ledger.harmlesshandle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.luyinbros.combineview.SimpleListView;
import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.base.BaseViewHolder;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.HandlerProductListBean;
import com.qhsoft.killrecord.model.remote.bean.HarmlessHandlerDetailBean;
import com.qhsoft.killrecord.model.remote.bean.PeopleListBean;
import com.qhsoft.killrecord.model.remote.service.HarmlessHandleService;
import com.qhsoft.killrecord.module.baseinfo.people.PeopleSelectOneListActivity;
import com.qhsoft.killrecord.module.inventory.handlerproduct.HandlerProductSelectListActivity;
import com.qhsoft.killrecord.net.BaseBean;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.util.DialogFactory;
import com.qhsoft.killrecord.util.NullableUtil;
import com.qhsoft.killrecord.util.TimeUtil;
import com.qhsoft.killrecord.view.FreeToolbar;
import com.qhsoft.killrecord.view.dialog.OnDateSelectedListener;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class HarmlessHandleAddActivity extends BaseActivity {
    private ItemAdapter itemAdapter;
    @Bind(R.id.mSimpleListView)
    public SimpleListView mSimpleListView;
    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;
    @Bind(R.id.tv_billno)
    TextView tv_billno;
    @Bind(R.id.tv_handleDate)
    TextView tv_handleDate;
    @Bind(R.id.tv_functionary)
    TextView tv_functionary;
    @Bind(R.id.tv_superviseName)
    TextView tv_superviseName;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_harmless_add;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mFreeToolbar.setTitle(getIntent().getStringExtra("title"));
        if (getIntent().getStringExtra("title").endsWith("新增")) {
            String billno = "CL" + System.currentTimeMillis();
            tv_billno.setText(billno);
            tv_handleDate.setText(TimeUtil.formatSimpleDate(System.currentTimeMillis()));
        }
        if (!getIntent().getStringExtra("title").endsWith("新增") && !getIntent().getStringExtra("title").endsWith("编辑")) {
            mFreeToolbar.setActionTextVisible(false);

        }
        mFreeToolbar.setActionTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_functionary.getText().toString().equals("")) {
                    showToast("负责人未选择");
                    return;
                }
                if (tv_superviseName.getText().toString().equals("")) {
                    showToast("监督人未选择");
                    return;
                }
                if (itemAdapter == null || itemAdapter.getGsonStr().size() == 0) {
                    showToast("未选择畜禽");
                    return;

                }
                JsonObject jsonObject = new JsonObject();


                if (getIntent().getStringExtra("id") != null) {
                    jsonObject.addProperty("id", getIntent().getStringExtra("id"));
                }
                jsonObject.addProperty("billno", tv_billno.getText().toString());
                jsonObject.addProperty("handleDate", tv_handleDate.getText().toString());
                jsonObject.addProperty("functionary", tv_functionary.getText().toString());
                jsonObject.addProperty("superviseName", tv_superviseName.getText().toString());
                if(objBean!=null){
                    if(objBean.getFsonid()!=null){
                        jsonObject.addProperty("fsonid", objBean.getFsonid());
                    }
                    if(objBean.getProjectCode()!=null){
                        jsonObject.addProperty("projectCode", objBean.getProjectCode());
                    }
                }


                jsonObject.add("tBruteHarmHandentryList", itemAdapter.getGsonStr());


                if (getIntent().getStringExtra("id") == null) {
                    requestData(HttpHandler.create(HarmlessHandleService.class)
                            .doAddApp(UserHelper.getSessionId(),
                                    getRequestBody(jsonObject.toString())))
                            .subscribe(new TaskObserver<BaseBean>() {
                                @Override
                                public void onSuccess(BaseBean value) {
                                    showToast(value.getMsg());
                                    if (value.isSuccess()) {
                                        EventBus.getDefault().post("", MyApp.REFRESH_LIST);
                                        finish();
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
                } else {
                    requestData(HttpHandler.create(HarmlessHandleService.class)
                            .doUpdateApp(UserHelper.getSessionId(),
                                    getRequestBody(jsonObject.toString())))
                            .subscribe(new TaskObserver<BaseBean>() {
                                @Override
                                public void onSuccess(BaseBean value) {
                                    showToast(value.getMsg());
                                    if (value.isSuccess()) {
                                        EventBus.getDefault().post("", MyApp.REFRESH_LIST);
                                        finish();
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
        });
        getDetail();

    }

    private HarmlessHandlerDetailBean.ObjBean objBean;

    private void getDetail() {
        if (getIntent().getStringExtra("id") != null) {
            requestData(HttpHandler.create(HarmlessHandleService.class).getDetail(UserHelper.getSessionId(),
                    getIntent().getStringExtra("id")
            )).subscribe(new TaskObserver<HarmlessHandlerDetailBean>() {
                @Override
                public void onSuccess(HarmlessHandlerDetailBean value) {
                    if (value.getObj() != null) {
                        objBean = value.getObj();
                        tv_billno.setText(objBean.getBillno());
                        tv_functionary.setText(objBean.getFunctionary());
                        tv_handleDate.setText(TimeUtil.formatSimpleDate(objBean.getHandleDate()));
                        tv_superviseName.setText(objBean.getSuperviseName());

                        if (itemAdapter == null) {
                            itemAdapter = new ItemAdapter();
                            mSimpleListView.setAdapter(itemAdapter);
                        }
                        itemAdapter.addItem(objBean.getTBruteHarmHandentryList());

                    }

                }

                @Override
                public void onFailure(FailureMessage failureMessage) {

                }

                @Override
                public void onSubscribe(Disposable d) {

                }
            });


        }
    }

    @Subscriber(tag = "getPeopleInfo")
    private void getPeopleInfo(PeopleListBean.RowsBean bean) {
        if (bean.getCertificateType().equals("负责人")) {
            tv_functionary.setText(bean.getName());
        } else {
            tv_superviseName.setText(bean.getName());
        }

    }

    @Subscriber(tag = "getHandlerProductList")
    private void getHandlerProductList(List<HandlerProductListBean.RowsBean> list) {
        d("size:" + list.size());

        List<HarmlessHandlerDetailBean.ObjBean.TBruteHarmHandentryListBean> list_item = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            HarmlessHandlerDetailBean.ObjBean.TBruteHarmHandentryListBean bean = new HarmlessHandlerDetailBean.ObjBean.TBruteHarmHandentryListBean();
            HandlerProductListBean.RowsBean rowsBean = list.get(i);


            bean.setOutDate(TimeUtil.getDateStr(rowsBean.getJcdate()));
            bean.setProductType(rowsBean.getAnimType());
            bean.setTestType(rowsBean.getTestType());
            bean.setProductName(rowsBean.getBruteName());
            bean.setCircleNo(rowsBean.getCircleNo());
            bean.setListno(rowsBean.getBillno());
            bean.setSupplyname(rowsBean.getSupplyname());
            if (rowsBean.getBearInvenQty() != null && !rowsBean.getBearInvenQty().equals("")) {
                bean.setHandleQty(Double.valueOf(rowsBean.getBearInvenQty()));
            }

            bean.setUnqualifiedNote(rowsBean.getBearNote());
            bean.setUnitName(rowsBean.getUnitName());


            bean.setEntityId(rowsBean.getId());

            list_item.add(bean);
        }

        if (itemAdapter == null) {
            itemAdapter = new ItemAdapter();
            mSimpleListView.setAdapter(itemAdapter);
        }
        itemAdapter.addItem(list_item);
    }

    @OnClick({R.id.tv_select, R.id.tv_handleDate, R.id.tv_functionary, R.id.tv_superviseName})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_select:
                Intent intent = new Intent(HarmlessHandleAddActivity.this, HandlerProductSelectListActivity.class);
                intent.putExtra("title", "选择畜禽");

                startActivity(intent);

                break;
            case R.id.tv_handleDate:
                DialogFactory.createDateBuilder().setOnDateSelectedListener(new OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(String date) {
                        tv_handleDate.setText(date);
                    }
                }).show(HarmlessHandleAddActivity.this);
                break;
            case R.id.tv_functionary:
                intent = new Intent(HarmlessHandleAddActivity.this, PeopleSelectOneListActivity.class);
                intent.putExtra("certificateType", "负责人");
                intent.putExtra("title", "选择负责人");
                startActivity(intent);
                break;
            case R.id.tv_superviseName:
                intent = new Intent(HarmlessHandleAddActivity.this, PeopleSelectOneListActivity.class);
                intent.putExtra("certificateType", "监督人");
                intent.putExtra("title", "选择监督人");
                startActivity(intent);
                break;

        }


    }

    private class ItemAdapter extends SimpleListView.Adapter<ItemHolder> {

        private List<HarmlessHandlerDetailBean.ObjBean.TBruteHarmHandentryListBean> list = new ArrayList<>();

        public List<HarmlessHandlerDetailBean.ObjBean.TBruteHarmHandentryListBean> getList() {
            return list;
        }

        public JsonArray getGsonStr() {
            JsonArray jsonArray = new Gson().toJsonTree(list).getAsJsonArray();
            return jsonArray;

        }


        @Override
        public ItemHolder onCreateHolder(ViewGroup viewGroup, int i) {
            return new ItemHolder(inflate(R.layout.layout_harmless_add_item));
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, final int i) {
            holder.iv_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(i);
                    notifySetChange();
                }
            });
            holder.bindData(list.get(i));

        }

        @Override
        public int getCount() {
            return NullableUtil.listSize(list);
        }

        public void addItem(List<HarmlessHandlerDetailBean.ObjBean.TBruteHarmHandentryListBean> list_child) {
            if (list_child != null && list_child.size() > 0) {
                list.addAll(list_child);
                notifySetChange();

            }

        }
    }

    public class ItemHolder extends BaseViewHolder {
        @Bind(R.id.iv_close)
        ImageView iv_close;
        @Bind(R.id.tv_productName)
        TextView tv_productName;
        @Bind(R.id.tv_productType)
        TextView tv_productType;
        @Bind(R.id.tv_unitName)
        TextView tv_unitName;
        @Bind(R.id.tv_handleQty)
        TextView tv_handleQty;

        @Bind(R.id.tv_supplyname)
        TextView tv_supplyname;
        @Bind(R.id.tv_listno)
        TextView tv_listno;
        @Bind(R.id.tv_testType)
        TextView tv_testType;
        @Bind(R.id.tv_unqualifiedNote)
        TextView tv_unqualifiedNote;
        @Bind(R.id.tv_circleNo)
        TextView tv_circleNo;


        public ItemHolder(View itemView) {
            super(itemView);

        }

        public void bindData(HarmlessHandlerDetailBean.ObjBean.TBruteHarmHandentryListBean bean) {
            tv_productName.setText(bean.getProductName());
            tv_productType.setText(bean.getProductType());
            tv_unitName.setText(bean.getUnitName());
            tv_handleQty.setText(bean.getHandleQty() + "");
            tv_supplyname.setText(bean.getSupplyname());
            tv_listno.setText(bean.getListno());
            tv_testType.setText(bean.getTestType());
            tv_unqualifiedNote.setText(bean.getUnqualifiedNote());
            tv_circleNo.setText(bean.getCircleNo());
            bean.setOutDate(bean.getOutDate());
            //编辑
            if (getIntent().getStringExtra("id") != null) {
                bean.setEntityId(bean.getInvId());
            }

        }


    }


}
