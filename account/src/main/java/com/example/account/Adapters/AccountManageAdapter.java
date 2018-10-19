package com.example.account.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.account.Holders.AccountViewHolder;
import com.example.account.Models.AccountModel;
import com.example.account.R;

import java.util.List;

public class AccountManageAdapter extends RecyclerView.Adapter<AccountViewHolder> {

    private Context mContext;
    private List<AccountModel> mDatas;

    public static final int ACCOUNT = 0;
    public static final int ADD_ACCOUNT = 1;
    public static final int QUIT_ACCOUNT = 2;

    public AccountManageAdapter(Context context, List<AccountModel> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        if(position<mDatas.size()){
            return ACCOUNT;
        }else if(position == mDatas.size()){
            return ADD_ACCOUNT;
        }else {
            return QUIT_ACCOUNT;
        }
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == ACCOUNT){
            view = LayoutInflater.from(mContext).inflate(R.layout.account_layout_account_manage_item
                    ,parent,false);
        }else if(viewType == ADD_ACCOUNT){
            view = LayoutInflater.from(mContext).inflate(R.layout.account_layout_account_mamage_add_account
                    ,parent,false);
        }else {
            view = LayoutInflater.from(mContext).inflate(R.layout.account_layout_account_manage_quit_item
                    ,parent,false);
        }

        return new AccountViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size()+2;
    }
}
