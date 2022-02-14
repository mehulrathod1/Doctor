package com.in.doctor.activity;

import static com.in.doctor.global.Glob.Token;
import static com.in.doctor.global.Glob.channel_name;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Base64InputStream;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.in.doctor.R;
import com.in.doctor.adapter.ReportAdapter;
import com.in.doctor.global.Glob;
import com.in.doctor.model.BookingDetails;
import com.in.doctor.model.CommonModel;
import com.in.doctor.model.Data;
import com.in.doctor.model.PatientLifestyle;
import com.in.doctor.model.PatientMedical;
import com.in.doctor.model.PatientPersonal;
import com.in.doctor.model.RelativeInformation;
import com.in.doctor.model.ReportModel;
import com.in.doctor.model.SendNotificationModel;
import com.in.doctor.model.ViewPatientDetailModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import net.gotev.uploadservice.BuildConfig;
import net.gotev.uploadservice.UploadServiceConfig;
import net.gotev.uploadservice.protocols.multipart.MultipartUploadRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingDetail extends AppCompatActivity {


    String TAG = "BookingDetail";
    TextView appointment_time, appointment_date, relative_relation, relative_name, relative_age, relative_blood_group, relative_gender, relative_marital_status, relative_comment, booking_for, patient_comment, patient_name, bookingId, booking_date, booking_time, booking_status, payment_status, patient_email, patient_number, patient_address, patient_age,
            patient_blood_group, patient_gender, patient_marital_status, alcohol_consumption, smoking_consumption, workout, sport, allergies,
            chronic_disease, medication, injury, chat, video_chat, audio_call, upload_report_file, download_patient_report;

    String patient_id, booking_idd, report_download, date_and_time;

    LinearLayout ll_booking_for, ll_life_clinic, ll_relative_detail, ll_patient_detail;

    ImageView profile_image, backButton;
    String patient_user_id;
    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 10;
    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAG = 1;
    File reportFile;


    Uri uri;
    AlertDialog reportAlert;
    AlertDialog.Builder reportAlertDialog;


    RecyclerView report_recycler;
    List<ReportModel.ReportData> reportDataList = new ArrayList<>();
    ReportAdapter reportAdapter;


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
        download_patient_report = findViewById(R.id.download_patient_report);
        backButton = findViewById(R.id.backButton);
        patient_comment = findViewById(R.id.patient_comment);
        ll_booking_for = findViewById(R.id.ll_booking_for);
        ll_life_clinic = findViewById(R.id.ll_life_clinic);
        booking_for = findViewById(R.id.booking_for);
        ll_relative_detail = findViewById(R.id.ll_relative_detail);
        ll_patient_detail = findViewById(R.id.ll_patient_detail);
        relative_name = findViewById(R.id.relative_name);
        relative_relation = findViewById(R.id.relative_relation);
        relative_age = findViewById(R.id.relative_age);
        relative_blood_group = findViewById(R.id.relative_blood_group);
        relative_gender = findViewById(R.id.relative_gender);
        relative_marital_status = findViewById(R.id.relative_marital_status);
        relative_comment = findViewById(R.id.relative_comment);
        appointment_date = findViewById(R.id.appointment_date);
        appointment_time = findViewById(R.id.appointment_time);


        reportAlertDialog = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.report_popup, null);
        reportAlertDialog.setView(view);
        reportAlert = reportAlertDialog.create();
        report_recycler = view.findViewById(R.id.report_recycler);


        Glob.progressDialog(this);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

//                    Intent intent = new Intent();
//                    intent.setType("application/pdf");
//                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//                    intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
//                    startActivityForResult(intent, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);

                    try {
                        Intent intent = new Intent();
                        intent.setType("application/pdf");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                        startActivityForResult(intent, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
                    } catch (Exception e) {

//                        Intent photoPickerIntent = new Intent(this, XYZ.class);
//                        startActivityForResult(photoPickerIntent, SELECT_IMAGE_REQUEST);

                    }
                } else {

                    ActivityCompat.requestPermissions(BookingDetail.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
                    Log.e("premitionnotgranted ", "onClick: " + "premitionnotgranted");

                }
            }
        });


        download_patient_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    Log.e("premitionnotgranted ", "onClick: " + "granted");


                    getPatientReport(Token, Glob.user_id, bookingId.getText().toString());

