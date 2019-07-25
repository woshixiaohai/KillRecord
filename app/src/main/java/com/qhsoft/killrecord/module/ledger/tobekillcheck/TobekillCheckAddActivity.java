package com.qhsoft.killrecord.module.ledger.tobekillcheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.PeopleListBean;
import com.qhsoft.killrecord.model.remote.bean.TobeKillListBean;
import com.qhsoft.killrecord.model.remote.bean.TobekillCheckDetailBean;
import com.qhsoft.killrecord.model.remote.service.TobekillCheckService;
import com.qhsoft.killrecord.module.baseinfo.people.PeopleSelectOneListActivity;
import com.qhsoft.killrecord.module.inventory.tobekill.TobeKillSelectOneListActivity;
import com.qhsoft.killrecord.net.BaseAddBean;
import com.qhsoft.killrecord.net.BaseBean;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.util.DialogFactory;
import com.qhsoft.killrecord.util.TimeUtil;
import com.qhsoft.killrecord.view.FreeToolbar;
import com.qhsoft.killrecord.view.dialog.OnDateSelectedListener;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class TobekillCheckAddActivity extends BaseActivity {
    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;
    @Bind(R.id.tv_billno)
    TextView tv_billno;
    @Bind(R.id.tv_checkDate)
    TextView tv_checkDate;
    @Bind(R.id.tv_jcBillno)
    TextView tv_jcBillno;
    @Bind(R.id.tv_supplyname)
    TextView tv_supplyname;
    @Bind(R.id.tv_bruteName)
    TextView tv_bruteName;
    @Bind(R.id.tv_bruteQty)
    TextView tv_bruteQty;
    @Bind(R.id.tv_circleNo)
    TextView tv_circleNo;
    @Bind(R.id.tv_checkName)
    TextView tv_checkName;
    @Bind(R.id.tv_bearQty)
    TextView tv_bearQty;
    @Bind(R.id.tv_bearNote)
    TextView tv_bearNote;
    @Bind(R.id.tv_slowQty)
    TextView tv_slowQty;
    @Bind(R.id.tv_slowNote)
    TextView tv_slowNote;
    @Bind(R.id.tv_shiftNo)
    TextView tv_shiftNo;
    @Bind(R.id.tv_shiftQty)
    TextView tv_shiftQty;
    @Bind(R.id.tv_verdict)
    TextView tv_verdict;






    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_tobekill_check_add;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mFreeToolbar.setTitle(getIntent().getStringExtra("title"));
        if(getIntent().getStringExtra("title").endsWith("新增")){
            String billno="DZ"+System.currentTimeMillis();
            tv_billno.setText(billno);
            tv_checkDate.setText(TimeUtil.formatSimpleDate(System.currentTimeMillis()));

        }
        if(!getIntent().getStringExtra("title").endsWith("新增")&&!getIntent().getStringExtra("title").endsWith("编辑")){
            mFreeToolbar.setActionTextVisible(false);
        }
        TobeKillListBean.RowsBean rowsBean= (TobeKillListBean.RowsBean) getIntent().getSerializableExtra(TobeKillListBean.RowsBean.class.getSimpleName());
        if(rowsBean!=null){
            getTobeKillInfo(rowsBean);
        }



        mFreeToolbar.setActionTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_billno.getText().toString().equals("")){
                    showToast("单据编号未填写");
                    return;
                }

                if(tv_jcBillno.getText().toString().equals("")){
                    showToast("进场编号未选择");
                    return;
                }
                if(tv_checkName.getText().toString().equals("")){
                    showToast("检查员未选择");
                    return;
                }
                if(!tv_shiftQty.getText().toString().equals("")&&tv_shiftNo.getText().toString().equals("")){
                    showToast("有填写转移数量时转移圈号必录");
                   return;
                }

                JsonObject jsonObject=new JsonObject();




                if(getIntent().getStringExtra("id")!=null){
                    jsonObject.addProperty("id",getIntent().getStringExtra("id"));
                }
                jsonObject.addProperty("type","0");
                jsonObject.addProperty("checkDate",tv_checkDate.getText().toString());
                jsonObject.addProperty("billno",tv_billno.getText().toString());
                jsonObject.addProperty("jcBillno",tv_jcBillno.getText().toString());
                jsonObject.addProperty("jcId",tv_jcBillno.getTag().toString());
                jsonObject.addProperty("bruteType",tv_bruteName.getTag().toString());
                jsonObject.addProperty("bruteName",tv_bruteName.getText().toString());
                jsonObject.addProperty("supplyname",tv_supplyname.getText().toString());

                jsonObject.addProperty("bruteQty",tv_bruteQty.getText().toString());

                jsonObject.addProperty("checkName",tv_checkName.getText().toString());
                jsonObject.addProperty("circleNo",tv_circleNo.getText().toString());
                jsonObject.addProperty("bearQty",tv_bearQty.getText().toString());
                jsonObject.addProperty("bearNote",tv_bearNote.getText().toString());
                jsonObject.addProperty("slowQty",tv_slowQty.getText().toString());
                jsonObject.addProperty("slowNote",tv_slowNote.getText().toString());
                jsonObject.addProperty("shiftNo",tv_shiftNo.getText().toString());
                jsonObject.addProperty("shiftQty",tv_shiftQty.getText().toString());
                jsonObject.addProperty("verdict",tv_verdict.getText().toString());





                if(getIntent().getStringExtra("id")==null){
                    requestData(HttpHandler.create(TobekillCheckService.class)
                            .doAddApp(UserHelper.getSessionId(),
                                    getRequestBody(jsonObject.toString())))
                            .subscribe(new TaskObserver<BaseAddBean>() {
                                @Override
                                public void onSuccess(BaseAddBean value) {
                                    d("onSuccess");
                                    showToast(value.getMsg());
                                    if(value.isSuccess()){
                                        EventBus.getDefault().post("", MyApp.REFRESH_LIST);
                                        finish();
                                    }

                                }

                                @Override
                                public void onFailure(FailureMessage failureMessage) {
                                    d("onSuccess");
                                    showToast(failureMessage.getMessageText());
                                }

                                @Override
                                public void onSubscribe(Disposable d) {

                                }
                            });
                }else {
                    checkDel(tv_billno.getText().toString(),tv_circleNo.getText().toString(),jsonObject);

                }






            }
        });
        getDetail();

    }

    private void doUpdateApp(JsonObject jsonObject) {
        requestData(HttpHandler.create(TobekillCheckService.class)
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

    private void getDetail() {
        if (getIntent().getStringExtra("id") != null) {
            requestData(HttpHandler.create(TobekillCheckService.class)
                    .getDetail(UserHelper.getSessionId(),
                            getIntent().getStringExtra("id")))
                    .subscribe(new TaskObserver<TobekillCheckDetailBean>() {
                        @Override
                        public void onSuccess(TobekillCheckDetailBean value) {
                            TobekillCheckDetailBean.ObjBean objBean = value.getObj();
                            if (objBean != null) {
                                tv_billno.setText(objBean.getBillno());
                                tv_checkDate.setText(TimeUtil.formatSimpleDate(objBean.getCheckDate()));
                                tv_jcBillno.setText(objBean.getJcBillno());
                                tv_jcBillno.setTag(objBean.getJcId());
                                tv_supplyname.setText(objBean.getSupplyname());
                                tv_bruteName.setText(objBean.getBruteName());
                                tv_bruteName.setTag(objBean.getBruteType());
                                tv_bruteQty.setText(objBean.getBruteQty()+"");
                                tv_circleNo.setText(objBean.getCircleNo());
                                tv_checkName.setText(objBean.getCheckName());
                                tv_bearQty.setText(objBean.getBearQty()+"");
                                tv_bearNote.setText(objBean.getBearNote());
                                tv_slowQty.setText(objBean.getSlowQty()+"");
                                tv_slowNote.setText(objBean.getSlowNote());
                                tv_shiftNo.setText(objBean.getShiftNo());
                                tv_shiftQty.setText(objBean.getShiftQty()+"");
                                tv_verdict.setText(objBean.getVerdict());

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

    @Subscriber(tag = "getTobeKillInfo")
    public void getTobeKillInfo(TobeKillListBean.RowsBean bean){
        tv_jcBillno.setText(bean.getBillno());
        tv_jcBillno.setTag(bean.getId());
        tv_supplyname.setText(bean.getSupplyname());
        tv_bruteName.setText(bean.getBruteName());
        tv_bruteName.setTag(bean.getBruteType());
        tv_bruteQty.setText(bean.getInvenQty());
        tv_circleNo.setText(bean.getCircleNo());



    }
    @Subscriber(tag = "getPeopleInfo")
    public void getPeopleInfo(PeopleListBean.RowsBean bean){
        tv_checkName.setText(bean.getName());


    }
    @OnClick({R.id.tv_checkDate,R.id.tv_checkName,R.id.tv_jcBillno})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_jcBillno:
                Intent intent=new Intent(TobekillCheckAddActivity.this, TobeKillSelectOneListActivity.class);
                intent.putExtra("title","选择进场编号");
                startActivity(intent);


                break;


            case R.id.tv_checkDate:
                DialogFactory.createDateBuilder()
                        .setOnDateSelectedListener(new OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(String date) {
                        tv_checkDate.setText(date);
                    }
                }).show(TobekillCheckAddActivity.this);
                break;
            case R.id.tv_checkName:
                 intent=new Intent(TobekillCheckAddActivity.this, PeopleSelectOneListActivity.class);
                intent.putExtra("title","选择检查员");
                intent.putExtra("certificateType", "检验员");
                startActivity(intent);

                break;

        }

    }
    private void checkDel(String billno, String circleno, final JsonObject jsonObject) {
        requestData(HttpHandler.create(TobekillCheckService.class)
                .checkDel(UserHelper.getSessionId(),
                        billno,
                        circleno))
                .subscribe(new TaskObserver<BaseAddBean>() {
                    @Override
                    public void onSuccess(BaseAddBean value) {

                        if (value.isSuccess()) {
                           doUpdateApp(jsonObject);

                        } else {
                            showToast(value.getMsg());
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
