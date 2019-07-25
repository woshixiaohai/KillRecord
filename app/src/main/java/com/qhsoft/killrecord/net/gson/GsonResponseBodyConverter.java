package com.qhsoft.killrecord.net.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.qhsoft.killrecord.util.Logger;


import java.io.IOException;
import java.io.Reader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Description:
 * Author: hong
 * Date: 2017-04-12 18:11
 */
final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> mBasicAdapter;
    private final boolean mIsNesting;
    private final boolean mIsBasic;

    GsonResponseBodyConverter(TypeAdapter<T> basicAdapter, boolean isBasic, boolean isNesting) {
        mBasicAdapter = basicAdapter;
        mIsBasic = isBasic;
        mIsNesting = isNesting;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T convert(ResponseBody value) throws IOException {

        try {


            JsonParser parser = new JsonParser();
            Reader reader = value.charStream();
            JsonElement jsonElement = parser.parse(reader);
            Logger.d(GsonResponseBodyConverter.class.getSimpleName(),  jsonElement.toString());
            if (mIsNesting || mIsBasic) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                String resultJson = jsonObject.toString();
                T resultBean;
                if (resultJson == null || resultJson.length() == 0
                        || resultJson.equalsIgnoreCase("null")) {
                    resultBean = mBasicAdapter.fromJson("{}");
                } else {
                    JsonElement dataElement = jsonObject.get("obj");
                    if (dataElement == null) {
                        resultBean = mBasicAdapter.fromJson(jsonObject.toString());
                    } else {
                        if (dataElement.isJsonNull()) {
                            resultBean = mBasicAdapter.fromJson(jsonObject.toString());
                        } else if (dataElement.isJsonObject()) {
                            resultBean = mBasicAdapter.fromJson(dataElement.getAsJsonObject().toString());
                        } else {
                            resultBean = mBasicAdapter.fromJson(jsonObject.toString());
                        }
                    }

                }

                return resultBean;
            }
            return mBasicAdapter.fromJson(jsonElement.toString());
        } catch (Exception e) {

            char[] chars = new char[1024];
            int len=-1;
            while ((len=value.charStream().read(chars))!=-1){
                String s=new String(chars, 0, len);
                Logger.d(GsonResponseBodyConverter.class.getSimpleName(), s);
            }
            return null;

        } finally {
            value.close();
        }
    }

}