package com.qhsoft.killrecord.module.ledger.outrecoder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
import com.qhsoft.killrecord.model.local.RegionListBean;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.BuyerListBean;
import com.qhsoft.killrecord.model.remote.bean.OutrecordDetailBean;
import com.qhsoft.killrecord.model.remote.bean.ProductStoreListBean;
import com.qhsoft.killrecord.model.remote.service.OutrecordService;
import com.qhsoft.killrecord.module.baseinfo.buyer.BuyerListActivity;
import com.qhsoft.killrecord.module.inventory.productstore.ProductStoreSelectListActivity;
import com.qhsoft.killrecord.net.BaseBean;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.util.DialogFactory;
import com.qhsoft.killrecord.util.NullableUtil;
import com.qhsoft.killrecord.util.TimeUtil;
import com.qhsoft.killrecord.view.FreeToolbar;
import com.qhsoft.killrecord.view.dialog.OnSelectedItemListener;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class OutrecordAddActivity extends BaseActivity {
    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;
    @Bind(R.id.tv_select)
    TextView tv_select;
    @Bind(R.id.mSimpleListView)
    SimpleListView mSimpleListView;
    private ItemAdapter itemAdapter;
    @Bind(R.id.tv_billno)
    TextView tv_billno;
    @Bind(R.id.tv_enterDate)
    TextView tv_enterDate;
    @Bind(R.id.tv_purchaser)
    TextView tv_purchaser;
    @Bind(R.id.tv_phone)
    TextView tv_phone;
    @Bind(R.id.tv_province)
    TextView tv_province;
    @Bind(R.id.tv_city)
    TextView tv_city;
    @Bind(R.id.tv_town)
    TextView tv_town;
    @Bind(R.id.tv_scope)
    TextView tv_scope;
    @Bind(R.id.cb_animalCert)
    CheckBox cb_animalCert;
    @Bind(R.id.cb_meatCert)
    CheckBox cb_meatCert;
    private RegionListBean regionListBean;
    private RegionListBean.DataBean province;
    private RegionListBean.DataBean city;
    private RegionListBean.DataBean area;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_outrecord_add;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mFreeToolbar.setTitle(getIntent().getStringExtra("title"));
        if (getIntent().getStringExtra("title").endsWith("新增")) {
            String billno = "CC" + System.currentTimeMillis();
            tv_billno.setText(billno);
            tv_enterDate.setText(TimeUtil.formatSimpleDate(System.currentTimeMillis()));
        }
        if (!getIntent().getStringExtra("title").endsWith("新增") && !getIntent().getStringExtra("title").endsWith("编辑")) {
            mFreeToolbar.setActionTextVisible(false);
        }
        mFreeToolbar.setActionTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_billno.getText().toString().equals("")){
                    showToast("出场编号未填写");
                    return;
                }
                if(tv_purchaser.getText().toString().equals("")){
                    showToast("购货业主未选择");
                    return;
                }
                if(tv_phone.getText().toString().equals("")){
                    showToast("联系方式未填写");
                    return;
                }
                if(tv_province.getText().toString().equals("")||tv_city.getText().toString().equals("")||tv_town.getText().toString().equals("")){
                    showToast("销售地址省市区未全部选择");
                    return;
                }
                if(tv_scope.getText().toString().equals("")){
                    showToast("经营场所未填写");
                    return;
                }




                if(itemAdapter==null||itemAdapter.getCount()==0){
                    showToast("出场产品未选择");
                    return;

                }
                JsonObject jsonObject=new JsonObject();




                if(getIntent().getStringExtra("id")!=null){
                    jsonObject.addProperty("id",getIntent().getStringExtra("id"));
                }
                jsonObject.addProperty("billno",tv_billno.getText().toString());
                jsonObject.addProperty("enterDate",tv_enterDate.getText().toString());
                jsonObject.addProperty("purchaser",tv_purchaser.getText().toString());
                jsonObject.addProperty("phone",tv_phone.getText().toString());
                jsonObject.addProperty("province",tv_province.getText().toString());
                jsonObject.addProperty("city",tv_city.getText().toString());
                jsonObject.addProperty("town",tv_town.getText().toString());
                jsonObject.addProperty("scope",tv_scope.getText().toString());
                if(cb_animalCert.isChecked()){
                    jsonObject.addProperty("animalCert","1");
                }else {
                    jsonObject.addProperty("animalCert","0");
                }
                if(cb_meatCert.isChecked()){
                    jsonObject.addProperty("meatCert","1");
                }else {
                    jsonObject.addProperty("meatCert","0");
                }
                if(objBean!=null){
                    if(objBean.getFsonid()!=null){
                        jsonObject.addProperty("fsonid", objBean.getFsonid());
                    }
                    if(objBean.getProjectCode()!=null){
                        jsonObject.addProperty("projectCode", objBean.getProjectCode());
                    }
                }





                jsonObject.add("tBruteEnterRecentryList",itemAdapter.getGsonStr());






                if(getIntent().getStringExtra("id")==null){
                    requestData(HttpHandler.create(OutrecordService.class)
                            .doAddApp(UserHelper.getSessionId(),
                                    getRequestBody(jsonObject.toString())))
                            .subscribe(new TaskObserver<BaseBean>() {
                                @Override
                                public void onSuccess(BaseBean value) {
                                    showToast(value.getMsg());
                                    if(value.isSuccess()){
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
                }else {
                    requestData(HttpHandler.create(OutrecordService.class)
                            .doUpdateApp(UserHelper.getSessionId(),
                                    getRequestBody(jsonObject.toString())))
                            .subscribe(new TaskObserver<BaseBean>() {
                                @Override
                                public void onSuccess(BaseBean value) {
                                    showToast(value.getMsg());
                                    if(value.isSuccess()){
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

    private OutrecordDetailBean.ObjBean objBean;
    private void getDetail() {
        if (getIntent().getStringExtra("id") != null) {
            requestData(HttpHandler.create(OutrecordService.class)
                    .getDetail(UserHelper.getSessionId(),
                            getIntent().getStringExtra("id")))
                    .subscribe(new TaskObserver<OutrecordDetailBean>() {
                        @Override
                        public void onSuccess(OutrecordDetailBean value) {
                            objBean = value.getObj();
                            if (objBean != null) {

                                tv_billno.setText(objBean.getBillno());
                                tv_enterDate.setText(TimeUtil.formatSimpleDate(objBean.getEnterDate()));
                                tv_purchaser.setText(objBean.getPurchaser());

                                tv_phone.setText(objBean.getPhone());
                                tv_province.setText(objBean.getProvince());
                                tv_city.setText(objBean.getCity());
                                tv_town.setText(objBean.getTown());
                                tv_scope.setText(objBean.getScope());
                                if(objBean.getAnimalCert()!=null&&objBean.getAnimalCert().equals("1")){
                                    cb_animalCert.setChecked(true);
                                }
                                if(objBean.getMeatCert()!=null&&objBean.getMeatCert().equals("1")){
                                    cb_meatCert.setChecked(true);
                                }




                                if (itemAdapter == null) {
                                    itemAdapter = new ItemAdapter();
                                    mSimpleListView.setAdapter(itemAdapter);
                                }
                                itemAdapter.addItem(objBean.getTBruteEnterRecentryList());








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

    @Subscriber(tag = "getBuyerInfo")
    private void getBuyerInfo(BuyerListBean.RowsBean bean) {
        tv_purchaser.setText(bean.getFmanageName());
        tv_purchaser.setTag(bean.getId());
        tv_phone.setText(bean.getFcontackPhone());
        tv_province.setText(bean.getFprovinceName());
        tv_city.setText(bean.getFcityName());
        tv_town.setText(bean.getFcountyDistrictName());
        tv_scope.setText(bean.getFmanageSite());

    }

    @Subscriber(tag = "getProductStoreList")
    private void getProductStoreList(List<ProductStoreListBean.RowsBean> list) {
        List<OutrecordDetailBean.ObjBean.TBruteEnterRecentryListBean> list_item = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            OutrecordDetailBean.ObjBean.TBruteEnterRecentryListBean bean = new OutrecordDetailBean.ObjBean.TBruteEnterRecentryListBean();
            ProductStoreListBean.RowsBean rowsBean = list.get(i);
            bean.setProductName(rowsBean.getProductName());
            bean.setUnitName(rowsBean.getUnitName());
            bean.setDeliveryQty(Double.valueOf(rowsBean.getInvenQty()));
            bean.setSupplyname(rowsBean.getSupplyname());
            bean.setMeatCert(rowsBean.getCertificateNo());
            bean.setAnimalCert(rowsBean.getAnimCertificateNo());
            bean.setZid(rowsBean.getZid());
            bean.setJcBillno(rowsBean.getJcBillno());
            bean.setCircleNo(rowsBean.getCircleNo());


            list_item.add(bean);


        }


        if (itemAdapter == null) {
            itemAdapter = new ItemAdapter();
            mSimpleListView.setAdapter(itemAdapter);
        }
        itemAdapter.addItem(list_item);


    }

    @OnClick({R.id.tv_select, R.id.tv_purchaser,R.id.tv_province,R.id.tv_city,R.id.tv_town})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_select:
                Intent intent=new Intent(OutrecordAddActivity.this, ProductStoreSelectListActivity.class);
                intent.putExtra("title","选择出场产品");
                startActivity(intent);


                break;
            case R.id.tv_purchaser:
                 intent = new Intent(OutrecordAddActivity.this, BuyerListActivity.class);
                intent.putExtra("title", "选择购货商");
                startActivity(intent);
                break;
            case R.id.tv_province:
                if(regionListBean==null){
                    return;
                }



                List<String> list_str=new ArrayList<>();
                for(int i=0;i<regionListBean.getData().size();i++){
                    list_str.add(regionListBean.getData().get(i).getRegion_name());
                }



                DialogFactory.createSingleBuilder().addSelectList(list_str).setTitle("选择省")
                        .setOnSelectedListener(new OnSelectedItemListener() {
                            @Override
                            public void onSelectedItem(int position) {
                                province=regionListBean.getData().get(position);
                                tv_province.setText(province.getRegion_name());
                            }
                        }).show(OutrecordAddActivity.this);

                break;
            case R.id.tv_city:
                if(province==null){
                    return;
                }



                list_str=new ArrayList<>();
                for(int i=0;i<province.getSon().size();i++){
                    list_str.add(province.getSon().get(i).getRegion_name());
                }



                DialogFactory.createSingleBuilder().addSelectList(list_str).setTitle("选择城市")
                        .setOnSelectedListener(new OnSelectedItemListener() {
                            @Override
                            public void onSelectedItem(int position) {
                                city=province.getSon().get(position);
                                tv_city.setText(city.getRegion_name());
                            }
                        }).show(OutrecordAddActivity.this);
                break;
            case R.id.tv_town:
                if(city==null){
                    return;
                }



                list_str=new ArrayList<>();
                for(int i=0;i<city.getSon().size();i++){
                    list_str.add(city.getSon().get(i).getRegion_name());
                }



                DialogFactory.createSingleBuilder().addSelectList(list_str).setTitle("选择城市")
                        .setOnSelectedListener(new OnSelectedItemListener() {
                            @Override
                            public void onSelectedItem(int position) {
                                area=city.getSon().get(position);
                                tv_town.setText(area.getRegion_name());
                            }
                        }).show(OutrecordAddActivity.this);
                break;

        }


    }

    private class ItemAdapter extends SimpleListView.Adapter<ItemHolder> {
        private List<OutrecordDetailBean.ObjBean.TBruteEnterRecentryListBean> list = new ArrayList<>();


        @Override
        public ItemHolder onCreateHolder(ViewGroup viewGroup, int i) {
            return new ItemHolder(inflate(R.layout.layout_outrecord_item));
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
            holder.binData(list.get(i));

        }

        @Override
        public int getCount() {
            return NullableUtil.listSize(list);
        }

        public void addItem(List<OutrecordDetailBean.ObjBean.TBruteEnterRecentryListBean> list_child) {
            if (list_child != null && list_child.size() > 0) {
                list.addAll(list_child);
                notifySetChange();

            }

        }

        public JsonArray getGsonStr(){
            JsonArray jsonArray=new Gson().toJsonTree(list).getAsJsonArray();
            return jsonArray;

        }
    }

    public class ItemHolder extends BaseViewHolder {
        @Bind(R.id.iv_close)
        ImageView iv_close;
        @Bind(R.id.tv_productName)
        TextView tv_productName;

        @Bind(R.id.tv_unitName)
        TextView tv_unitName;
        @Bind(R.id.tv_deliveryQty)
        TextView tv_deliveryQty;
        @Bind(R.id.tv_supplyname)
        TextView tv_supplyname;

        @Bind(R.id.tv_meatCert)
        TextView tv_meatCert;


        public ItemHolder(View itemView) {
            super(itemView);

        }

        public void binData(OutrecordDetailBean.ObjBean.TBruteEnterRecentryListBean bean) {
            tv_productName.setText(bean.getProductName());
            tv_unitName.setText(bean.getUnitName());
            tv_deliveryQty.setText(bean.getDeliveryQty() + "");
            tv_supplyname.setText(bean.getSupplyname());
            tv_meatCert.setText(bean.getMeatCert());
            //编辑时
            if(getIntent().getStringExtra("id")!=null&&bean.getInvId()!=null){
                bean.setZid(bean.getInvId());
            }

        }


    }

}
