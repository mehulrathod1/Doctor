package com.in.doctor.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.in.doctor.R;
import com.in.doctor.activity.Home;
import com.in.doctor.adapter.ManageCalenderAdapter;
import com.in.doctor.adapter.MyQuestionAdapter;
import com.in.doctor.model.ManageCalendarModel;
import com.in.doctor.model.MyQuestionModel;

import java.util.ArrayList;
import java.util.List;

public class MyQuestion extends Fragment {

    ImageView nevBack;
    TextView headerTitle;
    View view;

    MyQuestionAdapter adapter;
    RecyclerView recyclerView;
    List<MyQuestionModel> list = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_question, container, false);
        init();
        recyclerData();
        return view;

    }

    public void init() {

        nevBack = view.findViewById(R.id.nevBack);
        headerTitle = view.findViewById(R.id.header_title);
        recyclerView = view.findViewById(R.id.recycler);

        headerTitle.setText("My Question");


        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Home.class);
                startActivity(intent);
            }
        });

    }

    public void recyclerData() {

        MyQuestionModel model = new MyQuestionModel("Lorem ipsum dolor sit amet, consetetur. ?", "27/09/2021", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea. ", "$199");
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);



        adapter =new MyQuestionAdapter(list);

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

    }

}