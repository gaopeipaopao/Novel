package com.example.gaope.novel.Prove;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaope.novel.Base.BaseActivity;
import com.example.gaope.novel.ProveAccount.ProveAccountActivity;
import com.example.gaope.novel.R;
import com.example.gaope.novel.Register.RegisterActivity;
import com.example.gaope.novel.Tool.ActivityCollector;

/**
 * 验证码的Activity
 */

public class ProveActivity extends BaseActivity implements ProveView, View.OnClickListener, TextWatcher {

    private static final String TAG = "ProveActivity";

    private ProvePresenter provePresenter;

    //输入账号和验证码的
    private EditText proveAccount;
    private EditText proveNumber;

    //点击获取验证码的
    private TextView proveAgain;

    private Button proveButton;
    private String stringAccount;
    private String stringNumber;
    public static Handler handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prove);

        ActivityCollector.RegisterCollector.addActivity(this);

        provePresenter = new ProvePresenter(this);
        provePresenter.attachView(this);

        proveAccount = (EditText) findViewById(R.id.prove_account);
        proveNumber = (EditText) findViewById(R.id.prove_number);
        proveAgain = (TextView) findViewById(R.id.prove_again);
        proveButton = (Button) findViewById(R.id.prove_button);

        proveAgain.setOnClickListener(this);
        proveButton.setOnClickListener(this);

        proveAccount.addTextChangedListener(this);
        proveNumber.addTextChangedListener(this);

        proveButton.setEnabled(Boolean.FALSE);

        handlers = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        Log.d(TAG,"what："+msg.what);
                        showHaveSendNumber();
                        break;
                    case 1:
                        Log.d(TAG,"what："+msg.what);
                        loseEffect();
                        break;
                    case 2:
                        accountHaveExist();
                        break;
                    case 3:
                        showMistakeNumber();
                        break;
                    case 4:
                        showLoseEffectNumber();
                        break;
                }
            }
        };
    }

    /**
     * 点击空白时隐藏软键盘
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            //isShouldHideInput(v, ev)与hideInputMethod(this, v)的实现在BaseActivity中
            if (isShouldHideInput(v, ev)) {
                if(hideInputMethod(this, v)) {
                    return true; //隐藏键盘时，其他控件不响应点击事件==》注释则不拦截点击事件
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.prove_again:
                //发送网络请求获取验证码
                stringAccount = proveAccount.getText().toString();
                provePresenter.sendProve(stringAccount,returnIMEI());
                break;
            case R.id.prove_button:
                //判断验证码是否正确
                stringNumber = proveNumber.getText().toString();
                provePresenter.proveNumber(stringAccount,stringNumber,returnIMEI());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        provePresenter.detachView();
        ActivityCollector.RegisterCollector.removeActivity(this);
    }

    @Override
    public void showRightLengthAccount() {
        proveAccount.setText(null);
        Toast.makeText(getContexts(),"手机号的位数为11位",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showHaveSendNumber() {
        Toast.makeText(getContexts(),"短信验证码已经发送,5分钟内有效",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loseEffect() {
        Log.d(TAG,"失效");
        Toast.makeText(getContexts(),"失效",Toast.LENGTH_SHORT).show();
        provePresenter.setLoseEffect();
    }

    @Override
    public void showNoNetwork() {
        Log.d(TAG,"base");
        Toast.makeText(getContexts(),"当前网络不可用，请检查网络连接",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showMistake() {
        proveAccount.setText(null);
        proveNumber.setText(null);
        Toast.makeText(getContexts(),"该手机号不存在",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void intentNewAcitvity(String regiesterMessage) {
        Intent intent = new Intent(ProveActivity.this, ProveAccountActivity.class);
        intent.putExtra("手机号",stringAccount);
        intent.putExtra("key",regiesterMessage);
        startActivity(intent);
    }

    @Override
    public void showMistakeNumber() {
        proveNumber.setText(null);
        Toast.makeText(getContexts(),"验证码输入错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoseEffectNumber() {
        proveNumber.setText(null);
        Toast.makeText(getContexts(),"验证码已失效",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void accountHaveExist() {
        proveAccount.setText(null);
        proveNumber.setText(null);
        Toast.makeText(getContexts(),"该手机号已经注册",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGetNumber() {
        proveNumber.setText(null);
        Toast.makeText(getContexts(),"请先获得验证码",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(proveAccount.getText()) ||
                TextUtils.isEmpty(proveNumber.getText())) {
            proveButton.setEnabled(Boolean.FALSE);
            //Toast.makeText(getContexts(), "按钮不可点击", Toast.LENGTH_SHORT).show();
        } else {
            proveButton.setEnabled(Boolean.TRUE);
            //Toast.makeText(getContexts(), "按钮可点击", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
