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
}