//                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(report_download));
//
//                    String title = URLUtil.guessFileName(report_download, null, null);
//                    request.setTitle(title);
//                    request.setDescription("Downloading file please wail.....");
//                    String cookie = CookieManager.getInstance().getCookie(report_download);
//                    request.addRequestHeader("cookie", cookie);
//                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title);
//
//                    DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//                    downloadManager.enqueue(request);


                } else {
                    ActivityCompat.requestPermissions(BookingDetail.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_WRITE_EXTERNAL_STORAG);

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
                RelativeInformation relativeInformation = model.getData().getRelativeInformation();


                patient_user_id = patientPersonal.getPatientId();
                Log.e(TAG, "patient_id: " + patient_user_id);

                Glide.with(getApplicationContext())
                        .load(patientPersonal.getPatientImage())
                        .into(profile_image);

                patient_name.setText(patientPersonal.getPatientName());
                patient_comment.setText(patientPersonal.getPatientComments());
                appointment_time.setText(bookingDetails.getAppointmentTime());
                appointment_date.setText(bookingDetails.getAppointmentDate());
                bookingId.setText(bookingDetails.getBookingID());
                booking_for.setText(bookingDetails.getBookingFor());
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

                relative_name.setText(relativeInformation.getRelativeName());
                relative_age.setText(relativeInformation.getRelativeAge());
                relative_blood_group.setText(relativeInformation.getRelativeBloodGroup());
                relative_gender.setText(relativeInformation.getRelativeGender());
                relative_marital_status.setText(relativeInformation.getRelativeMaritalStatus());
                relative_comment.setText(patientPersonal.getPatientComments());
                relative_relation.setText(relativeInformation.getRelation());

                booking_idd = bookingDetails.getBookingID();
                patient_id = patientPersonal.getPatientId();

                date_and_time = bookingDetails.getAppointmentDate() + " " + bookingDetails.getAppointmentTime();
                Calendar compDate = Calendar.getInstance();

                compDate.add(Calendar.MINUTE, 5);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
                String getCurrentDateTime = sdf.format(compDate.getTime());
                String temp = "2022-02-03 12:10:00";// date_and_time

                Log.e("boosdfghjl", "onResponse: " + (date_and_time));

//                Log.e("demo", "onResponse: "+getCurrentDateTime.(temp)+ "---"+getCurrentDateTime +"-----" +temp);

                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
                //Parsing the given String to Date object
                Date date1 = null;
                try {

                    date1 = formatter.parse(getCurrentDateTime);
                    Date date2 = formatter.parse(date_and_time);
                    Boolean bool1 = date1.after(date2);
                    Boolean bool2 = date1.before(date2);
                    Boolean bool3 = date1.equals(date2);

                    if (bool1) {
                        Log.e("bool", "onResponse: " + (getCurrentDateTime + " is after " + date_and_time));
                        chat.setVisibility(View.VISIBLE);
                        upload_report_file.setVisibility(View.VISIBLE);
                        video_chat.setVisibility(View.GONE);

                    } else if (bool2) {
                        Log.e("bool", "onResponse: " + (getCurrentDateTime + " is before " + date_and_time));
                    } else if (bool3) {
                        Log.e("bool", "onResponse: " + (getCurrentDateTime + " is equals to " + date_and_time));
                    }

                } catch (ParseException e) {
                    e.printStackTrace();


                }


                if (bookingDetails.getBookingFor().equals("Me")) {
                    ll_booking_for.setVisibility(View.GONE);
                    ll_relative_detail.setVisibility(View.GONE);

                } else {
                    ll_life_clinic.setVisibility(View.GONE);
                    ll_patient_detail.setVisibility(View.GONE);
                }

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
//        File file = new File(getRealPathFromURI(data.getData()));
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), getRealPathFromURI(data.getData()));
//        MultipartBody.Part requestBody_report = MultipartBody.Part.createFormData("doctor_report", file.getName(), requestFile);


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
                Log.e("errbnor", "onFailure: " + t.getMessage());
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

                    uri = data.getData();
