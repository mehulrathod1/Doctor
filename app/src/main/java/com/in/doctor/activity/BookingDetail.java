package com.in.doctor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.in.doctor.R;
import com.in.doctor.global.Glob;
import com.in.doctor.model.SendNotificationModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);

        init();
        sendNotification(Glob.Token, "33", "test");
    }


    public void init() {


        Glob.progressDialog(this);

    }

    public void sendNotification(String token, String user_id, String message) {

        Api call = RetrofitClient.getClient("http://ciam.notionprojects.tech/api/patient/").create(Api.class);
        Glob.dialog.show();


        call.sendNotification(token, user_id, message).enqueue(new Callback<SendNotificationModel>() {
            @Override
            public void onResponse(Call<SendNotificationModel> call, Response<SendNotificationModel> response) {


                SendNotificationModel model = response.body();
//                Log.e("channel_id", "onResponse: " + model.getMessage());

                Glob.dialog.dismiss();
            }

            @Override
            public void onFailure(Call<SendNotificationModel> call, Throwable t) {
                Log.e("channel_id", "onResponse: " + t.getMessage());
            }
        });

    }
}