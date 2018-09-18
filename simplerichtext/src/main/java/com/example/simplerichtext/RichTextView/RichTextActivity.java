package com.example.simplerichtext.RichTextView;


import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Excutes.AddExcute;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.simplerichtext.R;
import com.example.simplerichtext.Util.ConstantUtil;
import com.example.simplerichtext.Util.DialogUtil;


public class RichTextActivity extends AppCompatActivity implements
        ViewTreeObserver.OnGlobalLayoutListener,
        View.OnClickListener ,View.OnTouchListener,SettingPopuWindow.callBack ,
        RichTextPresenter.RichTextViewInterface{

    private RichText mEditCapture;
    private static String SUB = "\u3000\u3000";
    private final  int CAPUTRE_BRIEF = 200;
    private static final String TAG = "RichTextActivity";
    private int mScreenHeight;
    private int mScreenWidth;
    private boolean mKeyboardOpened = false;
    private  View content ;
    private EditText mEditTitle;
    private InputMethodManager mInputManager;
    private LinearScrollView mScrollView;
    private LinearLayout mBottom_1;
    private LinearLayout mBottom_2;
    private LinearLayout mRichRoot;
    private int mEditType = 0;

    private ImageView mBack;
    private TextView mTextCount;
    private ImageView mUndo;
    private ImageView mRedo;
    private TextView mPulish;
    private TextView mCapture;

    private ImageView mDustbin;
    private ImageView mSetting;
    private ImageView mWrite;
    private ImageView mSave;
    private ImageView mRecordInput;
    private ImageView mComma;
    private ImageView mFullSport;
    private ImageView mColon;
    private ImageView mQuotaition;
    private ImageView mSoftDwon;

    private TextView mCaptureBrief;
    private String mCaptureNum;
    private String mCapureString = "";

    private RelativeLayout mRelaTitle;
    private boolean mDrak = false;

    private static final int EIDT_CAPURE = 1;
    private static final int EIDT_TITLE = 2;
    public static int mIndex = 3;
    private String mStatus;
    private MyPublishModule mBook;
    private RichTextPresenter mPresenter;
    private int mParentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity_richtext);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        mScreenHeight = metrics.heightPixels;
        mScreenWidth = metrics.widthPixels;
        content = this.findViewById(android.R.id.content);
        content.getViewTreeObserver().addOnGlobalLayoutListener(this);
        mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mCaptureNum = getIntent().getStringExtra("capture");
        Bundle bundle = getIntent().getBundleExtra("book");
        mBook = (MyPublishModule) bundle.getSerializable("book");
        mParentId = getIntent().getIntExtra("parent",-1);
        if(mBook!=null){
            mStatus = mBook.getStatus();
            Log.d(TAG, "onCreate: "+mBook.getBookType());
        }
        mPresenter = new RichTextPresenter(this);
        setWindow();
        init();
        if(mStatus.equals(HttpUtil.STATUS_UNPUBLISHED)){
            getUnData();
            showLoading();
            mCapureString = mBook.getFirstSummaray();
        }

    }


    protected void setWindow(){
        Window window  = getWindow();
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){

            View decorView = window.getDecorView();
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

                );
            }else {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                );
            }
        }else {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }

        window.setStatusBarColor(Color.TRANSPARENT);
    }


    private void init(){
        mEditCapture = findViewById(R.id.et_capture);
        mEditCapture.setText(SUB);
        mEditCapture.setSelection(mEditCapture.length());
        mEditTitle = findViewById(R.id.et_title);
        mEditTitle.setOnClickListener(this);
        mScrollView = findViewById(R.id.ll_scrollView);
        mRichRoot = findViewById(R.id.ll_richroot);
        mEditCapture.setOnTouchListener(this);
        mEditCapture.setOnClickListener(this);
        mScrollView.setOnClickListener(this);
        mBack = findViewById(R.id.iv_back);
        mBack.setOnClickListener(this);
        mTextCount = findViewById(R.id.tv_count);
        mUndo = findViewById(R.id.iv_edit_undo);
        mUndo.setOnClickListener(this);
        mRedo = findViewById(R.id.iv_edit_redo);
        mRedo.setOnClickListener(this);
        mPulish = findViewById(R.id.tv_publish);
        mPulish.setOnClickListener(this);
        mDustbin = findViewById(R.id.iv_dustbin);
        mDustbin.setOnClickListener(this);
        mSetting = findViewById(R.id.iv_setting);
        mSetting.setOnClickListener(this);
        mWrite = findViewById(R.id.iv_write);
        mWrite.setOnClickListener(this);
        mSave = findViewById(R.id.iv_save);
        mSave.setOnClickListener(this);
        mRecordInput = findViewById(R.id.iv_record);
        mRecordInput.setOnClickListener(this);
        mComma = findViewById(R.id.iv_comma);
        mComma.setOnClickListener(this);
        mFullSport = findViewById(R.id.iv_period);
        mFullSport.setOnClickListener(this);
        mColon = findViewById(R.id.iv_colon);
        mColon.setOnClickListener(this);
        mQuotaition = findViewById(R.id.iv_quotation);
        mQuotaition.setOnClickListener(this);
        mSoftDwon = findViewById(R.id.iv_close_soft);
        mSoftDwon.setOnClickListener(this);
        mBottom_1 = findViewById(R.id.ll_bottom_1);
        mBottom_2 = findViewById(R.id.ll_bottom_2);
        mEditCapture.setTextChangeListenr(mTextChangeListener);
        mRelaTitle = findViewById(R.id.rela_title);
        mCaptureBrief = findViewById(R.id.tv_capture_brief);
        mCaptureBrief.setOnClickListener(this);
        mCapture = findViewById(R.id.tv_capture);
        mCapture.setText("第"+mCaptureNum+"章");
        mEditCapture.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction()
                        == KeyEvent.ACTION_DOWN){
                    int selection = mEditCapture.getSelectionEnd();
                    int prelength = mEditCapture.getText().length();
                    mEditCapture.setText(mEditCapture.getText().insert(selection,
                             "\r\n"+
                            SUB));
                    if(selection!=prelength){
                        mEditCapture.setSelection(selection+"\r\n".length()+4);
                    }else {
                        mEditCapture.setSelection(mEditCapture.length());
                    }

                    return  true;
                }
                return false;
            }
        });
    }

    public void getUnData(){
        mPresenter.getUnpublishedData(mBook);
    }

    public void showLoading(){

    }

    public void dismissLoading(){

    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onGlobalLayout() {
        Rect rect = new Rect();
        content.getWindowVisibleDisplayFrame(rect);
        int heightOff = content.getRootView().getHeight()-(rect.bottom- rect.top);
        int height;
        if (mScreenHeight>=1920){
            height = 200;
        }else {
            height = 100;
        }
        
        if(!mKeyboardOpened && height<heightOff){
            mKeyboardOpened = true;
            content.setPadding(0,0,0,mScreenHeight-rect.bottom);
            keyboardOpen();
        }else if(mKeyboardOpened && heightOff<height){
            mKeyboardOpened = false;
            content.setPadding(0,0,0,0);
            keyboardClose();
        }
    }

    @Override
    public void onClick(View v) {

            if (v.getId() == R.id.et_capture ||
                    v.getId() == R.id.ll_richroot ||
                    v.getId() == R.id.iv_write) {
                mEditType = EIDT_CAPURE;
                mEditCapture.setFocusable(true);
                mEditCapture.setFocusableInTouchMode(true);
                mEditCapture.requestFocus();
                mEditCapture.findFocus();
                openKeyboard();
            } else if (v.getId() == R.id.et_title) {
                mEditType = EIDT_TITLE;
                mEditTitle.setFocusable(true);
                mEditTitle.setFocusableInTouchMode(true);
                mEditTitle.requestFocus();
                mEditTitle.findFocus();
                openKeyboard();
            } else if (v.getId() == R.id.iv_back) {
                back();
            } else if (v.getId() == R.id.iv_dustbin) {
                dusbtin();
            } else if (v.getId() == R.id.iv_setting) {

                setting();
            } else if (v.getId() == R.id.iv_save) {
                saveCapure();
            } else if (v.getId() == R.id.iv_record) {

            }else if(v.getId() == R.id.iv_comma) {

                addPunctuation(getResources().getString(R.string.simple_comma));
            }else if(v.getId() ==R.id.iv_period ) {

                addPunctuation(getResources().getString(R.string.simple_fullSpot));
            }else if(v.getId() == R.id.iv_colon) {

                addPunctuation(getResources().getString(R.string.simple_colon));
            }else if(v.getId() ==R.id.iv_quotation ) {

                addPunctuation(getResources().getString(R.string.simple_quotation));
            }else if(v.getId() == R.id.iv_close_soft) {

                hindKeyboard();
                //keyboardClose();
            }else if(v.getId() ==R.id.tv_publish ) {

                publish();
            }else if(v.getId() == R.id.tv_capture_brief){

                Intent intent = new Intent(this,CaptureBriefActivity.class);
                intent.putExtra("brief",mCapureString);
                startActivityForResult(intent,CAPUTRE_BRIEF);
            }

    }

    @Override
    public void saveUnPublishedSuceese(BaseModule<MyPublishModule> module) {

        if(module!=null){
            mBook = module.getData();
            Toast.makeText(this,R.string.simple_save_scueese,
                    Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this,R.string.simple_save_fail,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void saveUnPublishedFailed(BaseModule module) {
        if(module!=null){

            Toast.makeText(this,module.getMessage(),
                    Toast.LENGTH_SHORT).show();

            Log.d(TAG, "saveUnPublishedFailed: "+module.getMessage());

        }else {
            Toast.makeText(this,R.string.simple_save_fail,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void savePublishedSuceese(BaseModule<MyPublishModule> module) {
        if(module!=null){
            mBook = module.getData();
            Toast.makeText(this,R.string.simple_save_scueese,
                    Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this,R.string.simple_save_fail,
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void savePublishedFailed(BaseModule module) {
        if(module!=null){

            Toast.makeText(this,module.getMessage(),
                    Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this,R.string.simple_save_fail,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void publishBookSuceese(BaseModule<MyPublishModule> module) {

        if(module!=null){
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            module.getData().setStatus(HttpUtil.STATUS_PUBLISHED);
            bundle.putSerializable("book",module.getData());
            intent.putExtra("book",bundle);
            setResult(RESULT_OK,intent);
            Toast.makeText(this,R.string.simple_publish_scueese,
                    Toast.LENGTH_SHORT).show();
            finish();

        }else {
            Toast.makeText(this,R.string.simple_publish_failed,
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void publishBookFailed(BaseModule module) {
        if(module!=null){

            Toast.makeText(this,module.getMessage(),
                    Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this,R.string.simple_publish_failed,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void publishCaptureSuceese(BaseModule module) {

    }

    @Override
    public void publishCaptureFailed(BaseModule module) {
        if(module!=null){

            Toast.makeText(this,module.getMessage(),
                    Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this,R.string.simple_publish_failed,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getUnPublishedDataSuceese(BaseModule<MyPublishModule> module) {
        if(module!=null){
            mBook.setFirstTitle(module.getData().getFirstTitle());
            mBook.setFirstContent(module.getData().getFirstContent());
            mBook.setFirstSummaray(module.getData().getFirstSummaray());
            mCapureString = mBook.getFirstSummaray();
            if(mBook.getFirstTitle()!=null&&!mBook.getFirstTitle().equals("")){
                mEditTitle.setText(mBook.getFirstTitle());
            }
            if(mBook.getFirstContent()!=null&&!mBook.getFirstContent().equals("")) {
                mEditCapture.setText(mBook.getFirstContent());
            }
            dismissLoading();
        }else {
            Toast.makeText(this,R.string.simple_getdata_failed,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getUnPublishedDataFailed(BaseModule module) {
        if(module!=null){
            Toast.makeText(this,module.getMessage(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,R.string.simple_getdata_failed,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v.getId() == R.id.et_capture && mEditCapture.canScrollVertically(0)){
            v.getParent().requestDisallowInterceptTouchEvent(true);
            if(event.getAction() == MotionEvent.ACTION_UP){
                v.getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAPUTRE_BRIEF && resultCode == RESULT_OK){
            mCapureString = data.getStringExtra("brief");
        }
    }

    private void keyboardOpen(){
        Log.d(TAG, "keyboardOpen: ");
        mBottom_1.setVisibility(View.GONE);
        mBottom_2.setVisibility(View.VISIBLE);
        if(mEditType == EIDT_CAPURE){
            mEditCapture.requestFocus();
        }else if (mEditType == EIDT_TITLE){
            mEditTitle.requestFocus();
        }

    }

    private void hindKeyboard(){

        mInputManager.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(),
                0);
    }
    
    private void keyboardClose(){
        Log.d(TAG, "keyboardClose: ");
        mBottom_2.setVisibility(View.GONE);
        mBottom_1.setVisibility(View.VISIBLE);
        mEditCapture.setFocusable(false);
        mEditTitle.setFocusable(false);
        mEditType = 0;
        mScrollView.setFocusable(true);
        mScrollView.setFocusableInTouchMode(true);
    }

    private void openKeyboard(){
        if(!mKeyboardOpened){
            mInputManager.showSoftInput(mEditCapture,InputMethodManager.SHOW_FORCED);
        }

    }

    private RichText.textChangeListener mTextChangeListener = new RichText.textChangeListener() {
        @Override
        public void textChange(int count) {

            mTextCount.setText(count+getResources().getString(R.string.simple_count));
        }
    };

    private void back(){
        String s = mTextCount.getText().toString();
        int count = Integer.valueOf(s.split(getResources()
                .getString(R.string.simple_count))[0]);
        String title = mEditTitle.getText().toString();
        String content = mEditCapture.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("count",count);
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        setResult(RESULT_OK,intent);
        finish();

    }

    private void dusbtin(){
        if(mStatus.equals(HttpUtil.STATUS_PUBLISHED)){
            AlertDialog alertDialog = DialogUtil.CreateNomalDialog(this,
                    getResources().getString(R.string.simple_dustin_title),
                    getResources().getString(R.string.simple_dustin_meassage),
                    true, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(which == DialogInterface.BUTTON_POSITIVE){

                            }else if(which == DialogInterface.BUTTON_NEGATIVE){

                            }
                        }
                    });
            alertDialog.show();
        }else if(mStatus .equals( HttpUtil.STATUS_UNPUBLISHED)) {
            Toast.makeText(this,R.string.simple_cannot_delete,
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void setting(){

        final SettingPopuWindow popupWindow = new SettingPopuWindow(this,
                mScreenWidth-20,
                WindowManager.LayoutParams.WRAP_CONTENT );
        Log.d(TAG, "setting: "+mIndex);
        popupWindow.showAtLocation(content,Gravity.BOTTOM|Gravity.CENTER,
                0,20);

        popupWindow.setCallback(this);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ConstantUtil.mColorSelect = popupWindow.getNowPick();
                ConstantUtil.mFontIndex = popupWindow.getFontIndex();
                backgroundAlpha(1.0f);
            }
        });
        backgroundAlpha(0.5f);

    }

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;// 0.0-1.0
        getWindow().setAttributes(lp);
    }


    private void saveCapure(){
        String captureName = mEditTitle.getText().toString();
        if(!captureName.equals("")&& captureName.length()>0
                &&captureName.length()<=15){
            String content = mEditCapture.getText().toString();

                    Log.d(TAG, "saveCapure: "+mBook.getStatus());
                    if(mStatus.equals(HttpUtil.STATUS_PUBLISHED)){
                        Log.d(TAG, "saveCapure: "+mBook.getStatus());

                    }else if(mStatus.equals(HttpUtil.STATUS_UNPUBLISHED)){

                        Log.d(TAG, "saveCapure: "+mBook.getBookType());
                        mBook.setFirstTitle(captureName);
                        mBook.setFirstContent(content);
                        mBook.setFirstSummaray(mCapureString);
                        mPresenter.saveUnPublished(mBook);

                }
            }else {
                Toast.makeText(this,R.string.simple_capture_limit,
                        Toast.LENGTH_SHORT).show();
            }

    }

    private void publish(){
            String captureName = mEditTitle.getText().toString();
            if(!captureName.equals("")&& captureName.length()>0&&captureName.length()<=15){
                String content = mEditCapture.getText().toString();
                if(!content.equals("")&&content.length()>=200){
                    if(!mCapureString.equals("")&&mCapureString.length()>10){
                        mBook.setFirstTitle(captureName);
                        mBook.setFirstContent(content);
                        mBook.setFirstSummaray(mCapureString);
                        mPresenter.publish(mBook,mParentId);

                    }else {
                        Toast.makeText(this,R.string.simple_brief_limit,
                                Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this,R.string.simple_capture_limit,
                            Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this,R.string.simple_title_limit,Toast.LENGTH_SHORT).show();
            }

    }

    private void addPunctuation(String pun){
        mEditCapture.setText(mEditCapture.getText().toString()+pun);
        if(pun.equals(getResources().getString(R.string.simple_quotation))){
            mEditCapture.setSelection(mEditCapture.length()-1);
        }else {
            mEditCapture.setSelection(mEditCapture.length());
        }

    }

    public void setScrennManualMode() {
        ContentResolver contentResolver = getContentResolver();
        try {
            int mode = Settings.System.getInt(contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE);
            if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setLight(float light) {

         Window window = getWindow();
         WindowManager.LayoutParams lp = window.getAttributes();
         lp.screenBrightness = light;
         window.setAttributes(lp);

    }

    @Override
    public void setFontSize(float font,int index) {
        //mIndex = index;
        mEditCapture.setTextSize(font);
    }

    @Override
    public void changeBackgound(int id) {
        if(id == R.id.iv_white) {

            setBackgroudColor(getResources().getColor(R.color.simple_white)
                    , getResources().getColor(R.color.simple_white), false);
        }else if(id == R.id.iv_pink) {

            setBackgroudColor(getResources().getColor(R.color.simple_pink)
                    , getResources().getColor(R.color.simple_pink_less), false);
        }else if(id == R.id.iv_yellow) {

            setBackgroudColor(getResources().getColor(R.color.simple_orange)
                    , getResources().getColor(R.color.simple_orage_less), false);
        }else if(id == R.id.iv_green) {

            setBackgroudColor(getResources().getColor(R.color.simple_green)
                    , getResources().getColor(R.color.simple_green_less), false);
        }else if(id == R.id.iv_bule) {

            setBackgroudColor(getResources().getColor(R.color.simple_blue)
                    , getResources().getColor(R.color.simple_blue_less), false);
        }else if(id == R.id.iv_black){

            setBackgroudColor(getResources().getColor(R.color.simple_balck)
                        ,getResources().getColor(R.color.simple_black_less),true);
        }
    }

    private void setBackgroudColor(int mianColor,int titleColor,boolean black){
        if(!black){
            mRelaTitle.setBackgroundColor(titleColor);
            mScrollView.setBackgroundColor(mianColor);
            mBottom_1.setBackgroundColor(titleColor);
            if(mDrak){
                mBack.setImageResource(R.mipmap.simple_ic_back);
                mTextCount.setTextColor(getResources().getColor(R.color.simple_text_color));
                mPulish.setTextColor(getResources().getColor(R.color.simple_text_color));
                mEditCapture.setTextColor(getResources().getColor(R.color.simple_text_color));
                mEditTitle.setTextColor(getResources().getColor(R.color.simple_text_color));
               // mEditTitle.setHintTextColor(getResources().getColor(R.color.text_color));
                mDustbin.setImageResource(R.mipmap.simple_ic_bar_trash);
                mSetting.setImageResource(R.mipmap.simple_ic_setting);
                mWrite.setImageResource(R.mipmap.simple_ic_write);
                mSave.setImageResource(R.mipmap.simple_ic_save);
                mDrak = false;
            }
        }else {
            mRelaTitle.setBackgroundColor(titleColor);
            mScrollView.setBackgroundColor(mianColor);
            mBottom_1.setBackgroundColor(titleColor);
            mBack.setImageResource(R.mipmap.simple_ic_back_dark);
            mTextCount.setTextColor(getResources().getColor(R.color.simple_white));
            mPulish.setTextColor(getResources().getColor(R.color.simple_white));
            mEditCapture.setTextColor(getResources().getColor(R.color.simple_white));
            mEditTitle.setTextColor(getResources().getColor(R.color.simple_white));
            //mEditTitle.setHintTextColor(getResources().getColor(R.color.white));
            mDustbin.setImageResource(R.mipmap.simple_ic_bar_trash_dark);
            mSetting.setImageResource(R.mipmap.simple_ic_settings_dark);
            mWrite.setImageResource(R.mipmap.simple_ic_write_dark);
            mSave.setImageResource(R.mipmap.simple_ic_save_dark);
            mDrak = true;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setWindow();
    }

    @Override
    protected void onDestroy() {
        mPresenter.dettachView();
        super.onDestroy();
    }
}
