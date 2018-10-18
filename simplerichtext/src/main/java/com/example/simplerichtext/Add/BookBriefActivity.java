package com.example.simplerichtext.Add;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.Util;
import com.example.basecomponent.loading.LoadingUtil;
import com.example.basecomponent.BaseActivity;
import com.example.simplerichtext.Main.Presenters.EDBriefPresenter;
import com.example.simplerichtext.R;

public class BookBriefActivity extends BaseActivity implements
        View.OnClickListener ,TextWatcher,EDBriefPresenter.EDBriefView{

    private ImageView mBack;
    private TextView mSave;
    private EditText mBriefEd;
    private TextView mWords;

    private MyPublishModule mModle;
    private boolean mUpdate = false;
    private EDBriefPresenter mPersenter;
    private Dialog mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity_book_breif);
        mPersenter = new EDBriefPresenter(this);
        init();
    }

    private void init(){
        mBack = findViewById(R.id.iv_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSave = findViewById(R.id.tv_save);
        mSave.setOnClickListener(this);

        mBriefEd = findViewById(R.id.et_brief);
        mBriefEd.addTextChangedListener(this);

        mWords = findViewById(R.id.tv_words);

        Intent intent = getIntent();
        String brief = intent.getStringExtra("brief");
        Bundle bundle = intent.getBundleExtra("book");
        if(bundle!=null){
            mModle = (MyPublishModule) bundle.getSerializable("book");
            mUpdate = bundle.getBoolean("update");
            brief = mModle.getContent();
        }

        mBriefEd.setText(brief);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_save){
            if(!mUpdate){
                Intent intent = new Intent();
                intent.putExtra("brief",mBriefEd.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }else {
                if(Util.isNetworkAvailable(this)){
                    mModle .setContent(mBriefEd.getText().toString());
                    mPersenter.uploadBrief(mModle);
                    showLoading();
                }else {
                    Toast.makeText(this,R.string.simple_no_network,Toast.LENGTH_SHORT).show();
                }

            }


        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mWords.setText(Util.getABCCount(s.toString())
                +Util.getChCount(s.toString())+
                getResources().getString(R.string.simple_count));
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void uploadScusses(MyPublishModule module) {
        if(mModle !=null){
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("book",module);
            intent.putExtra("book",bundle);
            setResult(RESULT_OK,intent);
            dismissLoading();
            finish();
        }else {
            Toast.makeText(this,R.string.simple_update_failed,Toast.LENGTH_SHORT).show();
            dismissLoading();
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
    public void uploadFailed(BaseModule module) {
        if(mModle!=null){
            Toast.makeText(this,module.getMessage(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,R.string.simple_update_failed,Toast.LENGTH_SHORT).show();
        }
        dismissLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPersenter.deAttachView();
    }
}
