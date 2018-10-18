package com.example.simplerichtext.Add;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.Util;
import com.example.basecomponent.loading.LoadingUtil;
import com.example.basecomponent.BaseActivity;
import com.example.simplerichtext.Main.Presenters.EDTypePresenter;
import com.example.simplerichtext.R;

public class BookTypeActivity extends BaseActivity implements
        View.OnClickListener,RadioGroup.OnCheckedChangeListener,EDTypePresenter.EDTypeView {

    private RadioGroup mRadioGroup;
    private ImageView mBack;
    private TextView mSave;
    private int mCheckedId;
    private static final String TAG = "BookTypeActivity";
    private MyPublishModule mModle;
    private boolean mUpdate = false;
    private EDTypePresenter mPresenter;
    private Dialog mLoadingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity_book_type);
        mPresenter = new EDTypePresenter(this);
        Intent intent = getIntent();
        mCheckedId = intent.getIntExtra("type",-1);
        Bundle bundle = intent.getBundleExtra("book");
        if(bundle!=null){
            mModle = (MyPublishModule) bundle.getSerializable("book");
            mUpdate = bundle.getBoolean("update");
            getId(Util.getTypeValue(mModle.getBookType()));

        }
        init();
    }

    private void init(){
        mRadioGroup = findViewById(R.id.radio_group);
        mRadioGroup.setOnCheckedChangeListener(this);
        mBack = findViewById(R.id.iv_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSave = findViewById(R.id.tv_save);
        mSave.setOnClickListener(this);
        Log.d(TAG, "init: "+mCheckedId);
        if(mCheckedId !=-1){
            mRadioGroup.check(mCheckedId);
        }
    }

    private void showLoading(){
        if(mLoadingView== null){
            mLoadingView = LoadingUtil.showLoadingView(this);
        }
        mLoadingView.show();
    }

    private void dismissLoading(){
        if(mLoadingView!=null){
            mLoadingView.dismiss();
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_save){
            if(!mUpdate){
                Intent intent = new Intent();
                intent.putExtra("type",mCheckedId);
                intent.putExtra("type_name",((RadioButton)findViewById(mCheckedId))
                        .getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }else {
                if(Util.isNetworkAvailable(this)){
                    mModle.setBookType(Util.getTypeKey(((RadioButton)findViewById(mCheckedId))
                            .getText().toString()));
                    mPresenter.uploadBrief(mModle);
                    showLoading();
                }else {
                    Toast.makeText(this,R.string.simple_no_network,Toast.LENGTH_SHORT).show();
                }
            }


        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Log.d(TAG, "onCheckedChanged: "+checkedId);
        mCheckedId = checkedId;
    }

    @Override
    public void uploadScusses(MyPublishModule module) {
        if(mModle!=null){
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("book",module);
            intent.putExtra("book",bundle);
            setResult(RESULT_OK,intent);
            dismissLoading();
            finish();
        }else {
            dismissLoading();
            Toast.makeText(this,R.string.simple_update_failed,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void uploadFailed(BaseModule module) {
        dismissLoading();
        if(mModle!=null){
            Toast.makeText(this,module.getMessage(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,R.string.simple_update_failed,Toast.LENGTH_SHORT).show();
        }
    }

    private void getId(String type){
        Log.d(TAG, "getId: "+type);
        if(type.equals(getResources().
                getString(R.string.simple_fantasySentiment))){
            mCheckedId = R.id.rb_xh;
        }else  if(type.equals(getResources().
                getString(R.string.simple_ImmortalChivalry))){
            mCheckedId = R.id.rb_xx;
        }else  if(type.equals(getResources().
                getString(R.string.simple_AncientSentiment))) {
            mCheckedId = R.id.rb_gd;
        }else if(type.equals(getResources().
                getString(R.string.simple_ModernSentiment))) {
            mCheckedId = R.id.rb_xd;
        }else if(type.equals(getResources().
                getString(R.string.simple_RomanticYouth))) {
            mCheckedId = R.id.rb_qc;
        }else if(type.equals(getResources().
                getString(R.string.simple_SuspensePsychic))) {
            mCheckedId = R.id.rb_xy;
        }else if(type.equals(getResources().
                getString(R.string.simple_ScienceSpace))) {
            mCheckedId = R.id.rb_kh;
        }else if(type.equals(getResources().
                getString(R.string.simple_GameCompetition))) {
            Log.d(TAG, "getId: "+type);
            mCheckedId = R.id.rb_yx;
        }else if(type.equals(getResources().
                getString(R.string.simple_TanbiNovel))) {
            mCheckedId = R.id.rb_dm;
        }
    }
}
