package com.example.simplerichtext.Add;

import android.net.Uri;

import com.example.basecomponent.Modules.BookModule;
import com.example.basecomponent.Modules.MyPublishModule;

public class AddPersenter {

    private AddModleInterface mModle;
    private AddViewInterface mView;
    private boolean mAttachedView = false;

    public interface AddViewInterface{
        void updateData(BookModule book);
    }

    public interface AddModleInterface{
        void uploadBook(BookModule book, Uri image);
    }

    public AddPersenter(AddViewInterface view) {
        mView = view;
        mAttachedView = true;
        mModle = new AddModle(this);
    }

    public void upload(BookModule book,Uri image){
        mModle.uploadBook(book,image);
    }

    public void updateData(BookModule book){
        mView.updateData(book);
    }

}
