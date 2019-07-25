package com.qhsoft.killrecord.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtil {
    public static String getMIMEType(File f) {
        String type = "";
        String fName = f.getName();

        String end = fName
                .substring(fName.lastIndexOf(".") + 1, fName.length())
                .toLowerCase();
        if (end.equals("pdf")) {
            type = "application/pdf";//
        } else if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
                || end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            type = "audio/*";
        } else if (end.equals("3gp") || end.equals("mp4")) {
            type = "video/*";
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
                || end.equals("jpeg") || end.equals("bmp")) {
            type = "image/*";
        } else if (end.equals("apk")) {
            type = "application/vnd.android.package-archive";
        } else if (end.equals("pptx") || end.equals("ppt")) {
            type = "application/vnd.ms-powerpoint";
        } else if (end.equals("docx") || end.equals("doc")) {
            type = "application/vnd.ms-word";
        } else if (end.equals("xlsx") || end.equals("xls")) {
            type = "application/vnd.ms-excel";
        } else {
            type = "*/*";
        }
        return type;
    }

    public static File getDownloadDir(Context context) {
        File dir = getRootDir(context);
        File faceDir = new File(dir, "");
        if (!faceDir.exists())
            faceDir.mkdirs();
        return faceDir;
    }

    public static File getCaptureDir(Context context) {
        File dir = getRootDir(context);
        File faceDir = new File(dir, "capture");
        if (!faceDir.exists())
            faceDir.mkdirs();
        return faceDir;
    }

    public static File getSignureDir(Context context) {
        File dir = getRootDir(context);
        File faceDir = new File(dir, "signure");
        if (!faceDir.exists())
            faceDir.mkdirs();
        return faceDir;
    }

    public static File getRootDir(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File sd = Environment.getExternalStorageDirectory();
            File dir = new File(sd, "qhsoft");
            if (!dir.exists()) {
                dir.mkdirs();

            }
            return dir;
        } else {
            File sd = context.getFilesDir();
            File dir = new File(sd, "qhsoft");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return dir;
        }

    }

    /**
     * 创建文件�?
     *
     * @param dirName
     * @return
     */
    public static File createDir(String dirName, String path) {
        File file = new File(path + File.separator + dirName);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    /**
     * 创建文件
     *
     * @param fileName
     * @param path
     * @return
     */
    public static File createFile(String fileName, String path) {
        File file = new File(path + File.separator + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 写入内容到文件
     *
     * @param file
     * @param content
     * @return
     */
    public static boolean writeInfoToFile(File file, String content) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            bw.write(content);
            bw.newLine();
            bw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static void showAttachment(Activity activity, String filepath) {
        int len = filepath.lastIndexOf(".");
        if (len > 0) {
            String fileType = filepath.substring(len + 1, filepath.length());

            if (fileType.equalsIgnoreCase("jpg") || fileType.equalsIgnoreCase("JPG")

                    || fileType.equalsIgnoreCase("jpeg")
                    || fileType.equalsIgnoreCase("JPEG")
                    || fileType.equalsIgnoreCase("png")
                    || fileType.equalsIgnoreCase("PNG")
                    || fileType.equalsIgnoreCase("gif")
                    || fileType.equalsIgnoreCase("GIF")) {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(filepath)), "image/*");
                activity.startActivity(intent);
            } else if (fileType.equalsIgnoreCase("doc") || fileType.equalsIgnoreCase("docx")
                    || fileType.equalsIgnoreCase("xls")
                    || fileType.equalsIgnoreCase("ppt")
                    || fileType.equalsIgnoreCase("DOC")
                    || fileType.equalsIgnoreCase("DOCX")
                    || fileType.equalsIgnoreCase("XLS")
                    || fileType.equalsIgnoreCase("PPT")) {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(filepath)), "application/msword");
                activity.startActivity(intent);
            }
        }


    }

    public static void showAttachment(Activity activity, String filepath, String fileType) {


        if (fileType.equalsIgnoreCase("jpg") || fileType.equalsIgnoreCase("JPG")

                || fileType.equalsIgnoreCase("jpeg")
                || fileType.equalsIgnoreCase("JPEG")
                || fileType.equalsIgnoreCase("png")
                || fileType.equalsIgnoreCase("PNG")
                || fileType.equalsIgnoreCase("gif")
                || fileType.equalsIgnoreCase("GIF") || fileType.equalsIgnoreCase("image/jpeg")) {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(filepath)), "image/*");
            activity.startActivity(intent);
        } else if (fileType.equalsIgnoreCase("doc") || fileType.equalsIgnoreCase("docx")
                || fileType.equalsIgnoreCase("xls")
                || fileType.equalsIgnoreCase("ppt")
                || fileType.equalsIgnoreCase("DOC")
                || fileType.equalsIgnoreCase("DOCX")
                || fileType.equalsIgnoreCase("XLS")
                || fileType.equalsIgnoreCase("PPT")) {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(filepath)), "application/msword");
            activity.startActivity(intent);
        } else {
            install(activity);


        }


    }

    public static void install(final Activity activity) {

        File apkFile = new File( Environment.getExternalStorageDirectory()+"/qhsoft/", "KillRecord.apk");
        if(isCanIntallApk(activity,apkFile.getPath())){
            Uri apkUri;
            if (Build.VERSION.SDK_INT >= 24) {
                apkUri = FileProvider.getUriForFile(activity, "com.qhsoft.killrecord.myprovider", apkFile);
            } else {
                apkUri = Uri.fromFile(apkFile);
            }
            Intent installIntent = new Intent(Intent.ACTION_VIEW);
            installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            installIntent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            activity.startActivity(installIntent);
        }else {
            Logger.d(FileUtil.class.getSimpleName(),"此安装包版本低于当前App版本");
        }



    }
    public static boolean isCanIntallApk(Context context, String apkPath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES);
        if (info != null) {
            try {
                int app_versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
                if (info.versionCode > app_versionCode) {
                    return true;
                }

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }


        }
        return false;


    }







}
