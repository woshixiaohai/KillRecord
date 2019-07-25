package com.qhsoft.killrecord.photo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class FileUtil {


    private final static String PATTERN = "yyyyMMddHHmmss";

    /**
     * @param
     * @description 保存图片到手机sd卡, 并返回图片对应的文件i
     * @author ldm
     * @time 2016/7/11 9:55
     */
    public static String savebitmaptosdcard(Bitmap bm, ImageConfig imageConfig) {
        //自定义图片名称
        String name = new SimpleDateFormat(PATTERN, Locale.CHINA).format(new Date())+ ".png";

        //定义图片存放的位置
        File tempfile = new File(Environment.getExternalStorageDirectory() + imageConfig.getFilePath());
        if (!tempfile.exists()) {
            tempfile.mkdir();
        }
        String filename = Environment.getExternalStorageDirectory() + imageConfig.getFilePath() + name;
        File pic = new File(filename);
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(pic);
            bm.compress(Bitmap.CompressFormat.PNG, 100, os);
            os.flush();
            os.close();
            return filename;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对图片进行压缩


        return null;
    }


    public static File createTmpFile(Context context, String filePath) {

        String timeStamp = new SimpleDateFormat(PATTERN, Locale.CHINA).format(new Date());

        String externalStorageState = Environment.getExternalStorageState();

        File dir = new File(Environment.getExternalStorageDirectory() + filePath);

        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return new File(dir, timeStamp + ".jpg");
        } else {
            File cacheDir = context.getCacheDir();
            return new File(cacheDir, timeStamp + ".jpg");
        }

    }


    public static void createFile(String filePath) {
        String externalStorageState = Environment.getExternalStorageState();

        File dir = new File(Environment.getExternalStorageDirectory() + filePath);
        File cropFile = new File(Environment.getExternalStorageDirectory() + filePath + "/crop");

        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
            if (!cropFile.exists()) {
                cropFile.mkdirs();
            }

            File file = new File(dir, ".nomedia");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}