package com.qhsoft.killrecord.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.qhsoft.killrecord.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;


public class NetUtil {
    private static final String TAG = NetUtil.class.getSimpleName();

    /**
     * 保存数据到data/data/包名/files里
     * 内部类也必须序列化
     *
     * @param context
     * @param obj
     */
    public static void write(@NonNull Context context, Object obj) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(obj.getClass().getSimpleName(), Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void write(@NonNull Context context, Object obj, String filesName) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(filesName, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取data/data/包名/files里文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static Object read(@NonNull Context context, String fileName) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = context.openFileInput(fileName);
            ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    public static Object read(@NonNull Context context, Object obj) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = context.openFileInput(obj.getClass().getSimpleName());
            ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }
    public static String readRegionList(@NonNull Context context) {
        String str = "";
        try {
            InputStream is = context.getResources().openRawResource(R.raw.region_list);
            byte[] b = new byte[8092];
            int len = -1;
            while ((len = is.read(b)) != -1) {
                str = str + new String(b, 0, len, "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }




    /**
     * 动态构建请求参数
     * text/plain
     * multipart/form-data
     * image/*
     *
     * @param map
     * @param obj
     * @return
     */
    public static Map<String, RequestBody> getMapList(Map<String, RequestBody> map, Object obj) {

        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);

            try {

                if (fields[i].getType().getSimpleName().equals("File")) {
                    map.put(fields[i].getName(), RequestBody.create(MediaType.parse("image/*; charset=UTF-8"), (File) fields[i].get(obj)));
                } else {
                    map.put(fields[i].getName(), RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), fields[i].get(obj).toString()));
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        return map;
    }
    /**
     * 动态构建请求参数
     * text/plain
     * multipart/form-data
     * image/*
     *
     *@param obj
     * @return
     */
    ;

    public static Map<String, RequestBody> getMapList(Object obj) {
        Map<String, RequestBody> map = new HashMap<>();
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);

            try {

                if (fields[i].getType().getSimpleName().equals("File")) {
                    map.put(fields[i].getName(), RequestBody.create(MediaType.parse("image/*; charset=UTF-8"), (File) fields[i].get(obj)));
                } else {
                    map.put(fields[i].getName(), RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), fields[i].get(obj).toString()));
                }


            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        return map;
    }

    public static Map<String, RequestBody> getMapList(Map<String, String> src) {
        Map<String, RequestBody> map = new HashMap<>();
        Iterator<Map.Entry<String, String>> iter = src.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            Logger.d(NetUtil.class.getSimpleName(), "key:" + entry.getKey() + "  value:" + entry.getValue());
            if (entry.getValue().startsWith("/")) {
                map.put(entry.getKey(), RequestBody.create(MediaType.parse("image/*; charset=UTF-8"), new File(entry.getValue())));
            } else {
                map.put(entry.getKey(), RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), entry.getValue()));
            }

        }


        return map;
    }


    public static RequestBody getRequestBody(String str) {
        return RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), str);
    }

    public static RequestBody getRequestBody(File file) {
        return RequestBody.create(MediaType.parse("image/*; charset=UTF-8"), file);
    }


    ;


}
