package com.example.simplerichtext.RichTextView;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.CallBack;
import com.example.basecomponent.Excutes.AddExcute;
import com.example.basecomponent.Excutes.MyPublishExcute;
import com.example.basecomponent.Excutes.WriteExcute;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.Modules.WriteModule;

public class RichTextModule implements RichTextPresenter.RichTextModelInterface {

    private RichTextPresenter mPresenter;

    public RichTextModule(RichTextPresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void saveUnPublished(final MyPublishModule module) {
        AddExcute.updateBook(module, module.getStatus(), new CallBack<BaseModule<MyPublishModule>>() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onNext(BaseModule<MyPublishModule> value) {
                if(module.getStatus().equals(HttpUtil.STATUS_UNPUBLISHED)) {
                    mPresenter.saveUnPublishedSuceese(value);
                }else {
                    mPresenter.publishSuceese(value);
                }

            }

            @Override
            public void onError(BaseModule<MyPublishModule> e) {
                if(module.getStatus().equals(HttpUtil.STATUS_UNPUBLISHED)){
                    mPresenter.saveUnPublishedFailed(e);
                }else {
                    mPresenter.publishFailed(e);
                }

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void savePublished(MyPublishModule module) {

    }

    @Override
    public void getUnPublishedData(final MyPublishModule module) {
        WriteExcute.ecxcuteUnpublish(module.getBookId(), new CallBack<BaseModule<MyPublishModule>>() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onNext(BaseModule<MyPublishModule> value) {

                mPresenter.getUnPublishedDataSuceese(value);
            }

            @Override
            public void onError(BaseModule<MyPublishModule> e) {
                mPresenter.getUnPublishedDataFailed(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void publish(MyPublishModule module,int parentId) {
        if(module.getStatus().equals(HttpUtil.STATUS_UNPUBLISHED)){
            module.setStatus(HttpUtil.STATUS_PUBLISHED);
            saveUnPublished(module);
        }else {

        }
    }
}
