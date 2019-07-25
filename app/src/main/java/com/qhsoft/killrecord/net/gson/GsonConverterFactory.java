package com.qhsoft.killrecord.net.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import com.qhsoft.killrecord.net.BaseBean;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Description:
 * Author: hong
 * Date: 2017-04-12 18:11
 */
public final class GsonConverterFactory extends Converter.Factory {

    private static GsonConverterFactory mFactory;

    public static GsonConverterFactory create() {
        if (mFactory != null) {
            return mFactory;
        }

        return create(new GsonBuilder()
                .addSerializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        Expose expose = f.getAnnotation(Expose.class);
                        if (expose != null && !expose.deserialize()) {
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .addDeserializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        Expose expose = f.getAnnotation(Expose.class);
                        if (expose != null && !expose.serialize()) {
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .registerTypeHierarchyAdapter(List.class, new JsonDeserializer<List<?>>() {

                    @SuppressWarnings("unchecked")
                    @Override
                    public List<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        if (json.isJsonArray()) {
                            JsonArray array = json.getAsJsonArray();
                            Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
                            List list = new ArrayList();
                            for (JsonElement element : array) {
                                list.add(context.deserialize(element, itemType));
                            }
                            return list;
                        } else {
                            return Collections.EMPTY_LIST;
                        }

                    }
                }).create());
    }

    public static GsonConverterFactory create(Gson gson) {
        if (mFactory != null) {
            return mFactory;
        }
        mFactory = new GsonConverterFactory(gson);
        return mFactory;
    }

    private final Gson gson;

    private GsonConverterFactory(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }

        this.gson = gson;
    }

    public Gson getGson() {
        return gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> basicAdapter = gson.getAdapter(TypeToken.get(type));
        if (type instanceof Class) {
            if (type == BaseBean.class) {
                return new GsonResponseBodyConverter<>(basicAdapter, true, false);
            }
            if (BaseBean.class.isAssignableFrom((Class<?>) type)) {
                return new GsonResponseBodyConverter<>(basicAdapter, false, true);
            }
        }
        return new GsonResponseBodyConverter<>(basicAdapter, false, false);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new GsonRequestBodyConverter<>();
    }

}
