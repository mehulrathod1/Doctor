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
import android.widget.Toast;

import com.in.doctor.R;
import com.in.doctor.activity.Home;
import com.in.doctor.adapter.ManageBookingAdapter;
import com.in.doctor.adapter.ManageCalenderAdapter;
import com.in.doctor.model.ManageBookingModel;
import com.in.doctor.model.ManageCalendarModel;

import java.util.ArrayList;
import java.util.List;


public class ManageBooking extends Fragment {

    ImageView nevBack;
    TextView headerTitle;

    ManageBookingAdapter adapter;
    RecyclerView recyclerView;
    List<ManageBookingModel> list = new ArrayList<>();


    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_manage_booking, container, false);

        init();
        recyclerData();
        return view;
    }

    public void init() {
        nevBack = view.findViewById(R.id.nevBack);
        headerTitle = view.findViewById(R.id.header_title);
        recyclerView = view.findViewById(R.id.recycler);


        headerTitle.setText("Manage Calendar");


        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Home.class);
                startActivity(intent);
            }
        });

    }

    public void recyclerData() {

        ManageBookingModel model = new ManageBookingModel("9956328", "27/09/2021", "video consult", "02:30");
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);

        adapter = new ManageBookingAdapter(list, getContext(), new ManageBookingAdapter.Click() {
            @Override
            public void onClickAccept(int position) {
                Toast.makeText(getContext(), "dfghjhgfd", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onClickCancel(int position) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

}