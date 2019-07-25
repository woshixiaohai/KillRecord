package com.qhsoft.killrecord.photo.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qhsoft.killrecord.R;


public class GlideLoader implements ImageLoader {

    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(R.drawable.imageselector_photo)
                .centerCrop()
                .into(imageView);
    }


}
