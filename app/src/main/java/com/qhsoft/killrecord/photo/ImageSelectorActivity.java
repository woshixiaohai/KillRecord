package com.qhsoft.killrecord.photo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.photo.util.ImageConfig;
import com.qhsoft.killrecord.photo.util.AppUtil;
import com.qhsoft.killrecord.photo.util.GlideLoader;
import com.qhsoft.killrecord.photo.util.ImageSelectorUtil;

import java.io.File;
import java.util.ArrayList;


public class ImageSelectorActivity extends FragmentActivity implements ImageSelectorFragment.Callback {

    public static final String EXTRA_RESULT = "select_result";

    private ArrayList<String> pathList = new ArrayList<String>();

    private ImageConfig imageConfig;

    private TextView title_text;
    private TextView submitButton;
    private RelativeLayout ImageSelectorUtil_title_bar_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageselector_activity);
        imageConfig = ImageSelectorUtil.getImageConfig();
        boolean flag = true;
        if (imageConfig == null) {
        	flag = SANXINGCHULI();
        	imageConfig = new ImageConfig.Builder(
            // GlideLoader 可用自己用的缓存库
            new GlideLoader())
            // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
            .steepToolBarColor(getResources().getColor(R.color.mineblue))
            // 标题的背景颜色 （默认黑色）
            .titleBgColor(getResources().getColor(R.color.mineblue))
            // 提交按钮字体的颜色  （默认白色）
            .titleSubmitTextColor(getResources().getColor(R.color.white))
            // 标题颜色 （默认白色）
            .titleTextColor(getResources().getColor(R.color.white))
            // 开启多选   （默认为多选）  (单选 为 singleSelect)
//                            .singleSelect()
//                                .crop()
            // 多选时的最大数量   （默认 9 张）
            .mutiSelectMaxSize(9)
            // 已选择的图片路径
            .pathList(pathList)
            // 拍照后存放的图片路径（默认 /temp/picture）
            .filePath("/ImageSelectorUtil/Pictures")
            // 开启拍照功能 （默认开启）
            .showCamera()
            .requestCode(DemoActivity.REQUEST_CODE)
            .build();
        	ImageSelectorUtil.setmImageConfig(imageConfig);
        	System.out.println("asdsadasdad");
		}
        System.out.println(flag);
        System.out.println(pathList.size());
        if (flag) {
        	AppUtil.hideTitleBar(this, R.id.imageselector_activity_layout, imageConfig.getSteepToolBarColor());
            getSupportFragmentManager().beginTransaction().add(R.id.image_grid, Fragment.instantiate(this, ImageSelectorFragment.class.getName(), null)).commit();
            submitButton = (TextView) super.findViewById(R.id.title_right);
            title_text = (TextView) super.findViewById(R.id.title_text);
            ImageSelectorUtil_title_bar_layout = (RelativeLayout) super.findViewById(R.id.imageselector_title_bar_layout);
            init();
		} else {
			if (pathList != null && pathList.size() > 0) {
                Intent data = new Intent();
                data.putStringArrayListExtra(EXTRA_RESULT, pathList);
                setResult(RESULT_OK, data);
                exit();
            }
		}
    }
    
	public static final String IMAGE_CHOOSE = "image_choose";
	public static final String IMAGES = "images";
	public static final String CAMERA = "camera";
	public static final String splitStr = ",。;";
    private boolean SANXINGCHULI() {
    	pathList.clear();
    	SharedPreferences sp = getSharedPreferences(IMAGE_CHOOSE, 0);
    	String tempImages = sp.getString(IMAGES, "");
    	System.out.println(tempImages);
		String[] images = tempImages.split(splitStr);
		for (int i = 0; i < images.length; i++) {
			if (!TextUtils.isEmpty(images[i])) {
				pathList.add(images[i]);
			}
		}
		String cameraImg = sp.getString(CAMERA, "");
		File file = new File(cameraImg);
		if (TextUtils.isEmpty(cameraImg) || !file.exists()) {
			return true;
		} else {
			pathList.add(cameraImg);
			return false;
		}
    }

    private void init() {
//        submitButton.setVisibility(View.GONE);
        submitButton.setTextColor(imageConfig.getTitleSubmitTextColor());
        title_text.setTextColor(imageConfig.getTitleTextColor());
        ImageSelectorUtil_title_bar_layout.setBackgroundColor(imageConfig.getTitleBgColor());

        pathList = imageConfig.getPathList();

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                exit();
            }
        });


        if (pathList == null || pathList.size() <= 0) {
            submitButton.setText(R.string.finish);
            submitButton.setEnabled(false);
        } else {
            submitButton.setText((getResources().getText(R.string.finish)) + "(" + pathList.size() + "/" + imageConfig.getMaxSize() + ")");
            submitButton.setEnabled(true);
        }
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pathList != null && pathList.size() > 0) {
                    Intent data = new Intent();
                    data.putStringArrayListExtra(EXTRA_RESULT, pathList);
                    setResult(RESULT_OK, data);
                    exit();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ImageSelectorUtil.IMAGE_CROP_CODE && resultCode == RESULT_OK) {
            Intent intent = new Intent();
            pathList.add(cropImagePath);
            intent.putStringArrayListExtra(EXTRA_RESULT, pathList);
            setResult(RESULT_OK, intent);
            exit();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void exit() {
        finish();
    }

    private String cropImagePath;

    private void crop(String imagePath, int aspectX, int aspectY, int outputX, int outputY) {
        File file;
        if (AppUtil.existSDCard()) {
            file = new File(Environment.getExternalStorageDirectory() + imageConfig.getFilePath(), AppUtil.getImageName());
        } else {
            file = new File(getCacheDir(), AppUtil.getImageName());
        }

        cropImagePath = file.getAbsolutePath();
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(Uri.fromFile(new File(imagePath)), "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        startActivityForResult(intent, ImageSelectorUtil.IMAGE_CROP_CODE);
    }

    @Override
    public void onSingleImageSelected(String path) {
        if (imageConfig.isCrop()) {
            crop(path, imageConfig.getAspectX(), imageConfig.getAspectY(), imageConfig.getOutputX(), imageConfig.getOutputY());
        } else {
            Intent data = new Intent();
            pathList.add(path);
            data.putStringArrayListExtra(EXTRA_RESULT, pathList);
            setResult(RESULT_OK, data);
            exit();
        }
    }

    @Override
    public void onImageSelected(String path) {
        if (!pathList.contains(path)) {
            pathList.add(path);
        }
        if (pathList.size() > 0) {
            submitButton.setText((getResources().getText(R.string.finish)) + "(" + pathList.size() + "/" + imageConfig.getMaxSize() + ")");
            if (!submitButton.isEnabled()) {
                submitButton.setEnabled(true);
            }
        }
    }

    @Override
    public void onImageUnselected(String path) {
        if (pathList.contains(path)) {
            pathList.remove(path);
            submitButton.setText((getResources().getText(R.string.finish)) + "(" + pathList.size() + "/" + imageConfig.getMaxSize() + ")");
        } else {
            submitButton.setText((getResources().getText(R.string.finish)) + "(" + pathList.size() + "/" + imageConfig.getMaxSize() + ")");
        }
        if (pathList.size() == 0) {
            submitButton.setText(R.string.finish);
            submitButton.setEnabled(false);
        }
    }

    @Override
    public void onCameraShot(File imageFile) {
        if (imageFile != null) {
            Intent data = new Intent();
            pathList.add(imageFile.getAbsolutePath());
            data.putStringArrayListExtra(EXTRA_RESULT, pathList);
            setResult(RESULT_OK, data);
            exit();
        }
        if (imageFile != null) {
            if (imageConfig.isCrop()) {
                crop(imageFile.getAbsolutePath(), imageConfig.getAspectX(), imageConfig.getAspectY(), imageConfig.getOutputX(), imageConfig.getOutputY());
            } else {
                Intent data = new Intent();
                pathList.add(imageFile.getAbsolutePath());
                data.putStringArrayListExtra(EXTRA_RESULT, pathList);
                setResult(RESULT_OK, data);
                exit();
            }
        }

    }
}