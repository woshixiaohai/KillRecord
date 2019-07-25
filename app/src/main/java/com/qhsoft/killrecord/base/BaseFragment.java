package com.qhsoft.killrecord.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.qhsoft.killrecord.util.GlideUtil;
import com.qhsoft.killrecord.util.Logger;
import com.qhsoft.killrecord.util.NetUtil;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * Description:
 * Author:hong
 * Date:2017-09-07
 */

public abstract class BaseFragment extends RxFragment {
    private View rootView;
    private Toast mToast;
    private boolean isResume;
    private boolean isFirstToUser = false;

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getFragmentLayout() < 1) {
            throw new IllegalStateException("fragment content ID not use");
        }
        rootView = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this,rootView);
        EventBus.getDefault().register(this);
        bindView();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @LayoutRes
    protected abstract int getFragmentLayout();


    protected void bindView() {
    }

    @Override
    public void onResume() {
        super.onResume();
        isResume = true;
        if (getUserVisibleHint()) {
            if (!isFirstToUser) {
                onFirstUserVisible();
                isFirstToUser = true;
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isResume && isVisibleToUser) {
            if (!isFirstToUser) {
                onFirstUserVisible();
                isFirstToUser = true;
            } else {
                onUserVisible();
            }
        }
    }

    protected void onFirstUserVisible() {

    }

    protected void onUserVisible() {

    }

    /**
     * 显示Toast
     *
     * @param res String资源
     */
    protected void showToast(@StringRes int res) {
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), res, Toast.LENGTH_SHORT);
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
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }


    @SuppressWarnings("unchecked")
    protected <T extends View> T findViewById(@IdRes int id) {
        if (rootView == null) {
            throw new IllegalStateException("rootView  destroyed");
        }
        return (T) rootView.findViewById(id);
    }
    protected Observable requestData(Observable observable){
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.bindToLifecycle());
    }
    protected void d(String str){
        Logger.d(getClass().getSimpleName(),str);
    }
    protected View inflate(int resid){
        return LayoutInflater.from(getActivity()).inflate(resid,null);
    }
    protected void loadImage(String url, ImageView iv){
        GlideUtil.loadImage(getActivity(),url,iv);

    }
    protected RequestBody getRequestBody(String str){
        d(str);
        return NetUtil.getRequestBody(str);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rootView = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isResume = false;
        isFirstToUser = false;
    }
}
