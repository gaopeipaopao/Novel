package com.example.gaope.novel.Main.Mine.MineInfor;

import android.content.Context;
import android.util.Log;

import com.example.gaope.novel.Base.BasePresenter;

public class MinePresenter extends BasePresenter<MineView> implements IMinePresenter {

    private Context context;
    private MinePresenter minePresenter;

    public MinePresenter(Context context){
        this.context = context;
    }

    @Override
    public void getMineInfor() {
        Log.d("aaaA","MainInforPersenter");
        if (isViewAttached()){
            getView().aa();
        }
    }

    @Override
    public void postMineInfor() {

    }
}
