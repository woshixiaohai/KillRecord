package com.qhsoft.killrecord.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.util.NullableUtil;
import com.qhsoft.killrecord.view.myrecyclerview.CommonRcViewHolder;
import com.qhsoft.killrecord.view.myrecyclerview.LoadMoreAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public abstract class MyListAdapter<T> extends LoadMoreAdapter {
    protected List<T> list=new ArrayList<>();


    public MyListAdapter(int load_state) {
        super(load_state);

    }

    public MyListAdapter(int load_state, List<T> list) {
        super(load_state);
        this.list = list;

    }
    public void addList(List<T> chid){
        if(chid!=null&&chid.size()>0){
            list.addAll(chid);
            notifyDataSetChanged();
        }
    }
    public void clearList(){
        if(list!=null){
            list.clear();
            notifyDataSetChanged();
        }
    }



    @Override
    public int getCount() {

        return NullableUtil.listSize(list);

    }

    @Override
    public CommonRcViewHolder getViewHolder(ViewGroup parent, int viewType) {
        int resId=getResId();
        if(resId==0){
            resId= R.layout.layout_list_item;
        }
        View view=LayoutInflater.from(parent.getContext()).inflate(resId,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void loadData(CommonRcViewHolder holder, int position) {
        if(holder instanceof MyHolder &&position<list.size()){
            MyHolder myHolder= (MyHolder) holder;
            final T t=list.get(position);
            onBindData(t,myHolder.tv_1);

            if(myHolder.itemView.findViewById(R.id.cb_check)!=null){
                CheckBox checkBox=myHolder.itemView.findViewById(R.id.cb_check);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        onCheck(t,isChecked);
                    }
                });
            }

            myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(t);
                }
            });
        }

    }
    public class MyHolder<T> extends CommonRcViewHolder{
        @Bind(R.id.tv_1)
        TextView tv_1;





        public MyHolder(View itemView) {
            super(itemView);
        }


    }
    protected abstract void onBindData(T t,TextView tv_1);
    protected abstract void onItemClick(T t);
    protected  int getResId(){
        return 0;

    };
    protected void onCheck(T t, boolean isChecked){

    }


}