//                    uploadFile();
                    Log.e("djanogoooo", "onActivityResult: " + uri);


                    if (data.getClipData() != null) {
                        int count = data.getClipData().getItemCount();
                        int currentItem = 0;
                        while (currentItem < count) {
                            Uri imageUri = data.getClipData().getItemAt(currentItem).getUri();
                            //do something with the image (save it to some directory or whatever you need to do with it here)
                            currentItem = currentItem + 1;

                            uri = imageUri;
                            Log.e("djanogoooo", "onActivityResult: " + uri);
                            uploadFile();
                        }
                    } else if (data.getData() != null) {

                        uri = data.getData();
                        //do something with the image (save it to some directory or whatever you need to do with it here)
                        Log.e("djanogoooo", "onActivityResult: " + uri);
                        uploadFile();

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

    public void getPatientReport(String token, String doctorId, String booking_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();

        call.getPatientReport(token, doctorId, booking_id).enqueue(new Callback<ReportModel>() {
            @Override
            public void onResponse(Call<ReportModel> call, Response<ReportModel> response) {

                ReportModel model = response.body();

                reportDataList.clear();
                List<ReportModel.ReportData> DataList = model.getData();
                if (DataList != null) {
                    for (int i = 0; i < DataList.size(); i++) {
                        ReportModel.ReportData reportData = DataList.get(i);

                        ReportModel.ReportData data = new ReportModel.ReportData(reportData.getReportfile());

                        reportDataList.add(data);

                    }
                    reportData();
                    Glob.dialog.dismiss();
                    reportAlert.show();
                } else {
                    Glob.dialog.dismiss();
                    reportAlert.dismiss();
                    Toast.makeText(getApplicationContext(), "" + "No Report Found", Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<ReportModel> call, Throwable t) {

            }
        });

    }

    public void reportData() {

        reportAdapter = new ReportAdapter(reportDataList, getApplicationContext(), new ReportAdapter.Click() {
            @Override
            public void onViewClick(int position) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(reportDataList.get(position).getReportfile()));
                startActivity(browserIntent);
            }

            @Override
            public void onDownloadClick(int position) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    Log.e("premitionnotgranted ", "onClick: " + "granted");

                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(reportDataList.get(position).getReportfile()));
                    String title = URLUtil.guessFileName(reportDataList.get(position).getReportfile(), null, null);
                    request.setTitle(title);
                    request.setDescription("Downloading file please wail.....");
                    String cookie = CookieManager.getInstance().getCookie(reportDataList.get(position).getReportfile());
                    request.addRequestHeader("cookie", cookie);
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title);

                    DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    downloadManager.enqueue(request);

                    Toast.makeText(getApplicationContext(), "Downloading Started", Toast.LENGTH_SHORT).show();
                    reportAlert.dismiss();


                } else {
                    ActivityCompat.requestPermissions(BookingDetail.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
                    Log.e("premitionnotgranted ", "onClick: " + "premitionnotgranted");
                }
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        report_recycler.setLayoutManager(mLayoutManager);
        report_recycler.setAdapter(reportAdapter);

    }

    public void uploadFile() {


        try {
            if (Build.VERSION.SDK_INT >= 26) {

                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationChannel channel = new NotificationChannel("TestChannel", "TestApp Channel", NotificationManager.IMPORTANCE_LOW);
                manager.createNotificationChannel(channel);
            }

            UploadServiceConfig.initialize(getApplication(), "TestChannel", BuildConfig.DEBUG);

            MultipartUploadRequest uploadRequest = new MultipartUploadRequest(this, "http://ciam.notionprojects.tech/api/doctor/upload_patient_report.php")
                    .setMethod("POST")
                    .addFileToUpload(uri.toString(), "doctor_report")
                    .addParameter("doctor_id", Glob.user_id)
                    .addParameter("patient_id", patient_id)
                    .addParameter("booking_id", booking_idd)
                    .addParameter("token", Token);
//                    uploadPatientReport(Token, Glob.user_id, patient_id, booking_idd, reportFile);

            uploadRequest.startUpload();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}