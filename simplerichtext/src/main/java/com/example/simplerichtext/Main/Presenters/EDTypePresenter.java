package com.example.simplerichtext.Main.Presenters;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.simplerichtext.Main.Modules.EDType;

public class EDTypePresenter {

    private EDTypeView mView;
    private EDTypeModle mModle;

    private boolean mAttachView = false;

    public EDTypePresenter(EDTypeView view) {
        this.mView = view;
        mModle = new EDType(this);
        mAttachView = true;
    }

    public interface EDTypeView{
        void uploadScusses(MyPublishModule module);
        void uploadFailed(BaseModule module);
    }

    public interface  EDTypeModle{
        void uploadName(MyPublishModule myPublishModule);
    }

    public void uploadBrief(MyPublishModule module){
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
