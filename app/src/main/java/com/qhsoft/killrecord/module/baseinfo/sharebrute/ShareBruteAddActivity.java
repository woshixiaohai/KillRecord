package com.qhsoft.killrecord.module.baseinfo.sharebrute;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.ShareBruteDetailBean;
import com.qhsoft.killrecord.model.remote.service.ShareBruteService;
import com.qhsoft.killrecord.net.BaseBean;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.view.FreeToolbar;

import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class ShareBruteAddActivity extends BaseActivity {
    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;
    @Bind(R.id.tv_bruteType)
    TextView tv_bruteType;
    @Bind(R.id.tv_billno)
    TextView tv_billno;
    @Bind(R.id.tv_bruteName)
    TextView tv_bruteName;
    @Bind(R.id.tv_unitName)
    TextView tv_unitName;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_sharebrute_add;

    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mFreeToolbar.setTitle(getIntent().getStringExtra("title"));

        if(getIntent().getStringExtra("title").endsWith("新增")||getIntent().getStringExtra("title").endsWith("编辑")){
            mFreeToolbar.setActionTextClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(tv_billno.getText().toString().equals("")){
                        showToast("畜禽编号未填写");
                        return;
                    }
                    if(tv_bruteName.getText().toString().equals("")){
                        showToast("畜禽名称未填写");
                        return;
                    }
                    if(tv_bruteType.getText().toString().equals("")){
                        showToast("畜禽类型未选择");
                        return;
                    }
                    if(tv_unitName.getText().toString().equals("")){
                        showToast("计量单位未填写");
                        return;
                    }

                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("billno",tv_billno.getText().toString());
                        jsonObject.put("bruteName",tv_bruteName.getText().toString());
                        jsonObject.put("bruteType",tv_bruteType.getText().toString());
                        jsonObject.put("unitName",tv_unitName.getText().toString());
                        if(getIntent().getStringExtra("id")!=null){
                            jsonObject.put("id",getIntent().getStringExtra("id"));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    if(getIntent().getStringExtra("id")!=null){
                        requestData(HttpHandler.create(ShareBruteService.class)
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

                    }else {
                        requestData(HttpHandler.create(ShareBruteService.class)
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
                    }

                }
            });
        }else {
            mFreeToolbar.setActionTextVisible(false);
        }
        if(getIntent().getStringExtra("title").endsWith("新增")){
            String billno="Q"+((System.currentTimeMillis()+"").substring(1));
            tv_billno.setText(billno);
        }


        getDetail();


    }
    private ShareBruteDetailBean.ObjBean objBean;
    private void getDetail() {
        if(getIntent().getStringExtra("id")!=null){
            requestData(HttpHandler.create(ShareBruteService.class).getDetail(UserHelper.getSessionId(),
                    getIntent().getStringExtra("id")
            )).subscribe(new TaskObserver<ShareBruteDetailBean>() {
                @Override
                public void onSuccess(ShareBruteDetailBean value) {
                    if(value.getObj()!=null){
                        objBean=value.getObj();
                        tv_billno.setText(objBean.getBillno());
                        tv_bruteName.setText(objBean.getBruteName());
                        tv_bruteType.setText(objBean.getBruteType());
                        tv_unitName.setText(objBean.getUnitName());


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

    @OnClick({R.id.tv_bruteType})
    public void clickView(View v){
        switch (v.getId()){
            case R.id.tv_bruteType:
                getGroupCodeType("bruteType",tv_bruteType);
                break;

        }



    }
}
