package com.qhsoft.killrecord.module.file;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
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
import com.qhsoft.killrecord.view.FreeToolbar;

import org.simple.eventbus.EventBus;

import java.io.File;

import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Author:lin
 * Date:2017-10-18
 */


public class UploadFileActivity extends BaseActivity {

    private static final int REQUEST_EX = 1;
    private FreeToolbar mFreeToolbar;
    private TextView file_query;
    private TextView file_upload;
    private TextView tv_path;
    private TextView file_add_name;


    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_upload_file;
    }

    @Override
    protected void bindView() {
        super.bindView();

        mFreeToolbar=  findViewById(R.id.mFreeToolbar);
        file_query= findViewById(R.id.file_browse);
        file_upload=  findViewById(R.id.file_upload);
        tv_path=  findViewById(R.id.tv_path);
        file_add_name=  findViewById(R.id.file_add_name);


    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mFreeToolbar.setTitle("附件上传");

        file_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });
        file_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_path.getTag()!=null){
                    final String path=tv_path.getTag().toString();
                    String data=FilePostHelp.getData(path);
                    //TODO 文件上传接口对接
                    FileUploadParam fileUploadParam=new FileUploadParam();
                    final File file=new File(path);
                    fileUploadParam.setFileName(file.getName());
                    fileUploadParam.setFileData(data);

                    requestData(HttpHandler.create(FileUploadService.class)
                            .fileUpload(UserHelper.getSessionId(),
                            getRequestBody(fileUploadParam)))
                            .subscribe(new TaskObserver<FileUploadResultBean>() {
                                @Override
                                public void onSuccess(FileUploadResultBean value) {
                                    showToast(value.getMsg());
                                    EventBus.getDefault().post(value.getObj().getFilePath(),"getFilePath");
                                    EventBus.getDefault().post(value.getObj().getFileName(),"getFileName");
                                    EventBus.getDefault().post(value.getObj().getFileId(),"getFileId");
                                    //本地路径
                                    EventBus.getDefault().post(file.getPath(),"getPath");


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
        Intent intent = new Intent();
        intent.putExtra("explorer_title","打开目录");
        File file=Environment.getExternalStorageDirectory();
        intent.setDataAndType(Uri.fromFile(file), "*/*");
        intent.setClass(UploadFileActivity.this, FileBrowserActivity.class);
        startActivityForResult(intent, REQUEST_EX);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_EX) {
                String path = data.getData().getPath();

                tv_path.setText("文件路径:"+path);
                tv_path.setTag(path);
                File file=new File(path);
                file_add_name.setText(file.getName());
                EventBus.getDefault().post(path,"getPath");

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }




}
