package com.example.basecomponent.Modules;

import com.google.gson.annotations.SerializedName;

public class MyPublishModule {

    @SerializedName("bookid")
    private int mBookId;

    @SerializedName("bookName")
    private String mBookName;

    @SerializedName("bookType")
    private String mBookType;

    @SerializedName("createTime")
    private String mCreateTime;

    @SerializedName("branchNum")
    private int  mBranchNum;

    @SerializedName("readNum")
    private int mReadNum;

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
}
