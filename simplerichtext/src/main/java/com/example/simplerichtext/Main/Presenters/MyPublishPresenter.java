package com.example.simplerichtext.Main.Presenters;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.simplerichtext.Main.Modules.MyPulish;

import java.util.List;

public class MyPublishPresenter {

  private myPublishViewLisnter mView;
  private myPublishModuleLisnter mModule;

  public MyPublishPresenter(myPublishViewLisnter viewLisnter) {
    mView = viewLisnter;
    mModule = new MyPulish(this);
  }

  public  interface  myPublishViewLisnter{
        void setMyPublishData(List<MyPublishModule> myPublishModuleList);

    }

  public  interface myPublishModuleLisnter{
        void getMyPublishData();
    }


    public void onSubscribe(){

    }


    public void onNext(BaseModule<List<MyPublishModule>> value) {

      mView.setMyPublishData(value.getData());
    }


    public void onError(Throwable e) {


    }

}
