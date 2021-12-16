package com.in.doctor.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.in.doctor.R;
import com.in.doctor.adapter.TransactionHistoryAdapter;
import com.in.doctor.model.TransactionHistoryModel;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory extends Fragment {


    TransactionHistoryAdapter adapter;
    RecyclerView recyclerView;
    List<TransactionHistoryModel> list = new ArrayList<>();
    View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_transaction_history, container, false);

        init();
        recyclerData();

        return view;
    }

    public void init() {
        recyclerView = view.findViewById(R.id.recycler);


    }

    public void recyclerData() {

        TransactionHistoryModel model = new TransactionHistoryModel("9956328", "27/09/2021", "9956328", "$199");
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);


        adapter = new TransactionHistoryAdapter(list, getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

}