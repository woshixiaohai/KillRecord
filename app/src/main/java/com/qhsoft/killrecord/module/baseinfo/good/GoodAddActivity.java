package com.qhsoft.killrecord.module.baseinfo.good;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.FileUploadResultBean;
import com.qhsoft.killrecord.model.remote.bean.GoodDetailBean;
import com.qhsoft.killrecord.model.remote.bean.IcItemInfoBean;
import com.qhsoft.killrecord.model.remote.bean.MenuItemBean;
import com.qhsoft.killrecord.model.remote.service.GoodService;
import com.qhsoft.killrecord.module.file.UploadImageNewActivity;
import com.qhsoft.killrecord.net.BaseBean;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.util.DialogFactory;
import com.qhsoft.killrecord.view.FlowLayout;
import com.qhsoft.killrecord.view.FreeToolbar;
import com.qhsoft.killrecord.view.dialog.OnSelectedItemListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.disposables.Disposable;

public class GoodAddActivity extends BaseActivity {
    @Bind(R.id.mFreeToolbar)
    public FreeToolbar mFreeToolbar;
    @Bind(R.id.tv_barCode)
    public TextView tv_barCode;
    @Bind(R.id.tv_ftypeName)
    public TextView tv_ftypeName;


    @Bind(R.id.tv_name)
    public TextView tv_name;


    @Bind(R.id.tv_kfPeriod)
    public TextView tv_kfPeriod;
    @Bind(R.id.tv_kfDateType)
    public TextView tv_kfDateType;
    @Bind(R.id.cb_fever)
    public CheckBox cb_fever;
    @Bind(R.id.tv_model)
    public TextView tv_model;
    @Bind(R.id.tv_basicUnitName)
    public TextView tv_basicUnitName;
    @Bind(R.id.tv_coefficient)
    public TextView tv_coefficient;

    @Bind(R.id.tv_unitName)
    public TextView tv_unitName;

    @Bind(R.id.tv_cultureNo)
    public TextView tv_cultureNo;

    @Bind(R.id.tv_supplyName)
    public TextView tv_supplyName;

    @Bind(R.id.tv_packSpeci)
    public TextView tv_packSpeci;

    @Bind(R.id.tv_icitemType)
    public TextView tv_icitemType;


    @Bind(R.id.tv_note)
    public TextView tv_note;
    @Bind(R.id.iv_photo)
    public ImageView iv_photo;
    @Bind(R.id.mFlowLayout)
    FlowLayout mFlowLayout;


    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_good_add;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        if (getIntent().getStringExtra("title") != null) {
            mFreeToolbar.setTitle(getIntent().getStringExtra("title"));
        }
        if (!getIntent().getStringExtra("title").endsWith("新增") && !getIntent().getStringExtra("title").endsWith("编辑")) {
            mFreeToolbar.setActionTextVisible(false);
        }

