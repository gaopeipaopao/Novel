package com.example.basecomponent.Excutes;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.CallBack;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.Services.MyPublishService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.SafeObserver;
import io.reactivex.schedulers.Schedulers;


public class MyPublishExcute {

    public static void excute(CallBack<BaseModule<List<MyPublishModule>>> callBack){


        MyPublishService service = HttpUtil.getRetrofit().create(MyPublishService.class);
        service.getMyPublishData(HttpUtil.getAccessToken())
                .subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Observer<BaseModule<List<MyPublishModule>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseModule<List<MyPublishModule>> value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

}
