package com.example.simplerichtext.Main.Presenters;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.simplerichtext.Main.Modules.MyPulish;

import java.util.List;

public class MyPublishPresenter {

  private myPublishViewLisnter mView;
  private myPublishModuleLisnter mModule;
  private boolean mIsAttachView = false;
  private String mStatus;

  public MyPublishPresenter(myPublishViewLisnter viewLisnter) {
    mView = viewLisnter;
    mIsAttachView = true;
    mModule = new MyPulish(this);
  }

  public  interface  myPublishViewLisnter{
        void setMyPublishData(List<MyPublishModule> myPublishModuleList);
        void setDataError();
        void uploadImageSucssed();
        void uploadImageFailed();

    }

    public  interface myPublishModuleLisnter{
        void getMyPublishData(String status);
        void uploadImage(int id ,String image);
    }


    public void onSubscribe(){

    }

    public void uploadImage(int id,String image){
      mModule.uploadImage(id,image);
    }

    public void uploadImageSucssed(){
        mView.uploadImageSucssed();
    }

    public void uploadImageFailed(){
        mView.uploadImageFailed();
    }


    public void onNext(BaseModule<List<MyPublishModule>> value) {
      if(mIsAttachView){
          List<MyPublishModule> modules = value.getData();
          for(int i =0;i<modules.size();i++){
              modules.get(i).setStatus(mStatus);
          }
        mView.setMyPublishData(value.getData());
      }

    }


    public void onError(Throwable e) {
      mView.setDataError();
    }

    public void getData(String status) {
      mStatus = status;
        mModule.getMyPublishData(status);
    }

    public void dettachView(){

      mIsAttachView = false;
      mView = null;
      mModule = null;

    }

}
