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
import com.in.doctor.global.Glob;
import com.in.doctor.model.CompleteAssignmentModel;
import com.in.doctor.model.GetFcmTokenModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompletedAssignment extends AppCompatActivity {

    ImageView nevBack;
    TextView headerTitle;
    RecyclerView recyclerView;
    CompletedAssignmentAdapter adapter;
    List<CompleteAssignmentModel.Assignment> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_assignment);
        getSupportActionBar().hide();
        init();
        getCompleteAssignment(Glob.Token, Glob.user_id);
    }


    public void init() {

        Glob.progressDialog(this);
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


    public void getCompleteAssignment(String token, String doctorID) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();

        call.getCompletedAssignment(token, doctorID).enqueue(new Callback<CompleteAssignmentModel>() {
            @Override
            public void onResponse(Call<CompleteAssignmentModel> call, Response<CompleteAssignmentModel> response) {

                CompleteAssignmentModel model = response.body();

                List<CompleteAssignmentModel.Assignment> dataList = model.getCompleteAssignment();

                for (int i = 0; i < dataList.size(); i++) {

                    CompleteAssignmentModel.Assignment data = dataList.get(i);
                    CompleteAssignmentModel.Assignment dataa = new CompleteAssignmentModel.Assignment(data.getBooking_id(),
                            data.getAppointment_date(), data.getAmount_paid() + "  ₹", data.getPatient_id(), data.getPatient_document());
                    list.add(dataa);
                    Glob.dialog.dismiss();
                }
                recyclerData();
            }

            @Override
            public void onFailure(Call<CompleteAssignmentModel> call, Throwable t) {

                Glob.dialog.dismiss();
            }
        });
    }

    public void recyclerData() {


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