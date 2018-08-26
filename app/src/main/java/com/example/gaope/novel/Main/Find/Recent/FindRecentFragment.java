package com.example.gaope.novel.Main.Find.Recent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gaope.novel.Adapter.BookRecyclerAdapter;
import com.example.gaope.novel.Adapter.FindRecyclerAdapter;
import com.example.gaope.novel.R;

@SuppressLint("ValidFragment")
public class FindRecentFragment extends Fragment {

    private RecyclerView recyclerView;
    private FindRecyclerAdapter acappter;
    private Context context;

    public FindRecentFragment(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_recycler,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        Log.d("agagagg","afaffa");
        init();

        return view;
    }

    private void init(){
        Log.d("agagagg","afaffa");
        acappter = new FindRecyclerAdapter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(acappter);

    }
}
