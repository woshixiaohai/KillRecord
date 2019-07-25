package com.qhsoft.killrecord.module.baseinfo.people;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.PeopleDetailBean;
import com.qhsoft.killrecord.model.remote.service.PeopleService;
import com.qhsoft.killrecord.net.BaseBean;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.util.DialogFactory;
import com.qhsoft.killrecord.util.TimeUtil;
import com.qhsoft.killrecord.view.FreeToolbar;
import com.qhsoft.killrecord.view.dialog.OnDateSelectedListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class PeopleAddActivity extends BaseActivity {
    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;

    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_certificateType)
    TextView tv_certificateType;
    @Bind(R.id.tv_licenceNo)
    TextView tv_licenceNo;
    @Bind(R.id.tv_idCard)
    TextView tv_idCard;
    @Bind(R.id.tv_mobilePhone)
    TextView tv_mobilePhone;
    @Bind(R.id.tv_telePhone)
    TextView tv_telePhone;
    @Bind(R.id.tv_homePhone)
    TextView tv_homePhone;

    @Bind(R.id.tv_nation)
    TextView tv_nation;
    @Bind(R.id.rg_sex)
    RadioGroup rg_sex;
    @Bind(R.id.tv_graduateSchool)
    TextView tv_graduateSchool;
    @Bind(R.id.tv_graduateDate)
    TextView tv_graduateDate;

    @Bind(R.id.tv_professional)
    TextView tv_professional;

    @Bind(R.id.tv_xueli)
    TextView tv_xueli;
    @Bind(R.id.tv_professionalTitle)
    TextView tv_professionalTitle;
    @Bind(R.id.tv_politicalStatus)
    TextView tv_politicalStatus;
    @Bind(R.id.tv_workDate)
    TextView tv_workDate;
    @Bind(R.id.tv_originalWorkUnit)
    TextView tv_originalWorkUnit;
    @Bind(R.id.tv_birthday)
    TextView tv_birthday;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_people_add;

    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mFreeToolbar.setTitle(getIntent().getStringExtra("title"));
        if(getIntent().getStringExtra("title").endsWith("新增")||getIntent().getStringExtra("title").endsWith("编辑")){
            mFreeToolbar.setActionTextClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(tv_name.getText().toString().equals("")){
                        showToast("人员姓名未填写");
                        return;
                    }
                    if(tv_certificateType.getText().toString().equals("")){
                        showToast("人员类型未选择");
                        return;
                    }
                    if(tv_idCard.getText().toString().equals("")){
                        showToast("身份证号未填写");
                        return;
                    }
                    if(tv_mobilePhone.getText().toString().equals("")){
                        showToast("联系方式未填写");
                        return;
                    }
                    if(tv_mobilePhone.getText().toString().equals("")){
                        showToast("联系方式未填写");
                        return;
                    }
                    JSONObject jsonObject=new JSONObject();
                    try {

                        jsonObject.put("name",tv_name.getText().toString());
                        jsonObject.put("certificateType",tv_certificateType.getText().toString());
                        jsonObject.put("licenceNo",tv_licenceNo.getText().toString());
                        jsonObject.put("idCard",tv_idCard.getText().toString());
                        jsonObject.put("mobilePhone",tv_mobilePhone.getText().toString());
                        jsonObject.put("telePhone",tv_telePhone.getText().toString());
                        jsonObject.put("homePhone",tv_homePhone.getText().toString());
                        jsonObject.put("nation",tv_nation.getText().toString());
                        if(rg_sex.getCheckedRadioButtonId()==R.id.rg_sex_male){
                            jsonObject.put("sex","1");
                        }else {
                            jsonObject.put("sex","0");
                        }
                        jsonObject.put("graduateSchool",tv_graduateSchool.getText().toString());
                        jsonObject.put("graduateDate",tv_graduateDate.getText().toString());
                        jsonObject.put("professional",tv_professional.getText().toString());
                        jsonObject.put("professionalTitle",tv_professionalTitle.getText().toString());
                        jsonObject.put("politicalStatus",tv_politicalStatus.getText().toString());
                        jsonObject.put("workDate",tv_workDate.getText().toString());
                        jsonObject.put("originalWorkUnit",tv_originalWorkUnit.getText().toString());
                        jsonObject.put("birthday",tv_birthday.getText().toString());
                        jsonObject.put("xueli",tv_xueli.getText().toString());
                        if(getIntent().getStringExtra("id")!=null){
                            jsonObject.put("id",getIntent().getStringExtra("id"));
                        }
                        if(objBean!=null){
                            jsonObject.put("createDate",objBean.getCreateDate());
                            jsonObject.put("createName",objBean.getCreateName());
                            jsonObject.put("createBy",objBean.getCreateBy());
                            jsonObject.put("updateDate",objBean.getUpdateDate());
                            jsonObject.put("updateName",objBean.getUpdateName());
                            jsonObject.put("updateBy",objBean.getUpdateBy());
                            jsonObject.put("companyId",objBean.getCompanyId());
                            jsonObject.put("projectCode",objBean.getProjectCode());
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    requestData(HttpHandler.create(PeopleService.class)
                            .doAddOrUpdateApp(UserHelper.getSessionId(),
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
            });
        }else {
            mFreeToolbar.setActionTextVisible(false);
        }

        if(getIntent().getStringExtra("title").endsWith("新增")){
            rg_sex.check(R.id.rg_sex_male);
        }
        getDetail();
     




    }
    private PeopleDetailBean.ObjBean objBean;
    private void getDetail() {
        if(getIntent().getStringExtra("id")!=null){
            requestData(HttpHandler.create(PeopleService.class).getDetail(UserHelper.getSessionId(),
                    getIntent().getStringExtra("id")
                    )).subscribe(new TaskObserver<PeopleDetailBean>() {
                @Override
                public void onSuccess(PeopleDetailBean value) {
                    if(value.getObj()!=null){
                        objBean=value.getObj();
                        tv_birthday.setText(TimeUtil.formatSimpleDate(objBean.getBirthday()));
                        tv_certificateType.setText(objBean.getCertificateType());
                        tv_graduateDate.setText(TimeUtil.formatSimpleDate(objBean.getGraduateDate()));
                        tv_graduateSchool.setText(objBean.getGraduateSchool());
                        tv_homePhone.setText(objBean.getHomePhone());
                        tv_idCard.setText(objBean.getIdCard());
                        tv_licenceNo.setText(objBean.getLicenceNo());
                        tv_name.setText(objBean.getName());
                        tv_nation.setText(objBean.getNation());
                        tv_originalWorkUnit.setText(objBean.getOriginalWorkUnit());
                        tv_mobilePhone.setText(objBean.getMobilePhone());
                        tv_politicalStatus.setText(objBean.getPoliticalStatus());
                        tv_professional.setText(objBean.getProfessional());
                        tv_professionalTitle.setText(objBean.getProfessionalTitle());
                        tv_telePhone.setText(objBean.getTelePhone());
                        tv_workDate.setText(TimeUtil.formatSimpleDate(objBean.getWorkDate()));
                        tv_xueli.setText(objBean.getXueli());
                        if(objBean.getSex().equals("1")){
                            rg_sex.check(R.id.rg_sex_male);

                        }else {
                            rg_sex.check(R.id.rg_sex_female);
                        }

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

    @OnClick({R.id.tv_certificateType,R.id.tv_graduateDate,R.id.tv_workDate,R.id.tv_birthday})
    public void clickView(View v){
        switch (v.getId()){
            case R.id.tv_certificateType:
                getGroupCodeType("brutePersonType", tv_certificateType);
                break;
            case R.id.tv_graduateDate:
                DialogFactory.createDateBuilder().setOnDateSelectedListener(new OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(String date) {
                        tv_graduateDate.setText(date);
                    }
                }).show(PeopleAddActivity.this);
                break;
            case R.id.tv_workDate:
                DialogFactory.createDateBuilder()
                        .setOnDateSelectedListener(new OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(String date) {
                        tv_workDate.setText(date);
                    }
                }).show(PeopleAddActivity.this);
                break;
            case R.id.tv_birthday:
                DialogFactory.createDateBuilder()
                        .setOnDateSelectedListener(new OnDateSelectedListener() {
                            @Override
                            public void onDateSelected(String date) {
                                tv_birthday.setText(date);
                            }
                        }).show(PeopleAddActivity.this);
                break;

        }



    }
}
