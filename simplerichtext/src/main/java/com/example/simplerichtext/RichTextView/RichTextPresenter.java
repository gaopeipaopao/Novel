package com.example.simplerichtext.RichTextView;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.MyPublishModule;

public class RichTextPresenter {


    private RichTextModelInterface mModle;
    private RichTextViewInterface mView;
    private boolean mAttachView = false;

    public RichTextPresenter(RichTextViewInterface view) {
        this.mView = view;
        mModle = new RichTextModule(this);
        mAttachView = true;
    }

    public interface RichTextViewInterface{
        void saveUnPublishedSuceese(BaseModule<MyPublishModule> module);
        void saveUnPublishedFailed(BaseModule module);
        void savePublishedSuceese(BaseModule<MyPublishModule> module);
        void savePublishedFailed(BaseModule module);
        void getUnPublishedDataSuceese(BaseModule<MyPublishModule> module);
        void getUnPublishedDataFailed(BaseModule module);
        void publishBookSuceese(BaseModule<MyPublishModule> module);
        void publishBookFailed(BaseModule module);
        void publishCaptureSuceese(BaseModule module);
        void publishCaptureFailed(BaseModule module);
    }

    public interface RichTextModelInterface{
        void saveUnPublished(MyPublishModule module);
        void savePublished(MyPublishModule module);
        void getUnPublishedData(MyPublishModule module);
        void publish(MyPublishModule module,int parentId);

    }

    public  void saveUnPublishedSuceese(BaseModule<MyPublishModule> module){

        mView.saveUnPublishedSuceese(module);
    }
    public  void saveUnPublishedFailed(BaseModule module){
        mView.saveUnPublishedFailed(module);
    }
    public void savePublishedSuceese(BaseModule<MyPublishModule> module){

    }
    public void savePublishedFailed(BaseModule module){
        mView.savePublishedFailed(module);

    }
    public void saveUnPublished(MyPublishModule module){

        mModle.saveUnPublished(module);

    }
    public void savePublished(MyPublishModule module){
        mModle.savePublished(module);
    }

    public void dettachView(){
        if(mAttachView){
            mView = null;
            mAttachView = false;
        }
    }

    public void getUnpublishedData(MyPublishModule module){

        mModle.getUnPublishedData(module);
    }

    public void getUnPublishedDataSuceese(BaseModule<MyPublishModule> module){
        mView.getUnPublishedDataSuceese(module);
    }
    public void getUnPublishedDataFailed(BaseModule module){
        mView.getUnPublishedDataFailed(module);
    }

    public void publish(MyPublishModule module,int parentId){

        mModle.publish(module,parentId);
    }

    public void publishSuceese(BaseModule<MyPublishModule> module){
        mView.publishBookSuceese(module);
    }

    public void publishFailed(BaseModule module){
        mView.publishBookFailed(module);
    }
}
