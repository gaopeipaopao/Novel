package com.example.simplerichtext.Main.Presenters;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.WriteModule;
import com.example.simplerichtext.Main.Modules.CaptureModle;

import java.util.List;

public class NovelCaputrePresenter {

    private CaptureViewInterface mView;
    private CaptureModleInterface mModle;

    public NovelCaputrePresenter(CaptureViewInterface view) {
        this.mView = view;
        mModle = new CaptureModle(this);
    }

    public interface CaptureViewInterface{

        void setDataSucsses(List<WriteModule> modules);
        void getDataFailed(BaseModule module);
    }

    public interface CaptureModleInterface{

        void getData(String status);
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
}
