package com.qhsoft.killrecord.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qhsoft.killrecord.R;

import java.util.ArrayList;
import java.util.List;

public class SimpleTable extends LinearLayout {
    private int cellHeight;
    private int cellDeep;
    private String cellTitle;
    private int cellTitleSize;
    private int cellWidthBig;
    private int cellWidthSmall;
    private int cellLineColor;
    private int cellTextColor;
    private Context context;
    private List<TextView> list_tv=new ArrayList<>();




    public SimpleTable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.SimpleTable);
         cellHeight=typedArray.getInt(R.styleable.SimpleTable_cellHeight,30);
         cellDeep=typedArray.getInt(R.styleable.SimpleTable_cellDeep,2);
         cellTitle=typedArray.getString(R.styleable.SimpleTable_cellTitle);
         cellTitleSize=typedArray.getInt(R.styleable.SimpleTable_cellTitleSize,28);
         cellWidthBig=typedArray.getInt(R.styleable.SimpleTable_cellWidthBig,200);
         cellWidthSmall=typedArray.getInt(R.styleable.SimpleTable_cellWidthSmall,150);
         cellLineColor=typedArray.getColor(R.styleable.SimpleTable_cellLineColor,getResources().getColor(R.color.ling_bg));
         cellTextColor=typedArray.getColor(R.styleable.SimpleTable_cellTextColor,getResources().getColor(R.color.ling_bg));
         setOrientation(LinearLayout.VERTICAL);
         initTitle(context, attrs);








    }
    public void setCellText(int i,int j,String str){
        if(str!=null){
            String[] title=(cellTitle+"#").split("#");
            int size=title.length;
            int index=i*size+j;
            list_tv.get(index).setText(str);
        }




    }
    public void setCellText(List<TextView> list,int j,String str){
        if(str!=null&&!str.equals("null")){
            list.get(j).setText(str);
        }

    }

    public  void addItems(int size){
        for(int x=0;x<size;x++){
            addItem();
        }



    }

    public  List<TextView> addItem() {
         List<TextView> list_item=new ArrayList<>();
        LinearLayout ll_h= (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_ll_horizontal,null);


        String[] str=(cellTitle+"#").split("#");
        int width=(str.length-2)*cellWidthSmall+cellWidthBig*2+cellDeep*(str.length+1);

        addView(ll_h);

        View line_v=new View(context);
        line_v.setBackgroundColor(cellLineColor);
        LayoutParams params=new LayoutParams(cellDeep,cellHeight);

        ll_h.addView(line_v,params);

        if(str!=null){
            for(int i=0;i<str.length;i++){
                int cellWidth=0;
                if(i==str.length-1||i==0){
                    cellWidth=cellWidthBig;
                }else {
                    cellWidth=cellWidthSmall;
                }
                TextView tv= (TextView) LayoutInflater.from(context).inflate(R.layout.layout_textview,null);

                tv.setTextSize(cellTitleSize);
                tv.setTextColor(cellTextColor);

                ll_h.addView(tv,cellWidth,cellHeight);
                list_tv.add(tv);
                list_item.add(tv);

                line_v=new View(context);
                line_v.setBackgroundColor(cellLineColor);
                params=new LayoutParams(cellDeep,cellHeight);

                ll_h.addView(line_v,params);






            }
            View line_h=new View(context);
            line_h.setBackgroundColor(cellLineColor);
            params=new LayoutParams(width,cellDeep);

            addView(line_h,params);


        }
        return list_item;
    }

    private void initTitle(Context context, @Nullable AttributeSet attrs) {



        LinearLayout ll_h= (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_ll_horizontal,null);


        View line_h=new View(context);
        line_h.setBackgroundColor(cellLineColor);
        String[] str=(cellTitle+"#").split("#");
        int width=(str.length-2)*cellWidthSmall+cellWidthBig*2+cellDeep*(str.length+1);
        LayoutParams params=new LayoutParams(width,cellDeep);

        addView(line_h,params);
        addView(ll_h);

        View line_v=new View(context);
        line_v.setBackgroundColor(cellLineColor);
        params=new LayoutParams(cellDeep,cellHeight);

        ll_h.addView(line_v,params);

        if(str!=null){
            for(int i=0;i<str.length;i++){
                int cellWidth=0;
                if(i==str.length-1||i==0){
                    cellWidth=cellWidthBig;
                }else {
                    cellWidth=cellWidthSmall;
                }
                TextView tv= (TextView) LayoutInflater.from(context).inflate(R.layout.layout_textview,null);
                tv.setText(str[i]);
                tv.setTextSize(cellTitleSize);
                tv.setTextColor(cellTextColor);

                ll_h.addView(tv,cellWidth,cellHeight);
                line_v=new View(context);
                line_v.setBackgroundColor(cellLineColor);
                params=new LayoutParams(cellDeep,cellHeight);

                ll_h.addView(line_v,params);






            }
            line_h=new View(context);
            line_h.setBackgroundColor(cellLineColor);
            params=new LayoutParams(width,cellDeep);

            addView(line_h,params);


        }
    }


}
