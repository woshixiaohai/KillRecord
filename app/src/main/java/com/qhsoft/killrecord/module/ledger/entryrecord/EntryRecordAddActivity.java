package com.qhsoft.killrecord.module.ledger.entryrecord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.model.local.RegionListBean;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.EntryRecoderDetailBean;
import com.qhsoft.killrecord.model.remote.bean.FileUploadResultBean;
import com.qhsoft.killrecord.model.remote.bean.KillRangeListBean;
import com.qhsoft.killrecord.model.remote.bean.PeopleListBean;
import com.qhsoft.killrecord.model.remote.bean.SupplyListBean;
import com.qhsoft.killrecord.model.remote.service.EntryRecordService;
import com.qhsoft.killrecord.module.baseinfo.killrange.KillRangeSelectOneListActivity;
import com.qhsoft.killrecord.module.baseinfo.people.PeopleSelectOneListActivity;
import com.qhsoft.killrecord.module.baseinfo.supply.SupplyListActivity;
import com.qhsoft.killrecord.module.file.UploadImageNewActivity;
import com.qhsoft.killrecord.net.BaseAddBean;
import com.qhsoft.killrecord.net.BaseBean;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.util.DialogFactory;
import com.qhsoft.killrecord.util.NetUtil;
import com.qhsoft.killrecord.util.TimeUtil;
import com.qhsoft.killrecord.view.FlowLayout;
import com.qhsoft.killrecord.view.FreeToolbar;
import com.qhsoft.killrecord.view.dialog.OnDateSelectedListener;
import com.qhsoft.killrecord.view.dialog.OnSelectedItemListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class EntryRecordAddActivity extends BaseActivity {
    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;
    @Bind(R.id.tv_fcityName)
    TextView tv_fcityName;

    @Bind(R.id.tv_fprovinceName)
    TextView tv_fprovinceName;

    @Bind(R.id.tv_fcountyName)
    TextView tv_fcountyName;
    @Bind(R.id.tv_jcdate)
    TextView tv_jcdate;
    @Bind(R.id.rg_approachUse)
    RadioGroup rg_approachUse;
    @Bind(R.id.tv_billno)
    TextView tv_billno;
    @Bind(R.id.tv_supplyname)
    TextView tv_supplyname;
    @Bind(R.id.tv_phone)
    TextView tv_phone;
    @Bind(R.id.tv_coroner)
    TextView tv_coroner;

    @Bind(R.id.tv_bruteName)
    TextView tv_bruteName;

    @Bind(R.id.tv_bruteType)
    TextView tv_bruteType;
    @Bind(R.id.rg_isUniformity)
    RadioGroup rg_isUniformity;
    @Bind(R.id.mFlowLayout)
    FlowLayout mFlowLayout;

    @Bind(R.id.tv_certificateNo)
    TextView tv_certificateNo;
    @Bind(R.id.tv_circleNo)
    TextView tv_circleNo;
    @Bind(R.id.tv_approachQty)
    TextView tv_approachQty;
    @Bind(R.id.tv_dieQty)
    TextView tv_dieQty;
    @Bind(R.id.tv_result)
    TextView tv_result;




    private RegionListBean regionListBean;
    private RegionListBean.DataBean province;
    private RegionListBean.DataBean city;
    private RegionListBean.DataBean area;
    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_entry_record_add;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mFreeToolbar.setTitle(getIntent().getStringExtra("title"));

         regionListBean=new Gson().fromJson(NetUtil.readRegionList(getApplicationContext()), RegionListBean.class);

         if(!getIntent().getStringExtra("title").endsWith("新增")&&!getIntent().getStringExtra("title").endsWith("编辑")){
             mFreeToolbar.setActionTextVisible(false);
         }
        if(getIntent().getStringExtra("title").endsWith("新增")){

            tv_jcdate.setText(TimeUtil.formatSimpleDate(System.currentTimeMillis()));
            String billno="JC"+System.currentTimeMillis();
            tv_billno.setText(billno);

        }
        getGroupCodeTypeRadioGroup("approachUseType",rg_approachUse);
        getGroupCodeTypeRadioGroup("sf_01",rg_isUniformity);

        mFreeToolbar.setActionTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_supplyname.getText().toString().equals("")){
                    showToast("畜禽来源未选择");
                    return;
                }
                if(tv_phone.getText().toString().equals("")){
                    showToast("联系电话未填写");
                    return;
                }
                if(tv_coroner.getText().toString().equals("")){
                    showToast("检验员未选择");
                    return;
                }
                if(tv_bruteName.getText().toString().equals("")){
                    showToast("畜禽名称未选择");
                    return;
                }
                if(tv_fcityName.getText().toString().equals("")||tv_fcountyName.getText().equals("")||tv_fprovinceName.getText().toString().equals("")){
                    showToast("畜禽产地省市区未都选择");
                    return;
                }


                if(tv_certificateNo.getText().toString().equals("")){
                    showToast("动检编号未填写");
                    return;
                }
                if(tv_approachQty.getText().toString().equals("")){
                    showToast("进场数量未填写");
                    return;
                }
                if(tv_bruteType.getText().toString().equals("猪")||tv_bruteType.getText().toString().equals("牛")||tv_bruteType.getText().toString().equals("羊")){
                    if(mFlowLayout.getChildCount()==0){
                        showToast("畜禽类型是猪牛羊时未上传图片");
                        return;
                    }
                }

                try {
                    JSONObject jsonObject = new JSONObject();
                    if (getIntent().getStringExtra("id") != null) {
                        jsonObject.put("id", getIntent().getStringExtra("id"));
                    }
                    jsonObject.put("billno", tv_billno.getText().toString());

                    jsonObject.put("jcdate", tv_jcdate.getText().toString());
                    jsonObject.put("supplyname", tv_supplyname.getText().toString());
                    jsonObject.put("supplyid", tv_supplyname.getTag().toString());
                    jsonObject.put("phone", tv_phone.getText().toString());
                    jsonObject.put("coroner", tv_coroner.getText().toString());
                    for (int i=0;i<rg_approachUse.getChildCount();i++){
                        RadioButton rb= (RadioButton) rg_approachUse.getChildAt(i);
                        if(rb.isChecked()){
                            jsonObject.put("approachUse", rb.getText().toString());
                            break;
                        }
                    }
                    jsonObject.put("bruteName", tv_bruteName.getText().toString());
                    jsonObject.put("bruteId", tv_bruteName.getTag().toString());
                    jsonObject.put("bruteType", tv_bruteType.getText().toString());
                    jsonObject.put("fprovinceName", tv_fprovinceName.getText().toString());
                    jsonObject.put("fcityName", tv_fcityName.getText().toString());
                    jsonObject.put("fcountyName", tv_fcountyName.getText().toString());
                    jsonObject.put("certificateNo", tv_certificateNo.getText().toString());
                    for (int i=0;i<rg_isUniformity.getChildCount();i++){
                        RadioButton rb= (RadioButton) rg_isUniformity.getChildAt(i);
                        if(rb.isChecked()){
                            jsonObject.put("isUniformity", rb.getText().toString());
                            break;
                        }
                    }
                    jsonObject.put("circleNo", tv_circleNo.getText().toString());
                    jsonObject.put("approachQty", tv_approachQty.getText().toString());
                    jsonObject.put("dieQty", tv_dieQty.getText().toString());
                    jsonObject.put("result", tv_result.getText().toString());
                    jsonObject.put("diplomaSrc", filePath);



                    if(getIntent().getStringExtra("id")!=null){
                        checkDel(tv_billno.getText().toString(),tv_circleNo.getText().toString(),jsonObject);

                    }else {
                        requestData(HttpHandler.create(EntryRecordService.class)
                                .doAddApp(UserHelper.getSessionId(), getRequestBody(jsonObject.toString())))
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


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });
         getDetail();


    }

    private void doUpdateApp(JSONObject jsonObject) {
        requestData(HttpHandler.create(EntryRecordService.class)
                .doUpdateApp(UserHelper.getSessionId(), getRequestBody(jsonObject.toString())))
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

    private void getDetail() {
        if (getIntent().getStringExtra("id") != null) {
            requestData(HttpHandler.create(EntryRecordService.class)
                    .getDetail(UserHelper.getSessionId(),
                            getIntent().getStringExtra("id")))
                    .subscribe(new TaskObserver<EntryRecoderDetailBean>() {
                        @Override
                        public void onSuccess(EntryRecoderDetailBean value) {
                            EntryRecoderDetailBean.ObjBean objBean = value.getObj();
                            if (objBean != null) {
                                filePath = objBean.getDiplomaSrc();
                                if(filePath!=null&&!filePath.equals("")){
                                    String[] url = (filePath + ",").split(",");
                                    for (int i = 0; i < url.length; i++) {
                                        addImageView(url[i], mFlowLayout);
                                    }
                                }

                                tv_billno.setText(objBean.getBillno());
                                tv_approachQty.setText(objBean.getApproachQty()+"");
                                tv_bruteName.setText(objBean.getBruteName());
                                tv_bruteName.setTag(objBean.getBruteId());
                                tv_bruteType.setText(objBean.getBruteType());
                                tv_certificateNo.setText(objBean.getCertificateNo());
                                tv_circleNo.setText(objBean.getCircleNo());
                                tv_coroner.setText(objBean.getCoroner());
                                tv_dieQty.setText(objBean.getDieQty()+"");
                                tv_fcityName.setText(objBean.getFcityName());
                                tv_fcountyName.setText(objBean.getFcountyName());
                                tv_fprovinceName.setText(objBean.getFprovinceName());
                                tv_jcdate.setText(TimeUtil.formatSimpleDate(objBean.getJcdate()));
                                tv_phone.setText(objBean.getPhone());
                                tv_result.setText(objBean.getResult());
                                tv_supplyname.setText(objBean.getSupplyname());
                                tv_supplyname.setTag(objBean.getSupplyid());

                                for (int i=0;i<rg_approachUse.getChildCount();i++){
                                    RadioButton rb= (RadioButton) rg_approachUse.getChildAt(i);
                                    if(rb.getText().toString().equals(objBean.getApproachUse())){
                                        rg_approachUse.check(rb.getId());
                                    }
                                }
                                for (int i=0;i<rg_isUniformity.getChildCount();i++){
                                    RadioButton rb= (RadioButton) rg_isUniformity.getChildAt(i);
                                    if(rb.getText().toString().equals(objBean.getIsUniformity())){
                                        rg_isUniformity.check(rb.getId());
                                    }
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

    @Subscriber(tag = "getSupplyInfo")
    private void getSupplyInfo(SupplyListBean.RowsBean bean){
        tv_supplyname.setText(bean.getFmanageName());
        tv_supplyname.setTag(bean.getId());
        tv_fprovinceName.setText(bean.getFprovinceName());
        tv_fcityName.setText(bean.getFcityName());
        tv_fcountyName.setText(bean.getFcountyDistrictName());
        tv_phone.setText(bean.getFcontackPhone());

    }
    @Subscriber(tag = "getPeopleInfo")
    private void getPeopleInfo( PeopleListBean.RowsBean bean){
        tv_coroner.setText(bean.getName());

    }
    @Subscriber(tag = "getKillRangeInfo")
    private void getKillRangeInfo(KillRangeListBean.RowsBean bean){
        tv_bruteName.setText(bean.getBruteName());
        tv_bruteName.setTag(bean.getId());
        tv_bruteType.setText(bean.getBruteType());


    }
    private String filePath;

    @Subscriber(tag = "getFilePath")
    private void getFilePath(List<FileUploadResultBean.ObjBean> list_obj) {
        filePath = null;
        mFlowLayout.removeAllViews();
        for (int i = 0; i < list_obj.size(); i++) {
            FileUploadResultBean.ObjBean objBean = list_obj.get(i);
            if (filePath != null) {
                filePath = filePath + "," + objBean.getFilePath();
            } else {
                filePath = objBean.getFilePath();
            }
            addImageView(objBean.getFilePath(), mFlowLayout);
        }


    }



    @OnClick({R.id.iv_diplomaSrc,R.id.tv_jcdate,R.id.tv_supplyname,R.id.tv_coroner,
            R.id.tv_bruteName,R.id.tv_fprovinceName,R.id.tv_fcityName,R.id.tv_fcountyName})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_diplomaSrc:
                Intent intent=new Intent(EntryRecordAddActivity.this, UploadImageNewActivity.class);
                intent.putExtra("tag", "getFilePath");
                startActivity(intent);


                break;
            case R.id.tv_fprovinceName:
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
                        tv_fprovinceName.setText(province.getRegion_name());
                    }
                }).show(EntryRecordAddActivity.this);

                break;
            case R.id.tv_fcityName:
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
                                tv_fcityName.setText(city.getRegion_name());
                            }
                        }).show(EntryRecordAddActivity.this);
                break;
            case R.id.tv_fcountyName:
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
                                tv_fcountyName.setText(area.getRegion_name());
                            }
                        }).show(EntryRecordAddActivity.this);
                break;
            case R.id.tv_jcdate:
                DialogFactory.createDateBuilder().setOnDateSelectedListener(new OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(String date) {
                        tv_jcdate.setText(date);
                    }
                }).show(EntryRecordAddActivity.this);
                break;
            case R.id.tv_supplyname:
                 intent=new Intent(EntryRecordAddActivity.this, SupplyListActivity.class);
                 intent.putExtra("title","选择供货商");
                startActivity(intent);

                break;
            case R.id.tv_coroner:
                intent=new Intent(EntryRecordAddActivity.this, PeopleSelectOneListActivity.class);
                intent.putExtra("title","选择检验员");
                intent.putExtra("certificateType","检验员");
                startActivity(intent);

                break;
            case R.id.tv_bruteName:
                intent=new Intent(EntryRecordAddActivity.this, KillRangeSelectOneListActivity.class);
                intent.putExtra("title","选择畜禽");
                startActivity(intent);

                break;

        }

    }
    private void checkDel(String billno, String circleno, final JSONObject jsonObject) {
        requestData(HttpHandler.create(EntryRecordService.class)
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
