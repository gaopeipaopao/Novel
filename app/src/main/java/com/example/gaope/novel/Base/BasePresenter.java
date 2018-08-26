package com.example.gaope.novel.Base;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

/**
 * Presenter对View的引用方法定义在BasePresenter中
 * @param <V>
 */

public class BasePresenter<V extends BaseView> {

    private static final String TAG = "BasePresenter";

    private V view;

    //Presenter与View连接
    public void attachView(V view){
        Log.d(TAG,"attachView:"+this.view);
        this.view = view;
        Log.d(TAG,"attachView1:"+this.view.toString());
    }

    //Presenter与View断开连接
    public void  detachView(){
      //  Log.d(TAG,"attachView:"+view);
//        Log.d(TAG,"bbbbb:"+this.view.toString());
        this.view = null;
    }

    //判断View与Presenter是否连接
    public boolean isViewAttached(){
        Log.d(TAG,"ifattachView:"+this.view);
        return this.view != null;
    }

    public V getView(){
        Log.d(TAG,"getView:"+view);
        return view;
    }

}
