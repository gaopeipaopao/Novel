package com.example.simplerichtext.Main.Presenters;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.simplerichtext.Main.Modules.MyPulish;

import java.util.List;

public class MyPublishPresenter {

  private myPublishViewLisnter mView;
  private myPublishModuleLisnter mModule;
  private boolean mIsAttachView = false;

  public MyPublishPresenter(myPublishViewLisnter viewLisnter) {
    mView = viewLisnter;
    mIsAttachView = true;
    mModule = new MyPulish(this);
  }

  public  interface  myPublishViewLisnter{
        void setMyPublishData(List<MyPublishModule> myPublishModuleList);
        void setDataError();

    }

  public  interface myPublishModuleLisnter{
        void getMyPublishData();
    }


    public void onSubscribe(){

    }


    public void onNext(BaseModule<List<MyPublishModule>> value) {
      if(mIsAttachView){
        mView.setMyPublishData(value.getData());
      }

    }


    public void onError(Throwable e) {
      mView.setDataError();
    }

    public void getData(){
        mModule.getMyPublishData();
    }

    public void dettachView(){

      mIsAttachView = false;
      mView = null;
      mModule = null;

    }

}
