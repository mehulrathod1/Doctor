package com.in.doctor.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.in.doctor.R;
import com.in.doctor.adapter.TransactionHistoryAdapter;
import com.in.doctor.model.TransactionHistoryModel;

import java.util.ArrayList;
import java.util.List;


public class MyRevenue extends Fragment {

    TransactionHistoryAdapter adapter;
    RecyclerView recyclerView;
    List<TransactionHistoryModel> list = new ArrayList<>();

    View view;
    Fragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_revenue, container, false);
        init();
        recyclerData();
        return view;
    }

    @SuppressLint("ResourceAsColor")
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

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.firstFrame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

}