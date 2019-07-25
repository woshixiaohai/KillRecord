package com.qhsoft.killrecord.module.baseinfo.killrange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.KillRangeDetailBean;
import com.qhsoft.killrecord.model.remote.bean.ShareBruteListBean;
import com.qhsoft.killrecord.model.remote.service.KillRangeService;
import com.qhsoft.killrecord.module.baseinfo.sharebrute.ShareBruteSelectOneListActivity;
import com.qhsoft.killrecord.net.BaseBean;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.view.FreeToolbar;

import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class KillRangeAddActivity extends BaseActivity {
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
        return R.layout.activity_kill_range_add;

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
                        showToast("畜禽名称未选择");
                        return;
                    }
                    if(tv_bruteType.getText().toString().equals("")){
                        showToast("畜禽类型未填写");
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
                        requestData(HttpHandler.create(KillRangeService.class)
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
                        requestData(HttpHandler.create(KillRangeService.class)
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

        getDetail();



    }
    @Subscriber(tag = "getShareBruteInfo")
    private void getShareBruteInfo(ShareBruteListBean.RowsBean bean){
        tv_billno.setText(bean.getBillno());
        tv_bruteName.setText(bean.getBruteName());
        tv_bruteType.setText(bean.getBruteType());
        tv_unitName.setText(bean.getUnitName());
    }


    private KillRangeDetailBean.ObjBean objBean;
    private void getDetail() {
        if(getIntent().getStringExtra("id")!=null){
            requestData(HttpHandler.create(KillRangeService.class).getDetail(UserHelper.getSessionId(),
                    getIntent().getStringExtra("id")
            )).subscribe(new TaskObserver<KillRangeDetailBean>() {
                @Override
                public void onSuccess(KillRangeDetailBean value) {
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





    @OnClick({R.id.tv_bruteName})
    public void clickView(View v){
        switch (v.getId()){

            case R.id.tv_bruteName:
                Intent intent=new Intent(KillRangeAddActivity.this, ShareBruteSelectOneListActivity.class);
                intent.putExtra("title",getIntent().getStringExtra("title").replaceAll("新增","").replaceAll("编辑",""));
                startActivity(intent);

                break;

        }



    }
}
