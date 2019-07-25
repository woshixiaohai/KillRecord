package com.qhsoft.killrecord.view.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.qhsoft.killrecord.R;

import java.lang.reflect.Field;

/**
 * Description:
 * Author:lin
 * Date:2017-09-15
 */

public class DatePickerDialog extends DialogFragment {
    private DatePicker mDatePicker;
    private View actionConfirm;
    private View actionCancel;


    private int year;
    private int month;
    private int day;
    private OnDateSelectedListener onDateSelectedListener;

    public void setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
        this.onDateSelectedListener = onDateSelectedListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.DatePicker);

        year = getArguments().getInt("year", 1949);
        month = getArguments().getInt("month", 10);
        day = getArguments().getInt("day", 1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_date_picker, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDatePicker = view.findViewById(R.id.mDatePicker);
        actionConfirm = view.findViewById(R.id.action_confirm);
        actionCancel = view.findViewById(R.id.action_cancel);

        mDatePicker.updateDate(year, month - 1, day);
        actionConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onDateSelectedListener != null) {
                    String date = mDatePicker.getYear() + "-" + (mDatePicker.getMonth() + 1) + "-" + mDatePicker.getDayOfMonth();
                    onDateSelectedListener.onDateSelected(date);
                }
                dismiss();
            }
        });
        actionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mDatePicker.setBackgroundColor(getResources().getColor(R.color.date_bg));
        setDatePickerDividerColor(mDatePicker);

    }

    private void setDatePickerDividerColor(DatePicker datePicker) {

        LinearLayout llFirst = (LinearLayout) datePicker.getChildAt(0);

        LinearLayout mSpinners = (LinearLayout) llFirst.getChildAt(0);
        for (int i = 0; i < mSpinners.getChildCount(); i++) {
            NumberPicker picker = (NumberPicker) mSpinners.getChildAt(i);

            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {//颜色
                    pf.setAccessible(true);
                    try {
//                        pf.set(picker.getHeight(), 2);
                        pf.set(picker, new ColorDrawable(getResources().getColor(R.color.text_black_light)));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }


            }
        }
    }


}
