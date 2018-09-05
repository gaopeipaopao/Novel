package com.example.simplerichtext.Main.Modules;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.CallBack;
import com.example.basecomponent.Excutes.MyPublishExcute;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.Services.MyPublishService;
import com.example.simplerichtext.Main.Presenters.MyPublishPresenter;

import java.util.ArrayList;
import java.util.List;


public class MyPulish implements MyPublishPresenter.myPublishModuleLisnter {

    private MyPublishPresenter mPersenter;

    public MyPulish(MyPublishPresenter persenter) {
        this.mPersenter = persenter;
    }

    @Override
    public void getMyPublishData() {

        MyPublishExcute.excute(new CallBack<BaseModule<List<MyPublishModule>>>() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onNext(BaseModule<List<MyPublishModule>> value) {
                mPersenter.onNext(value);
            }

            @Override
            public void onError(BaseModule<List<MyPublishModule>> e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}