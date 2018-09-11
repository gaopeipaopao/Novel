package com.example.basecomponent.Modules;

import com.google.gson.annotations.SerializedName;

public class MyPublishModule {

    @SerializedName("bookId")
    private int mBookId;

    @SerializedName("bookName")
    private String mBookName;

    @SerializedName("bookType")
    private String mBookType;

    @SerializedName("bookImage")
    private String mBookCover;

    @SerializedName("createTime")
    private String mCreateTime;

    @SerializedName("focusOnNum")
    private int mFocusOnNum;

    @SerializedName("commentNum")
    private int mCommentNum;

    @SerializedName("branchNum")
    private int  mBranchNum;

    @SerializedName("readNum")
    private int mReadNum;

    @SerializedName("allWords")
    private int mAllWords;

    @SerializedName("maxLayer")
    private int mMaxLayer;

    @SerializedName("joinUsers")
    private int mJoinUsers;

    @SerializedName("content")
    private String mContent;

    @SerializedName("status")
    private String mStatus;

    @SerializedName("author")
    private AuthorModule mAuthor;

    public int getBookId() {
        return mBookId;
    }

    public String getBookName() {
        return mBookName;
    }

    public String getBookType() {
        return mBookType;
    }

    public String getCreateTime() {
        return mCreateTime;
    }

    public int getBranchNum() {
        return mBranchNum;
    }

    public int getReadNum() {
        return mReadNum;
    }

    public String getContent() {
        return mContent;
    }

    public AuthorModule getAuthor() {
        return mAuthor;
    }

    public String getBookCover() {
        return mBookCover;
    }

    public int getFocusOnNum() {
        return mFocusOnNum;
    }

    public int getCommentNum() {
        return mCommentNum;
    }

    public int getAllWords() {
        return mAllWords;
    }

    public int getMaxLayer() {
        return mMaxLayer;
    }

    public int getJoinUsers() {
        return mJoinUsers;
    }


    public void setBookId(int mBookId) {
        this.mBookId = mBookId;
    }

    public void setBookName(String mBookName) {
        this.mBookName = mBookName;
    }

    public void setBookType(String mBookType) {
        this.mBookType = mBookType;
    }

    public void setBookCover(String mBookCover) {
        this.mBookCover = mBookCover;
    }

    public void setCreateTime(String mCreateTime) {
        this.mCreateTime = mCreateTime;
    }

    public void setFocusOnNum(int mFocusOnNum) {
        this.mFocusOnNum = mFocusOnNum;
    }

    public void setCommentNum(int mCommentNum) {
        this.mCommentNum = mCommentNum;
    }

    public void setBranchNum(int mBranchNum) {
        this.mBranchNum = mBranchNum;
    }

    public void setmReadNum(int mReadNum) {
        this.mReadNum = mReadNum;
    }

    public void setAllWords(int mAllWords) {
        this.mAllWords = mAllWords;
    }

    public void setMaxLayer(int mMaxLayer) {
        this.mMaxLayer = mMaxLayer;
    }

    public void setmJoinUsers(int mJoinUsers) {
        this.mJoinUsers = mJoinUsers;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public void setAuthor(AuthorModule mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }
}
