package com.example.simplerichtext.RichTextView;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.example.basecomponent.Util;
import com.example.simplerichtext.R;

import java.util.ArrayList;
import java.util.List;



public class RichText extends AppCompatEditText {

    private Context mContext;
    private static final String TAG = "RichText";
    private static String SUB = "\u3000\u3000";
    private textChangeListener mListener;
    private final  int MAX_ADD = 6;
    private final  int MAX_DE = 6;

    private List<String> mAddStrings = new ArrayList<>();
    private List<String> mDeStrings = new ArrayList<>();
    int mAddSize = 0;
    int mDeSize = 0;

    public RichText(Context context) {
        this(context,null);
    }

    public RichText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public RichText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        addTextWatch();
    }

    private void addTextWatch(){
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(mListener!=null){

                    mListener.textChange(Util.getABCCount(s.toString())+
                            Util.getChCount(s.toString()));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setTextChangeListenr(textChangeListener listenr){
        mListener = listenr;
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (lengthBefore != lengthAfter) {
            int startSelection = getSelectionStart();
            setText(text);
            setSelection(startSelection);
        }

    }

    public interface textChangeListener{
        void textChange(int count);
    }


}
