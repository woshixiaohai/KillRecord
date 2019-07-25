package com.qhsoft.killrecord.module.baseinfo.sharegood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qhsoft.killrecord.MyApp;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.FileUploadResultBean;
import com.qhsoft.killrecord.model.remote.bean.ShareGoodDetailBean;
import com.qhsoft.killrecord.model.remote.service.GoodShareService;
import com.qhsoft.killrecord.module.file.UploadImageNewActivity;
import com.qhsoft.killrecord.net.BaseBean;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.util.TypeCodeUtil;
import com.qhsoft.killrecord.view.FlowLayout;
import com.qhsoft.killrecord.view.FreeToolbar;

import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class GoodShareAddActivity extends BaseActivity {
    @Bind(R.id.mFreeToolbar)
    public FreeToolbar mFreeToolbar;


    @Bind(R.id.iv_pic)
    public ImageView iv_pic;
    @Bind(R.id.tv_barCode)
    public EditText tv_barCode;

    @Bind(R.id.tv_ftypeName)
    public TextView tv_ftypeName;

    @Bind(R.id.tv_kfDateType)
    public TextView tv_kfDateType;

    @Bind(R.id.tv_name)
    public TextView tv_name;

    @Bind(R.id.tv_kfPeriod)
    public TextView tv_kfPeriod;

    @Bind(R.id.tv_model)
    public TextView tv_model;
    @Bind(R.id.tv_note)
    public TextView tv_note;
    @Bind(R.id.cb_fever)
    public CheckBox cb_fever;


    @Bind(R.id.tv_basicUnitName)
    TextView tv_basicUnitName;
    @Bind(R.id.tv_icitemType)
    TextView tv_icitemType;
    @Bind(R.id.tv_packSpeci)
    TextView tv_packSpeci;

    @Bind(R.id.mFlowLayout)
    FlowLayout mFlowLayout;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_goods_share_add;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        if (getIntent().getStringExtra("title") != null) {
            mFreeToolbar.setTitle(getIntent().getStringExtra("title"));
        }
        if (getIntent().getStringExtra("title").endsWith("新增") || getIntent().getStringExtra("title").endsWith("编辑")) {
            mFreeToolbar.setActionText("保存");
            mFreeToolbar.setActionTextClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tv_model.getText().toString().equals("")) {
                        showToast("未填写规格");
                        return;
                    }
                    if (tv_basicUnitName.getText().toString().equals("") || tv_basicUnitName.getText().toString().equals("请选择")) {
                        showToast("未选择小包装单位");
                        return;
                    }
                    if (tv_ftypeName.getText().toString().equals("") || tv_ftypeName.getText().toString().equals("请选择")) {
                        showToast("未选择商品类型");
                        return;
                    }
                    if (tv_barCode.getText().toString().equals("")) {
                        showToast("未填写条形码");
                        return;
                    }
                    if (tv_name.getText().toString().equals("")) {
                        showToast("未填写商品名称");
                        return;
                    }

                    if (tv_icitemType.getText().toString().equals("") || tv_icitemType.getText().toString().equals("请选择")) {
                        showToast("未选择商品类别");
                        return;
                    }
                    if (!cb_fever.isChecked() && tv_kfPeriod.getText().toString().equals("")) {
                        showToast("未填写保质期");
                        return;
                    }

                    if (tv_kfDateType.getText().toString().equals("") || tv_kfDateType.getText().toString().equals("请选择")) {
                        showToast("未选择保质期类型");
                        return;
                    }
                    if (tv_packSpeci.getText().toString().equals("") || tv_packSpeci.getText().toString().equals("请选择")) {
                        showToast("未选择包装方式");
                        return;
                    }


                    try {
                        JSONObject jsonObject = new JSONObject();
                        if (getIntent().getStringExtra("id") != null) {
                            jsonObject.put("id", getIntent().getStringExtra("id"));
                        }

                        if (cb_fever.isChecked()) {

                            jsonObject.put("fever", 1);

                        } else {
                            jsonObject.put("fever", 0);
                        }
                        jsonObject.put("model", tv_model.getText().toString());

                        jsonObject.put("basicUnitName", tv_basicUnitName.getTag().toString());
                        jsonObject.put("ftypeName", tv_ftypeName.getTag().toString());
                        jsonObject.put("barCode", tv_barCode.getText().toString());
                        jsonObject.put("name", tv_name.getText().toString());
                        jsonObject.put("note", tv_note.getText().toString());

                        jsonObject.put("icitemType", tv_icitemType.getTag().toString());
                        jsonObject.put("photo", filePath);
                        if (!tv_kfPeriod.getText().toString().equals("")) {
                            jsonObject.put("kfPeriod", tv_kfPeriod.getText().toString());
                        }

                        if (tv_kfDateType.getTag() != null) {
                            jsonObject.put("kfDateType", tv_kfDateType.getTag().toString());
                        }


                        jsonObject.put("packSpeci", tv_packSpeci.getTag().toString());


                        if (getIntent().getStringExtra("id") != null) {
                            requestData(HttpHandler.create(GoodShareService.class)
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
                            requestData(HttpHandler.create(GoodShareService.class)
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
        } else {
            mFreeToolbar.setActionTextVisible(false);
        }


        if (getIntent().getStringExtra("title").endsWith("新增")) {
            tv_kfDateType.setText("天");
            tv_kfDateType.setTag("天");
            String time = (System.currentTimeMillis() + "").substring(1);
            tv_barCode.setText("Q" + time);
            tv_barCode.setSelection(("Q" + time).length());
        }

        getDetail();

    }

    private void getDetail() {
        if (getIntent().getStringExtra("id") != null) {
            requestData(HttpHandler.create(GoodShareService.class)
                    .getCheck(UserHelper.getSessionId(),
                            getIntent().getStringExtra("id")))
                    .subscribe(new TaskObserver<ShareGoodDetailBean>() {
                        @Override
                        public void onSuccess(ShareGoodDetailBean value) {
                            ShareGoodDetailBean.ObjBean objBean = value.getObj();
                            if (objBean != null) {
                                filePath = objBean.getPhoto();
                                if(filePath!=null&&!filePath.equals("")){
                                    String[] url = (filePath + ",").split(",");
                                    for (int i = 0; i < url.length; i++) {
                                        addImageView(url[i], mFlowLayout);
                                    }
                                }


                                tv_barCode.setText(objBean.getBarCode());

                                tv_ftypeName.setText(objBean.getFtypeName());
                                tv_ftypeName.setTag(objBean.getFtypeName());
                                if (objBean.getFever() == 0) {
                                    cb_fever.setChecked(false);
                                } else {
                                    cb_fever.setChecked(true);
                                }
                                tv_note.setText(objBean.getNote());
                                tv_model.setText(objBean.getModel());
                                tv_kfDateType.setText(objBean.getKfDateType());
                                tv_kfDateType.setTag(objBean.getKfDateType());

                                tv_kfPeriod.setText(objBean.getKfPeriod() + "");
                                tv_name.setText(objBean.getName());
                                tv_basicUnitName.setText(objBean.getBasicUnitName());
                                tv_basicUnitName.setTag(objBean.getBasicUnitName());
                                tv_icitemType.setText(objBean.getIcitemType());
                                tv_icitemType.setTag(objBean.getIcitemType());
                                tv_packSpeci.setText(objBean.getPackSpeci());
                                tv_packSpeci.setTag(objBean.getPackSpeci());
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

    @OnClick(R.id.iv_pic)
    public void clickSelect() {

        Intent intent = new Intent(GoodShareAddActivity.this, UploadImageNewActivity.class);
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


    @OnClick(R.id.tv_ftypeName)
    public void clickFtypeName() {
        TypeCodeUtil.getGroupCodeType(GoodShareAddActivity.this, "ftypeNameType", tv_ftypeName);

    }

    @OnClick(R.id.tv_kfDateType)
    public void clickKfDateType() {
        TypeCodeUtil.getGroupCodeType(GoodShareAddActivity.this, "kfpType", tv_kfDateType);

    }


    @OnClick(R.id.tv_basicUnitName)
    public void clickBasicUnitName() {
        TypeCodeUtil.getGroupCodeType(GoodShareAddActivity.this, "basicUnitNameType", tv_basicUnitName);

    }


    @OnClick(R.id.tv_icitemType)
    public void clickIcitemType() {
        TypeCodeUtil.getGroupCodeType(GoodShareAddActivity.this, "icitemType", tv_icitemType);

    }


    @OnClick(R.id.tv_packSpeci)
    public void clickPackSpeci() {
        TypeCodeUtil.getGroupCodeType(GoodShareAddActivity.this, "packSpeciType", tv_packSpeci);

    }


}
