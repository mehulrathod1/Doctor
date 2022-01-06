package com.in.doctor.activity;

import static com.in.doctor.global.Glob.Token;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.in.doctor.R;
import com.in.doctor.global.Glob;
import com.in.doctor.model.BookingDetails;
import com.in.doctor.model.Data;
import com.in.doctor.model.PatientLifestyle;
import com.in.doctor.model.PatientMedical;
import com.in.doctor.model.PatientPersonal;
import com.in.doctor.model.SendNotificationModel;
import com.in.doctor.model.ViewPatientDetailModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingDetail extends AppCompatActivity {


    String TAG = "BookingDetail";

    TextView patient_name, bookingId, booking_date, booking_time, booking_status, payment_status, patient_email, patient_number, patient_address, patient_age,
            patient_blood_group, patient_gender, patient_marital_status, alcohol_consumption, smoking_consumption, workout, sport, allergies,
            chronic_disease, medication, injury, chat, video_chat;

    ImageView profile_image;
    String patient_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);
        getSupportActionBar().hide();


        Intent intent = getIntent();
        String booking_Id = intent.getStringExtra("bookingId");

        init();

        Log.e(TAG, "onCreate: " + booking_Id);

        patientDetail(Token, "13", booking_Id);


    }


    public void init() {

        profile_image = findViewById(R.id.ProfileImage);
        patient_name = findViewById(R.id.patient_name);
        bookingId = findViewById(R.id.booking_id);
        booking_date = findViewById(R.id.booking_date);
        booking_time = findViewById(R.id.booking_time);
        booking_status = findViewById(R.id.booking_status);
        payment_status = findViewById(R.id.payment_status);
        patient_email = findViewById(R.id.patient_email);
        patient_number = findViewById(R.id.patient_number);
        patient_address = findViewById(R.id.patient_address);
        patient_age = findViewById(R.id.patient_age);
        patient_blood_group = findViewById(R.id.patient_blood_group);
        patient_gender = findViewById(R.id.patient_gender);
        patient_marital_status = findViewById(R.id.patient_marital_status);
        alcohol_consumption = findViewById(R.id.alcohol_consumption);
        smoking_consumption = findViewById(R.id.smoking_consumption);
        workout = findViewById(R.id.workout);
        sport = findViewById(R.id.sport);
        allergies = findViewById(R.id.allergies);
        chronic_disease = findViewById(R.id.chronic_disease);
        medication = findViewById(R.id.medication);
        injury = findViewById(R.id.injury);
        chat = findViewById(R.id.chat);
        video_chat = findViewById(R.id.video_chat);


        Glob.progressDialog(this);


        video_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendNotification(Glob.Token, patient_user_id, "test");


            }
        });
    }

    public void sendNotification(String token, String user_id, String message) {


        Api call = RetrofitClient.getClient("").create(Api.class);

        call.sendNotification(token, user_id, message).enqueue(new Callback<SendNotificationModel>() {
            @Override
            public void onResponse(Call<SendNotificationModel> call, Response<SendNotificationModel> response) {

                SendNotificationModel model = response.body();
                SendNotificationModel.SendNotification data = model.getData();

                Log.e("id", "onResponse: " + data.getChannelName());
                Log.e("id", "onResponse: " + data.getUser_id());
            }

            @Override
            public void onFailure(Call<SendNotificationModel> call, Throwable t) {

            }
        });
    }

    public void patientDetail(String token, String doctor_id, String booking_id) {
        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();


        call.patientDetail(token, doctor_id, booking_id).enqueue(new Callback<ViewPatientDetailModel>() {
            @Override
            public void onResponse(Call<ViewPatientDetailModel> call, Response<ViewPatientDetailModel> response) {
                ViewPatientDetailModel model = response.body();

                PatientPersonal patientPersonal = model.getData().getPatientPersonal();
                PatientMedical patientMedical = model.getData().getPatientMedical();
                PatientLifestyle patientLifestyle = model.getData().getPatientLifestyle();
                BookingDetails bookingDetails = model.getData().getBookingDetails();


                patient_user_id = patientPersonal.getPatientId();

                Log.e(TAG, "patient_id: " + patient_user_id);

                Glide.with(getApplicationContext())
                        .load(patientPersonal.getPatientImage())
                        .into(profile_image);

                patient_name.setText(patientPersonal.getPatientName());
                bookingId.setText(bookingDetails.getBookingID());
                booking_date.setText(bookingDetails.getBookingDate());
                booking_time.setText(bookingDetails.getBookingTime());
                booking_status.setText(bookingDetails.getBookingStatus());
                payment_status.setText(bookingDetails.getPaymentStatus());
                patient_email.setText(patientPersonal.getPatientEmail());
                patient_number.setText(patientPersonal.getPatientNo());
                patient_address.setText(patientPersonal.getPatientAddress());
                patient_age.setText(patientPersonal.getPatientAge());
                patient_blood_group.setText(patientPersonal.getPatientBloodGroup());
                patient_gender.setText(patientPersonal.getPatientGender());
                patient_marital_status.setText(patientPersonal.getPatientMaritalStatus());
                alcohol_consumption.setText(patientLifestyle.getPatientAlchol());
                smoking_consumption.setText(patientLifestyle.getPatientSmoking());
                workout.setText(patientLifestyle.getPatientWorkoutLevel());
                sport.setText(patientLifestyle.getPatientSportsInvolvement());
                allergies.setText(patientMedical.getPatientAllergies());
                chronic_disease.setText(patientMedical.getPatientChronicDisease());
                medication.setText(patientMedical.getPatientMedication());
                injury.setText(patientMedical.getPatientSurgeryInjury());

                Glob.dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ViewPatientDetailModel> call, Throwable t) {

            }
        });

    }

}