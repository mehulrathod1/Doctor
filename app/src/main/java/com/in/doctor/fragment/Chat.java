package com.in.doctor.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.in.doctor.R;
import com.in.doctor.activity.ChatDashboard;
import com.in.doctor.adapter.ChatAdapter;
import com.in.doctor.model.ChatModel;

import java.util.ArrayList;
import java.util.List;

public class Chat extends Fragment {

    ChatAdapter adapter;
    RecyclerView recyclerView;
    List<ChatModel> list = new ArrayList<>();
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        init();
        recyclerData();
        return view;
    }

    public void init() {

        recyclerView = view.findViewById(R.id.recycler);

    }

    public void recyclerData() {

        ChatModel model = new ChatModel("", "Lorem ipsum", "Lorem ipsum dolor sit amet,");
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);

        adapter = new ChatAdapter(list, getContext(), new ChatAdapter.Click() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), ChatDashboard.class);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

}