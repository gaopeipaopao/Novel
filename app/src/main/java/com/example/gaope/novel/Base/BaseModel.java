package com.example.gaope.novel.Base;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.CallBack;
import com.example.basecomponent.Modules.LoginModule;

import java.util.concurrent.Callable;

import okhttp3.Callback;

/**
 * BaseModel中定义了对外的请求数据规则
 * @param
 */

public interface BaseModel<T>{

    //子类发送网络请求的接口
    void execute(Callback callback,String... args);

    void  execute(CallBack<T> observer, String... args);

}
