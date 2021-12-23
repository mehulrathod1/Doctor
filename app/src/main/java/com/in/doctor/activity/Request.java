package com.in.doctor.activity;

import static com.in.doctor.global.Glob.Token;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.in.doctor.R;
import com.in.doctor.adapter.ManageBookingAdapter;
import com.in.doctor.global.Glob;
import com.in.doctor.model.CommonModel;
import com.in.doctor.model.ManageBookingModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Request extends AppCompatActivity {
    ImageView nevBack;
    TextView headerTitle;
    RecyclerView recyclerView;
    ManageBookingAdapter adapter;
    List<ManageBookingModel.Booking> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        getSupportActionBar().hide();
        init();
        getBookingRequest(Token, "13");

    }


    public void init() {
        nevBack = findViewById(R.id.nevBack);
        headerTitle = findViewById(R.id.header_title);
        recyclerView = findViewById(R.id.recycler);

        Glob.progressDialog(this);

        headerTitle.setText("My Booked Appointment");

        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
    }


    public void getBookingRequest(String token, String doctor_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();

        call.getBookingRequest(token, doctor_id).enqueue(new Callback<ManageBookingModel>() {
            @Override
            public void onResponse(Call<ManageBookingModel> call, Response<ManageBookingModel> response) {
                ManageBookingModel manageBookingModel = response.body();

                List<ManageBookingModel.Booking> dataList = manageBookingModel.getDataList();
                for (int i = 0; i < dataList.size(); i++) {

                    ManageBookingModel.Booking model = dataList.get(i);

                    ManageBookingModel.Booking data = new ManageBookingModel.Booking(
                            model.getBookingId(), model.getBookingDate(),
                            model.getBookingTime(), model.getBookingOf()
                    );
                    list.add(data);

                }
                recyclerData();
                Glob.dialog.dismiss();

            }

            @Override
            public void onFailure(Call<ManageBookingModel> call, Throwable t) {

            }
        });
    }

    public void bookingRequestAccept(String token, String doctor_id, String booking_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();


        call.bookingRequestAccept(token, doctor_id, booking_id).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {

                CommonModel commonModel = response.body();
                Toast.makeText(getApplicationContext(), commonModel.getMessage(), Toast.LENGTH_SHORT).show();
                Glob.dialog.dismiss();
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {

            }
        });

    }

    public void bookingRequestCancel(String token, String doctor_id, String booking_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();


        call.bookingRequestCancel(token, doctor_id, booking_id).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {

                CommonModel commonModel = response.body();
                Toast.makeText(getApplicationContext(), commonModel.getMessage(), Toast.LENGTH_SHORT).show();
                Glob.dialog.dismiss();
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {

            }
        });

    }

    public void recyclerData() {


        adapter = new ManageBookingAdapter(list, this, new ManageBookingAdapter.Click() {
            @Override
            public void onClickAccept(int position) {

                String bookingId = list.get(position).getBookingId();
                Log.e("fghgfds", "onClickAccept: " + bookingId);
                bookingRequestAccept(Token, "13", bookingId);
            }

            @Override
            public void onClickCancel(int position) {
                String bookingId = list.get(position).getBookingId();
                Log.e("fghgfds", "onClickAccept: " + bookingId);
                bookingRequestCancel(Token, "13", bookingId);
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}