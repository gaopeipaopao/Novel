package com.example.simplerichtext.Main.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.Util;
import com.example.simplerichtext.Base.BaseActivity;
import com.example.simplerichtext.Main.Presenters.EDNamePresenter;
import com.example.simplerichtext.R;

public class EDBookNameActivity extends BaseActivity implements
        View.OnClickListener ,EDNamePresenter.EDNameView{

    private ImageView mBack;
    private EditText mEdName;
    private TextView mCount;
    private TextView mSave;
    private EDNamePresenter mPresenter;
    private MyPublishModule mMyPublishModule;

    private static final String TAG = "EDBookNameActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity_edbook_name);
        Intent intent = getIntent();
        mMyPublishModule = (MyPublishModule) intent.
                getBundleExtra("book").getSerializable("book");
        mPresenter = new EDNamePresenter(this);
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

        mEdName = findViewById(R.id.et_name);
        mCount = findViewById(R.id.tv_count);
        mSave = findViewById(R.id.tv_save);
        mSave.setOnClickListener(this);
        mEdName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCount.setText(String.valueOf(s.length())+getResources()
                        .getString(R.string.simple_count));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdName.setText(mMyPublishModule.getBookName());
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.tv_save){
            if(Util.isNetworkAvailable(this)){
                if(!mEdName.getText().equals("")){
//                Intent intent = new Intent();
//                intent.putExtra("bookname",mEdName.getText().toString());
//                setResult(RESULT_OK,intent);
                    Log.d(TAG, "onClick: ");
                    mMyPublishModule.setBookName(mEdName.getText().toString());
                    mPresenter.uploadName(mMyPublishModule);

                }else {
                    Toast.makeText(this,R.string.simple_name_empty,
                            Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this,R.string.simple_no_network,
                        Toast.LENGTH_SHORT).show();
            }


        }
    }

    @Override
    public void uploadScusses(MyPublishModule module) {
        if(module!=null){
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            Log.d(TAG, "uploadScusses: "+module.getBookName());
            bundle.putSerializable("book",module);
            intent.putExtra("book",bundle);
            setResult(RESULT_OK,intent);
            finish();
        }
    }

    @Override
    public void uploadFailed(BaseModule module) {
        if(module!=null){
            Log.d(TAG, "uploadFailed: "+module.getMessage());
            Toast.makeText(this,module.getMessage(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,R.string.simple_update_failed,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.deAttachView();
    }
}
