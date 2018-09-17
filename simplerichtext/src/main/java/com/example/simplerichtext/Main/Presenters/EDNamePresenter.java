package com.example.simplerichtext.Main.Presenters;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.simplerichtext.Main.Modules.EDName;

public class EDNamePresenter {

    private EDNameView mView;
    private EDNameModle mModle;
    private boolean mAttachView = false;

    public EDNamePresenter(EDNameView view) {
        this.mView = view;
        mModle = new EDName(this);
        mAttachView = true;

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

    public void deAttachView(){
        if(mAttachView){
            mAttachView = false;
            mView = null;
        }
    }
}
