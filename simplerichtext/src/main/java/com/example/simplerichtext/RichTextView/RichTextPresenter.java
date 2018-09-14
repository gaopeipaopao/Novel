package com.example.simplerichtext.RichTextView;

public class RichTextPresenter {


    private RichTextModelInterface mModle;
    private RichTextPresenter mView;

    public RichTextPresenter(RichTextPresenter view) {
        this.mView = view;
    }

    public interface RichTextViewInterface{

    }

    public interface RichTextModelInterface{

    }
}
