package com.qhsoft.killrecord.module.ledger.killcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.luyinbros.combineview.SimpleListView;
import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseFragment;
import com.qhsoft.killrecord.base.BaseViewHolder;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.GoodListBean;
import com.qhsoft.killrecord.model.remote.bean.KillCheckOneDetailBean;
import com.qhsoft.killrecord.model.remote.bean.KillCheckTwoDetailBean;
import com.qhsoft.killrecord.model.remote.bean.PeopleListBean;
import com.qhsoft.killrecord.model.remote.service.KillCheckService;
import com.qhsoft.killrecord.module.baseinfo.good.GoodSelectListActivity;
import com.qhsoft.killrecord.module.baseinfo.people.PeopleSelectOneListActivity;
import com.qhsoft.killrecord.net.BaseBean;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.util.DialogFactory;
import com.qhsoft.killrecord.util.NullableUtil;
import com.qhsoft.killrecord.util.TimeUtil;
import com.qhsoft.killrecord.view.dialog.OnDateSelectedListener;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.disposables.Disposable;



public class KillCheckAddTwoFragment extends BaseFragment {
    @Bind(R.id.mSimpleListView)
    SimpleListView mSimpleListView;
    private ItemAdapter itemAdapter;
    @Bind(R.id.tv_billno)
    TextView tv_billno;
    @Bind(R.id.tv_ruleDate)
    TextView tv_ruleDate;
    @Bind(R.id.tv_checkName)
    TextView tv_checkName;
    @Bind(R.id.tv_szBillno)
    TextView tv_szBillno;
    @Bind(R.id.tv_result)
    TextView tv_result;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_killcheck_two;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity().getIntent().getStringExtra("zhId") == null||getActivity().getIntent().getStringExtra("zhId").equals("")) {
            tv_ruleDate.setText(TimeUtil.formatSimpleDate(System.currentTimeMillis()));
            String billno = "ZH" + System.currentTimeMillis();
            tv_billno.setText(billno);

        }
        if(getActivity().getIntent().getStringExtra("title").endsWith("编辑")){
            tv_billno.setInputType(InputType.TYPE_NULL);
        }


        getDetail();


    }


    public void initData(KillCheckOneDetailBean value){
           if(value!=null&&value.getObj()!=null){
               tv_szBillno.setText(value.getObj().getBillno());
               tv_szBillno.setTag(value.getObj().getId());
               if(tv_ruleDate.getText().toString().equals("")){
                   tv_ruleDate.setText(TimeUtil.formatSimpleDate(System.currentTimeMillis()));
                   String billno = "ZH" + System.currentTimeMillis();
                   tv_billno.setText(billno);
               }


           }



    }
    private  KillCheckTwoDetailBean.ObjBean objBean;
    private void getDetail() {
        if (getActivity().getIntent().getStringExtra("zhId") != null&&!getActivity().getIntent().getStringExtra("zhId").equals("")) {
            requestData(HttpHandler.create(KillCheckService.class)
                    .getEntityInfoTwo(UserHelper.getSessionId(),
                            getActivity().getIntent().getStringExtra("zhId")))
                    .subscribe(new TaskObserver<KillCheckTwoDetailBean>() {
                        @Override
                        public void onSuccess(KillCheckTwoDetailBean value) {
                            objBean = value.getObj();
                            if (objBean != null) {
                                tv_billno.setText(objBean.getBillno());
                                tv_ruleDate.setText(TimeUtil.formatSimpleDate(objBean.getRuleDate()));
                                tv_checkName.setText(objBean.getCheckName());
                                tv_szBillno.setText(objBean.getSzBillno());
                                tv_szBillno.setTag(objBean.getSzId());
                                tv_result.setText(objBean.getResult());
                                if (itemAdapter == null) {
                                    itemAdapter = new ItemAdapter();
                                    mSimpleListView.setAdapter(itemAdapter);

                                }
                                itemAdapter.addItem(objBean.getTBruteRuleAftentryList());


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

    public void saveData() {
        if (tv_billno.getText().toString().equals("")) {
            showToast("单据编号未填写");
            return;
        }
        if (tv_checkName.getText().toString().equals("")) {
            showToast("检验员未选择");
            return;
        }
        if(itemAdapter==null){
            showToast("未选择产品");
            return;
        }
        if(itemAdapter.getGsonStr()==null){
            return;

        }
        if(itemAdapter.getGsonStr().size()==0){
            showToast("未选择产品");
            return;
        }


        JsonObject jsonObject = new JsonObject();
        if (getActivity().getIntent().getStringExtra("zhId")!=null&&!getActivity().getIntent().getStringExtra("zhId").equals("")) {
            jsonObject.addProperty("id", getActivity().getIntent().getStringExtra("zhId"));
        }

        jsonObject.addProperty("billno", tv_billno.getText().toString());
        jsonObject.addProperty("checkName", tv_checkName.getText().toString());
        jsonObject.addProperty("ruleDate", tv_ruleDate.getText().toString());
        jsonObject.addProperty("szBillno", tv_szBillno.getText().toString());
        jsonObject.addProperty("szId", tv_szBillno.getTag().toString());
        jsonObject.addProperty("result", tv_result.getText().toString());
        if(objBean!=null){
            if(objBean.getFsonid()!=null){
                jsonObject.addProperty("fsonid", objBean.getFsonid());
            }
            if(objBean.getProjectCode()!=null){
                jsonObject.addProperty("projectCode", objBean.getProjectCode());
            }


        }

        jsonObject.add("tBruteRuleAftentryList", itemAdapter.getGsonStr());
        if (getActivity().getIntent().getStringExtra("zhId")!=null&&!getActivity().getIntent().getStringExtra("zhId").equals("")) {
            requestData(HttpHandler.create(KillCheckService.class)
                    .doUpdateAppTwo(UserHelper.getSessionId(),
                            getRequestBody(jsonObject.toString())))
                    .subscribe(new TaskObserver<BaseBean>() {
                        @Override
                        public void onSuccess(BaseBean value) {
                            showToast(value.getMsg());
                            if (value.isSuccess()) {

                                EventBus.getDefault().post("", MyApp.REFRESH_LIST);
                                getActivity().finish();
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
            requestData(HttpHandler.create(KillCheckService.class)
                    .doAddAppTwo(UserHelper.getSessionId(),
                            getRequestBody(jsonObject.toString())))
                    .subscribe(new TaskObserver<BaseBean>() {
                        @Override
                        public void onSuccess(BaseBean value) {
                            showToast(value.getMsg());
                            if (value.isSuccess()) {

                                EventBus.getDefault().post("", MyApp.REFRESH_LIST);
                                getActivity().finish();
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

    @Subscriber(tag = "getPeopleInfo")
    private void getPeopleInfo(PeopleListBean.RowsBean bean) {
        tv_checkName.setText(bean.getName());

    }

    @Subscriber(tag = "getGoodList")
    private void getGoodList(List<GoodListBean.RowsBean> list) {
        List<KillCheckTwoDetailBean.ObjBean.TBruteRuleAftentryListBean> list_item = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            KillCheckTwoDetailBean.ObjBean.TBruteRuleAftentryListBean bean=new KillCheckTwoDetailBean.ObjBean.TBruteRuleAftentryListBean();
            GoodListBean.RowsBean rowsBean=list.get(i);
            bean.setProductName(rowsBean.getName());
            bean.setSzBillno(tv_szBillno.getText().toString());
            bean.setUnitName(rowsBean.getBasicUnitName());
            bean.setIsQualified("1");



            list_item.add(bean);
        }




        if (itemAdapter == null) {
            itemAdapter = new ItemAdapter();
            mSimpleListView.setAdapter(itemAdapter);

        }
        itemAdapter.addItem(list_item);

    }


    @OnClick({R.id.tv_add, R.id.tv_ruleDate, R.id.tv_checkName})
    public void clikc(View v) {
        switch (v.getId()) {
            case R.id.tv_add:
                Intent intent = new Intent(getActivity(), GoodSelectListActivity.class);
                intent.putExtra("title", "选择产品");
                startActivity(intent);


                break;
            case R.id.tv_ruleDate:
                DialogFactory.createDateBuilder().setOnDateSelectedListener(new OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(String date) {
                        tv_ruleDate.setText(date);
                    }
                }).show(getActivity());
                break;
            case R.id.tv_checkName:
                 intent = new Intent(getActivity(), PeopleSelectOneListActivity.class);
                intent.putExtra("title", "选择检查员");
                intent.putExtra("certificateType", "检验员");
                startActivity(intent);
                break;
        }


    }

    private class ItemAdapter extends SimpleListView.Adapter<ItemHolder> {
        private List<KillCheckTwoDetailBean.ObjBean.TBruteRuleAftentryListBean> list = new ArrayList<>();

        @Override
        public ItemHolder onCreateHolder(ViewGroup viewGroup, int i) {
            return new ItemHolder(inflate(R.layout.layout_killcheck_two_item));
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

        public void addItem(List<KillCheckTwoDetailBean.ObjBean.TBruteRuleAftentryListBean> list_child) {
            if (list_child != null && list_child.size() > 0) {
                list.addAll(list_child);
                notifySetChange();
            }

        }

        public JsonArray getGsonStr() {
            for(int i=0;i<list.size();i++){
                KillCheckTwoDetailBean.ObjBean.TBruteRuleAftentryListBean bean=list.get(i);
                if(bean.getProductQty()==0){
                    showToast("产品数量未填写或不能等于0");
                    return null;
                }
                if(bean.getCertificateNo().equals("")){
                    showToast("肉品编号未填写");
                    return null;
                }

            }

            return new Gson().toJsonTree(list).getAsJsonArray();
        }


    }

    public class ItemHolder extends BaseViewHolder {
        @Bind(R.id.iv_close)
        ImageView iv_close;
        @Bind(R.id.tv_productName)
        TextView tv_productName;
        @Bind(R.id.tv_unitName)
        TextView tv_unitName;
        @Bind(R.id.rg_isQualified)
        RadioGroup rg_isQualified;
        @Bind(R.id.tv_productQty)
        TextView tv_productQty;
        @Bind(R.id.tv_certificateNo)
        TextView tv_certificateNo;
        @Bind(R.id.tv_noQualified)
        TextView tv_noQualified;
        private KillCheckTwoDetailBean.ObjBean.TBruteRuleAftentryListBean bean;


        public ItemHolder(View itemView) {
            super(itemView);
        }

        public void bindData(final KillCheckTwoDetailBean.ObjBean.TBruteRuleAftentryListBean bean) {
            this.bean=bean;
            tv_productName.setText(bean.getProductName());
            tv_unitName.setText(bean.getUnitName());
            if (bean.getIsQualified().equals("1")) {
                rg_isQualified.check(R.id.rg_isQualified_yes);
            } else {
                rg_isQualified.check(R.id.rg_isQualified_no);
            }
            if(bean.getProductQty()>0){
                tv_productQty.setText(bean.getProductQty()+"");
            }
            tv_certificateNo.setText(bean.getCertificateNo());
            tv_noQualified.setText(bean.getNoQualified());
            rg_isQualified.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(rg_isQualified.getCheckedRadioButtonId()==R.id.rg_isQualified_yes){
                        bean.setIsQualified("1");
                    }else {
                        bean.setIsQualified("0");
                    }
                }
            });




        }


        @OnTextChanged(R.id.tv_productQty)
        public void onTextChangedProductQty(CharSequence s, int start, int before, int count){
            if(s!=null&&!s.toString().equals("")){
                bean.setProductQty(Integer.valueOf(s.toString()));
            }

        }
        @OnTextChanged(R.id.tv_certificateNo)
        public void onTextChangedCertificateNo(CharSequence s, int start, int before, int count){
            if(s!=null){
                bean.setCertificateNo(s.toString());
            }

        }
        @OnTextChanged(R.id.tv_noQualified)
        public void onTextChangedNoQualified(CharSequence s, int start, int before, int count){
            if(s!=null){
                bean.setNoQualified(s.toString());
            }

        }



    }
}
