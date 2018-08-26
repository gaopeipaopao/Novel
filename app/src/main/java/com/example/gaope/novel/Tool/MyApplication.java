package com.example.gaope.novel.Tool;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static MyApplication mInstance;
    /**
     * 获取context
     */
    public static Context getInstance() {
        if (mInstance == null) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }
}
