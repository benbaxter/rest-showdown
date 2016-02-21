package com.benjamingbaxter.beer.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benjamingbaxter.beer.android.R;
import com.benjamingbaxter.beer.android.ui.adapter.BeerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class BeerFragment extends Fragment {

    @Bind(R.id.beers)
    RecyclerView recyclerView;
    protected BeerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new BeerAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
}
