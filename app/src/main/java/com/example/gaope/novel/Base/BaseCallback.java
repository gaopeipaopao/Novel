package com.example.gaope.novel.Base;

/**
 * 请求数据时反馈的各种状态
 */

public interface BaseCallback<T> {


    //请求数据成功时
    void onSuccess(T data);

    //请求失败
    void onFailure(String msg);

    //请求错误
    void onError();

}
