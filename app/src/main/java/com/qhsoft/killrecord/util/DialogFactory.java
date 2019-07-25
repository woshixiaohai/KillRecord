package com.qhsoft.killrecord.util;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import com.qhsoft.killrecord.view.dialog.DatePickerDialog;
import com.qhsoft.killrecord.view.dialog.LoadingDialog;
import com.qhsoft.killrecord.view.dialog.MessageDialog;
import com.qhsoft.killrecord.view.dialog.OnDateSelectedListener;
import com.qhsoft.killrecord.view.dialog.OnSelectedItemListener;
import com.qhsoft.killrecord.view.dialog.OnSheetSelectedListener;
import com.qhsoft.killrecord.view.dialog.SheetDialog;
import com.qhsoft.killrecord.view.dialog.SingleDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author:hong
 * Date:2017-09-07
 */

public class DialogFactory {

    /**
     * 显示提交对话框，属于loading 对话框的一种
     * 默认信息为提交中
     *
     * @param activity 当前的activity
     */
    public static void showCommitDialog(FragmentActivity activity) {
        showLoadingDialog(activity, "提交中");
    }

    /**
     * 显示加载对话框
     *
     * @param activity host
     * @param message  对话框文本信息
     */
    public static void showLoadingDialog(FragmentActivity activity, String message) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        DialogFragment currentDialog = (DialogFragment) fragmentManager.findFragmentByTag("LoadingDialog");
        if (currentDialog == null) {
            LoadingDialog dialog = new LoadingDialog();
            Bundle bundle = new Bundle();
            bundle.putString("message", message);
            dialog.setArguments(bundle);
            dialog.show(fragmentManager, "LoadingDialog");
            fragmentManager.executePendingTransactions();
        }
    }

    /**
     * 隐藏提交对话框
     *
     * @param activity 当前的activity
     */
    public static void dismissLoadingDialog(FragmentActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        DialogFragment currentDialog = (DialogFragment) fragmentManager.findFragmentByTag("LoadingDialog");
        if (currentDialog == null) {
            return;
        }
        currentDialog.dismiss();

    }

    /**
     * 获取消息对话框构建体
     *
     * @return
     */
    public static MessageBuilder createMessageDialogBuilder() {
        return new MessageBuilder();
    }

    /**
     * 底部操作对话框
     *
     * @return
     */
    public static SheetBuilder createSheetDialogBuilder() {
        return new SheetBuilder();
    }

    /**
     * 单选对话框
     *
     * @return
     */
    public static SingleBuilder createSingleBuilder() {
        return new SingleBuilder();
    }

    /**
     * 日期选择器
     *
     * @return
     */
    public static DateBuilder createDateBuilder() {
        return new DateBuilder();
    }



    public final static class MessageBuilder {

        private String message;


        private MessageBuilder() {

        }



        /**
         * 设置消息
         *
         * @param message
         * @return
         */
        public MessageBuilder setMessage(String message) {
            this.message = message;
            return this;
        }





        /**
         * 显示
         *
         * @param context FragmentActivity才能显示
         */
        public void show(Context context) {
            FragmentActivity activity;
            if (context instanceof FragmentActivity) {
                activity = (FragmentActivity) context;
            } else {
                new IllegalStateException("不支持的activity").printStackTrace();
                return;
            }
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            DialogFragment currentDialog = (DialogFragment) fragmentManager.findFragmentByTag("messageDialog");
            if (currentDialog == null) {
                MessageDialog dialog = new MessageDialog();

                Bundle bundle = new Bundle();
                bundle.putString("message", message);
                dialog.setArguments(bundle);

                dialog.show(fragmentManager, "messageDialog");
                fragmentManager.executePendingTransactions();
            }
        }

    }

    public final static class SheetBuilder {
        private List<String> actionTextList;

        private OnSheetSelectedListener onSheetSelectedListener;

        public SheetBuilder setOnSheetSelectedListener(OnSheetSelectedListener onSheetSelectedListener){
            this.onSheetSelectedListener=onSheetSelectedListener;
            return this;
        }
        private SheetBuilder() {
            actionTextList = new ArrayList<>();
        }


        /**
         * 添加选项
         *
         * @param text 文字
         * @return
         */
        public SheetBuilder addActionText(String text) {
            if (!TextUtils.isEmpty(text)) {
                actionTextList.add(text);
            }
            return this;
        }

        /**
         * 添加选项数组
         *
         * @param actionArray
         * @return
         */
        public SheetBuilder addActionArray(String[] actionArray) {
            if (actionArray != null && actionArray.length > 0) {
                for (int i = 0; i < actionArray.length; i++) {
                    actionTextList.add(actionArray[i]);
                }
            }
            return this;
        }



        public SheetBuilder andThen(DialogConsumer<SheetBuilder> consumer) {
            consumer.accept(this);
            return this;
        }

        /**
         * 显示
         *
         * @param context FragmentActivity才能显示
         */
        public void show(Context context) {
            FragmentActivity activity;
            if (context instanceof FragmentActivity) {
                activity = (FragmentActivity) context;
            } else {
                new IllegalStateException("不支持的activity").printStackTrace();
                return;
            }
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            DialogFragment currentDialog = (DialogFragment) fragmentManager.findFragmentByTag("SheetDialog");
            if (currentDialog == null) {
                SheetDialog dialog = new SheetDialog();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("actionTextList", (ArrayList<String>) actionTextList);



                dialog.setArguments(bundle);
                dialog.show(fragmentManager, "SheetDialog");
                fragmentManager.executePendingTransactions();
                dialog.setOnSelectedListener(new SheetDialog.OnSelectedListener() {
                    @Override
                    public void onSelected(int itemPosition, String actionName) {
                        if(onSheetSelectedListener!=null){
                            onSheetSelectedListener.onSheetSelected(itemPosition,actionName);
                        }
                    }
                });





            }
        }
    }

    public final static class SingleBuilder {

        private List<String> selectList;

        private String title;
        private String selectedName;
        private int selectedPosition = -1;
        private OnSelectedItemListener onSelectedListener;

        public SingleBuilder() {
            selectList = new ArrayList<>();
        }



        public SingleBuilder setOnSelectedListener(OnSelectedItemListener onSelectedListener) {
            this.onSelectedListener = onSelectedListener;
            return this;
        }


        /**
         * 设置标题
         *
         * @param title
         * @return
         */
        public SingleBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * 设置选中的选项
         *
         * @param selectedName
         * @return
         */
        public SingleBuilder setSelectedName(String selectedName) {
            this.selectedName = selectedName;
            return this;
        }

        /**
         * 设置选中的位置
         * 如果选中的文本被识别出来，则设置此项失效
         *
         * @param selectedPosition
         * @return
         */
        public SingleBuilder setSelectedPosition(int selectedPosition) {
            this.selectedPosition = selectedPosition;
            return this;
        }

        /**
         * 添加选项数组
         *
         * @param strings
         * @return
         */
        public SingleBuilder addSelectArray(String[] strings) {
            if (strings != null && strings.length != 0) {
                for (String s : strings) {
                    selectList.add(s);
                }
            }
            return this;
        }

        /**
         * 添加选项list
         *
         * @param list
         * @return
         */
        public SingleBuilder addSelectList(List<String> list) {
            if (list != null && list.size() != 0) {
                selectList.addAll(list);
            }
            return this;
        }

        /**
         * 添加选中的文本
         *
         * @param str
         * @return
         */
        public SingleBuilder addSelectText(String str) {
            if (str!=null&&!TextUtils.isEmpty(str)) {
                selectList.add(str);
            }
            return this;
        }

        /**
         * 显示
         *
         * @param context FragmentActivity才能显示
         */
        public void show(Context context) {
            if (selectList.size() == 0) {
                return;
            }
            FragmentActivity activity;
            if (context instanceof FragmentActivity) {
                activity = (FragmentActivity) context;
            } else {
                new IllegalStateException("不支持的activity").printStackTrace();
                return;
            }
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            DialogFragment currentDialog = (DialogFragment) fragmentManager.findFragmentByTag("SingleDialog");
            if (currentDialog == null) {
                SingleDialog dialog = new SingleDialog();


                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putStringArrayList("selectList", (ArrayList<String>) selectList);
                if (!TextUtils.isEmpty(selectedName)) {
                    bundle.putString("selectedName", selectedName);
                }
                if (selectedPosition < selectList.size() - 1) {
                    bundle.putInt("selectedPosition", selectedPosition);
                } else {
                    //TODO 删除提示
                    new IllegalStateException("非法的selectedPosition").printStackTrace();
                }

                dialog.setArguments(bundle);
                if (onSelectedListener != null) {
                    dialog.setOnSelectedListener(onSelectedListener);
                }
                dialog.show(fragmentManager, "SingleDialog");
                fragmentManager.executePendingTransactions();
                dialog.setQueryVisible();

            }
        }
    }

    public final static class DateBuilder {

        private int year;
        private int month;
        private int day;

        private OnDateSelectedListener onDateSelectedListener;

        public DateBuilder() {
            Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH) + 1;
            day = c.get(Calendar.DAY_OF_MONTH);

        }



        public DateBuilder setDate(Date date) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH) + 1;
            day = c.get(Calendar.DAY_OF_MONTH);
            return this;
        }


        public DateBuilder setDate(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
            return this;
        }

        public DateBuilder setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
            this.onDateSelectedListener = onDateSelectedListener;
            return this;
        }

        /**
         * 显示
         *
         * @param context FragmentActivity才能显示
         */
        public void show(Context context) {
            FragmentActivity activity;
            if (context instanceof FragmentActivity) {
                activity = (FragmentActivity) context;
            } else {
                new IllegalStateException("不支持的activity").printStackTrace();
                return;
            }
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            DialogFragment currentDialog = (DialogFragment) fragmentManager.findFragmentByTag("DatePicker");
            if (currentDialog == null) {
                DatePickerDialog dialog = new DatePickerDialog();
                Bundle bundle = new Bundle();

                bundle.putInt("year", year);
                bundle.putInt("month", month);
                bundle.putInt("day", day);
                dialog.setArguments(bundle);
                if (onDateSelectedListener != null) {
                    dialog.setOnDateSelectedListener(onDateSelectedListener);
                }
                dialog.show(fragmentManager, "DatePicker");
                fragmentManager.executePendingTransactions();
            }
        }

    }

    public interface DialogConsumer<T> {
        void accept(T builder);
    }


}
