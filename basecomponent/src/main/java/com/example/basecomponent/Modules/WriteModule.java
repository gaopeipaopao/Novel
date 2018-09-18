package com.example.basecomponent.Modules;

import android.widget.LinearLayout;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WriteModule {

    @SerializedName("simpleBookDTO")
    private simpleBook mSimpleBook;

    @SerializedName("myWriteBranchDTOS")
    private List<myWrite> mMyWrites;

    public class simpleBook{

        @SerializedName("bookId")
        private int mBookId;

        @SerializedName("bookName")
        private String mBookName;

        @SerializedName("bookType")
        private String mBookType;

        @SerializedName("bookImage")
        private String mBookImage;

        @SerializedName("createTime")
        private String mCreateTime;

        @SerializedName("branchNum")
        private String mBranchNum;

        @SerializedName("readNum")
        private String mReadNum;

        @SerializedName("author")
        private AuthorModule mAuthor;

        public int getmBookId() {
            return mBookId;
        }

        public void setmBookId(int mBookId) {
            this.mBookId = mBookId;
        }

        public String getmBookName() {
            return mBookName;
        }

        public void setmBookName(String mBookName) {
            this.mBookName = mBookName;
        }

        public String getmBookType() {
            return mBookType;
        }

        public void setmBookType(String mBookType) {
            this.mBookType = mBookType;
        }

        public String getmBookImage() {
            return mBookImage;
        }

        public void setmBookImage(String mBookImage) {
            this.mBookImage = mBookImage;
        }

        public String getmCreateTime() {
            return mCreateTime;
        }

        public void setmCreateTime(String mCreateTime) {
            this.mCreateTime = mCreateTime;
        }

        public String getmBranchNum() {
            return mBranchNum;
        }

        public void setmBranchNum(String mBranchNum) {
            this.mBranchNum = mBranchNum;
        }

        public String getmReadNum() {
            return mReadNum;
        }

        public void setmReadNum(String mReadNum) {
            this.mReadNum = mReadNum;
        }

        public AuthorModule getmAuthor() {
            return mAuthor;
        }

        public void setmAuthor(AuthorModule mAuthor) {
            this.mAuthor = mAuthor;
        }
    }

    public class myWrite{

        @SerializedName("branchId")
        private int mBranchId;

        @SerializedName("title")
        private String mTitle;

        @SerializedName("createTime")
        private String mCreateTime;

        @SerializedName("summary")
        private String mSummary;

        @SerializedName("likeNum")
        private int mLikeNum;

        @SerializedName("dislikeNum")
        private int mDislikeNum;

        public int getmBranchId() {
            return mBranchId;
        }

        public void setmBranchId(int mBranchId) {
            this.mBranchId = mBranchId;
        }

        public String getmTitle() {
            return mTitle;
        }

        public void setmTitle(String mTitle) {
            this.mTitle = mTitle;
        }

        public String getmCreateTime() {
            return mCreateTime;
        }

        public void setmCreateTime(String mCreateTime) {
            this.mCreateTime = mCreateTime;
        }

        public String getmSummary() {
            return mSummary;
        }

        public void setmSummary(String mSummary) {
            this.mSummary = mSummary;
        }

        public int getmLikeNum() {
            return mLikeNum;
        }

        public void setmLikeNum(int mLikeNum) {
            this.mLikeNum = mLikeNum;
        }

        public int getmDislikeNum() {
            return mDislikeNum;
        }

        public void setmDislikeNum(int mDislikeNum) {
            this.mDislikeNum = mDislikeNum;
        }
    }

    public simpleBook getmSimpleBook() {
        return mSimpleBook;
    }

    public void setmSimpleBook(simpleBook mSimpleBook) {
        this.mSimpleBook = mSimpleBook;
    }

    public List<myWrite> getmMyWrites() {
        return mMyWrites;
    }

    public void setmMyWrites(List<myWrite> mMyWrites) {
        this.mMyWrites = mMyWrites;
    }
}
