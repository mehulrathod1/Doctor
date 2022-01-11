package com.in.doctor.activity;

import static com.in.doctor.global.Glob.Token;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.in.doctor.R;
import com.in.doctor.adapter.ManageCalenderAdapter;
import com.in.doctor.global.Glob;
import com.in.doctor.model.CommonModel;
import com.in.doctor.model.ManageCalendarModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageCalendar extends AppCompatActivity {

    ImageView nevBack;
    TextView headerTitle;


    ManageCalenderAdapter adapter;
    RecyclerView recyclerView;
    List<ManageCalendarModel.CalenderModel> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_calendar);
        getSupportActionBar().hide();

        init();
        getCalender(Token, Glob.user_id);
    }

    public void init() {

        nevBack = findViewById(R.id.nevBack);
        headerTitle = findViewById(R.id.header_title);
        recyclerView = findViewById(R.id.recycler);

        Glob.progressDialog(this);

        headerTitle.setText("Manage Calendar");
        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

    }


    public void getCalender(String token, String doctor_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();

        call.getCalender(token, doctor_id).enqueue(new Callback<ManageCalendarModel>() {
            @Override
            public void onResponse(Call<ManageCalendarModel> call, Response<ManageCalendarModel> response) {

//                list.clear();
                ManageCalendarModel calendarModel = response.body();

                List<ManageCalendarModel.CalenderModel> dataList = calendarModel.getCalenderList();

                for (int i = 0; i < dataList.size(); i++) {

                    ManageCalendarModel.CalenderModel model = dataList.get(i);

                    ManageCalendarModel.CalenderModel data = new ManageCalendarModel.CalenderModel(
                            model.getBooingId(), model.getDoctorName(), model.getLocation(),
                            model.getStatus(), model.getProfile(), model.getFees()
                    );


                    Log.e("data", "onResponse: " + model.getDoctorName());
                    list.add(data);
                }
                recyclerData();
                Glob.dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ManageCalendarModel> call, Throwable t) {

            }
        });

    }

    public void recyclerData() {

        adapter = new ManageCalenderAdapter(list, this, new ManageCalenderAdapter.Click() {
            @Override
            public void onButtonClick(int position) {


                Log.e("id", "onButtonClick: " + list.get(position).getDoctorName());


            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}