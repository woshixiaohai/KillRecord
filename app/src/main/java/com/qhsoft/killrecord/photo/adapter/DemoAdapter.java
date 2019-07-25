package com.qhsoft.killrecord.photo.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.photo.DemoActivity;
import com.qhsoft.killrecord.photo.GalleryActivity;

import com.qhsoft.killrecord.photo.ImageSelectorActivity;
import com.qhsoft.killrecord.photo.util.ImageConfig;
import com.qhsoft.killrecord.photo.util.GlideLoader;
import com.qhsoft.killrecord.photo.util.ImageSelectorUtil;

import java.util.ArrayList;


public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {

    private Activity context;
    private LayoutInflater mLayoutInflater;
    private ArrayList<String> result;
    private final static String TAG = "Adapter";

    public DemoAdapter(Activity context, ArrayList<String> result) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.result = result;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.image, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
    	if (position == result.size()) {
            holder.image.setImageResource(R.drawable.icon_add_pic_unfocused);
            if (position == 9) {
                holder.image.setVisibility(View.GONE);
            }
        } else {
        	Glide.with(context)
            .load(result.get(position))
            .centerCrop()
            .into(holder.image);
        }
    	holder.image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (position == result.size()) {
					ImageConfig imageConfig
                    = new ImageConfig.Builder(
                    // GlideLoader 可用自己用的缓存库
                    new GlideLoader())
                    // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                    .steepToolBarColor(context.getResources().getColor(R.color.mineblue))
                    // 标题的背景颜色 （默认黑色）
                    .titleBgColor(context.getResources().getColor(R.color.mineblue))
                    // 提交按钮字体的颜色  （默认白色）
                    .titleSubmitTextColor(context.getResources().getColor(R.color.white))
                    // 标题颜色 （默认白色）
                    .titleTextColor(context.getResources().getColor(R.color.white))
                    // 开启多选   （默认为多选）  (单选 为 singleSelect)
//                    .singleSelect()
//                        .crop()
                    // 多选时的最大数量   （默认 9 张）
                    .mutiSelectMaxSize(9)
                    // 已选择的图片路径
                    .pathList(result)
                    // 拍照后存放的图片路径（默认 /temp/picture）
                    .filePath("/ImageSelector/Pictures")
                    // 开启拍照功能 （默认开启）
                    .showCamera()
                    .requestCode(DemoActivity.REQUEST_CODE)
                    .build();
					ImageSelectorUtil.open(context, imageConfig);   // 开启图片选择器
				} else {
                    Intent data = new Intent(context, GalleryActivity.class);
                    data.putStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT, result);
                    data.putExtra("position", position);
                    context.startActivityForResult(data, DemoActivity.REQUEST_CODE);
				}
			}
		});
    }

    @Override
    public int getItemCount() {
    	if (result.size() == 9) {
            return 9;
        }
        return (result.size() + 1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
