package com.example.simplerichtext.Main.Presenters;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.simplerichtext.Main.Modules.EDBrief;

public class EDBriefPresenter {

    private EDBriefView mView;
    private EDBriefModle mModle;
    private boolean mAttachView = false;

    public EDBriefPresenter(EDBriefView view) {
        this.mView = view;
        mModle = new EDBrief(this);
        mAttachView = true;
    }

    public interface EDBriefView{
        void uploadScusses(MyPublishModule module);
        void uploadFailed(BaseModule module);
    }

    public interface  EDBriefModle{
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
