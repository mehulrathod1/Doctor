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
import android.widget.Toast;

import com.in.doctor.R;
import com.in.doctor.adapter.OnlineConsultantAdapter;
import com.in.doctor.global.Glob;
import com.in.doctor.model.OnlineConsultantModel;
import com.in.doctor.model.UpcomingAppointmentModel;
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


    List<UpcomingAppointmentModel.Upcoming> upcomingList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_consultants);
        getSupportActionBar().hide();

        init();
//        getOnlineConsultant(Token, Glob.user_id);

        getAppointment(Token,Glob.user_id);
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
                finish();
            }
        });

    }

//    public void getOnlineConsultant(String token, String doctor_id) {
//
//        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
//        Glob.dialog.show();
//
//        call.getOnlineConsultant(token, doctor_id).enqueue(new Callback<OnlineConsultantModel>() {
//            @Override
//            public void onResponse(Call<OnlineConsultantModel> call, Response<OnlineConsultantModel> response) {
//
//
//                OnlineConsultantModel onlineConsultantModel = response.body();
//
//                if (onlineConsultantModel != null) {
//                    List<OnlineConsultantModel.Consultant> dataList = onlineConsultantModel.getConsultantList();
//                    for (int i = 0; i < dataList.size(); i++) {
//
//                        OnlineConsultantModel.Consultant model = dataList.get(i);
//                        OnlineConsultantModel.Consultant data = new OnlineConsultantModel.Consultant(
//                                model.getBookingID(), model.getPatientName(),
//                                model.getBookingTime(), model.getBookingDate(),
//                                model.getFees(), model.getLocation(), model.getStatus(), model.getProfilePic(),
//                                model.getPatientAge());
//                        list.add(data);
//                    }
//                    recyclerData();
//                }
//                Glob.dialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<OnlineConsultantModel> call, Throwable t) {
//
//            }
//        });
//    }
//
//    public void recyclerData() {
//
//        adapter = new OnlineConsultantAdapter(list, this, new OnlineConsultantAdapter.Click() {
//            @Override
//            public void onButtonClick(int position) {
//
//
//                String booking_Id = list.get(position).getBookingID();
//
//                Intent intent = new Intent(getApplicationContext(), BookingDetail.class);
//                intent.putExtra("bookingId", booking_Id);
//                startActivity(intent);
//            }
//        });
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(mLayoutManager);
//        adapter.notifyDataSetChanged();
//        recyclerView.setAdapter(adapter);
//    }


    public void getAppointment(String token, String doctor_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();

        call.getAppointment(token, doctor_id).enqueue(new Callback<UpcomingAppointmentModel>() {
            @Override
            public void onResponse(Call<UpcomingAppointmentModel> call, Response<UpcomingAppointmentModel> response) {

                UpcomingAppointmentModel upcomingAppointmentModel = response.body();
                List<UpcomingAppointmentModel.Upcoming> dataList = upcomingAppointmentModel.getUpcomingList();

                if (dataList.size() != 0) {
                    for (int i = 0; i < dataList.size(); i++) {

                        UpcomingAppointmentModel.Upcoming model = dataList.get(i);

                        UpcomingAppointmentModel.Upcoming data = new UpcomingAppointmentModel.Upcoming(model.getBooking_id(),
                                model.getAppointment_date(),
                                model.getAppointment_time(),
                                model.getPatient_id(),
                                model.getPatient_name(),
                                model.getPatientAge(),
                                model.getProfile_image(),
                                model.getFees(),
                                model.getCoupon_discount(),
                                model.getTo_be_paid(),
                                model.getLocation(),
                                model.getBooking_of());

                        upcomingList.add(data);
                    }
                    recyclerData();
                    Glob.dialog.dismiss();
                }

            }


            @Override
            public void onFailure(Call<UpcomingAppointmentModel> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void recyclerData() {

        adapter = new OnlineConsultantAdapter(upcomingList, this, new OnlineConsultantAdapter.Click() {
            @Override
            public void onButtonClick(int position) {


                String booking_Id = upcomingList.get(position).getBooking_id();

                Intent intent = new Intent(getApplicationContext(), BookingDetail.class);
                intent.putExtra("bookingId", booking_Id);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }


}