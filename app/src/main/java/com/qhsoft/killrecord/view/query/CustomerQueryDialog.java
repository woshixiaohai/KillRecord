package com.qhsoft.killrecord.view.query;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseQueryDialog;

import butterknife.Bind;

public class CustomerQueryDialog extends BaseQueryDialog {
    @Bind(R.id.tv_title_1)
    TextView tv_title_1;

    @Bind(R.id.tv_title_2)
    TextView tv_title_2;

    @Bind(R.id.tv_value_1)
    TextView tv_value_1;

    @Bind(R.id.tv_value_2)
    TextView tv_value_2;
    @Bind(R.id.ll_line_1)
    LinearLayout ll_line_1;
    @Bind(R.id.ll_line_2)
    LinearLayout ll_line_2;

    public CustomerQueryDialog(Activity context) {
        super(context);

    }
    public void setTitleText(String str1,String str2){
        tv_title_1.setText(str1);
        tv_title_2.setText(str2);
    }
    public void setTwoTextInvisible(){
        ll_line_2.setVisibility(View.GONE);
    }

    @Override
    protected void onQuery() {
        if(onQueryListener!=null){
            onQueryListener.onQuery(tv_value_1.getText().toString(),tv_value_2.getText().toString());
        }

    }

    @Override
    protected void onReset() {
        tv_value_1.setText("");
        tv_value_2.setText("");

    }

    @Override
    protected void onAddView(Activity context, View itemView) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_customer_query;
    }
    private OnQueryListener onQueryListener;
    public void setOnQueryListener( OnQueryListener onQueryListener){
        this.onQueryListener=onQueryListener;
    }
    public interface OnQueryListener{
         void onQuery(String value_1,String value_2);
    }
}
