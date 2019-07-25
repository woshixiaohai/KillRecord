package com.qhsoft.killrecord.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.util.DisplayUtils;

/**
 * Description:
 * Author:lin
 * Date:2017-09-07
 */

public class MessageDialog extends DialogFragment {

    private TextView tvMessage;
    private TextView actionConfirm;
    private TextView actionCancel;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.AlertDialogStyle);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvMessage = view.findViewById(R.id.tv_message);
        actionConfirm =  view.findViewById(R.id.action_confirm);
        actionCancel =  view.findViewById(R.id.action_cancel);
        tvMessage.setText("");
        actionConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });




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
        wlp.width = (int) (DisplayUtils.getPhoneScreenParam(getActivity())[0]*0.7);
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.gravity = Gravity.CENTER;
        //重新配置属性
        window.setAttributes(wlp);
    }
}
