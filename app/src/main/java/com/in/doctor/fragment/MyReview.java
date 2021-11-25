package com.in.doctor.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.in.doctor.R;
import com.in.doctor.activity.Home;
import com.in.doctor.adapter.BookedAppointmentAdapter;
import com.in.doctor.adapter.MyReviewAdapter;
import com.in.doctor.model.BookedAppointmentModel;
import com.in.doctor.model.MyReviewModel;

import java.util.ArrayList;
import java.util.List;

public class MyReview extends Fragment {

    ImageView nevBack;
    TextView headerTitle;
    RecyclerView recyclerView;
    MyReviewAdapter adapter;
    List<MyReviewModel> list = new ArrayList<>();

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_my_review, container, false);
        init();
        recyclerData();
        return view;
    }

    public void init() {
        nevBack = view.findViewById(R.id.nevBack);
        headerTitle = view.findViewById(R.id.header_title);
        recyclerView = view.findViewById(R.id.recycler);

        headerTitle.setText("My Booked Appointment");

        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Home.class);
                startActivity(intent);
            }
        });
    }

    public void recyclerData() {

        MyReviewModel model = new MyReviewModel("", "Lorem ipsum.", "27/09/2021","Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea.");
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);


        adapter = new MyReviewAdapter(list, getContext(), new MyReviewAdapter.Click() {
            @Override
            public void onItemClick(int position) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}