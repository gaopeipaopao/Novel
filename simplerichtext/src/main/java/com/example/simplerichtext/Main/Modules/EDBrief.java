package com.example.simplerichtext.Main.Modules;

import android.support.v4.content.PermissionChecker;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.CallBack;
import com.example.basecomponent.Excutes.AddExcute;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.simplerichtext.Main.Presenters.EDBriefPresenter;

public class EDBrief implements EDBriefPresenter.EDBriefModle {

    private EDBriefPresenter mPresenter;

    public EDBrief(EDBriefPresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void uploadName(MyPublishModule myPublishModule) {
        AddExcute.updateBook(myPublishModule, AddExcute.UNPUBLISHED, new CallBack<BaseModule<MyPublishModule>>() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onNext(BaseModule<MyPublishModule> value) {
                if(value!=null&&value.getStatus() == 0){
                    mPresenter.uploadSucsses(value.getData());
                }

            }

            @Override
            public void onError(BaseModule<MyPublishModule> e) {
                mPresenter.uploadFailed(e);
            }

            @Override
            public void onComplete() {

            }
        });

    }
}
