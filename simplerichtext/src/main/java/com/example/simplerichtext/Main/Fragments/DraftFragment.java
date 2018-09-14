package com.example.simplerichtext.Main.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.WriteModule;
import com.example.basecomponent.Util;
import com.example.simplerichtext.Main.Adapters.PublishedCaptureAdater;
import com.example.simplerichtext.Main.Presenters.NovelCaputrePresenter;
import com.example.simplerichtext.R;

import java.util.ArrayList;
import java.util.List;

public class DraftFragment extends Fragment implements NovelCaputrePresenter.CaptureViewInterface {

    private View mView;
    private RecyclerView mRecyclerView;
    private PublishedCaptureAdater mAdapter;
    private RelativeLayout mRelativeBg;
    private NovelCaputrePresenter mPresenter;
    private List<WriteModule> mDatas = new ArrayList<>();
    private AddCallBackInterface mAddCall;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.simple_fragment_draft_box,container,false);
        mRecyclerView = mView.findViewById(R.id.recycler_view);
        mAdapter = new PublishedCaptureAdater(getContext(),mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mRelativeBg = mView.findViewById(R.id.fragment_no_data);
        mPresenter = new NovelCaputrePresenter(this);
        getData();
        return mView;
    }

    private void getData(){
        if(Util.isNetworkAvailable(getContext())){
            mPresenter.getData(HttpUtil.STATUS_DRAFT);
        }else {
            Toast.makeText(getContext(),R.string.simple_no_network,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void setDataSucsses(List<WriteModule> modules) {
        if(modules!=null){
            mDatas.clear();
            mDatas.addAll(modules);
            mAdapter.notifyDataSetChanged();
            if(mDatas.size() != 0){
                if(mAddCall!=null){
                    mAddCall.setAdd(false);
                }
                mRelativeBg.setVisibility(View.GONE);
            }else {
                if(mAddCall!=null){
                    mAddCall.setAdd(true);
                }

                mRelativeBg.setVisibility(View.VISIBLE);
            }
        }else {
            Toast.makeText(getContext(),R.string.simple_getdata_failed,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getDataFailed(BaseModule module) {
        if(module!=null){
            Toast.makeText(getContext(),module.getMessage(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(),R.string.simple_getdata_failed,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
        if(activity instanceof AddCallBackInterface){
            mAddCall = (AddCallBackInterface)activity;
        }
    }

    public interface AddCallBackInterface{
        void setAdd(boolean canAdd);
    }
}
