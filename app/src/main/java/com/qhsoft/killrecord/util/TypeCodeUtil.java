package com.qhsoft.killrecord.util;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.model.local.UserHelper;
import com.qhsoft.killrecord.model.remote.bean.TypeCodeBean;
import com.qhsoft.killrecord.model.remote.bean.TypeGroupCodeBean;
import com.qhsoft.killrecord.model.remote.service.BaseDataService;
import com.qhsoft.killrecord.net.HttpHandler;
import com.qhsoft.killrecord.net.TaskObserver;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.view.dialog.OnSelectedItemListener;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Author:lin
 * Date:2017-09-25
 */

public class TypeCodeUtil {

    /**
     * 获取数据词典名称
     * @param type
     * @param tv
     * @param son
     */
    public static void getCodeType(final RxAppCompatActivity activity, String type, final TextView tv, String son){
        HttpHandler.create(BaseDataService.class)
                .getTypeCode(type, son, UserHelper.getSessionId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(activity.<TypeCodeBean>bindToLifecycle())
                .subscribe(new TaskObserver<TypeCodeBean>() {
                    @Override
                    public void onSuccess(TypeCodeBean value) {
                        tv.setText(value.getObj());

                    }

                    @Override
                    public void onFailure(FailureMessage e) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                });
    }

    /**
     * 显示数据字典组对话框
     * @param type
     * @param tv
     */
    public static void getGroupCodeType(final RxAppCompatActivity activity, final String type, final TextView tv){
        TypeGroupCodeBean typeGroupCodeBean= (TypeGroupCodeBean) NetUtil.read(activity,TypeGroupCodeBean.class.getSimpleName()+type);
        if(typeGroupCodeBean==null){
            HttpHandler.create(BaseDataService.class)
                    .getTypeGroupCode(type, UserHelper.getSessionId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(activity.<TypeGroupCodeBean>bindToLifecycle())
                    .subscribe(new TaskObserver<TypeGroupCodeBean>() {
                        @Override
                        public void onSuccess(TypeGroupCodeBean value) {
                            NetUtil.write(activity,value,TypeGroupCodeBean.class.getSimpleName()+type);
                            getCodeList(activity,value, tv);

                        }

                        @Override
                        public void onFailure(FailureMessage e) {

                        }

                        @Override
                        public void onSubscribe(Disposable d) {
                        }
                    });

        }else{
            getCodeList(activity,typeGroupCodeBean, tv);
        }

    }
    public static void getGroupCodeTypeRadioGroup(final RxAppCompatActivity activity, final String type, final RadioGroup rg){
        TypeGroupCodeBean typeGroupCodeBean= (TypeGroupCodeBean) NetUtil.read(activity,TypeGroupCodeBean.class.getSimpleName()+type);
        if(typeGroupCodeBean==null){
            HttpHandler.create(BaseDataService.class)
                    .getTypeGroupCode(type, UserHelper.getSessionId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(activity.<TypeGroupCodeBean>bindToLifecycle())
                    .subscribe(new TaskObserver<TypeGroupCodeBean>() {
                        @Override
                        public void onSuccess(TypeGroupCodeBean value) {
                            NetUtil.write(activity,value,TypeGroupCodeBean.class.getSimpleName()+type);
                            getCodeListRadioGroup(activity,value,rg);

                        }

                        @Override
                        public void onFailure(FailureMessage e) {

                        }

                        @Override
                        public void onSubscribe(Disposable d) {
                        }
                    });

        }else{
            getCodeListRadioGroup(activity,typeGroupCodeBean,rg);
        }

    }

    public static void getGroupCodeTypeList(final RxAppCompatActivity activity, final String type, final OnGetTypeCodeListener onGetTypeCodeListener){


            HttpHandler.create(BaseDataService.class)
                    .getTypeGroupCode(type, UserHelper.getSessionId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(activity.<TypeGroupCodeBean>bindToLifecycle())
                    .subscribe(new TaskObserver<TypeGroupCodeBean>() {
                        @Override
                        public void onSuccess(TypeGroupCodeBean value) {
                            onGetTypeCodeListener.getTypeCode(value.getObj().getTstypes());

                        }

                        @Override
                        public void onFailure(FailureMessage e) {

                        }

                        @Override
                        public void onSubscribe(Disposable d) {
                        }
                    });



    }



    /**
     * 显示数据字典组对话框
     * @param value
     * @param tv
     */
    private static void getCodeList(FragmentActivity activity, TypeGroupCodeBean value, final TextView tv) {
        final List<TypeGroupCodeBean.ObjBean.TstypesBean> list= value.getObj().getTstypes();
        String[] strs=new String[list.size()];
        for(int i=0;i<list.size();i++){
            strs[i]=list.get(i).getTypename();
        }
        DialogFactory.createSingleBuilder()
                .setSelectedName(tv.getText().toString())
                .addSelectArray(strs)
                .setTitle(value.getObj().getTypegroupname())
                .setOnSelectedListener(new OnSelectedItemListener() {
            @Override
            public void onSelectedItem(int position) {
                tv.setText(list.get(position).getTypename());
                tv.setTag(list.get(position).getTypecode());
            }
        }) .show(activity);

    }
    private static void getCodeListRadioGroup(FragmentActivity activity, TypeGroupCodeBean value,RadioGroup rg) {
        final List<TypeGroupCodeBean.ObjBean.TstypesBean> list= value.getObj().getTstypes();

        for(int i=0;i<list.size();i++){
            RadioButton rb= (RadioButton) LayoutInflater.from(activity).inflate(R.layout.layout_radiogroup_item,null);

            rb.setText(list.get(i).getTypename());
            rb.setTag(list.get(i).getTypecode());
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(140,80);
            rg.addView(rb,params);
            LinearLayout.LayoutParams params_rb= (LinearLayout.LayoutParams) rb.getLayoutParams();
            params_rb.leftMargin=30;
            rb.setLayoutParams(params_rb);
            if(i==0){
                rg.check(rb.getId());
            }

        }



    }

    /**
     * 返回数据字典数据
     */
    public interface OnGetTypeCodeListener{
        void getTypeCode(List<TypeGroupCodeBean.ObjBean.TstypesBean> list);
    }



}
