package com.example.simplerichtext.Main.Presenters;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.MyPublishModule;

import java.util.List;

public class MyPublishPresenter {

  public  interface  myPublishViewLisnter{
        void setMyPublishData(List<MyPublishModule> myPublishModuleList);

    }

  public  interface myPublishModuleLisnter{
        void getMyPublishData();
    }


    public void onSubscribe(){

    }


    public void onNext(BaseModule<List<MyPublishModule>> value) {


    }


    public void onError(Throwable e) {


    }

}
