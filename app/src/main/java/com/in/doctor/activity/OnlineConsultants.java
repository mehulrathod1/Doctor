package com.in.doctor.activity;

import static com.in.doctor.global.Glob.Token;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.in.doctor.R;
import com.in.doctor.adapter.OnlineConsultantAdapter;
import com.in.doctor.global.Glob;
import com.in.doctor.model.ManageBookingModel;
import com.in.doctor.model.OnlineConsultantModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineConsultants extends AppCompatActivity {

    ImageView nevBack;
    TextView headerTitle;

    OnlineConsultantAdapter adapter;
    RecyclerView recyclerView;
    List<OnlineConsultantModel.Consultant> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_consultants);
        getSupportActionBar().hide();

        init();
        getOnlineConsultant(Token, "13");
    }

    public void init() {

        nevBack = findViewById(R.id.nevBack);
        headerTitle = findViewById(R.id.header_title);
        recyclerView = findViewById(R.id.recycler);

        headerTitle.setText("Online Consultant");

        Glob.progressDialog(this);


        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

    }

    public void getOnlineConsultant(String token, String doctor_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();

        call.getOnlineConsultant(token, doctor_id).enqueue(new Callback<OnlineConsultantModel>() {
            @Override
            public void onResponse(Call<OnlineConsultantModel> call, Response<OnlineConsultantModel> response) {

                OnlineConsultantModel onlineConsultantModel = response.body();

                List<OnlineConsultantModel.Consultant> dataList = onlineConsultantModel.getConsultantList();
                for (int i = 0; i < dataList.size(); i++) {

                    OnlineConsultantModel.Consultant model = dataList.get(i);


                    OnlineConsultantModel.Consultant data = new OnlineConsultantModel.Consultant(
                            model.getBookingID(), model.getPatientName(),
                            model.getBookingTime(), model.getBookingDate(),
                            model.getFees(), model.getLocation(), model.getStatus()
                    );
                    list.add(data);

                }
                recyclerData();
                Glob.dialog.dismiss();

            }

            @Override
            public void onFailure(Call<OnlineConsultantModel> call, Throwable t) {

            }
        });
    }


    public void recyclerData() {

        adapter = new OnlineConsultantAdapter(list, this, new OnlineConsultantAdapter.Click() {
            @Override
            public void onButtonClick(int position) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}