        mFreeToolbar.setActionTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_barCode.getText().toString().equals("")) {
                    showToast("未填写条形码");
                    return;
                }
                if (tv_ftypeName.getText().toString().equals("") || tv_ftypeName.getText().toString().equals("未选择")) {
                    showToast("未选择商品类型");
                    return;
                }
                if (tv_name.getText().toString().equals("")) {
                    showToast("未填写商品名称");
                    return;
                }

                if (tv_kfPeriod.getText().toString().equals("")) {
                    showToast("未填写保质期");
                    return;
                }
                if (tv_model.getText().toString().equals("")) {
                    showToast("未填写规格");
                    return;
                }

                if (tv_basicUnitName.getText().toString().equals("") || tv_basicUnitName.getText().toString().equals("未选择")) {
                    showToast("未选择小包装单位");
                    return;
                }


                if (tv_packSpeci.getText().toString().equals("") || tv_packSpeci.getText().toString().equals("未选择")) {
                    showToast("未选择包装方式");
                    return;
                }

                if (tv_icitemType.getText().toString().equals("") || tv_icitemType.getText().toString().equals("未选择")) {
                    showToast("未选择商品类别");
                    return;
                }


                try {
                    JSONObject jsonObject = new JSONObject();
                    if (getIntent().getStringExtra("id") != null) {
                        jsonObject.put("id", getIntent().getStringExtra("id"));
                    }
                    jsonObject.put("barCode", tv_barCode.getText().toString());
                    jsonObject.put("ftypeName", tv_ftypeName.getText().toString());
                    jsonObject.put("name", tv_name.getText().toString());

                    jsonObject.put("kfPeriod", tv_kfPeriod.getText().toString());
                    jsonObject.put("kfDateType", tv_kfDateType.getText().toString());
                    if (cb_fever.isChecked()) {
                        jsonObject.put("fever", 1);
                    } else {
                        jsonObject.put("fever", 0);
                    }
                    jsonObject.put("model", tv_model.getText().toString());
                    jsonObject.put("basicUnitName", tv_basicUnitName.getText().toString());
                    jsonObject.put("coefficient", tv_coefficient.getText().toString());

                    if (!tv_unitName.getText().toString().equals("")) {
                        jsonObject.put("unitName", tv_unitName.getText().toString());
                    }


                    jsonObject.put("cultureNo", tv_cultureNo.getText().toString());
                    if (!tv_supplyName.getText().toString().equals("")) {
                        jsonObject.put("supplyName", tv_supplyName.getText().toString());
                    }

                    jsonObject.put("packSpeci", tv_packSpeci.getText().toString());
                    jsonObject.put("icitemType", tv_icitemType.getText().toString());
                    jsonObject.put("note", tv_note.getText().toString());
                    jsonObject.put("photo", filePath);


                    if (getIntent().getStringExtra("id") != null) {
                        requestData(HttpHandler.create(GoodService.class)
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
                    } else {
                        requestData(HttpHandler.create(GoodService.class)
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

        if (getIntent().getStringExtra("title").endsWith("新增")) {
            tv_coefficient.setEnabled(false);
            tv_coefficient.setText("1.0");
        }

        getDetail();

    }

    @OnTextChanged(R.id.tv_unitName)
    public void OnTextChangedUnitName(CharSequence s, int start, int before, int count) {
        if (!s.toString().equals("")) {
            tv_coefficient.setEnabled(true);
        } else {
            tv_coefficient.setEnabled(false);
            tv_coefficient.setText("1.0");
        }


    }


    private void getDetail() {
        if (getIntent().getStringExtra("id") != null) {
            requestData(HttpHandler.create(GoodService.class)
                    .getCheck(UserHelper.getSessionId(),
                            getIntent().getStringExtra("id")))
                    .subscribe(new TaskObserver<GoodDetailBean>() {
                        @Override
                        public void onSuccess(GoodDetailBean value) {
                            GoodDetailBean.ObjBean objBean = value.getObj();
                            if (objBean != null) {
                                filePath = objBean.getPhoto();
                                if(filePath!=null&&!filePath.equals("")){
                                    String[] url = (filePath + ",").split(",");
                                    for (int i = 0; i < url.length; i++) {
                                        addImageView(url[i], mFlowLayout);
                                    }
                                }

                                tv_barCode.setText(objBean.getBarCode());
                                tv_basicUnitName.setText(objBean.getBasicUnitName());

                                tv_icitemType.setText(objBean.getIcitemType());
                                tv_kfDateType.setText(objBean.getKfDateType());
                                tv_name.setText(objBean.getName());
                                tv_note.setText(objBean.getNote());
                                tv_packSpeci.setText(objBean.getPackSpeci());
                                tv_cultureNo.setText(objBean.getCultureNo());
                                tv_coefficient.setText(objBean.getCoefficient() + "");
                                tv_kfPeriod.setText(objBean.getKfPeriod() + "");
                                tv_note.setText(objBean.getNote());
                                tv_ftypeName.setText(objBean.getFtypeName());
                                tv_model.setText(objBean.getModel());
                                tv_supplyName.setText(objBean.getSupplyName());
                                tv_unitName.setText(objBean.getUnitName());
                                if (objBean.getFever() == 1) {
                                    cb_fever.setChecked(true);
                                } else {
                                    cb_fever.setChecked(false);
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

    @OnClick(R.id.tv_name)
    public void clickName() {
        requestData(HttpHandler.create(GoodService.class)
                .getMaterialList(UserHelper.getSessionId()))
                .subscribe(new TaskObserver<List<MenuItemBean>>() {
                    @Override
                    public void onSuccess(final List<MenuItemBean> value) {
                        if (value != null && value.size() > 0) {
                            List<String> list = new ArrayList<>();
                            for (int i = 0; i < value.size(); i++) {
                                list.add(value.get(i).getText());
                            }


                            DialogFactory.createSingleBuilder().setTitle("选择商品").addSelectList(list)
                                    .setOnSelectedListener(new OnSelectedItemListener() {
                                        @Override
                                        public void onSelectedItem(int position) {
                                            getMaterialInfo(value.get(position).getId());
                                        }
                                    }).show(GoodAddActivity.this);
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

    public void getMaterialInfo(String id) {
        requestData(HttpHandler.create(GoodService.class)
                .getMaterialInfo(UserHelper.getSessionId(), id))
                .subscribe(new TaskObserver<IcItemInfoBean>() {
                    @Override
                    public void onSuccess(IcItemInfoBean value) {
                        if (value != null && value.getObj() != null) {
                            IcItemInfoBean.ObjBean objBean = value.getObj();
                            loadImage(objBean.getPhoto(), iv_photo);
                            tv_barCode.setText(objBean.getBarCode());
                            tv_basicUnitName.setText(objBean.getBasicUnitName());

                            tv_icitemType.setText(objBean.getIcitemType());
                            tv_kfDateType.setText(objBean.getKfDateType());
                            tv_name.setText(objBean.getName());
                            tv_note.setText(objBean.getNote());
                            tv_packSpeci.setText(objBean.getPackSpeci());
                            tv_kfPeriod.setText(objBean.getKfPeriod() + "");
                            tv_ftypeName.setText(objBean.getFtypeName());
                            tv_model.setText(objBean.getModel());


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


    @OnClick(R.id.iv_photo)
    public void clickSelect() {

        Intent intent = new Intent(GoodAddActivity.this, UploadImageNewActivity.class);
        intent.putExtra("tag", "getFilePath");
        startActivity(intent);

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


}
