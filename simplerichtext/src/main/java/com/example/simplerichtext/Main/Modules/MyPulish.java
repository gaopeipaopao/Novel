package com.example.simplerichtext.Main.Modules;

import com.example.basecomponent.BaseModule;
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



    }
}
