package com.qhsoft.killrecord.module.file;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.FileUploadResultBean;
import com.qhsoft.killrecord.model.remote.param.FileUploadParam;
import com.qhsoft.killrecord.model.remote.service.FileUploadService;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.photo.util.PhotoSelectorUtil;
import com.qhsoft.killrecord.view.FlowLayout;
import com.qhsoft.killrecord.view.FreeToolbar;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.simple.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.qhsoft.killrecord.photo.util.PhotoSelectorUtil.REQUEST_CODE;

/**
 * Description:
 * Author:lin
 * Date:2017-10-18
 */


public class UploadImageNewActivity extends BaseActivity {

    @Bind(R.id.file_upload)
    TextView file_upload;
    @Bind(R.id.mFlowLayout)
    FlowLayout mFlowLayout;
    @Bind(R.id.file_add_name)
    TextView file_add_name;
    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;



    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_upload_file_new;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mFreeToolbar.setTitle("上传图片");

    }
    @OnClick({R.id.file_browse,R.id.file_upload})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.file_browse:
                RxPermissions permissions = new RxPermissions(UploadImageNewActivity.this);
                permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    showFileChooser();
                                } else {
                                    showToast("未开启相应权限");
                                }
                            }
                        });
                break;
            case R.id.file_upload:
                if (list != null) {
                    list_obj.clear();
                    for (int i = 0; i < list.size(); i++) {
                        if(i==list.size()-1){
                            uploadTask(list.get(i),true);
                        }else {
                            uploadTask(list.get(i),false);
                        }

                    }
                }
                break;
        }


    }

    private List< FileUploadResultBean.ObjBean> list_obj=new ArrayList<>();
    private void uploadTask(final String path, final boolean isLast) {

        final String data = FilePostHelp.getData(path);
        //TODO 文件上传接口对接
        FileUploadParam fileUploadParam = new FileUploadParam();
        File file = new File(path);
        if (file.length() > 1 * 1024 * 1024) {
            showToast("文件太大了");
            return;
        }
        fileUploadParam.setFileName(file.getName());
        fileUploadParam.setFileData(data);


        requestData(HttpHandler.create(FileUploadService.class)
                .fileUpload(UserHelper.getSessionId(),getRequestBody(fileUploadParam)))
                .subscribe(new TaskObserver<FileUploadResultBean>() {
                    @Override
                    public void onSuccess(FileUploadResultBean value) {

                        if (value != null) {
                            showToast(value.getMsg());
                            if (value.getObj() != null) {
                                FileUploadResultBean.ObjBean objBean = value.getObj();
                                if (path != null) {
                                    objBean.setLocalPath(path);
                                }
                                list_obj.add(objBean);
                                if (getIntent().getStringExtra("tag") != null&&isLast) {
                                    EventBus.getDefault().post(list_obj, getIntent().getStringExtra("tag"));
                                }


                            }

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



    private void showFileChooser() {

        PhotoSelectorUtil.initMoreImageConfig(UploadImageNewActivity.this);


    }

    private List<String> list;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {


            int width = getResources().getDisplayMetrics().widthPixels;

            list = PhotoSelectorUtil.getMorePhotoUrl(data);

            StringBuilder sb=new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if(i==list.size()-1){
                    sb.append(list.get(i));
                }else {
                    sb.append(list.get(i)+"\n");
                }

                View view = inflate(R.layout.layout_imageview);
                ImageView image = view.findViewById(R.id.image);
                loadImagePath(list.get(i), image);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) image.getLayoutParams();
                params.width = width/4 - 10;
                params.height = width/4 - 10;
                image.setLayoutParams(params);

                params = new LinearLayout.LayoutParams(width/4, width/4);
                mFlowLayout.addView(view, params);


            }
            file_add_name.setText(sb.toString());


        }

    }


}
