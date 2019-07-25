package com.qhsoft.killrecord.view.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.util.DisplayUtils;
import com.qhsoft.killrecord.view.MaterialProgressDrawable;


/**
 * Description:
 * Author:lin
 * Date:2017-09-07
 */

public class LoadingDialog extends DialogFragment {
    private ImageView ivLoading;
    private TextView tvMessage;
    private MaterialProgressDrawable mProgressDrawable;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.AlertDialogStyle);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_loading, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivLoading =  view.findViewById(R.id.iv_loading);
        tvMessage = view.findViewById(R.id.tv_message);
        mProgressDrawable = new MaterialProgressDrawable(getContext(), ivLoading);
        mProgressDrawable.updateSizes(MaterialProgressDrawable.DEFAULT);
        mProgressDrawable.setBackgroundColor(0xFFFAFAFA);
        mProgressDrawable.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.loading));

        mProgressDrawable.setProgressRotation(0.8f);
        mProgressDrawable.setStartEndTrim(0f, 0.5f);
        mProgressDrawable.setAlpha(255);
        mProgressDrawable.start();

        ivLoading.setImageDrawable(mProgressDrawable);
        tvMessage.setText(  getArguments().getString("message"));
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    getActivity().finish();
                    dismiss();
                }
                return false;
            }
        });
        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Dialog dialog = getDialog();
        if (dialog == null) {
            return;
        }
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.width = (int) (DisplayUtils.getPhoneScreenParam(getActivity())[1] * 0.3);
        ;
        wlp.height = wlp.width;
        wlp.gravity = Gravity.CENTER;
        //重新配置属性
        window.setAttributes(wlp);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mProgressDrawable != null) {
            mProgressDrawable.stop();
        }
    }
}
