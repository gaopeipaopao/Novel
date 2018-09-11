package com.example.simplerichtext.Add;

import com.example.basecomponent.Modules.MyPublishModule;

public class AddBookMessage {

    private MyPublishModule module;
    public AddBookMessage(MyPublishModule myPublishModule) {
        module = myPublishModule;
    }

    public MyPublishModule getModule() {
        return module;
    }

    public void setModule(MyPublishModule module) {
        this.module = module;
    }
}
