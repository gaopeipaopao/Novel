package com.example.gaope.novel.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.example.gaope.novel.Tool.MyApplication;

import okhttp3.Callback;

public class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView{

    private static final String TAG = "BaseFragment";
    private P presenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // Log.d(TAG,"onAcreat");
        getContexts();
       // showNoNetwork();
    }

    @Override
    public Context getContexts() {
      //  Log.d(TAG,"context:"+MyApplication.getInstance());
        return MyApplication.getInstance();
    }


    public void attachPresenter(P presenter){
    //    Log.d(TAG,"aP:"+presenter);
        this.presenter = presenter;
    }
    public P getPresenter(){
     //   Log.d(TAG,"app:"+presenter);
        return this.presenter;
    }


}
