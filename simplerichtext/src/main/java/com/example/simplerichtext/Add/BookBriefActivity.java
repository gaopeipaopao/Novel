package com.example.simplerichtext.Add;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.basecomponent.Util;
import com.example.simplerichtext.Base.BaseActivity;
import com.example.simplerichtext.R;

public class BookBriefActivity extends BaseActivity implements View.OnClickListener ,TextWatcher{

    private ImageView mBack;
    private TextView mSave;
    private EditText mBriefEd;
    private TextView mWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity_book_breif);
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
        mBriefEd.setText(brief);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_save){
            Intent intent = new Intent();
            intent.putExtra("brief",mBriefEd.getText().toString());
            setResult(RESULT_OK,intent);

            finish();
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
}
