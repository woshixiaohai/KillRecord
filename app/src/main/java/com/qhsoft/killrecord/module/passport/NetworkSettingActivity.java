package com.qhsoft.killrecord.module.passport;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.net.HttpConfig;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.view.FreeToolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NetworkSettingActivity extends BaseActivity {
    @Bind(R.id.tv_ip)
    public TextView tv_ip;
    @Bind(R.id.tv_port)
    public TextView tv_port;

    @Bind(R.id.tv_save)
    public TextView tv_save;
    @Bind(R.id.mFreeToolbar)
    public FreeToolbar mFreeToolbar;
    @Bind(R.id.tv_domain)
    EditText tvDomain;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_network_setting;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mFreeToolbar.setTitle("网络设置");
        tv_ip.setText(spUtil.getIp());
        tv_port.setText(spUtil.getPort());
        tvDomain.setText(spUtil.getDomain());

    }

    @OnClick(R.id.tv_save)
    public void save() {
        if(tvDomain.getText().toString().equals("")){
            if (tv_ip.getText().toString().equals("")) {
                showToast("请填写ip");
                return;
            }
            if (tv_port.getText().toString().equals("")) {
                showToast("请填写端口");
                return;
            }

            spUtil.setIp(tv_ip.getText().toString());
            spUtil.setPort(tv_port.getText().toString());
        }
        spUtil.setDomain(tvDomain.getText().toString());

        HttpHandler.clearRetrofit();
        HttpConfig.initBaseUrl(getApplicationContext());

        finish();


    }



}
