package com.example.gaope.novel.Prove;

import android.content.Context;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import com.example.gaope.novel.Base.BasePresenter;
import com.example.gaope.novel.Entry.EntryActivity;
import com.example.gaope.novel.Tool.DataModel;
import com.example.gaope.novel.Tool.JudgeNetworkCoon;
import com.example.gaope.novel.Tool.JudgePhone;
import com.example.gaope.novel.Tool.Token;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ProvePresenter extends BasePresenter<ProveView> implements IProvePresenter {

    private static final String TAG = "ProvePresenter";

    private ProveModel proveModel;
    private String account = new String();
    private Context context;

    //发送请求后得到的验证码
    private String number = null;

    //请求验证码返回的数据的长度
    private int statusLen = 0;

    //判断网络是否可以使用
    private Boolean isNetwork = false;

    //用户是否获取过验证码
    private Boolean numberAgain = false;

    //注册时需要使用的发送验证码时返回的message
    private String regiesterMessage;

    public ProvePresenter(Context context){
        this.context = context;
        proveModel = new ProveModel();
    }

    @Override
    public void sendProve(String stringAccount,String imie) {

        account = stringAccount;
        statusLen = 1;

        isNetwork = JudgeNetworkCoon.isNetworkAvailable(context);
        if (!isNetwork){
            if (isViewAttached()){
                Log.d(TAG,"judgenet");
                getView().showNoNetwork();
                return;
            }
        }

        if (stringAccount.length() > 11){
            if (isViewAttached()){
                getView().showRightLengthAccount();
                return;
            }
        }

        if (stringAccount.length() < 11){
            if (isViewAttached()){
                getView().showRightLengthAccount();
                return;
            }
        }

        if (!JudgePhone.isPhoneLegal(stringAccount)){
            Log.d(TAG,"judgePhone:"+JudgePhone.isPhoneLegal(stringAccount));
            if (isViewAttached()){
                getView().showMistake();
                return;
            }
        }


        DataModel.request(Token.PROVE_MODEL).execute(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               // Log.d(TAG,"aa:"+response.body().string());

                String responsel = response.body().string();
                Log.d(TAG,"aa:"+responsel);

                numberAgain = true;
                JSONObject jsonObject = null;
                String messages = null;

                try {
                    jsonObject = new JSONObject(responsel);
                    messages = jsonObject.getString("message");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d(TAG,"messageNumber:"+messages);

                if (messages.equals("该手机号已被注册")){
                    if (isViewAttached()){
                        Message message2 = Message.obtain();
                        message2.what = 2;
                        ProveActivity.handlers.sendMessage(message2);
                    }
                }

                if (messages.equals("发送成功")){
                    if (isViewAttached()){

                        Message message = Message.obtain();
                        message.what = 0;
                        ProveActivity.handlers.sendMessage(message);

                        Message message1 = new Message();
                        message1.what = 1;
                        ProveActivity.handlers.sendEmptyMessageAtTime( message1.what,SystemClock.uptimeMillis() + 1000 * 60 *5);
                    }
                }

            }
        },stringAccount,imie);
    }

    @Override
    public void proveNumber(final String stringAccount, String stringNumber, String imie) {

        isNetwork = JudgeNetworkCoon.isNetworkAvailable(context);
        if (!isNetwork){
            if (isViewAttached()){
                Log.d(TAG,"judgenet");
                getView().showNoNetwork();
                return;
            }
        }

        if (!numberAgain){
            if (isViewAttached()){
                getView().showGetNumber();
                return;
            }
        }

        DataModel.request(Token.PROVE_NUMBER_MODEL).execute(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responsel = response.body().string();
                Log.d(TAG,"aa1111:"+responsel);

                JSONObject jsonObject = null;
                int status = 0;

                try {
                    jsonObject = new JSONObject(responsel);
                    regiesterMessage = jsonObject.getString("message");
                    status = jsonObject.getInt("status");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (status == 0){
                    if (isViewAttached()){
                        Log.d(TAG,"numberProve");
                        getView().intentNewAcitvity(regiesterMessage);
                    }
                }

                if (status == 1 && regiesterMessage.contains("验证码不匹配")){
                    if (isViewAttached()){
                        Message message3 = Message.obtain();
                        message3.what = 3;
                        ProveActivity.handlers.sendMessage(message3);
                       // getView().showMistakeNumber();
                    }
                }
                if (status == 1 && regiesterMessage.contains("验证码不存在")){
                    if (isViewAttached()){
                        Message message4 = Message.obtain();
                        message4.what = 4;
                        ProveActivity.handlers.sendMessage(message4);
                        // getView().showMistakeNumber();
                    }
                }

                Log.d(TAG,"messageNumber:"+regiesterMessage);
            }
        },stringAccount,stringNumber,imie);

    }

    @Override
    public void setLoseEffect() {
        numberAgain = false;
    }
}
