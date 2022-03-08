package com.in.doctor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.in.doctor.R;
import com.in.doctor.adapter.CompletedAssignmentAdapter;
import com.in.doctor.adapter.ReportAdapter;
import com.in.doctor.global.Glob;
import com.in.doctor.model.CompleteAssignmentModel;
import com.in.doctor.model.GetFcmTokenModel;
import com.in.doctor.model.ReportModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompletedAssignment extends AppCompatActivity {

    ImageView nevBack;
    TextView headerTitle, patientName, PatientNo, Amount, TransactionId, TransactionDate;
    RecyclerView recyclerView;
    CompletedAssignmentAdapter adapter;
    List<CompleteAssignmentModel.Assignment> list = new ArrayList<>();
    AlertDialog alert, reportAlert;
    AlertDialog.Builder alertDialog, reportAlertDialog;
    ImageView closeAlert, reportClose;
    RecyclerView report_recycler;
    List<ReportModel.ReportData> reportDataList = new ArrayList<>();
    ReportAdapter reportAdapter;
    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 10;


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

        alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.invoice_popup, null);
        alertDialog.setView(dialogLayout);
        alert = alertDialog.create();


        reportAlertDialog = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.report_popup, null);
        reportAlertDialog.setView(view);
        reportAlert = reportAlertDialog.create();

        report_recycler = view.findViewById(R.id.report_recycler);
        reportClose = view.findViewById(R.id.reportClose);


        patientName = dialogLayout.findViewById(R.id.patientName);
        PatientNo = dialogLayout.findViewById(R.id.PatientNo);
        Amount = dialogLayout.findViewById(R.id.Amount);
        TransactionId = dialogLayout.findViewById(R.id.TransactionId);
        TransactionDate = dialogLayout.findViewById(R.id.TransactionDate);
        closeAlert = dialogLayout.findViewById(R.id.dialogClose);


        headerTitle.setText("Completed Assignment");

        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        closeAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
        reportClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportAlert.dismiss();
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

                if (dataList.size() != 0) {
                    for (int i = 0; i < dataList.size(); i++) {

                        CompleteAssignmentModel.Assignment data = dataList.get(i);
                        CompleteAssignmentModel.Assignment dataa = new CompleteAssignmentModel.Assignment(
                                data.getBooking_id(),
                                data.getAppointment_date(),
                                data.getAmount_paid() + "  ₹",
                                data.getPatient_id(),
                                data.getPatient_document(),
                                data.getInvoice(),
                                data.getBooking_of(),
                                data.getPatient_name(),
                                data.getAppointment_time());
                        list.add(dataa);
                        Glob.dialog.dismiss();
                    }
                    recyclerData();
                } else {
                    Glob.dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<CompleteAssignmentModel> call, Throwable t) {

                Log.e("error", "onFailure: " + t.getMessage());
                Glob.dialog.dismiss();
            }
        });
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


                String url = list.get(position).getInvoice();
                if (url.equals("")) {
                    Log.e("pdfUri", "onViewClick: " + "null");
                } else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(browserIntent);
                }
            }

            @Override
            public void onClickInvoiceDownload(int position) {

                String url = list.get(position).getInvoice();

                if (url.equals("")) {
                    Log.e("pdfUri", "onViewClick: " + "null");
                } else {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                        Log.e("premitionnotgranted ", "onClick: " + "granted");

                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                        String title = URLUtil.guessFileName(url, null, null);
                        request.setTitle(title);
                        request.setDescription("Downloading file please wail.....");
                        String cookie = CookieManager.getInstance().getCookie(url);
                        request.addRequestHeader("cookie", cookie);
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title);

                        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        downloadManager.enqueue(request);
                        Toast.makeText(getApplicationContext(), "Downloading Started", Toast.LENGTH_SHORT).show();
                        reportAlert.dismiss();


                    } else {
                        ActivityCompat.requestPermissions(CompletedAssignment.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
                        Log.e("premitionnotgranted ", "onClick: " + "premitionnotgranted");
                    }

                }
            }

            @Override
            public void onClickDocumentView(int position) {


                String bookingId = list.get(position).getBooking_id();
                getPatientReport(Glob.Token, Glob.user_id, bookingId);

//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getPatient_document()));
//                startActivity(browserIntent);
            }

            @Override
            public void onClickDocumentDownload(int position) {


                String bookingId = list.get(position).getBooking_id();
                getPatientReport(Glob.Token, Glob.user_id, bookingId);
            }

            @Override
            public void onClickChatWithPatient(int position) {

                Intent intent = new Intent(getApplicationContext(), ChatDashboard.class);
                intent.putExtra("user_id", list.get(position).getPatient_id());
                intent.putExtra("user_name", list.get(position).getPatient_name());
                intent.putExtra("user_image", "");
                startActivity(intent);
            }
        });


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void reportData() {

        reportAdapter = new ReportAdapter(reportDataList, getApplicationContext(), new ReportAdapter.Click() {
            @Override
            public void onViewClick(int position) {


                String uri = reportDataList.get(position).getReportfile();


                if (uri.equals("")) {
                    Log.e("pdfUri", "onViewClick: " + "null");
                } else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(browserIntent);
                }
            }

            @Override
            public void onDownloadClick(int position) {


                String uri = reportDataList.get(position).getReportfile();
                if (uri.equals("")) {
                    Log.e("pdfUri", "onViewClick: " + "null");
                } else {

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
                        ActivityCompat.requestPermissions(CompletedAssignment.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
                        Log.e("premitionnotgranted ", "onClick: " + "premitionnotgranted");
                    }

                }

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        report_recycler.setLayoutManager(mLayoutManager);
        report_recycler.setAdapter(reportAdapter);

    }

}