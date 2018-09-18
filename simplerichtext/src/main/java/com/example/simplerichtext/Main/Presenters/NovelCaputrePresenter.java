package com.example.simplerichtext.Main.Presenters;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.Modules.WriteModule;
import com.example.simplerichtext.Main.Modules.CaptureModle;

import java.util.List;

public class NovelCaputrePresenter {

    private CaptureViewInterface mView;
    private CaptureModleInterface mModle;
    private boolean mAttachView = false;

    public NovelCaputrePresenter(CaptureViewInterface view) {
        this.mView = view;
        mModle = new CaptureModle(this);
        mAttachView = true;
    }

    public interface CaptureViewInterface{

        void setDataSucsses(List<WriteModule> modules);
        void getDataFailed(BaseModule module);
        void getUnpublishedSuccses(BaseModule<MyPublishModule> module);
    }

    public interface CaptureModleInterface{

        void getData(String status);
        void getUnpblishedData(int id);
    }

    public void setDataSucsses(List<WriteModule> modules){

        mView.setDataSucsses(modules);

    }
    public void getDataFailed(BaseModule module){
        mView.getDataFailed(module);

    }
    public void getData(String status){
        mModle.getData(status);
    }

    public void getUnPublishedData(int bookId){
        mModle.getUnpblishedData(bookId);
    }

    public void getUnpublishedScusses(BaseModule<MyPublishModule> module){
        mView.getUnpublishedSuccses(module);
    }

    public void getUnpublishedFailed(BaseModule module){
        mView.getDataFailed(module);
    }

    public void dettachView(){
        if(mAttachView){
            mView = null;
        }
    }
}
