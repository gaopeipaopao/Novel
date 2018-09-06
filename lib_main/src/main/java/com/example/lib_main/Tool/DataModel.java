package com.example.lib_main.Tool;

import android.util.Log;

import com.example.lib_main.Base.BaseModel;

/**
 * 负责请求的数据的分发，找到对应的Model,发送网络请求时就有统一的格式
 */

public class DataModel {

    private static final String TAG = "DataModel";


    public static BaseModel request(String token){
        BaseModel baseModel = null;
        try {
            baseModel = (BaseModel) Class.forName(token).newInstance();
            Log.d(TAG,"ababba");
            Log.d(TAG,"baseModel:"+baseModel);
        } catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return baseModel;
    }
}
