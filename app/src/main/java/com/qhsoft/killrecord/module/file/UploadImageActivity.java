package com.qhsoft.killrecord.module.file;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.FileUploadResultBean;
import com.qhsoft.killrecord.model.remote.param.FileUploadParam;
import com.qhsoft.killrecord.model.remote.service.FileUploadService;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.view.FreeToolbar;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.simple.eventbus.EventBus;

import java.io.File;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Description:
 * Author:lin
 * Date:2017-10-18
 */


public class UploadImageActivity extends BaseActivity {

    private static final int REQUEST_EX = 1;
    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;

    @Bind(R.id.file_browse)
    TextView file_query;
    @Bind(R.id.file_upload)
    TextView file_upload;
    @Bind(R.id.tv_path)
    TextView tv_path;
    @Bind(R.id.file_add_name)
    TextView file_add_name;
    @OnClick(R.id.file_browse)
    public void clickQuery(){
        RxPermissions permissions = new RxPermissions(UploadImageActivity.this);
        permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
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

    }


    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_upload_file;
    }



    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mFreeToolbar.setTitle("附件上传");

        RxView.clicks(file_upload)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (tv_path.getTag() != null) {
                            String path = tv_path.getTag().toString();
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
                                    .fileUpload(UserHelper.getSessionId(),
                                            getRequestBody(fileUploadParam)))
                                    .subscribe(new TaskObserver<FileUploadResultBean>() {
                                        @Override
                                        public void onSuccess(FileUploadResultBean value) {

                                            if(value!=null){
                                                showToast(value.getMsg());
                                                if(value.getObj()!=null){
                                                    FileUploadResultBean.ObjBean objBean=value.getObj();
                                                    if(localPath!=null){
                                                        objBean.setLocalPath(localPath);
                                                    }
                                                    if(getIntent().getStringExtra("tag")!=null){
                                                        EventBus.getDefault().post(objBean, getIntent().getStringExtra("tag"));
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
                    }
                });


    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, REQUEST_EX);


    }
    private String localPath;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_EX) {

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String path = cursor.getString(columnIndex);
                cursor.close();


                tv_path.setText("文件路径:" + path);
                tv_path.setTag(path);
                File file = new File(path);
                file_add_name.setText(file.getName());
                localPath=path;



            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
