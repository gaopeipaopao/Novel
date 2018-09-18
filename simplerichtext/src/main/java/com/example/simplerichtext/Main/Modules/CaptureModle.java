package com.example.simplerichtext.Main.Modules;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.CallBack;
import com.example.basecomponent.Excutes.WriteExcute;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.Modules.WriteModule;
import com.example.simplerichtext.Main.Presenters.NovelCaputrePresenter;

import java.util.List;

public class CaptureModle implements NovelCaputrePresenter.CaptureModleInterface {

    private NovelCaputrePresenter mPresenter;

    public CaptureModle(NovelCaputrePresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void getData(String status) {
        WriteExcute.executeWrite(status, new CallBack<BaseModule<List<WriteModule>>>() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onNext(BaseModule<List<WriteModule>> value) {
                mPresenter.setDataSucsses(value.getData());
            }

            @Override
            public void onError(BaseModule<List<WriteModule>> e) {
                mPresenter.getDataFailed(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void addCapture(){

    }

    @Override
    public void getUnpblishedData(int id){

        WriteExcute.ecxcuteUnpublish(id, new CallBack<BaseModule<MyPublishModule>>() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onNext(BaseModule<MyPublishModule> value) {
                mPresenter.getUnpublishedScusses(value);
            }

            @Override
            public void onError(BaseModule<MyPublishModule> e) {
                mPresenter.getUnpublishedFailed(e);
            }

            @Override
            public void onComplete() {

            }
        });

    }
}
