package com.qhsoft.killrecord.module.passport;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.LoginBean;
import com.qhsoft.killrecord.model.remote.service.PasscheckService;
import com.qhsoft.killrecord.module.MainActivity;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @Bind(R.id.et_user_name)
    TextView et_user_name;
    @Bind(R.id.et_password)
    TextView et_password;
    @Bind(R.id.cb_remember)
    CheckBox cb_remember;
    @Bind(R.id.tv_app_name)
    TextView tvAppName;


    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        cb_remember.setChecked(spUtil.getRemember());

        if (cb_remember.isChecked()) {
            et_user_name.setText(spUtil.getUserName());
            et_password.setText(spUtil.getPassword());
        }
        try {
            ActivityInfo activityInfo = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            String value=activityInfo.metaData.getString("app_name");
            tvAppName.setText(value);
            //上杭县晟新食品有限公司



        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @OnCheckedChanged(R.id.cb_remember)
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        spUtil.setRemember(isChecked);

    }

    @OnClick(R.id.tv_login)
    public void clickLogin() {
        if (spUtil.getIp().equals("") || spUtil.getPort().equals("")) {
            showToast("网络未设置");
            return;
        }
        if (et_user_name.getText().toString().equals("")) {
            showToast("未填写用户名");
            return;
        }
        if (et_password.getText().toString().equals("")) {
            showToast("未填写密码");
            return;
        }


            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("userName", et_user_name.getText().toString());
            jsonObject.addProperty("password", et_password.getText().toString());
            requestData(HttpHandler.create(PasscheckService.class)
                    .login(getRequestBody(jsonObject.toString())))
                    .subscribe(new TaskObserver<LoginBean>() {
                        @Override
                        public void onSuccess(LoginBean value) {
                            spUtil.setUserName(et_user_name.getText().toString());
                            spUtil.setPassword(et_password.getText().toString());
                            UserHelper.initializeUserBean(value);
                            if (value.isSuccess()) {
                                if(value.getObj().getSonId()!=null&&!value.getObj().getSonId().equals("")){
                                    if(value.getObj().getProjectCode().equals("7")){
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }else {
                                        showToast("登录用户不是屠宰端用户");
                                    }

                                }else {
                                    showToast("登录用户不是企业端用户");
                                }

                            } else {
                                showToast(value.getMsg());
                            }


                        }

                        @Override
                        public void onFailure(FailureMessage failureMessage) {
                            showToast(failureMessage.getMessageText());
                        }


                    });




    }

    @OnClick(R.id.ic_close_use)
    public void clickCloseUser() {
        et_user_name.setText("");

    }

    @OnClick(R.id.ic_close_password)
    public void clickClosePwd() {
        et_password.setText("");

    }

    @OnClick(R.id.tv_settings)
    public void clickSetting() {
        Intent intent = new Intent(LoginActivity.this, NetworkSettingActivity.class);
        startActivity(intent);

    }



}
