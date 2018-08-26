package com.example.gaope.novel.Entry;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.util.TimeUtils;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.example.gaope.novel.Base.BaseActivity;
import com.example.gaope.novel.Base.BaseModel;
import com.example.gaope.novel.Base.BasePresenter;
import com.example.gaope.novel.Base.BaseView;
import com.example.gaope.novel.Main.Home.HomeActivity;
import com.example.gaope.novel.Tool.DataModel;
import com.example.gaope.novel.Tool.JudgeNetworkCoon;
import com.example.gaope.novel.Tool.Token;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.acl.LastOwnerException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 登录的Presenter
 */

public class EntryPresenter extends BasePresenter<EntryView> implements IEntryPresenter {

    private static final String TAG = "EntryPresenter";

    private BaseView view;
    private EntryModel model;
    private Context context;

    //判断网络是否可以使用
    private Boolean isNetwork = false;

    //用户是否获取过验证码
    private Boolean numberAgain = false;

    //用户输入的验证码是否正确
    private Boolean numberResult;

    private String accessToken;
    private String refreshToken;

    public EntryPresenter(Context context){
        model = new EntryModel();
        this.context = context;
    }

    @Override
    public void sendEntry(String stringAccount, String stringPassword,
                          String stringNumber, final String imei) {

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

        DataModel.request(Token.ENTRY_MODEL).execute(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (isViewAttached()){
                    getView().showMistake();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String response1 = response.body().string();
                int status = 0;
                int statusLen = 0;
                String stausMessage = null;
                Log.d(TAG,response1);
                try {
                    JSONObject object = new JSONObject(response1);
                    statusLen = object.length();
                    if (statusLen == 2){
                        status =object.getInt("status");
                        stausMessage = object.getString("message");
                        Log.d(TAG,"message:"+"entry"+stausMessage);
                        Log.d(TAG,"statusImage:"+status);
                    }else if (statusLen > 2){
                        accessToken = object.getString("access_token");
                        refreshToken = object.getString("refresh_token");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (status == 1 && stausMessage.contains("验证码")){
                    if (isViewAttached()){
                        Message message = Message.obtain();
                        message.what = 2;
                        EntryActivity.handlers.sendMessage(message);
                        //showMistake()方法中有更新UI的操作
//                        getView().showMistake();
                    }
                }else if (status == 1 && stausMessage.contains("账号")){
                    if (isViewAttached()){
                        Message message = Message.obtain();
                        message.what = 0;
                        EntryActivity.handlers.sendMessage(message);
                    }
                }else if (status == 0){
                    if (isViewAttached()){
                        getView().intentNewActivity(accessToken,refreshToken);
                    }
                }

            }
        },stringAccount,stringPassword,stringNumber,imei);
    }

    @Override
    public void imageNumber(String imei) {

        isNetwork = JudgeNetworkCoon.isNetworkAvailable(context);
        if (!isNetwork){
            if (isViewAttached()){
                Log.d(TAG,"judgenet");
                getView().setDefaultImage();
                getView().showNoNetwork();
                return;
            }
        }

       // Log.d(TAG,"Token.ENTRY_NUMBER_MODEL"+DataModel.request(Token.ENTRY_NUMBER_MODEL));
        DataModel.request(Token.ENTRY_NUMBER_MODEL).execute(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) {
              //      Log.d(TAG,":"+response);
               // 用户获取过验证码了
                numberAgain = true;
                if (isViewAttached()){
                    Log.d(TAG,"aaaaaabb");
                    Message message = Message.obtain();
                    Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    message.what = 1;
                    message.obj = bitmap;
                    EntryActivity.handlers.sendMessage(message);
                    //getView().laodImageNumber(response.body().string());
                }
            }
        },imei);
    }

}
