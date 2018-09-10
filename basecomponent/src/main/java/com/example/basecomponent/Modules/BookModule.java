package com.example.basecomponent.Modules;

import com.google.gson.annotations.SerializedName;

public class BookModule {

    @SerializedName("bookId")
    private int mId;

    @SerializedName("bookName")
    private String mBookName;

    @SerializedName("bookType")
    private String mBookType;

    @SerializedName("content")
    private String mContent;

    @SerializedName("bookImage")
    private String mBookImage;

    @SerializedName("createBy")
    private String mCreateBy;

    @SerializedName("createTime")
    private String mCreateTime;

    @SerializedName("focusOnNum")
    private int mFocusOnNum;

    @SerializedName("commentNum")
    private int mCommentNum;

    @SerializedName("branchNum")
    private int mBranchNum;

    @SerializedName("readNum")
    private int mReadNum;

    @SerializedName("allWords")
    private int mAllWords;

    @SerializedName("maxLayer")
    private int mMaxLayer;

    @SerializedName("joinUsers")
    private int mJoinUsers;

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getBookName() {
        return mBookName;
    }

    public void setBookName(String mBookName) {
        this.mBookName = mBookName;
    }

    public String getBookType() {
        return mBookType;
    }

    public void setBookType(String mBookType) {
        this.mBookType = mBookType;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public String getBookImage() {
        return mBookImage;
    }

    public void setBookImage(String mBookImage) {
        this.mBookImage = mBookImage;
    }

    public String getCreateBy() {
        return mCreateBy;
    }

    public void setCreateBy(String mCreateBy) {
        this.mCreateBy = mCreateBy;
    }

    public String getCreateTime() {
        return mCreateTime;
    }

    public void setCreateTime(String mCreateTime) {
        this.mCreateTime = mCreateTime;
    }

    public int getFocusOnNum() {
        return mFocusOnNum;
    }

    public void setFocusOnNum(int mFocusOnNum) {
        this.mFocusOnNum = mFocusOnNum;
    }

    public int getCommentNum() {
        return mCommentNum;
    }

    public void setmCommentNum(int mCommentNum) {
        this.mCommentNum = mCommentNum;
    }

    public int getBranchNum() {
        return mBranchNum;
    }

    public void setBranchNum(int mBranchNum) {
        this.mBranchNum = mBranchNum;
    }

    public int getReadNum() {
        return mReadNum;
    }

    public void setReadNum(int mReadNum) {
        this.mReadNum = mReadNum;
    }

    public int getAllWords() {
        return mAllWords;
    }

    public void setAllWords(int mAllWords) {
        this.mAllWords = mAllWords;
    }

    public int getMaxLayer() {
        return mMaxLayer;
    }

    public void setMaxLayer(int mMaxLayer) {
        this.mMaxLayer = mMaxLayer;
    }

    public int getJoinUsers() {
        return mJoinUsers;
    }

    public void setJoinUsers(int mJoinUsers) {
        this.mJoinUsers = mJoinUsers;
    }
}
