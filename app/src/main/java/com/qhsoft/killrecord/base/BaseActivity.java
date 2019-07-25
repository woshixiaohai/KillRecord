package com.qhsoft.killrecord.base;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.net.HttpConfig;
import com.qhsoft.killrecord.util.FileUtil;
import com.qhsoft.killrecord.util.GlideUtil;
import com.qhsoft.killrecord.util.Logger;
import com.qhsoft.killrecord.util.NetUtil;
import com.qhsoft.killrecord.util.SpUtil;
import com.qhsoft.killrecord.util.TypeCodeUtil;
import com.qhsoft.killrecord.view.FlowLayout;

import org.simple.eventbus.EventBus;

import java.io.File;
import java.util.Map;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * Description:
 * Author:lin
 * Date:2017-09-07
 */

public abstract class BaseActivity extends BaseAutoLayoutActivity {
    private Toast mToast;
    protected SpUtil spUtil;
    private DownLoadCompleteReceiver downLoadCompleteReceiver;
    private DownloadManager downManager;

    @SuppressLint("ResourceType")
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateViewBefore(savedInstanceState);


        if (getContentViewLayoutId() < 1) {
            throw new IllegalStateException("activity content ID not use");
        }
        setContentView(getContentViewLayoutId());
        spUtil=new SpUtil(getApplication());
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        bindView();
        onViewCreated(savedInstanceState);
        onCreateViewCompleted();








    }
    private class DownLoadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

                d(id+"");
                if (id > 0 && downManager != null) {
                    if (downManager.getUriForDownloadedFile(id) != null) {
                        String path = downManager.getUriForDownloadedFile(id).getPath();
                        d(path);
                        String mimeType=downManager.getMimeTypeForDownloadedFile(id);
                        d(mimeType);
                        FileUtil.showAttachment(BaseActivity.this, path,mimeType);
                    }


                }

            }
        }
    }

    protected void downloadFile( String url) {
        d(HttpConfig.getPictureUrl(url));
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(HttpConfig.getPictureUrl(url)));

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setTitle("下载");
        request.setDescription("附件正在下载");
        request.setAllowedOverRoaming(false);

        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, System.currentTimeMillis()+"");
        downManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        downManager.enqueue(request);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        if (downLoadCompleteReceiver == null) {
            downLoadCompleteReceiver = new DownLoadCompleteReceiver();
        }
        registerReceiver(downLoadCompleteReceiver, intentFilter);


    }
    protected void downloadFile( String url,String fileName) {
        d(HttpConfig.getPictureUrl(url));
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(HttpConfig.getPictureUrl(url)));

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setTitle("下载");
        request.setDescription("附件正在下载");
        request.setAllowedOverRoaming(false);//漫游状态是否允许下载
        File file=new File(Environment.getExternalStorageDirectory()+"/qhsoft/"+fileName);
        if(file.exists()){
            file.delete();
        }

        request.setDestinationInExternalPublicDir( "qhsoft", fileName);


        downManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        downManager.enqueue(request);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        if (downLoadCompleteReceiver == null) {
            downLoadCompleteReceiver = new DownLoadCompleteReceiver();
        }
        registerReceiver(downLoadCompleteReceiver, intentFilter);


    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @LayoutRes
    protected abstract int getContentViewLayoutId();

    /**
     * 在setContentView之前
     *
     * @param savedInstanceState
     */
    protected void onCreateViewBefore(Bundle savedInstanceState) {
    }

    /**
     * 在setContentView之后
     */
    protected abstract void onViewCreated(Bundle savedInstanceState);

    /**
     * 在onViewCreate之后
     */
    protected void onCreateViewCompleted() {

    }

    protected void bindView() {

    }


    /**
     * 显示Toast
     *
     * @param res String资源
     */
    protected void showToast(@StringRes int res) {
        if (mToast == null) {
            mToast = Toast.makeText(this, res, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(res);
        }
        mToast.show();
    }

    /**
     * 显示Toast
     *
     * @param text 文本
     */
    protected void showToast(String text) {
        if(text==null||text.equals("")){
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        }
        mToast.setText(text);
        mToast.show();
    }
    protected void d(String str){
        Logger.d(getClass().getSimpleName(),str);
    }



    protected  <T extends View> T findViewByIds(@IdRes int id) {

        return (T)findViewById(id);
    }
    protected <T extends View> T  inflate(int resid){
        return (T)LayoutInflater.from(this).inflate(resid,null);
    }
    protected View inflate(int resid, ViewGroup parent){
        return LayoutInflater.from(this).inflate(resid,parent,false);
    }
    protected void loadImage(String url, ImageView iv){
        GlideUtil.loadImage(this,url,iv);

    }
    protected void loadPath(String path, ImageView iv){
        GlideUtil.loadImagePath(this,path,iv);
    }
    protected Observable requestData(Observable observable){
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.bindToLifecycle());
    }
    protected Map<String, RequestBody> getMapList(Object obj){
        return NetUtil.getMapList(obj);
    }
    protected Map<String,RequestBody> getMapList(Map<String, RequestBody> map,Object obj){
        return NetUtil.getMapList(map,obj);
    }
    protected Map<String,RequestBody> getMapList(Map<String, String> map){
        return NetUtil.getMapList(map);
    }


    protected RequestBody getRequestBody(File file){
        return NetUtil.getRequestBody(file);
    }
    protected RequestBody getRequestBody(String str){
        d(str);
        return NetUtil.getRequestBody(str);
    }
    protected RequestBody getRequestBody(Object object){
        String str=new Gson().toJson(object);
        d(str);
        return NetUtil.getRequestBody(str);
    }
    protected void getGroupCodeType(String type,TextView tv){
        TypeCodeUtil.getGroupCodeType(this,type,tv);
    }
    protected void loadImagePath(String path,ImageView iv){
        GlideUtil.loadImagePath(this,path,iv);

    }
    protected void addImageView(String filePath, FlowLayout mFlowLayout) {
        int width = getResources().getDisplayMetrics().widthPixels-40;
        View view = inflate(R.layout.layout_imageview);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        loadImage(filePath, image);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) image.getLayoutParams();
        params.width = width/4 - 10;
        params.height = width/4 - 10;
        image.setLayoutParams(params);

        params = new LinearLayout.LayoutParams(width/4, width/4);
        mFlowLayout.addView(view, params);
    }
    protected void getGroupCodeTypeRadioGroup(String type,RadioGroup rg){
        TypeCodeUtil.getGroupCodeTypeRadioGroup(this,type,rg);
    }




}
