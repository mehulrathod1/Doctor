package com.in.doctor.activity;

import static com.in.doctor.global.Glob.Token;
import static com.in.doctor.global.Glob.channel_name;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.in.doctor.R;
import com.in.doctor.global.Glob;
import com.in.doctor.model.BookingDetails;
import com.in.doctor.model.CommonModel;
import com.in.doctor.model.Data;
import com.in.doctor.model.PatientLifestyle;
import com.in.doctor.model.PatientMedical;
import com.in.doctor.model.PatientPersonal;
import com.in.doctor.model.SendNotificationModel;
import com.in.doctor.model.ViewPatientDetailModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingDetail extends AppCompatActivity {


    String TAG = "BookingDetail";

    TextView patient_name, bookingId, booking_date, booking_time, booking_status, payment_status, patient_email, patient_number, patient_address, patient_age,
            patient_blood_group, patient_gender, patient_marital_status, alcohol_consumption, smoking_consumption, workout, sport, allergies,
            chronic_disease, medication, injury, chat, video_chat, audio_call, upload_report_file;


    String patient_id,booking_idd;

    ImageView profile_image;
    String patient_user_id;
    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 10;
    File reportFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);
        getSupportActionBar().hide();


        Intent intent = getIntent();
        String booking_Id = intent.getStringExtra("bookingId");

        init();

        Log.e(TAG, "onCreate: " + booking_Id);

        patientDetail(Token, Glob.user_id, booking_Id);


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
        audio_call = findViewById(R.id.audio_call);
        upload_report_file = findViewById(R.id.upload_report_file);

        Glob.progressDialog(this);


        video_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendNotification(Glob.Token, patient_user_id, "test");
            }
        });

        audio_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        upload_report_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    Log.e("premitionnotgranted ", "onClick: " + "granted");


                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("application/pdf");
                    startActivityForResult(intent, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat.requestPermissions(BookingDetail.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);

                    Log.e("premitionnotgranted ", "onClick: " + "premitionnotgranted");
                }
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

                Log.e("id", "onResdfghjponse:" + data.getChannelName());
                String channel = data.getChannelName();


                Intent intent = new Intent(getApplicationContext(), VideoCallScreen.class);
                intent.putExtra("channel_name", channel);
                startActivity(intent);


                Log.e("asdfghjkjhgfdsa", "onResponse: " + channel_name);
                Log.e("id", "onResponse:" + data.getUser_id());
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


                booking_idd = bookingDetails.getBookingID();
                patient_id = patientPersonal.getPatientId();

                Glob.dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ViewPatientDetailModel> call, Throwable t) {

            }
        });

    }

    public void uploadPatientReport(String token, String doctor_id, String patient_id, String booking_id, File doctor_report) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();

        RequestBody requestBody_token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody requestBody_doctor_id = RequestBody.create(MediaType.parse("multipart/form-data"), doctor_id);
        RequestBody requestBody_patient_id = RequestBody.create(MediaType.parse("multipart/form-data"), patient_id);
        RequestBody requestBody_booking_id = RequestBody.create(MediaType.parse("multipart/form-data"), booking_id);

        MultipartBody.Part requestBody_report = null;
        RequestBody requestBody_req_report = RequestBody.create(MediaType.parse("multipart/form-data"), doctor_report);
        requestBody_report = MultipartBody.Part.createFormData("doctor_report", reportFile.getName(), requestBody_req_report);


        call.uploadPatientReport(requestBody_token, requestBody_doctor_id, requestBody_patient_id, requestBody_booking_id, requestBody_report).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {

                CommonModel model = response.body();

                Toast.makeText(getApplicationContext(), "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                Glob.dialog.dismiss();
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {


                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Glob.dialog.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {


            case 10:

                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file


                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new
                            Date());
                    reportFile = new File(getCacheDir(), "IMG_" + timeStamp + ".pdf");

                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(reportFile);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        fos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    uploadPatientReport(Token,Glob.user_id,patient_id,booking_idd,reportFile);

                    Uri uri = data.getData();
                    String uriString = uri.toString();
                    if (uriString.startsWith("content://")) {
                        Cursor cursor = null;
                        try {
                            cursor = getContentResolver().query(uri, null, null, null, null);
                            if (cursor != null && cursor.moveToFirst()) {
                                String displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                                Log.e("displayName", "onActivivvtyResult: " + displayName);
                                upload_report_file.setText(displayName);
                            }
                        } finally {
                            cursor.close();
                        }
//                    addBookingAppointmentWithReport(Token, user_id, doctorId, appointmentDate, appointmentTime, "online", comment.getText().toString(), txtFees.getText().toString(), reportFile);
//                    Uri uri = data.getData();
//                    String uriString = uri.toString();
//                    reportFile = new File(uriString);
//                    String path = reportFile.getAbsolutePath();
//                    String displayName = null;

//                    Log.e("path", "onActivityResult: " + reportFile);
//
//
//                    if (uriString.startsWith("content://")) {
//                        Cursor cursor = null;
//                        try {
//                            cursor = getContentResolver().query(uri, null, null, null, null);
//                            if (cursor != null && cursor.moveToFirst()) {
//                                displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
//                                Log.e("displayName", "onActivivvtyResult: " + displayName);
//                                choose_file.setText(displayName);
//                            }
//                        } finally {
//                            cursor.close();
//                        }
//                    } else if (uriString.startsWith("file://")) {
//                        displayName = reportFile.getName();
//                        Log.e("displayName", "onActivityResult: " + displayName);
                    }
                }
                break;
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Granted.
                    Log.e("premitionnotgranted ", "onClick: " + "ggggg");

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("application/pdf");
//                    startActivity(intent);
                    startActivityForResult(intent, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);

                } else {
                    Log.e("premitionnotgranted ", "onClick: " + "ddd");
                }
                break;
        }
    }


}