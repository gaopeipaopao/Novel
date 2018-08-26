package com.example.gaope.novel.ProveAccount;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.gaope.novel.Base.BasePresenter;
import com.example.gaope.novel.Prove.IProvePresenter;
import com.example.gaope.novel.Register.RegisterActivity;
import com.example.gaope.novel.Tool.DataModel;
import com.example.gaope.novel.Tool.JudgeNetworkCoon;
import com.example.gaope.novel.Tool.Token;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ProveAccountPresenter extends BasePresenter<ProveAccountView> implements IProveAccountPresenter {

    private static final String TAG = "ProveAccountPresenter";

    private Context context;
    private Boolean isNetwork;

    public ProveAccountPresenter(Context context){
        this.context = context;
    }

    @Override
    public void judgeAccount(String stringAccount) {
        isNetwork = JudgeNetworkCoon.isNetworkAvailable(context);
        if (!isNetwork){
            if (isViewAttached()){
                Log.d(TAG,"judgenet");
                getView().showNoNetwork();
                return;
            }
        }
        if (stringAccount.length() < 2 || stringAccount.length() >20){
            if (isViewAttached()){
                getView().showAccountLength();
                return;
            }
        }

        DataModel.request(Token.PROVE_ACCOUNT_MODEL).execute(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responsel = response.body().string();
                Log.d(TAG,"provename:"+responsel);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject((responsel));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int status = 10;
                try {
                    status = jsonObject.getInt("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(TAG,"status:"+status);
                if (status == 0){
                    if (isViewAttached()){
                        Message message = Message.obtain();
                        message.what = 0;
                        ProveAccountActivity.handlers.sendMessage(message);
//                        getView().intentEntry();
                    }
                }
                if (status == -1){
                    if (isViewAttached()){
                        getView().intentRegister();
//                        getView().intentEntry();
                    }

                }
            }
        },stringAccount);
    }
}
