package com.example.simplerichtext.Main.Presenters;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.simplerichtext.Main.Modules.EDName;

public class EDNamePresenter {

    private EDNameView mView;
    private EDNameModle mModle;

    public EDNamePresenter(EDNameView view) {
        this.mView = view;
        mModle = new EDName(this);

    }

    public interface EDNameView{

        void uploadScusses(MyPublishModule module);
        void uploadFailed(BaseModule module);
    }


    public interface  EDNameModle{
        void uploadName(MyPublishModule myPublishModule);
    }

    public void uploadName(MyPublishModule module){
        mModle.uploadName(module);
    }

    public void uploadSucsses(MyPublishModule module){
        mView.uploadScusses(module);
    }

    public void uploadFailed(BaseModule module){
        mView.uploadFailed(module);
    }
}
