package com.qhsoft.killrecord.photo.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;

import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.photo.ImageSelectorActivity;

import java.util.List;





public class PhotoSelectorUtil {

    public static final int REQUEST_CODE = 1000;
    /**
     * 项目路径
     */
    public final static String PROJECTPATH = Environment.getExternalStorageDirectory().getPath() + java.io.File.separator + "KillRecord" + java.io.File.separator;
    /**
     * 项目图片路径
     */
    public final static String PROJECTIMAGEPATH = PROJECTPATH + "Image" + java.io.File.separator;



    // 初始化照片选择器 不带裁剪器
    public static void initImageConfig(Activity activity) {
        try {
            ImageConfig imageConfig = new ImageConfig.Builder(
                    // GlideLoader 可用自己用的缓存库
                    new GlideLoader())
                    .steepToolBarColor(activity.getResources().getColor(R.color.theme_bg))
                    .titleBgColor(activity.getResources().getColor(R.color.theme_bg))
                    .titleSubmitTextColor(activity.getResources().getColor(R.color.white))
                    .titleTextColor(activity.getResources().getColor(R.color.white))
                    // 开启多选 （默认为多选） (单选 为 singleSelect)
                    .singleSelect()
                    // .crop()
                    // 多选时的最大数量 （默认 9 张）
                    // .mutiSelectMaxSize(9)
                    // 已选择的图片路径
                    // .pathList(imgUrl)
                    // 拍照后存放的图片路径（默认 /temp/picture）
                    .filePath(PROJECTIMAGEPATH)
                    // 开启拍照功能 （默认开启）
                    .showCamera().requestCode(REQUEST_CODE).build();

            ImageSelectorUtil.open(activity, imageConfig);
        } catch (Exception e) {
            ToastUtil.showToast(activity,"请打开App存储权限");
        }
    }

    // 照片多选
    public static void initMoreImageConfig(Activity activity) {
        try {
            ImageConfig imageConfig = new ImageConfig.Builder(
                    // GlideLoader 可用自己用的缓存库
                    new GlideLoader())
                    // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                    .steepToolBarColor(activity.getResources().getColor(R.color.theme_bg))
                    .titleBgColor(activity.getResources().getColor(R.color.theme_bg))
                    .titleSubmitTextColor(activity.getResources().getColor(R.color.white))
                    .titleTextColor(activity.getResources().getColor(R.color.white))
                    // .singleSelect()
                    // 裁剪
                    // .crop()
                    // 多选时的最大数量 （默认 9 张）
                    .mutiSelectMaxSize(5)
                    // 已选择的图片路径
                    // .pathList(imgUrl)
                    // 拍照后存放的图片路径（默认 /temp/picture）
                    .filePath(PROJECTIMAGEPATH)
                    // 开启拍照功能 （默认开启）
                    .showCamera().requestCode(REQUEST_CODE).build();

            ImageSelectorUtil.open(activity, imageConfig);
        } catch (Exception e) {
            ToastUtil.showToast(activity,"请打开App存储权限");

        }
    }

    // 初始化图片选择器 + 裁剪器
    public static void initImageConfigOrTailoring(Activity activity) {
        try {
            ImageConfig imageConfig = new ImageConfig.Builder(new GlideLoader())
                    .steepToolBarColor(activity.getResources().getColor(R.color.theme_bg))
                    .titleBgColor(activity.getResources().getColor(R.color.theme_bg))
                    .titleSubmitTextColor(activity.getResources().getColor(R.color.white))
                    .titleTextColor(activity.getResources().getColor(R.color.white))
                    // 开启单选 （默认为多选）
                    .singleSelect()
                    // 开启拍照功能 （默认关闭）
                    .showCamera()
                    // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                    .filePath(PROJECTIMAGEPATH).crop().requestCode(REQUEST_CODE).build();
            ImageSelectorUtil.open(activity, imageConfig);
        } catch (Exception e) {
            ToastUtil.showToast(activity,"请打开App存储权限");

        }

    }

    // 初始化图片选择器 + 裁剪器
    public static void initImageConfigToFragment(Activity activity) {
        try {
            ImageConfig imageConfig = new ImageConfig.Builder(new GlideLoader())
                    .steepToolBarColor(activity.getResources().getColor(R.color.theme_bg))
                    .titleBgColor(activity.getResources().getColor(R.color.theme_bg))
                    .titleSubmitTextColor(activity.getResources().getColor(R.color.white))
                    .titleTextColor(activity.getResources().getColor(R.color.white))
                    // 开启单选 （默认为多选）
                    .singleSelect()
                    // 开启拍照功能 （默认关闭）
                    .showCamera()
                    // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                    .filePath(PROJECTIMAGEPATH).crop().requestCode(REQUEST_CODE).build();
            ImageSelectorUtil.open(activity, imageConfig);
        } catch (Exception e) {
            ToastUtil.showToast(activity,"请打开App存储权限");

        }
    }

    // 图片选择器图片地址获取
    public static String getPhotoUrl(Intent data) {
        List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
        String url = pathList.get(0);
        return url == null ? "" : url;
    }

    // 图片选择器图片地址获取 多张
    public static List<String> getMorePhotoUrl(Intent data) {

        List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
        return pathList;
    }
}
