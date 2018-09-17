package com.example.basecomponent.Excutes;

import android.util.Log;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.CallBack;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.Services.MyPublishService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.SafeObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class MyPublishExcute {


    private static final String TAG = "MyPublishExcute";

    public static void excute(final CallBack<BaseModule<List<MyPublishModule>>>
                                      callBack,String status){


        MyPublishService service = HttpUtil.getRetrofit().create(MyPublishService.class);
        Log.d(TAG, "excute: "+HttpUtil.Bearer+HttpUtil.getAccessToken());
        service.getMyPublishData(HttpUtil.Bearer+HttpUtil.
                getAccessToken(),status)
                .subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Observer<BaseModule<List<MyPublishModule>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseModule<List<MyPublishModule>> value) {

                        callBack.onNext(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

}
