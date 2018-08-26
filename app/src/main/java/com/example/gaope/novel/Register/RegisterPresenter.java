package com.example.gaope.novel.Register;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.example.gaope.novel.Base.BasePresenter;
import com.example.gaope.novel.Entry.EntryActivity;
import com.example.gaope.novel.Tool.DataModel;
import com.example.gaope.novel.Tool.JudgeNetworkCoon;
import com.example.gaope.novel.Tool.Token;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterPresenter extends BasePresenter<RegisterView> implements IRegisterPresenter{

    private static final String TAG = "RegisterPresenter";

    private String phoneNumbers;
    private int statusLen;
    private Context context;
    private Boolean isNetwork;

    public RegisterPresenter(Context context){
        this.context = context;
    }

    public void sendRegister(String phoneNumber,
                             String stringAccount, String stringPassword,
                             String stringPasswordAgain,
                             String regiesterMessageKey,
                             String imie){
        phoneNumbers = phoneNumber;
        isNetwork = JudgeNetworkCoon.isNetworkAvailable(context);
        if (!isNetwork){
            if (isViewAttached()){
                Log.d(TAG,"judgenet");
                getView().showNoNetwork();
                return;
            }
        }
        if (stringPassword.length() < 6 || stringAccount.length() >30){
            if (isViewAttached()){
                getView().showPasswordLength();
                return;
            }
        }
        if (!stringPassword.equals(stringPasswordAgain)){
            if (isViewAttached()){
                getView().judgePassword();
                return;
            }
        }

        DataModel.request(Token.REGISTER_MODEL).execute(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responsel = response.body().string();
                Log.d(TAG,":" + response.body().string());
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(responsel);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                statusLen = jsonObject.length();
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
                        RegisterActivity.handlers.sendMessage(message);
//                        getView().intentEntry();
                    }
                }
                if (status == -1){

                }
            }
        },stringAccount,phoneNumbers,stringPassword,regiesterMessageKey,imie);
    }

}
