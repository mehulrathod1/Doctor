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
import com.in.doctor.adapter.CompletedAssignmentAdapter;
import com.in.doctor.model.BookedAppointmentModel;
import com.in.doctor.model.CompleteAssignmentModel;

import java.util.ArrayList;
import java.util.List;

public class CompletedAssignment extends Fragment {

    ImageView nevBack;
    TextView headerTitle;
    RecyclerView recyclerView;
    CompletedAssignmentAdapter adapter;
    List<CompleteAssignmentModel> list = new ArrayList<>();

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_completed_assignment, container, false);
        init();
        recyclerData();

        return view;
    }

    public void init() {
        nevBack = view.findViewById(R.id.nevBack);
        headerTitle = view.findViewById(R.id.header_title);
        recyclerView = view.findViewById(R.id.recycler);

        headerTitle.setText("Completed Assignment");

        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Home.class);
                startActivity(intent);
            }
        });
    }

    public void recyclerData() {

        CompleteAssignmentModel model = new CompleteAssignmentModel("9956328", "27/09/2021", "$199 ");
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);


        adapter = new CompletedAssignmentAdapter(list, getContext(), new CompletedAssignmentAdapter.Click() {
            @Override
            public void onClickPrescriptionView(int position) {

            }

            @Override
            public void onClickPrescriptionDownload(int position) {

            }

            @Override
            public void onClickInvoiceView(int position) {

            }

            @Override
            public void onClickInvoiceDownload(int position) {

            }

            @Override
            public void onClickDocumentView(int position) {

            }

            @Override
            public void onClickDocumentDownload(int position) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

}