package com.qhsoft.killrecord.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.net.HttpConfig;


/**
 * Description:
 * Author:lin
 * Date:2017-09-12
 */

public class GlideUtil {
    public static void loadImage(Context context, String relativeUrl, ImageView imageView) {
        if (TextUtils.isEmpty(relativeUrl)) {
            return;
        }
        Logger.d(GlideUtil.class.getSimpleName(), HttpConfig.getPictureUrl(relativeUrl));
        Glide.with(context).load(HttpConfig.getPictureUrl(relativeUrl))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(new DefaultDrawableImageViewTarget(imageView));


    }

    public static void loadImagePath(Context context, String path, ImageView imageView) {
        if (context == null) {
            throw new IllegalStateException("context 不能为空");
        }
        Glide.with(context)
                .load(path)
                .placeholder(R.mipmap.ic_launcher).fitCenter()
                .error(R.mipmap.ic_launcher).into(new DefaultDrawableImageViewTarget(imageView));


    }


    private static class DefaultDrawableImageViewTarget extends GlideDrawableImageViewTarget {

        public DefaultDrawableImageViewTarget(ImageView view) {
            super(view);
        }

        @Override
        public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
            if (view.getScaleType() != ImageView.ScaleType.FIT_XY) {
                view.setScaleType(ImageView.ScaleType.FIT_XY);
            }
            super.onResourceReady(drawable, anim);
        }

        @Override
        protected void setResource(GlideDrawable drawable) {
            super.setResource(drawable);
        }

        @Override
        public void onLoadFailed(Exception e, Drawable errorDrawable) {
            if (errorDrawable != null && view != null && view.getScaleType() != ImageView.ScaleType.FIT_XY) {
                view.setScaleType(ImageView.ScaleType.CENTER);
            }
            super.onLoadFailed(e, errorDrawable);
        }

        @Override
        public void onLoadStarted(Drawable placeholder) {
            if (placeholder != null && view != null && view.getScaleType() != ImageView.ScaleType.FIT_XY) {
                view.setScaleType(ImageView.ScaleType.CENTER);
            }
            super.onLoadStarted(placeholder);
        }

        @Override
        public void onLoadCleared(Drawable placeholder) {
            if (placeholder != null && view != null && view.getScaleType() != ImageView.ScaleType.FIT_XY) {
                view.setScaleType(ImageView.ScaleType.CENTER);
            }
            super.onLoadCleared(placeholder);
        }
    }


}
