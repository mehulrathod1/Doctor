package com.in.doctor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.in.doctor.R;
import com.in.doctor.adapter.CompletedAssignmentAdapter;
import com.in.doctor.model.CompleteAssignmentModel;

import java.util.ArrayList;
import java.util.List;

public class CompletedAssignment extends AppCompatActivity {

    ImageView nevBack;
    TextView headerTitle;
    RecyclerView recyclerView;
    CompletedAssignmentAdapter adapter;
    List<CompleteAssignmentModel> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_assignment);
        getSupportActionBar().hide();
        init();
        recyclerData();
    }


    public void init() {
        nevBack = findViewById(R.id.nevBack);
        headerTitle = findViewById(R.id.header_title);
        recyclerView = findViewById(R.id.recycler);

        headerTitle.setText("Completed Assignment");

        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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


        adapter = new CompletedAssignmentAdapter(list, this, new CompletedAssignmentAdapter.Click() {
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

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

}