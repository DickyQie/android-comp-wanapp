package com.zhangqie.network.livedata;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.zhangqie.network.bean.ApiResponse;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * @date: 2020\7\24 0024
 * @description:
 */
public class LiveDataCallAdapterFactory extends CallAdapter.Factory {

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != LiveData.class) {
            return null;
        }
        //获取第一个泛型类型
        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Class<?> rawType = getRawType(observableType);
        Log.e("TAG", "rawType = " +rawType.toString());
        boolean isApiResponse = true;
        if (rawType != ApiResponse.class) {
            //不是返回ApiResponse类型的返回值
            isApiResponse = false;
        }

        if (!(observableType instanceof ParameterizedType)) {
            Log.e("TAG", "rawType = resource must be parameterized" + rawType.toString());
//            throw new IllegalArgumentException("resource must be parameterized");
        }

        return new LiveDataCallAdapter<String>(observableType, isApiResponse);
    }
}
