package com.example.simplerichtext.Add;

import android.net.Uri;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.MyPublishModule;

public class AddPersenter {

    private AddModleInterface mModle;
    private AddViewInterface mView;
    private boolean mAttachedView = false;

    public interface AddViewInterface{
        void updateData(MyPublishModule book);
        void uploadFailed(BaseModule module);
    }

    public interface AddModleInterface{
        void uploadBook(MyPublishModule book, String image);
    }

    public AddPersenter(AddViewInterface view) {
        mView = view;
        mAttachedView = true;
        mModle = new AddModle(this);
    }

    public void upload(MyPublishModule book,String image){
        mModle.uploadBook(book,image);
    }

    public void updateData(MyPublishModule book){
        mView.updateData(book);
    }

    public void uploadFailed(BaseModule module){
        mView.uploadFailed(module);
    }

}
